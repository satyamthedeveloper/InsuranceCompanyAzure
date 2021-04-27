import React, { Component } from 'react';
import GetQuotationComponent from './GetQuotationComponent';
import FileUploadComponent from './FileUploadComponent'

class QuotationComponent extends Component {
    render() {
        return (
            <div>
                <div className="container">
                    {/* <h4 className="text-center" style={{ color: "green" }}>Login Success!!</h4> */}
                    <div >
                        <GetQuotationComponent />
                    </div>
                    <div>
                        <FileUploadComponent />
                    </div>
                </div>
            </div>
        );
    }
}

export default QuotationComponent;