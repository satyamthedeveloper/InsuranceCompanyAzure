import axios from 'axios';

const CUSTOMER_API_LOGIN_URL = "http://localhost:7000/customer/login";

const CUSTOMER_API_REGISTER_URL = "http://localhost:7000/customer/register";

const SALES_API_GET_QUOTATION = "http://localhost:7001/sales/getQuote";

const SALES_API_SAVE_QUOTATION = "http://localhost:7001/sales/saveQuote";

const SALES_API_RETRIVE_QUOTATION = "http://localhost:7001/sales/retriveQuote";

const SALES_API_SUBMIT_QUOTATION = "http://localhost:7001/sales/submit";

const SALES_API_UPLOAD_FILE = "http://localhost:7001/upload";

const SALES_API_GET_CUSTOMER = "http://localhost:7001/sales/getCustomer";

class ServiceUrls {

    getloginUrl(user) {
        return axios.post(CUSTOMER_API_LOGIN_URL, user);
    }

    getRegisterUrl(customer) {
        return axios.post(CUSTOMER_API_REGISTER_URL, customer);
    }

    getQuotationUrl(details) {
        return axios.post(SALES_API_GET_QUOTATION, details);
    }

    getSaveQuotationUrl(details) {
        return axios.put(SALES_API_SAVE_QUOTATION, details);
    }

    getRetriveQuotationUrl(details) {
        return axios.post(SALES_API_RETRIVE_QUOTATION, details);
    }

    getSubmitQuotationUrl(details) {
        return axios.put(SALES_API_SUBMIT_QUOTATION, details);
    }

    getUploadUrl(path) {
        return axios.post(SALES_API_UPLOAD_FILE, path);
    }

    getCustomerUrl(details) {
        return axios.post(SALES_API_GET_CUSTOMER, details);
    }
}

export default new ServiceUrls();

