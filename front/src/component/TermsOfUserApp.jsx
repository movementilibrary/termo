import React, { Component } from 'react';
import TermsOfUserComponent from './TermsOfUserComponent';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import CourseComponent from './CourseComponent';

class TermsOfUserApp extends Component {
    render() {
        return (
            <Router>
                <>
                    <Switch>
                        <Route path="/" exact component={TermsOfUserComponent} />
                    </Switch>
                </>
            </Router>
        )
    }
}

export default TermsOfUserApp