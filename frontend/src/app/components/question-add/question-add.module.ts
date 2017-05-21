import {NgModule, OnInit, Input} from '@angular/core';
import {BrowserModule}  from '@angular/platform-browser';
import {QuestionAddComponent} from './question-add.component';

@NgModule({
    imports: [
        BrowserModule
    ],
    declarations: [
        QuestionAddComponent
    ],
    bootstrap: [QuestionAddComponent]
})
export class QuestionAddModule{}
