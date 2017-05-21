import { Option } from './option';

export class Question {
    id: string;
    name: string;
    questionTypeId: number = 0;
    options: Option[] = [];
    answer: string;
    answered: boolean;
    uppercaseValidate: boolean;
    required: boolean;
    image: string;
    imageUrl: string;

    constructor(data: any) {
        if (data) {
            this.id = data.id;
            this.name = data.name;
            this.questionTypeId = data.questionTypeId;
            this.required = data.required;
            this.uppercaseValidate = data.uppercaseValidate;
            this.image = data.image;
            for (let o of data.options) {
                this.options.push(new Option(o));
            }
        }
    }
}
