import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import LoginComponent from './components/LoginComponent';
import RegisterComponent from './components/RegisterComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import QuotationComponent from './components/QuotationComponent'

function App() {
  return (

    <div>
      <Router>
        <HeaderComponent />
        <div className="container" >
          <Switch>
            <Route path="/" exact component={LoginComponent}></Route>
            <Route path="/sign-up" component={RegisterComponent}></Route>
            <Route path="/quotation" component={QuotationComponent}></Route>

          </Switch>
        </div>
        <FooterComponent />
      </Router>
    </div>



  );
}

export default App;
