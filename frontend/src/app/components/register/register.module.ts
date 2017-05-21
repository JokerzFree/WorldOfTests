import {NgModule, OnInit, Input} from '@angular/core';
import {BrowserModule}  from '@angular/platform-browser';
import { Router }  from '@angular/router';
import {RegisterComponent} from './register.component';
@NgModule({
    imports: [
        BrowserModule
    ],
    declarations: [
        RegisterComponent
    ],
    bootstrap: [RegisterComponent]
})
export class RegisterModule {}
