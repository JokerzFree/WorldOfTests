import {Component, OnInit, Input} from '@angular/core';
import {PostService} from "../../services/post.service";
import {AccessService} from "../../services/access.service";
import {Post} from "../../models/post";

@Component({
    selector: 'my-post-add-component',
    templateUrl: './post-add.component.html',
    providers: [
    	PostService,
        AccessService
    ]
})
export class PostAddComponent implements OnInit {

    @Input() post:Post;
    loaded:boolean;
    access:boolean;
    errorMessage:any;
    postAdded:boolean;
    data:string;

    constructor(private postService:PostService, private accessService:AccessService) {
        this.postAdded = false;
    }

    ngOnInit() {
        this.checkAccess();
    }

    checkAccess(){
        this.accessService.getAccess('post-add').subscribe(
                access => {
                    this.loaded = true;
                    this.access = true;
                    this.post = new Post();
                },
                error => {
                    this.loaded = true;
                    this.access = false;
                    this.errorMessage = <any> error;
                }
            )
    }

    save() {
        this.postService.save(this.post)
            .subscribe(data => this.data = JSON.stringify(data));
        this.postAdded = true;
    }
}