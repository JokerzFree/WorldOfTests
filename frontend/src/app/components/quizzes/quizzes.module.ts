import {NgModule} from '@angular/core';
import {BrowserModule}  from '@angular/platform-browser';
import {QuizzesComponent} from './quizzes.component';

@NgModule({
    imports: [
        BrowserModule
    ],
    declarations: [
        QuizzesComponent
    ],
    bootstrap: [QuizzesComponent]
})
export class QuizessModule{}
