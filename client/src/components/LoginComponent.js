import React, { Component } from 'react';
import { Redirect } from 'react-router';
import ServiceURLs from '../services/ServiceUrls'

class LoginComponent extends Component {

    constructor(props) {
        super(props);

        this.state = {
            pan: '',
            date: '',
            password: '',
            output: ''
        }

        this.login = this.login.bind(this);
        this.register = this.register.bind(this);

    }

    login = (event) => {
        event.preventDefault();

        let user = {
            pan: this.state.pan, date: this.state.date, password: this.state.password
        }

        ServiceURLs.getloginUrl(user).then(
            
            (res) => {
                console.log("eror");    
                this.setState({ output: res.data });
                console.log("message => " + this.state.output);
                this.props.history.push('/quotation');
            }).catch(
                () => {
                    this.setState({ output: "Check Input Data Type" });
                });
    }

    register() {
        this.props.history.push('/sign-up');
    }

    render() {
        return (
            <div>
                <div className="container">
                    <div className="row">
                        <div className="text-center" style={{ color: "red" }}>
                            {this.state.output}
                        </div>
                        <div className="card col-md-6 offset-md-3 offset-md-3" style={{ marginTop: "40px" }}>
                            <h3 className="text-center" style={{ marginTop: "5px" }}>Login</h3>
                            <div className="card-body">
                                <form >
                                    <div className="text-end">
                                        <button className="btn btn-primary text-center" onClick={this.register}>Register</button>
                                    </div>
                                    <div className="form-group">
                                        <label>PAN</label>
                                        <input placeholder="PAN Number" name="" value={this.state.pan} onChange={event => this.setState({ pan: event.target.value })} className="form-control" />
                                    </div>
                                    <div className="form-group">
                                        <label>Date of Birth</label>
                                        <input placeholder="YYYY-MM-DD" name="" value={this.state.date} onChange={event => this.setState({ date: event.target.value })} className="form-control" />
                                    </div>
                                    <div className="form-group">
                                        <label>Password</label>
                                        <input placeholder="password" name="" value={this.state.password} onChange={event => this.setState({ password: event.target.value })} className="form-control" type="password" />
                                    </div>

                                    <button className="btn btn-success text-center" onClick={this.login} style={{ marginLeft: "10px", marginTop: "15px" }}>Login</button>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div >
        );
    }
}

export default LoginComponent;