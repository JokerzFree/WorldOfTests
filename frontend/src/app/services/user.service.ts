import {Observable} from "rxjs/Observable";
import {Injectable} from "@angular/core";
import {Response} from "@angular/http";
import { Headers, Http } from '@angular/http';
import "rxjs/Rx";

import {User} from '../models/user';
import {ErrorHandlerService} from './error-handler.service';

@Injectable()
export class UserService {

    private url:string;

    constructor(private http:Http, private errorHandler: ErrorHandlerService) {
        this.url = 'http://localhost:8080/api/users';
    }

    getUsers():Observable<User[]> {
        return this.http.get(this.url, {headers: this.prepareHeaders()})
                        .map(res => res.json())
                        .catch(this.errorHandler.handleError);
    }

    getCurrentUser():Observable<User> {
        return this.http.get(this.url+"/user", {headers: this.prepareHeaders()})
                        .map(res => res.json())
                        .catch(this.errorHandler.handleError);
    }

    getUser(userId:any):Observable<User> {
        return this.http.get(this.url + "/" + userId, {headers: this.prepareHeaders()})
                        .map(res => res.json())
                        .catch(this.errorHandler.handleError);
    }

    deleteUser(userId:number) {
        return this.http.delete(this.url + "/admin/deleteUser/" + userId, {headers: this.prepareHeaders()})
                        .catch(this.errorHandler.handleError);
    }

    create(user:User) {
        return this.http.post(this.url, JSON.stringify(user), {headers: this.prepareHeaders()})
                        .catch(this.errorHandler.handleError);
    }

    setLastLoginTime() {
        let timeZoneOffset = new Date().getTimezoneOffset();
        return this.http.post(this.url+"/lastLoginTime", JSON.stringify({offset:timeZoneOffset}), {headers: this.prepareHeaders()})
                        .catch(this.errorHandler.handleError);
    }

    updateEmail(email: string){
        return this.http.post(this.url+"/updateEmail", JSON.stringify({email: email}), {headers: this.prepareHeaders()})
                        .catch(this.errorHandler.handleError);
    }

    updatePassword(password: string){
        return this.http.post(this.url+"/updatePassword", JSON.stringify({password: password}), {headers: this.prepareHeaders()})
                        .catch(this.errorHandler.handleError);
    }

    hasRole(roles:string[]){
        let currentUser: User;
        this.getCurrentUser().subscribe(
            user => {
                currentUser = user;
            }
        );
        let access: boolean = false;
        for (var i = 0, length1 = roles.length; i < length1; i++){
            for (var j = 0, length2 = currentUser.roles.length; j < length2; j++){
                if (roles[i] == currentUser.roles[j].rolename){
                    access = true;
                }
            }
        }
        return access;
    }

    prepareHeaders(){
        return new Headers({
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'x-auth-token': localStorage.getItem('jwt')
        });
    }
}
