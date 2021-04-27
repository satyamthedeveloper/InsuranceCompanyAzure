import React, { Component } from 'react';
import ServiceUrls from '../services/ServiceUrls'
import axios from 'axios'

const SALES_API_UPLOAD_FILE = "http://localhost:7001/upload";

class FileUploadComponent extends Component {

    constructor(props) {
        super(props);

        this.state = {
            path: '',
            textMessage: ''
        }
    }


    // upload = (event) => {
    //     event.preventDefault();

    //     let file = {
    //         path: this.state.path
    //     }

    //     console.log(file.path);
    //     ServiceUrls.getUploadUrl(file).then(
    //         (res) => {
    //             console.log(res.data);

    //         }
    //     );
    // }

    onFileChangeHandler = (e) => {
        e.preventDefault();
        this.setState({
            selectedFile: e.target.files[0]
        });
        const formData = new FormData();
        formData.append('file', this.state.selectedFile);
        //Append the rest data then send
        axios({
            method: 'post',
            url: SALES_API_UPLOAD_FILE,
            data: formData,
            headers: { 'Content-Type': 'multipart/form-data' }
        })
            .then(function (response) {
                //handle success
                console.log(response);

            },
                function (error) {
                    // handle error 
                });

        this.setState({
            textMessage: 'File Upload Successful'
        })
    }


    render() {
        return (
            <div>
                <div className="text-center" style={{ marginTop: "5px", color: "slateblue" }}>
                    <h4>{this.state.textMessage}</h4>
                </div>
                <div className="card card-body" style={{ marginTop: "20px" }}>
                    <form encType="multipart/form-data">
                        <div className="form-group">
                            <label>Upload Address Proof:</label>
                            <input type="file" value={this.state.path} onChange={this.onFileChangeHandler} className="form-control" />
                        </div>

                    </form>
                </div>
            </div>
        );
    }
}

export default FileUploadComponent;