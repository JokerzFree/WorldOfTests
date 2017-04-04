import {Observable} from "rxjs/Observable";
import {Injectable} from "@angular/core";
import {Response} from "@angular/http";
import { Headers, Http } from '@angular/http';
import "rxjs/Rx";

import {User} from '../models/user';

@Injectable()
export class UserService {

    private url:string;

    constructor(private http:Http) {
        this.url = 'http://localhost:8080/api/users';
    }

    getUsers():Observable<User[]> {
        return this.http.get(this.url, {headers: this.prepareHeaders()})
                        .map(res => res.json())
                        .catch(this.handleError);
    }

    getCurrentUser():Observable<User> {
        return this.http.get(this.url+"/user", {headers: this.prepareHeaders()})
                        .map(res => res.json())
                        .catch(this.handleError);
    }

    getUser(id:any):Observable<User> {
        return this.http.get(this.url + "/" + id, {headers: this.prepareHeaders()})
                        .map(res => res.json())
                        .catch(this.handleError);
    }

    create(user:User) {
        return this.http.post(this.url, JSON.stringify(user), {headers: this.prepareHeaders()})
                        .catch(this.handleError);
    }

    setLastLoginTime() {
        var timeZoneOffset = new Date().getTimezoneOffset();
        return this.http.post(this.url+"/lastLoginTime", JSON.stringify({offset:timeZoneOffset}), {headers: this.prepareHeaders()})
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
