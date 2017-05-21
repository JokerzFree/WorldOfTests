import {Observable} from "rxjs/Observable";
import {Injectable} from "@angular/core";
import {Response} from "@angular/http";
import { Headers, Http } from '@angular/http';
import { ToastrService } from 'ngx-toastr';
import "rxjs/Rx";

@Injectable()
export class ErrorHandlerService {

    constructor(private toastr: ToastrService) {
    }

    showError(error: any){
        this.toastr.error(error.message, error.title);
    }

    handleError(error: Response){
    	let errorTitle: string = 'Undefined error';;
        let errorMessage: string = 'Error attend during request';;
        if (error instanceof Response) {
        	let body: any = error.json();
            errorTitle = body.error;
            errorMessage = body.message;
        }
        return Observable.throw({"title": errorTitle, "message": errorMessage});
    }
}