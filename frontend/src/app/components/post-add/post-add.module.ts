import {NgModule, OnInit, Input} from '@angular/core';
import {BrowserModule}  from '@angular/platform-browser';
import {PostAddComponent} from './post-add.component';

@NgModule({
    imports: [
        BrowserModule
    ],
    declarations: [
        PostAddComponent
    ],
    bootstrap: [PostAddComponent]
})
export class PostAddModule{}
