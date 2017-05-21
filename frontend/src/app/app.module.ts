import { RouterModule, Routes } from '@angular/router';

import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {CustomFormsModule} from 'ng2-validation'
import {HttpModule} from '@angular/http';
import {BrowserModule}  from '@angular/platform-browser';
import {TranslateModule} from "ng2-translate";
import {CKEditorModule} from 'ng2-ckeditor';
import {ToastrModule} from 'ngx-toastr';
import {ToastrService} from 'ngx-toastr';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {AuthGuard} from './services/auth-guard';
import {UserService} from './services/user.service';
import {LoginService} from './services/login.service';
import {ErrorHandlerService} from './services/error-handler.service';

import {AccessDirective} from "./directives/access/access.directive";

import {HeaderComponent}  from './components/header/header.component';
import {FooterComponent}  from './components/footer/footer.component';
import {AboutComponent}  from './components/about/about.component';
import {LoginComponent}  from './components/login/login.component';
import {PostsComponent}  from './components/posts/posts.component';
import {PostDetailComponent}  from './components/post-detail/post-detail.component';
import {QuizzesComponent}  from './components/quizzes/quizzes.component';
import {QuizDetailComponent}  from './components/quiz-detail/quiz-detail.component';
import {QuizAddComponent}  from './components/quiz-add/quiz-add.component';
import {QuestionAddComponent}  from './components/question-add/question-add.component';
import {PostAddComponent}  from './components/post-add/post-add.component';
import {ProfileComponent} from './components/profile/profile.component';
import {RegisterComponent} from './components/register/register.component';
import {UsersComponent} from './components/users/users.component';
import {LoaderComponent} from './components/loader/loader.component';
import {ErrorComponent} from './components/error/error.component';

import {AppComponent} from './app.component';

export const routes: Routes = [
    {
        path: '',
        redirectTo: '/posts',
        pathMatch: 'full'
    },
    {
        path: 'about',
        component: AboutComponent
    },
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'posts',
        component: PostsComponent
    },
    {
        path: 'postdetail/:id',
        component: PostDetailComponent
    },
    {
        path: 'postadd',
        component: PostAddComponent, 
        canActivate: [AuthGuard]
    },
    {
        path: 'quizzes',
        component: QuizzesComponent
    },
    {
        path: 'quizdetail/:id',
        component: QuizDetailComponent
    },
    {
        path: 'quizadd',
        component: QuizAddComponent,
        canActivate: [AuthGuard]
    },
    {
        path: 'profile',
        component: ProfileComponent,
        canActivate: [AuthGuard]
    },
    {
        path: 'register',
        component: RegisterComponent
    },
    {
    	path: 'users',
    	component: UsersComponent,
    	canActivate: [AuthGuard]
    },
    {
        path: 'error',
        component: ErrorComponent
    }
];

@NgModule({
    imports: [
    	RouterModule.forRoot(routes),
        HttpModule,
        BrowserModule,
        FormsModule,
        CustomFormsModule,
        TranslateModule.forRoot(),
        CKEditorModule,
        BrowserAnimationsModule,
        ToastrModule.forRoot()
    ],
    declarations: [
        AppComponent,
        HeaderComponent,
        FooterComponent,
        AboutComponent,
        LoginComponent,
        PostsComponent,
        PostDetailComponent,
        PostAddComponent,
        ProfileComponent,
        RegisterComponent,
        UsersComponent,
        QuizzesComponent,
        QuizDetailComponent,
        QuizAddComponent,
        QuestionAddComponent,
        LoaderComponent,
        ErrorComponent,
        AccessDirective
    ],
    providers: [
        AuthGuard,
        LoginService,
        UserService,
        ErrorHandlerService,
        ToastrService
    ],
    entryComponents: [QuestionAddComponent],
    bootstrap: [AppComponent]
})
export class AppModule {
}
