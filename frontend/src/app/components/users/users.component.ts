import {Component, OnInit, Input} from "@angular/core";
import { Router }  from '@angular/router';
import {UserService} from "../../services/user.service";
import {RoleService} from "../../services/role.service";
import {AccessService} from "../../services/access.service";
import {AccessDirective} from "../../directives/access/access.directive";
import {ErrorHandlerService}  from '../../services/error-handler.service';
import {User} from "../../models/user";

@Component({
    selector: 'users',
    templateUrl: './users.component.html',
    providers: [
    	UserService,
        RoleService,
        AccessService,
        AccessDirective
    ]
})

export class UsersComponent implements OnInit {
    users:User[];

    constructor(private router:Router,
                private userService:UserService,
                private roleService:RoleService,
                private accessService:AccessService,
                private errorHandler:ErrorHandlerService) {

    }

    ngOnInit() {
        this.loadUsers();
    }

    loadUsers(){
        this.userService.getUsers().subscribe(
            users => {
                this.users = users;
            },
            error => this.errorHandler.showError(error)
        );
    }

    deleteUser(id:number){
        this.userService.deleteUser(id).subscribe(
            ()=>{
                this.loadUsers()
            }, 
            error => this.errorHandler.showError(error)
        );
    }
}
