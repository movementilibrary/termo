import React, { Component } from 'react'
import TermsOfUserService from '../service/TermsOfUserService';

const INSTRUCTOR = 'in28minutes'

class TermsOfUserComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            courses: [],
            message: null
        }
        this.updateCourseClicked = this.updateCourseClicked.bind(this)
        this.addTermsOfUserClicked = this.addTermsOfUserClicked.bind(this)
        this.refreshTermsOfUser = this.refreshTermsOfUser.bind(this)
    }

    componentDidMount() {
        this.refreshTermsOfUser();
    }

    refreshTermsOfUser() {
        TermsOfUserService.retrieveAllCourses(INSTRUCTOR)//HARDCODED
            .then(
                response => {
                    //console.log(response);
                    this.setState({ courses: response.data })
                }
            )
    }

    deleteCourseClicked(id) {
        TermsOfUserService.deleteCourse(INSTRUCTOR, id)
            .then(
                response => {
                    this.setState({ message: `Delete of course ${id} Successful` })
                    this.refreshCourses()
                }
            )

    }

    addTermsOfUserClicked() {
        this.props.history.push(`/courses/-1`)
    }

    updateCourseClicked(id) {
        console.log('update ' + id)
        this.props.history.push(`/courses/${id}`)
    }

    render() {
        console.log('render')
        return (
            <div className="container">
                <h3>Gliese Termo de Uso</h3>
                {this.state.message && <div class="alert alert-success">{this.state.message}</div>}
                <div className="container">
                    <form>
                        <div class="form-group">
                            <label for="termoUso">Termo de uso</label>
                            <textarea class="form-control" id="termoUso" rows="3" placeholder="Termo de uso"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="resumoTermo">Resumo</label>
                            <textarea class="form-control" id="resumoTermo" rows="2" placeholder="Resumo do termo"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="versaoTermo">Versão</label>
                            <input type="email" class="form-control" id="versaoTermo" placeholder="V0.0"></input>
                        </div>
                    </form>
                    <div className="row">
                        <button className="btn btn-success" onClick={this.addCourseClicked}>Inserir novo termo</button>
                    </div>
                </div>
            </div>
        )
    }
}

export default TermsOfUserComponent