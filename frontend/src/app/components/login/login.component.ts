import {Component} from "@angular/core";
import { Router }  from '@angular/router';
import {LoginService}  from '../../services/login.service';
import {UserService}  from '../../services/user.service';

@Component({
    selector: 'login',
    templateUrl: './login.component.html',
    providers: [
        LoginService,
        UserService
    ]
})
export class LoginComponent {
    constructor(private router:Router, private loginService:LoginService, private userService:UserService) {
    }

    login(event: any, username: any, password: any) {
        event.preventDefault();
        this.loginService.login(username, password)
            .subscribe(() => {
                this.userService.setLastLoginTime().subscribe(()=>{}, this.handleError);
                this.router.navigate(['/add']);
            }, this.handleError);
    }

    logout():void {
        localStorage.removeItem('jwt');
    }


    handleError(error:any) {
        console.log(error.status);
    }
}