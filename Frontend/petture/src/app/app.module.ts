import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FooterComponent } from './components/footer/footer.component';
import { LogginComponent } from './components/loggin/loggin.component';
import {MatInputModule} from '@angular/material/input'; 
import {MatButtonModule} from '@angular/material/button';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HomeComponent } from './components/home/home.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { PostCardComponent } from './components/post-card/post-card.component';
import {MatIconModule} from '@angular/material/icon';
import { SearchComponent } from './components/search/search.component';
import { ProfileCardComponent } from './components/profile-card/profile-card.component';
import { ProfileComponent } from './components/profile/profile.component';
import {MatGridListModule} from '@angular/material/grid-list';
import { IncomingRequestsComponent } from './components/incoming-requests/incoming-requests.component'; 
import { HttpClientModule } from '@angular/common/http';
import { routing } from './app.routing';
import { CookieService } from 'ngx-cookie-service';
import { RegisterComponent } from './components/register/register.component';
import {MatDialogModule} from '@angular/material/dialog';
import { NewPostComponent } from './components/new-post/new-post.component'; 
import {MatMenuModule} from '@angular/material/menu';
import { BuddiesComponent } from './components/buddies/buddies.component'; 


@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    LogginComponent,
    HomeComponent,
    NavBarComponent,
    PostCardComponent,
    SearchComponent,
    ProfileCardComponent,
    ProfileComponent,
    IncomingRequestsComponent,
    RegisterComponent,
    NewPostComponent,
    BuddiesComponent
  ],
  imports: [
    BrowserModule,
    routing,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatGridListModule,
    MatDialogModule,
    MatMenuModule
  ],
  providers: [CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
