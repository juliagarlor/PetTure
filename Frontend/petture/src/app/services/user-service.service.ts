import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';
import { Picture } from '../models/picture';
import { Profile } from '../models/profile';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  url: string = 'http://localhost:8080/';
  headers = new HttpHeaders();

  constructor(
    private http: HttpClient,
    private cookies: CookieService
  ) {
   }

  login(user: any): Observable<any>{
    return this.http.post(this.url + 'user/auth/login', user);
  }

  setToken(token: string, username: string){
    this.cookies.set("token", token);
    this.cookies.set("username", username);
  }

  getToken(){
    return this.cookies.get("token");
  }

  getUsername(){
    return this.cookies.get("username");
  }

  signOut(){
    this.cookies.delete("token");
    this.cookies.deleteAll("username");
  }

  register(user: any): Observable<any>{
    return this.http.post<any>(this.url + 'user/auth/register', user);
  }

  getProfile(): Observable<IncomingProfile>{
    return this.http.get<IncomingProfile>( this.url + 'user/search/' + this.getUsername());
  }

  getRequests(): Observable<BasicProfile[]>{
    this.headers = new HttpHeaders().set('Content-Type', 'application/json').set('Authorization', 'Bearer ' + this.getToken());
    return this.http.get<BasicProfile[]>(this.url + 'user/requests/'+ this.getUsername(), {headers: this.headers});
  }

  removeRequest(requestId: string): Observable<any>{
    this.headers = new HttpHeaders().set('Content-Type', 'application/json').set('Authorization', 'Bearer ' + this.getToken());
    return this.http.put<any>(this.url + 'user/remove/request/' + this.getUsername(), requestId, {headers: this.headers});
  }

  addABuddy(buddyId: string): Observable<any>{
    this.headers = new HttpHeaders().set('Content-Type', 'application/json').set('Authorization', 'Bearer ' + this.getToken());
    return this.http.put<any>(this.url + 'user/buddy/' + this.getUsername(), buddyId, {headers: this.headers});
  }

  updateProfilePic(newProfilePic: string): Observable<any>{
    this.headers = new HttpHeaders().set('Content-Type', 'application/json').set('Authorization', 'Bearer ' + this.getToken());
    console.log(newProfilePic);
    return this.http.put<any>(this.url + 'user/profile-pic/' + this.getUsername(), newProfilePic, {headers: this.headers});
  }

  getPublicProfiles(): Observable<BasicProfile[]>{
    return this.http.get<BasicProfile[]>(this.url + 'user/search/public-profiles');
  }

  newRequest(requestedUser: string): Observable<any>{
    return this.http.put<any>(this.url + 'user/request/' + this.getUsername(), requestedUser)
  }
}

interface BasicProfile{
  userName: string,
  profilePic: string
}

interface IncomingProfile{
  userName: string,
  password: string,
  profilePicture: string,
  visibility: string,
  pics: Picture[],
  buddyNum: number
}



