import { Component, OnInit, Input, ViewChild, ViewContainerRef, ComponentFactoryResolver } from '@angular/core';
import { PostService } from "../../services/post.service";
import { QuizService } from "../../services/quiz.service";
import { UploadService } from "../../services/upload.service";
import {ErrorHandlerService}  from '../../services/error-handler.service';
import { QuestionAddComponent } from "../question-add/question-add.component";
import { Quiz } from "../../models/quiz";
import { QuizConfig } from "../../models/quiz-config";
import { Question } from "../../models/question";
import { Option } from "../../models/option";

@Component({
	selector: 'quiz-add-component',
	templateUrl: './quiz-add.component.html',
	providers: [
		QuizService,
		UploadService
	]
})
export class QuizAddComponent implements OnInit {

	@Input() quiz: Quiz;
	count: number;
	quizAdded: boolean;
	data: string;
	files: string[];
	q:QuestionAddComponent[];

	@ViewChild('questions', {read: ViewContainerRef})
	questions: ViewContainerRef;

	constructor(private quizService: QuizService, 
				private componentFactoryResolver: ComponentFactoryResolver,
				private uploadService: UploadService,
				private errorHandler: ErrorHandlerService) {
		this.count = 0;
		this.quiz = new Quiz(null);
		this.quiz.config = new QuizConfig(null);
		this.quiz.imageUrl = "assets/images/none.png";
		this.q = [];
		this.files = [];
	}

	ngOnInit() {
	}

	addQuestion() {
		this.count++;
		let questionAddFactory = this.componentFactoryResolver.resolveComponentFactory(QuestionAddComponent);
		let questionAddRef = this.questions.createComponent(questionAddFactory);
		questionAddRef.instance.question.id = String(this.count);
		this.q.push(questionAddRef.instance);

		questionAddRef.instance.delete.subscribe(() => {
			this.q.splice(this.q.indexOf(questionAddRef.instance), 1);
			questionAddRef.destroy();
		});
	}

    addFile(fileInput: any){
    	let reader = new FileReader();
        let image:string = Object.bind(this.image);

        reader.onload = (e:any) => {
            this.quiz.imageUrl = e.target.result;
        };

        reader.readAsDataURL(fileInput.target.files[0]);
    	//
    	if (fileInput.target.files && fileInput.target.files[0]) {
			this.uploadService.uploadFile("", "quiz", fileInput.target.files).then(
			    (result: any) => {
			    	this.quiz.image = result;
			    	console.log(result);
			    },  
			    (error: any) => this.errorHandler.showError(error)
			);
    	}
    }

	confirmQuiz() {
		if (!this.quiz.title || !this.quiz.description){
			return false;
		}
		this.files = [];
		this.quiz.questions = [];
		let answers:any[] = [];
		//
		if (this.quiz.image){
			this.files.push(this.quiz.image);
		}
		//
		for (var i = 0; i < this.q.length; ++i) {
			let question:Question = Object.assign({}, this.q[i].question);
			if (question.image){
				this.files.push(question.image);
			}
			switch (Number(question.questionTypeId)) {
				case 1:
					let selRadioOption:Option = question.options.find(o => o.isAnswer);
					selRadioOption.isAnswer = false;
           			answers.push({"q": question.id, "a": selRadioOption.id});
					break;
				case 2:
					let selCheckboxOptions:Option[] = question.options.filter(o => o.isAnswer);
					let allOptions:string[] = [];
					for (let option of selCheckboxOptions){
						option.isAnswer = false;
						allOptions.push(option.id);
					}
           			answers.push({"q": question.id, "a": allOptions});
					break;
				case 3:
					if (question.answer && !question.uppercaseValidate){
						question.answer = question.answer.toLowerCase();
					}
					answers.push({"q": question.id, "a": question.answer});
					question.answer = '';
					break;
				case 4:
					let selOneMenuOption:Option = question.options.find(o => o.isAnswer);
					selOneMenuOption.isAnswer = false;
           			answers.push({"q": question.id, "a": selOneMenuOption.id});
					break;
			}
			this.quiz.questions.push(question);
		}
		this.save(this.quiz, answers, this.files);
	}

	save(quiz: Quiz, answers: any[], files: string[]) {
		this.quizService.save(quiz, answers, files)
			.subscribe(
				data => {
					this.quizAdded = true;
					this.data = JSON.stringify(data);
				},
            	error => this.errorHandler.showError(error)
			);
	}
}