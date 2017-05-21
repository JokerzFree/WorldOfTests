import {Observable} from "rxjs/Observable";
import {Injectable} from "@angular/core";
import {Response} from "@angular/http";
import { Headers, Http } from '@angular/http';
import {ErrorHandlerService} from './error-handler.service';
import "rxjs/Rx";

@Injectable()
export class LoginService {

    constructor(private http:Http, private errorHandler: ErrorHandlerService) {}

    login(username: any, password: any) {
        let loginRequest = JSON.stringify({username: username, password: password});
        let headers = new Headers({'Content-Type': 'text/plain', 'Accept': 'application/json'});

        return this.http.post('http://localhost:8080/api/login', loginRequest, { headers: headers })
                        .map(resp => {
                            localStorage.setItem('jwt', resp.headers.get('x-auth-token'));
                        })
                        .catch(this.errorHandler.handleError);
    }

    logout():void {
        localStorage.removeItem('jwt');
    }


    isSignedIn():boolean {
        return localStorage.getItem('jwt') !== null;
    }


}
