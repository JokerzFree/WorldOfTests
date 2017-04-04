import {NgModule} from '@angular/core';
import {BrowserModule}  from '@angular/platform-browser';
import {PostsComponent} from './post-add.component';

@NgModule({
    imports: [
        BrowserModule
    ],
    declarations: [
        PostsComponent
    ],
    bootstrap: [PostsComponent]
})
export class PostsModule{}
