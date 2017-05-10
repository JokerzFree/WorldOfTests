import {Observable} from "rxjs/Observable";
import {Injectable} from "@angular/core";
import {Response} from "@angular/http";
import { Headers, Http } from '@angular/http';
import "rxjs/Rx";


@Injectable()
export class UploadService {

    url: string;

    constructor(private http:Http) {
        this.url = 'http://localhost:8080/api/uploads';
    }

    uploadFile(path: string, files: Array<File>) {
        return new Promise((resolve, reject) => {
            var formData: any = new FormData();
            var xhr = new XMLHttpRequest();
            for(var i = 0; i < files.length; i++) {
                formData.append("file", files[i], files[i].name);
            }
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4) {
                    if (xhr.status == 200) {
                        resolve(xhr.response);
                    } else {
                        reject(xhr.response);
                    }
                }
            }
            xhr.open("POST", this.url + "/images/upload" + path, true);
            xhr.setRequestHeader('x-auth-token', localStorage.getItem('jwt'));
            xhr.send(formData);
        });
    }

	getImage(user_id:Number, filename:String):Observable<any> {
		return this.http.get(this.url + "/images/" + user_id + "/" + filename, {headers: this.prepareHeaders()})
		  .map(this.extractUrl)
		  .catch(this.handleError);
	}

	getPdf(user_id:Number, filename:String):Observable<any> {
		return this.http.get(this.url + "/quizes/" + user_id + "/" + filename, {headers: this.prepareHeaders()})
		  .map(this.extractUrl)
		  .catch(this.handleError);
	}

	extractUrl(res:Response):string {
       console.log(res);
		return res.url;
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
