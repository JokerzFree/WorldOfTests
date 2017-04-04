import {Component, OnInit, Input} from "@angular/core";
import { Router }  from '@angular/router';
import {LoginService}  from '../../services/login.service';
import {TranslateService} from 'ng2-translate';

@Component({
    selector: 'header',
    templateUrl: './header.component.html',
    providers: [
    	LoginService
    ]
})
export class HeaderComponent {

    isSignedIn:boolean;
    errorMessage:string;

    constructor(private router:Router, private loginService:LoginService, private translate:TranslateService) {
        this.isSignedIn = loginService.isSignedIn();
        router.events.subscribe(() => {
            this.isSignedIn = loginService.isSignedIn();
        });
    }

    login(event: any, email: any, password: any) {
        event.preventDefault();
        this.loginService.login(email, password)
            .subscribe(() => {
                this.router.navigate(['/add']);
            }, this.handleError)
        ;
    }

    logout() {
        this.loginService.logout();
        this.router.navigate(['/login']);
    }

    changeLang(lang: string) {
        this.translate.use(lang);
    }

    handleError(error: any) {
        console.log(error.status);
    }
}