import {NgModule} from '@angular/core';
import {BrowserModule}  from '@angular/platform-browser';
import {LoaderComponent} from './loader.component';
@NgModule({
    imports: [
        BrowserModule
    ],
    declarations: [
        LoaderComponent
    ],
    bootstrap: [LoaderComponent]
})
export class LoaderModule {
}
