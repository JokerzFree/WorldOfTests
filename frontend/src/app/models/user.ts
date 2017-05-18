import { Role } from './role';

export class User {
    id:number;
    username:string;
    password:string;
    email:string;
    name:string;
    roles: Role[];
    avatar:string;
}
