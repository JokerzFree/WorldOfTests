import {NgModule, OnInit, Input} from '@angular/core';
import {BrowserModule}  from '@angular/platform-browser';
import { QuizDetailComponent } from './quiz-detail.component';
import { Router, ActivatedRoute } from '@angular/router';
import {QuizService} from "../../services/quiz.service";
import {Quiz} from "../../models/quiz";

@NgModule({
    imports: [
        BrowserModule
    ],
    declarations: [
        QuizDetailComponent
    ],
    bootstrap: [QuizDetailComponent]
})
export class QuizDetailModule{}
