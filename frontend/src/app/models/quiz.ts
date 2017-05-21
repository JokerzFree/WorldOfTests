import { QuizConfig } from './quiz-config';
import { Question } from './question';

export class Quiz {
    id: number;
    title: string;
    image: string;
    imageUrl: string;
    description: string;
    config: QuizConfig;
    questions: Question[];

    constructor(data: any) {
        this.questions = [];
        if (data) {
            this.id = data.id;
            this.title = data.title;
            this.image = data.image;
            this.description = data.description;
            let data_json = JSON.parse(data.json_quiz);
            console.log(data_json);
            this.config = new QuizConfig(data_json.config);
            for (let q of data_json.questions) {
                this.questions.push(new Question(q));
            }
        }
    }
}
