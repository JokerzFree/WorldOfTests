import {Component, OnInit, Input} from "@angular/core";
import { Router }  from '@angular/router';
import {UserService} from "../../services/user.service";
import {UploadService}  from '../../services/upload.service';
import {ErrorHandlerService}  from '../../services/error-handler.service';
import { ToastrService } from 'ngx-toastr';
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

    state:String;
    user:User;
    image:String;

    constructor(private router:Router,
                private userService:UserService,
                private uploadService:UploadService,
                private errorHandler:ErrorHandlerService) {
        this.state = 'profile';
    }

    ngOnInit() {
        this.loadInfo();
    }

    changeState(state:String){
        this.state = state;
    }

    updateInfo(){

    }

    updateAvatar(fileInput: any){
        this.uploadService.uploadFile("", fileInput.target.files).then(
            (result: any) => {
                console.log(result);
                this.loadInfo();
            },  
            (error: any) => this.errorHandler.showError(error)
        );
    }

    updateEmail(email:string){
        this.userService.updateEmail(email).subscribe(
            user => {
                this.loadInfo();
                this.state = "profile";
            },
            error => this.errorHandler.showError(error)
        );
    }

    updatePassword(password:string){
        this.userService.updatePassword(password).subscribe(
            user => {
                this.loadInfo();
                this.state = "profile";
            },
            error => this.errorHandler.showError(error)
        );
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
            error => this.errorHandler.showError(error)
        );
    }
}
