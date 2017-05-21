import { Component, OnInit, Input } from "@angular/core";
import { Router, ActivatedRoute } from '@angular/router';
import { QuizService } from "../../services/quiz.service";
import { Quiz } from "../../models/quiz";
import { Question } from "../../models/question";
import { QuizConfig } from "../../models/quiz-config";
import { Option } from "../../models/option";
import { Observable, Subscription } from 'rxjs/Rx';
import { ToastrService } from 'ngx-toastr';
import { UploadService } from "../../services/upload.service";
import { ErrorHandlerService }  from '../../services/error-handler.service';

@Component({
    selector: 'quiz-detail',
    templateUrl: './quiz-detail.component.html',
    providers: [
        QuizService,
        UploadService
    ]
})
export class QuizDetailComponent implements OnInit {

    id: number;
    quiz: Quiz;
    mode = 'quiz';
    result: boolean;
    config: QuizConfig;
    timer: number;
    timerEvent: Subscription;

    pager = {
        index: 0,
        count: 1
    };

    constructor(private quizService: QuizService, 
                private route: ActivatedRoute, 
                private toastr: ToastrService,
                private uploadService:UploadService,
                private errorHandler:ErrorHandlerService) { 
        this.timer = 0;
    }

    ngOnInit() {
        this.route.params.subscribe(params => {
            if (params['id'] !== undefined) {
                let id = +params['id'];
                this.quizService.getQuiz(id).subscribe(res => {
                    this.id = id;
                    this.quiz = new Quiz(res);
                    this.shuffle(this.quiz.questions);
                    for (var i = 0; i < this.quiz.questions.length; i++) {
                        let question = this.quiz.questions[i];
                        this.shuffle(question.options);
                        if (question.image){
                            this.uploadService.getQuizFile(this.quiz.id, question.image).subscribe(
                                (file) => {
                                    question.imageUrl = file;
                                },
                                (error) => this.errorHandler.showError(error)
                            );
                        }
                    }
                    this.config = this.quiz.config;
                    this.pager.count = this.quiz.questions.length;
                    if (this.config.duration) {
                        this.runQuizTimer();
                    }
                });
            }
        });
    }

    ngOnDestroy(){
        if (this.config.duration){
            this.timerEvent.unsubscribe();
        }
    }
    
    runQuizTimer() {
        let timer = Observable.timer(0,1000);
        this.timerEvent = timer.subscribe(x => {
            this.timer = x;
            if (x == Number(this.config.duration)*60){
                this.timerEvent.unsubscribe();
                this.onSubmit();
            }
        });
    }

    get currentTimer():String {
        let date = new Date(null);
        let secondsLeft = Number(this.config.duration)*60-this.timer;
        date.setSeconds(secondsLeft);
        return date.toISOString().substr(11, 8);
    }

    get currentQuestion(): Question {
        return (this.quiz.questions) ? this.quiz.questions[this.pager.index] : null;
    }

    goTo(index: number, allow?: boolean) {
        if (index >= this.pager.count || index < 0) {
            return false;
        }
        if (!this.config.allowBack && this.currentQuestion.required && !this.isAnswered(this.currentQuestion)){
            this.toastr.warning('Question still not answered', 'Answer is required!');
            return false;
        }
        if (this.config.allowBack || allow) {
            if (index >= 0 && index < this.pager.count) {
                this.pager.index = index;
            }
            this.mode = 'quiz';
        }
    }

    goToNext() {
        this.goTo(this.pager.index + 1, true);
    }

    onRadioSelect(question: Question, option: Option) {
        question.options.forEach((x) => { if (x.id !== option.id) x.selected = false; });
    }

    onCheckSelect(question: Question, option: Option){

    }

    onOneMenuSelect(question: Question, option: String){
        question.options.forEach((x) => 
            { 
                if (x.id != option) {
                    x.selected = false; 
                } else {
                    x.selected = true;
                }
            });
    }

    isAnswered(question: Question) {
        switch (Number(question.questionTypeId)) {
            case 1:
                return Boolean(question.options.find(x => x.selected));
            case 2:
                return Boolean(question.options.find(x => x.selected));
            case 3:
                return Boolean(question.answer);
            case 4:
                return Boolean(question.options.find(x => x.selected));
        }
        return false;
    };

    isCorrect() {
        return this.result;
    };

    onSubmit() {
        if (this.config.duration){
            this.timerEvent.unsubscribe();
        }
        let answers: any[] = [];
        let complete = true;
        this.quiz.questions.forEach(q => {
            if (complete && q.required && !this.isAnswered(q)){
                this.toastr.warning('Question: "'+q.name+'" still not answered', 'Answer is required!');
                complete = false;
                return false;
            }
            switch (Number(q.questionTypeId)) {
                case 1:
                    let selRadioOption: Option = q.options.find(o => o.selected);
                    let radioOptionId: String = (selRadioOption) ? selRadioOption.id : "";
                    answers.push({ "q": q.id, "a": radioOptionId });
                    break;
                case 2:
                    let selCheckboxOptions: Option[] = q.options.filter(o => o.selected);
                    let allOptions: string[] = [];
                    for (let option of selCheckboxOptions) {
                        allOptions.push(option.id);
                    }
                    answers.push({ "q": q.id, "a": allOptions });
                    break;
                case 3:
                    if (q.answer && !q.uppercaseValidate){
                        q.answer = q.answer.toLowerCase();
                    }
                    answers.push({ "q": q.id, "a": q.answer })
                    break;
                case 4:
                    let selOneMenuOption: Option = q.options.find(o => o.selected);
                    let oneMenuOptionId: String = (selOneMenuOption) ? selOneMenuOption.id : "";
                    answers.push({ "q": q.id, "a": oneMenuOptionId });
                    break;
            }
        });
        if (complete){
            this.quizService.checkQuizAnswer(this.id, answers).subscribe(res => {
                this.result = res;
            });
            //
            this.mode = 'result';
        } else {
            if (this.config.allowBack) this.mode = 'review';
        }
    }

    shuffle(a:any) {
        for (let i = a.length; i; i--) {
            let j = Math.floor(Math.random() * i);
            [a[i - 1], a[j]] = [a[j], a[i - 1]];
        }
    }
}