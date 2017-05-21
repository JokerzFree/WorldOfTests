import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from './user.service';

@Injectable()
export class RoleService {
	userService: UserService;
	constructor(private router: Router, @Inject(UserService) userService: UserService) {
		this.userService = userService;
	}

	hasAnyRole(roles: string[]):boolean{
		let access: boolean = false;
        this.userService.getCurrentUser().map(
            user => {
                for (let i = 0; i < user.roles.length; i++){
                	if (roles.indexOf(user.roles[i].rolename) > -1){
                		access = true;
        			}
                }
                return access;
            }
        );
        return access;
	}
}