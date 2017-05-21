import {Component} from "@angular/core";
import { Router }  from '@angular/router';
import {LoginService}  from '../../services/login.service';
import {UserService}  from '../../services/user.service';
import {ErrorHandlerService} from '../../services/error-handler.service';

@Component({
    selector: 'login',
    templateUrl: './login.component.html',
    providers: [
        LoginService,
        UserService
    ]
})
export class LoginComponent {

    constructor(private router:Router, private loginService:LoginService, private userService:UserService, private errorHandler: ErrorHandlerService) {
    }

    login(event: any, username: any, password: any) {
        event.preventDefault();
        this.loginService.login(username, password)
            .subscribe(
                () => {
                    this.userService.setLastLoginTime().subscribe(()=>{}, error => this.errorHandler.showError(error));
                    this.router.navigate(['/posts']);
                }, 
                (error) => this.errorHandler.showError(error));
    }

    logout():void {
        localStorage.removeItem('jwt');
    }
}