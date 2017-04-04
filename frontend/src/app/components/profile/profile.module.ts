import {NgModule} from '@angular/core';
import {BrowserModule}  from '@angular/platform-browser';
import {ProfileComponent} from './profile.component';

@NgModule({
    imports: [
        BrowserModule
    ],
    declarations: [
        ProfileComponent
    ],
    bootstrap: [ProfileComponent]
})
export class ProfileModule{}
