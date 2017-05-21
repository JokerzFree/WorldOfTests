export class Option {
    id: string;
    questionId: string;
    name: string;
    selected: boolean;
    isAnswer: boolean;

    constructor(data: any) {
        this.isAnswer = false;
        this.selected = false;
        if (data){
            this.id = data.id;
            this.questionId = data.questionId;
            this.name = data.name;
        }
    }
}
