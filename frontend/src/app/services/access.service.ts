import {Observable} from "rxjs/Observable";
import {Injectable} from "@angular/core";
import {Response} from "@angular/http";
import { Headers, Http } from '@angular/http';
import "rxjs/Rx";

import {User} from '../models/user';

@Injectable()
export class AccessService {

    url: string;

    constructor(private http:Http) {
        this.url = 'http://localhost:8080/api/access';
    }

	getAccess(link:string):Observable<User> {
		return this.http.get(this.url + "/" + link, {headers: this.prepareHeaders()})
                        .do(res => res.json())
		               .catch(this.handleError);
	}

    prepareHeaders(){
        return new Headers({
            'x-auth-token': localStorage.getItem('jwt')
        });
    }

    private handleError(error:Response) {
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }

}
