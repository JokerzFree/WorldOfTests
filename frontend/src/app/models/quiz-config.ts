export class QuizConfig {
    allowBack: boolean;
    duration: number = 0;
    shuffleQuestions: boolean;
    shuffleOptions: boolean;
    showPager: boolean = true;

    constructor(data: any) {
        if (data){
            this.allowBack = data.allowBack;
            this.duration = data.duration;
            this.shuffleQuestions = data.shuffleQuestions;
            this.shuffleOptions = data.shuffleOptions;
            this.showPager = data.showPager;
        }
    }
}
