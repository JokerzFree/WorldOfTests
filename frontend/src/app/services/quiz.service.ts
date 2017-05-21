import {Observable} from "rxjs/Observable";
import {Injectable} from "@angular/core";
import {Response} from "@angular/http";
import { Headers, Http } from '@angular/http';
import "rxjs/Rx";

import {Quiz} from '../models/quiz';

@Injectable()
export class QuizService {

    private url:string;

    constructor(private http:Http) {
        this.url = 'http://localhost:8080/api/quizzes';
    }

    getQuizzes():Observable<Quiz[]> {
        return this.http.get(this.url, {headers: this.prepareHeaders()})
                        .map(res => res.json())
                        .catch(this.handleError);
    }

    getQuiz(id:any):Observable<Quiz> {
        return this.http.get(this.url + "/" + id, {headers: this.prepareHeaders()})
                        .map(res => res.json())
                        .catch(this.handleError);
    }

    save(quiz:Quiz, answers:any[], filenames:String[]) {
        let data = JSON.stringify({
            title: quiz.title,
            description: quiz.description,
            json_quiz: JSON.stringify(quiz), 
            json_answer: JSON.stringify(answers),
            image: quiz.image,
            filenames: filenames
        });
        return this.http.post(this.url, data, {headers: this.prepareHeaders()})
                        .catch(this.handleError);
    }

    checkQuizAnswer(id:any, answers:any):Observable<boolean> {
        let data = JSON.stringify({id: id, answers: JSON.stringify(answers)});
        return this.http.post(this.url+'/check', data, {headers: this.prepareHeaders()})
                        .map(res => res.json())
                        .catch(this.handleError);
    }

    prepareHeaders(){
        return new Headers({
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'x-auth-token': localStorage.getItem('jwt')
        });
    }

    private handleError(error:Response) {
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
}
