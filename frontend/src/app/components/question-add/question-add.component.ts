import { Component, OnInit, Input, EventEmitter } from '@angular/core';
import { PostService } from "../../services/post.service";
import { QuizService } from "../../services/quiz.service";
import { UploadService } from "../../services/upload.service";
import { ErrorHandlerService } from "../../services/error-handler.service";
import { Question } from "../../models/question";
import { Option } from "../../models/option";

@Component({
    selector: 'question-add-component',
    templateUrl: './question-add.component.html',
    providers: [
        QuizService
    ]
})
export class QuestionAddComponent implements OnInit {
    @Input() question: Question;
    count: number;

    constructor(private quizService: QuizService, private uploadService: UploadService, private errorHandler: ErrorHandlerService) {
        this.count = 0;
        this.question = new Question(null);
    }

    ngOnInit() {
    }

    addFile(fileInput: any){
        let reader = new FileReader();

        reader.onload = (e:any) => {
            this.question.imageUrl = e.target.result;
        };

        reader.readAsDataURL(fileInput.target.files[0]);
        //
        if (fileInput.target.files && fileInput.target.files[0]) {
            this.uploadService.uploadFile("", "quiz", fileInput.target.files).then(
                (result: any) => {
                    this.question.image = result;
                    console.log(result);
                },  
                (error: any) => this.errorHandler.showError(error)
            );
        }
    }

    onQuestionTypeChange(){
        this.count = 0;
        this.question.options = [];
        this.question.answer = '';
    }

    optionAdd(){
        console.log(this.question);
        this.count++;
        let opt:Option = new Option(null);
        opt.questionId = this.question.id;
        opt.id = this.question.id+"_"+this.count;
        this.question.options.push(opt);
    }

    optionRemove(option:Option){
        this.question.options.splice(this.question.options.indexOf(option), 1);
    }

    onSelectRadioOption(option:Option){
        this.leftOneOptionInQuestion(option);
    }

    onSelectMenuOption(option:Option){
        this.leftOneOptionInQuestion(option);
    }

    leftOneOptionInQuestion(option:Option){
        for (var i = 0; i < this.question.options.length; i++) {
            this.question.options[i].isAnswer = false;
        }
        option.isAnswer = true;
    }

    delete = new EventEmitter();

    onClickedDelete() {
        this.delete.emit('event');
    }

    save() {
    }
}