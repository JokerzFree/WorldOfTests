import {NgModule} from '@angular/core';
import {BrowserModule}  from '@angular/platform-browser';
import {UsersComponent} from './users.component';

@NgModule({
    imports: [
        BrowserModule
    ],
    declarations: [
        UsersComponent
    ],
    bootstrap: [UsersComponent]
})
export class UsersModule{}
