import {Component, OnInit, Input} from "@angular/core";
import { Router, ActivatedRoute } from '@angular/router';
import {PostService} from "../../services/post.service";
import {Post} from "../../models/post";

@Component({
    selector: 'post-detail',
    templateUrl: './post-detail.component.html',    
    providers: [
    	PostService
    ]
})
export class PostDetailComponent implements OnInit {

    post:Post;
    commentShow:boolean;

    constructor(private postService:PostService, private route: ActivatedRoute) {
    }


    ngOnInit() {
        this.commentShow = false;
        this.route.params.subscribe(params => {
            if (params['id'] !== undefined) {
                let id = +params['id'];
                this.postService.getPost(id).subscribe(post => this.post = post);
            }
        });
    }
}