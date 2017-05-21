import {NgModule, OnInit, Input} from '@angular/core';
import {BrowserModule}  from '@angular/platform-browser';
import { Router }  from '@angular/router';
import {LoginComponent} from './login.component';
@NgModule({
    imports: [
        BrowserModule
    ],
    declarations: [
        LoginComponent
    ],
    bootstrap: [LoginComponent]
})
export class LoginModule {}
