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
    errorMessage:any;
    postAdded:boolean;
    data:string;

    constructor(private postService:PostService, private accessService:AccessService) {
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