import {Component, OnInit, Input} from "@angular/core";
import { Router }  from '@angular/router';
import {UserService} from "../../services/user.service";
import {UploadService}  from '../../services/upload.service';
import {User} from "../../models/user";

@Component({
    selector: 'profile',
    templateUrl: './profile.component.html',
    providers: [
    	UserService,
        UploadService
    ]
})

export class ProfileComponent implements OnInit {

    user:User;
    image:String;
    errorMessage:string;

    constructor(private router:Router,
                private userService:UserService,
                private uploadService:UploadService) {

    }

    ngOnInit() {
        this.loadInfo();
    }
    
    update(fileInput: any){
        this.uploadService.uploadFile("", fileInput.target.files).then(
            (result: any) => {
                console.log(result);
                this.loadInfo();
            },  
            (error: any) => {
                console.error(error);
            });
    }

    loadInfo(){
        this.userService.getCurrentUser().subscribe(
            user => {
                this.user = user;
                this.uploadService.getImage(this.user.id, this.user.avatar)
                    .subscribe((file) => {
                        this.image = file;
                    });
            },
            error => this.errorMessage = <any> error
        );
    }
}
