import {Observable} from "rxjs/Observable";
import {Injectable} from "@angular/core";
import {Response} from "@angular/http";
import { Headers, Http } from '@angular/http';
import {ErrorHandlerService} from './error-handler.service';
import "rxjs/Rx";


@Injectable()
export class UploadService {

    url: string;

    constructor(private http:Http, private errorHandler: ErrorHandlerService) {
        this.url = 'http://localhost:8080/api/uploads';
    }

    uploadFile(path: string, type: string, files: Array<File>) {
        return new Promise((resolve, reject) => {
            var formData: any = new FormData();
            var xhr = new XMLHttpRequest();
            for(var i = 0; i < files.length; i++) {
                formData.append("file", files[i], files[i].name);
            }
            formData.append("type", type);
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4) {
                    if (xhr.status == 200) {
                        resolve(xhr.response);
                    } else {
                        reject(xhr.response);
                    }
                }
            }
            xhr.open("POST", this.url + "/files/upload" + path, true);
            xhr.setRequestHeader('x-auth-token', localStorage.getItem('jwt'));
            xhr.send(formData);
        });
    }

	getUserFile(user_id:Number, filename:String):Observable<any> {
		return this.http.get(this.url + "/userFiles/" + user_id + "/" + filename, {headers: this.prepareHeaders()})
		  .map(this.extractUrl)
		  .catch(this.errorHandler.handleError);
	}

	getQuizFile(quiz_id: Number, filename:String):Observable<any> {
		return this.http.get(this.url + "/quizFiles/" + quiz_id + "/" + filename, {headers: this.prepareHeaders()})
		  .map(this.extractUrl)
		  .catch(this.errorHandler.handleError);
	}

	getPdf(user_id:Number, filename:String):Observable<any> {
		return this.http.get(this.url + "/quizFiles/" + user_id + "/" + filename, {headers: this.prepareHeaders()})
		  .map(this.extractUrl)
		  .catch(this.errorHandler.handleError);
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

}
