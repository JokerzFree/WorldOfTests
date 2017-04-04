import { Component } from '@angular/core';
import { Router }  from '@angular/router';
import {UploadService}  from '../../services/upload.service';

@Component({
    selector: 'about',
    templateUrl: './about.component.html',
    providers: [
    	UploadService
    ]
})
export class AboutComponent {

    file:string;

    constructor(private uploadService: UploadService) {
    }
 
    load(){
        this.uploadService.getPdf("file.pdf").subscribe(
            (doc) => {
                this.file = doc;
            });
    }
}