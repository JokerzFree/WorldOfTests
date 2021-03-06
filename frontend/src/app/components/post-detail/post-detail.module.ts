import {NgModule, OnInit, Input} from '@angular/core';
import {BrowserModule}  from '@angular/platform-browser';
import { Router, ActivatedRoute } from '@angular/router';
import {PostService} from "../../services/post.service";
import {PostDetailComponent} from './post-detail.component';
import {Post} from "../../models/post";

@NgModule({
    imports: [
        BrowserModule
    ],
    declarations: [
        PostDetailComponent
    ],
    bootstrap: [PostDetailComponent]
})
export class PostDetailModule{}
