import {Component} from '@angular/core';
import {TranslateService} from 'ng2-translate';
import '../styles/styles.css';
import '../styles/ng2-toastr.min.css';
import '../styles/bootstrap4-helpers.min.css';


@Component({
    selector: 'app',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
    providers: [
    	TranslateService
    ]
})
export class AppComponent {

	constructor(private translate:TranslateService){
		translate.addLangs(['en', 'ru']);
        translate.setDefaultLang('en');
	}
}
