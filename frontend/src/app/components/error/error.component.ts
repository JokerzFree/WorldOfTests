import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params }  from '@angular/router';

@Component({
    selector: 'error',
    templateUrl: './error.component.html',
    providers: [
    ]
})
export class ErrorComponent implements OnInit {
	path: String;

    constructor(private route:ActivatedRoute) {
    }

    ngOnInit(){
    	this.route.params.subscribe(params => {
    		this.path = params['path'];
    	});
    }
}