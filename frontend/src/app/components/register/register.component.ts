import {Component, OnInit, Input} from "@angular/core";
import { Router }  from '@angular/router';
import {User} from '../../models/user';
import {UserService}  from '../../services/user.service';

@Component({
    selector: 'register',
    templateUrl: './register.component.html',
    providers: [
        UserService
    ]
})
export class RegisterComponent implements OnInit {
    @Input() user:User;
    
    constructor(private router:Router, private userService:UserService) {
    }

    ngOnInit() {
        this.user = new User();
    }

    register() {
        event.preventDefault();
        this.userService.create(this.user)
            .subscribe(() => {
                this.router.navigate(['/login']);
            }, this.handleError);
    }

    handleError(error:any) {
        console.log(error.status);
    }
}