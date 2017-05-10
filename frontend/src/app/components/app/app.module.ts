import { RouterModule, Routes } from '@angular/router';

import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {CustomFormsModule} from 'ng2-validation'
import {HttpModule} from '@angular/http';
import {BrowserModule}  from '@angular/platform-browser';
import {TranslateModule} from "ng2-translate";
import {CKEditorModule} from 'ng2-ckeditor';

import {AuthGuard} from '../../services/auth-guard';
import {UserService} from '../../services/user.service';
import {LoginService} from '../../services/login.service';

import {HeaderComponent}  from '../header/header.component';
import {FooterComponent}  from '../footer/footer.component';
import {AboutComponent}  from '../about/about.component';
import {LoginComponent}  from '../login/login.component';
import {PostsComponent}  from '../posts/posts.component';
import {PostDetailComponent}  from '../post-detail/post-detail.component';
import {QuizzesComponent}  from '../quizzes/quizzes.component';
import {QuizDetailComponent}  from '../quiz-detail/quiz-detail.component';
import {QuizAddComponent}  from '../quiz-add/quiz-add.component';
import {QuestionAddComponent}  from '../question-add/question-add.component';
import {PostAddComponent}  from '../post-add/post-add.component';
import {ProfileComponent} from '../profile/profile.component';
import {RegisterComponent} from '../register/register.component';
import {UsersComponent} from '../users/users.component';
import {LoaderComponent} from '../loader/loader.component';

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
        CKEditorModule
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
        LoaderComponent
    ],
    providers: [
        AuthGuard,
        LoginService,
        UserService
    ],
    entryComponents: [QuestionAddComponent],
    bootstrap: [AppComponent]
})
export class AppModule {
}
