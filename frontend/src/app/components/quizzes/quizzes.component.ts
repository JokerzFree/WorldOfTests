import {Component, OnInit, Input} from "@angular/core";
import { Router }  from '@angular/router';
import {QuizService} from "../../services/quiz.service";
import { UploadService } from "../../services/upload.service";
import { ErrorHandlerService }  from '../../services/error-handler.service';
import {Quiz} from "../../models/quiz";

@Component({
    selector: 'my-quizzes',
    templateUrl: './quizzes.component.html',
    providers: [
    	QuizService,
        UploadService
    ]
})

export class QuizzesComponent implements OnInit {

    quizzes:Quiz[];
    errorMessage:string;

    constructor(private router:Router,
                private quizService:QuizService,
                private uploadService:UploadService,
                private errorHandler:ErrorHandlerService) {

    }

    ngOnInit() {
        this.getQuizzes();
    }

    getQuizzes() {
        this.quizService.getQuizzes()
            .subscribe(
            quizzes => {
                this.quizzes = quizzes;
                for (let i = 0; i < this.quizzes.length; ++i){
                    let quiz = this.quizzes[i];
                    if (quiz.image){
                        this.uploadService.getQuizFile(quiz.id, quiz.image).subscribe(
                            (file) => {
                                quiz.imageUrl = file;
                            },
                            (error) => this.errorHandler.showError(error)
                        );
                    }
                }
            },
            error => this.errorHandler.showError(error)
        );
    }

    gotoDetail(quiz:Quiz) {
        let link = ['/quizdetail', quiz.id];
        this.router.navigate(link);
    }
}
