import {NgModule, OnInit, Input} from '@angular/core';
import {BrowserModule}  from '@angular/platform-browser';
import {QuizAddComponent} from './quiz-add.component';

@NgModule({
    imports: [
        BrowserModule
    ],
    declarations: [
        QuizAddComponent
    ],
    bootstrap: [QuizAddComponent]
})
export class QuizAddModule{}
