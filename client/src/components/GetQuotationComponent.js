import React, { Component } from 'react';
import ServiceUrls from '../services/ServiceUrls';



class GetQuotationComponent extends Component {
    constructor(props) {
        super(props);

        this.state = {
            pan: '',
            name: '',
            c_id: '',
            c_pan: '',
            policy: '',
            premium: '',
            amount: '',
            active: '',
            q_id: '',
            c_name: '',
            gender: '',
            textMessage: ''
        }
    }

    calculate = (event) => {
        event.preventDefault();

        let details = {
            pan: this.state.pan,
            policyName: this.state.name
        };

        console.log("input: " + details.pan + " : " + details.policyName);
        ServiceUrls.getQuotationUrl(details)
            .then(
                res => {
                    console.log('resdata :' + res.data.amountInsured);
                    this.setState({
                        amount: res.data.amountInsured,
                        c_pan: res.data.pan,
                        policy: res.data.policyName,
                        premium: res.data.premium,
                        c_id: res.data.qid,
                        active: res.data.submitted,
                        pan: '',
                        name: ''
                    });
                }
            );
        ServiceUrls.getCustomerUrl(details)
            .then(
                res => {
                    this.setState({
                        c_name: res.data.name,
                        gender: res.data.gender
                    });
                }
            );

    }

    // save = (event) => {
    //     event.preventDefault();

    //     let quotation = {
    //         amountInsured: this.state.amount,
    //         pan: this.state.c_pan,
    //         policyName: this.state.policy,
    //         premium: this.state.premium,
    //         qid: this.state.c_id,
    //         submitted: this.state.active
    //     };

    //     ServiceUrls.getSaveQuotationUrl(quotation);
    // }

    retrive = (event) => {
        event.preventDefault();

        let details = {
            id: this.state.q_id,
        };

        ServiceUrls.getRetriveQuotationUrl(details)
            .then(
                res => {
                    console.log('resdata :' + res.data.amountInsured);
                    this.setState({
                        amount: res.data.amountInsured,
                        c_pan: res.data.pan,
                        policy: res.data.policyName,
                        premium: res.data.premium,
                        c_id: res.data.qid,
                        active: res.data.submitted,
                        q_id: ''
                    });
                }
            );
        let c_details = {
            pan: this.state.c_pan,
            policyName: this.state.policy
        };
        ServiceUrls.getCustomerUrl(c_details)
            .then(
                res => {
                    this.setState({
                        c_name: res.data.name,
                        gender: res.data.gender
                    });
                }
            );
    }

    submit = (event) => {
        event.preventDefault();

        let quotation = {
            amountInsured: this.state.amount,
            pan: this.state.c_pan,
            policyName: this.state.policy,
            premium: this.state.premium,
            qid: this.state.c_id,
            submitted: this.state.active
        };

        ServiceUrls.getSubmitQuotationUrl(quotation);

        this.setState({
            amount: '',
            c_pan: '',
            policy: '',
            premium: '',
            c_id: '',
            active: '',
            pan: '',
            name: '',
            c_name: '',
            gender: '',
            textMessage: 'Quotation Submitted'
        });
    }

    render() {
        return (
            <div className="row" style={{ marginTop: "15px" }}>
                <div className="text-center" style={{ marginTop: "5px", color: "slateblue" }}>
                    <h4>{this.state.textMessage}</h4>
                </div>
                <div className="col-md-6">
                    <div className="container">
                        <div className="card ">
                            <h4 className="text-center" style={{ marginTop: "5px", color: "GrayText" }}>Calculate Quotation</h4>
                            <div className="card-body">
                                <form>

                                    <div className="form-group">
                                        <label>PAN:</label>
                                        <input placeholder="PAN Number" name="pan" value={this.state.pan} onChange={event => this.setState({ pan: event.target.value })} className="form-control" />
                                    </div>
                                    <div className="form-group">
                                        <label>Insurance: </label>
                                        <input placeholder="Insurance Name" name="name" value={this.state.name} onChange={event => this.setState({ name: event.target.value })} className="form-control" />
                                    </div>
                                    <button className="btn btn-info" onClick={this.calculate.bind()} style={{ marginLeft: "10px", marginTop: "15px" }}>Calculate</button>
                                    <div className="form-group">
                                        <label>Quotation Id: </label>
                                        <input placeholder="Quotation Id" name="q_id" value={this.state.q_id} onChange={event => this.setState({ q_id: event.target.value })} className="form-control" />
                                    </div>
                                    <button className="btn btn-info" onClick={this.retrive.bind()} style={{ marginLeft: "10px", marginTop: "15px" }}>Retrive</button>


                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="col-md-6">
                    <div className="container">
                        <div className="card ">
                            <h4 className="text-center" style={{ marginTop: "5px", color: "GrayText" }}>Quotation</h4>
                            <div className="card-body">
                                <form >
                                    <div className="form-group">
                                        <label>Policy Id:</label>
                                        <input value={this.state.c_id} className="form-control" readOnly />
                                    </div>
                                    <div className="form-group">
                                        <label>PAN: </label>
                                        <input value={this.state.c_pan} className="form-control" readOnly />
                                    </div>
                                    <div className="form-group">
                                        <label>Customer Name: </label>
                                        <input value={this.state.c_name} className="form-control" readOnly />
                                    </div>
                                    <div className="form-group">
                                        <label>Gender: </label>
                                        <input value={this.state.gender} className="form-control" readOnly />
                                    </div>
                                    <div className="form-group">
                                        <label>Policy Name:</label>
                                        <input value={this.state.policy} className="form-control" readOnly />
                                    </div>
                                    <div className="form-group">
                                        <label>Monthly Premium: </label>
                                        <input value={this.state.premium} className="form-control" readOnly />
                                    </div>
                                    <div className="form-group">
                                        <label>Total Amount Insured:</label>
                                        <input value={this.state.amount} className="form-control" readOnly />
                                    </div>
                                    <div className="form-group">
                                        <label>Activated: </label>
                                        <input value={this.state.active} className="form-control" readOnly />
                                    </div>

                                    <button className="btn btn-info" onClick={this.submit.bind()} style={{ marginLeft: "10px", marginTop: "15px" }}>Submit</button>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default GetQuotationComponent;