import {Observable} from "rxjs/Observable";
import {Injectable} from "@angular/core";
import {Response} from "@angular/http";
import { Headers, Http } from '@angular/http';
import "rxjs/Rx";

@Injectable()
export class LoginService {

    constructor(private http:Http) {}

    login(username: any, password: any):Observable<Response> {
        let loginRequest = JSON.stringify({username: username, password: password});
        let headers = new Headers({'Content-Type': 'text/plain', 'Accept': 'application/json'});

        return this.http.post('http://localhost:8080/api/login', loginRequest, { headers: headers })
                        .do(resp => {
                            localStorage.setItem('jwt', resp.headers.get('x-auth-token'));
                        });
    }

    logout():void {
        localStorage.removeItem('jwt');
    }

    private handleError(error:Response) {
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }


     isSignedIn():boolean {
        return localStorage.getItem('jwt') !== null;
    }


}
