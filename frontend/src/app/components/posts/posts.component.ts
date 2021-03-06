import {Component, OnInit, Input} from "@angular/core";
import { Router }  from '@angular/router';
import {PostService} from "../../services/post.service";
import {Post} from "../../models/post";

@Component({
    selector: 'my-posts',
    templateUrl: './posts.component.html',
    providers: [
    	PostService
    ]
})

export class PostsComponent implements OnInit {

    posts:Post[];
    errorMessage:string;

    constructor(private router:Router,
                private postsService:PostService) {

    }

    ngOnInit() {
        this.getPosts();
    }

    getPosts() {
        this.postsService.getPosts()
            .subscribe(
            posts => this.posts = posts,
            error => this.errorMessage = <any> error
        );
    }

    gotoDetail(post:Post) {
        let link = ['/postdetail', post.id];
        this.router.navigate(link);
    }
}
