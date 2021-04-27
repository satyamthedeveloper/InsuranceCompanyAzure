import React, { Component } from 'react';
import ServiceUrls from '../services/ServiceUrls';

class RegisterComponent extends Component {

    constructor(props) {
        super(props);

        this.state = {
            pan: '',
            name: '',
            dateOfBirth: '',
            gender: '',
            address: '',
            password: ''
        }

        this.register = this.register.bind();



    }



    register = (e) => {
        e.preventDefault();

        let customer = {
            pan: this.state.pan, name: this.state.name, dateOfBirth: this.state.dateOfBirth, gender: this.state.gender, address: this.state.address, password: this.state.password
        };
        console.log('customer => ' + JSON.stringify(customer));

        ServiceUrls.getRegisterUrl(customer).then(
            res => {
                this.props.history.push('/');
            })
    }

    cancel() {
        this.props.history.push('/');
    }

    render() {
        return (
            <div>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3" style={{ marginTop: "40px" }}>
                            <h3 className="text-center" style={{ marginTop: "5px" }}>Register New Customer</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label>PAN:</label>
                                        <input placeholder="PAN Number" name="pan" value={this.state.pan} onChange={event => this.setState({ pan: event.target.value })} className="form-control" />
                                    </div>
                                    <div className="form-group">
                                        <label>Name</label>
                                        <input placeholder="Full Name" name="name" value={this.state.name} onChange={event => this.setState({ name: event.target.value })} className="form-control" />
                                    </div>
                                    <div className="form-group">
                                        <label>Date of Birth</label>
                                        <input placeholder="YYYY-MM-DD" name="dateOfBith" value={this.state.dateOfBirth} onChange={event => this.setState({ dateOfBirth: event.target.value })} className="form-control" />
                                    </div>
                                    <div className="form-group">
                                        <label>Gender</label>
                                        <input placeholder="MALE/FEMALE" name="gender" value={this.state.gender} onChange={event => this.setState({ gender: event.target.value })} className="form-control" />
                                    </div>
                                    <div className="form-group">
                                        <label>Address</label>
                                        <input placeholder="Current Address" name="address" value={this.state.address} onChange={event => this.setState({ address: event.target.value })} className="form-control" />
                                    </div>
                                    <div className="form-group">
                                        <label>Password</label>
                                        <input placeholder="password" name="password" value={this.state.password} onChange={event => this.setState({ password: event.target.value })} className="form-control" type="password" />
                                    </div>


                                    <button className="btn btn-success" onClick={this.register} style={{ marginLeft: "10px", marginTop: "15px" }}>Register</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{ marginLeft: "10px", marginTop: "15px" }}>Cancel</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default RegisterComponent;