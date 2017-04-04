import {Component, OnInit, Input} from '@angular/core';
import {PostService} from "../../services/post.service";
import {Post} from "../../models/post";

@Component({
    selector: 'my-post-add-component',
    templateUrl: './post-add.component.html',
    providers: [
    	PostService
    ]
})
export class PostAddComponent implements OnInit {

    @Input() post:Post;
    error:any;
    postAdded:boolean;
    data:string;

    constructor(private postService:PostService) {
        this.postAdded = false;
    }

    ngOnInit() {
        this.post = new Post();
    }

    save() {
        this.postService.save(this.post)
            .subscribe(data => this.data = JSON.stringify(data));
        this.postAdded = true;
    }
}