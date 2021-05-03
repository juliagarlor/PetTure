import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  url: string = 'http://localhost:8080/';
  headers = new HttpHeaders();
  profileToCheck = new BehaviorSubject('');
  currentProfileToCheck = this.profileToCheck.asObservable();

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

  updateProfileToCheck(newProfileToCheck: string): void{
    this.profileToCheck.next(newProfileToCheck);
  }

  getProfile(username: string): Observable<IncomingProfile>{
    return this.http.get<IncomingProfile>( this.url + 'user/search/' + username);
  }

  getRequests(): Observable<BasicProfile[]>{
    this.headers = new HttpHeaders().set('Content-Type', 'application/json').set('Authorization', 'Bearer ' + this.getToken());
    return this.http.get<BasicProfile[]>(this.url + 'user/requests/'+ this.getUsername(), {headers: this.headers});
  }

  removeRequest(requestId: string): Observable<any>{
    this.headers = new HttpHeaders().set('Content-Type', 'application/json').set('Authorization', 'Bearer ' + this.getToken());
    return this.http.put<any>(this.url + 'user/remove/request/' + this.getUsername(), requestId, {headers: this.headers});
  }

  getRequested(): Observable<string[]>{
    this.headers = new HttpHeaders().set('Content-Type', 'application/json').set('Authorization', 'Bearer ' + this.getToken());
    return this.http.get<string[]>(this.url + 'user/requested/' + this.getUsername(), {headers: this.headers});
  }

  getBuddies(username: string): Observable<BasicProfile[]>{
    this.headers = new HttpHeaders().set('Content-Type', 'application/json').set('Authorization', 'Bearer ' + this.getToken());
    return this.http.get<BasicProfile[]>(this.url + 'user/buddies/'+ username, {headers: this.headers});
  }

  addABuddy(buddyId: string): Observable<any>{
    this.headers = new HttpHeaders().set('Content-Type', 'application/json').set('Authorization', 'Bearer ' + this.getToken());
    return this.http.put<any>(this.url + 'user/buddy/' + this.getUsername(), buddyId, {headers: this.headers});
  }

  updateProfilePic(newProfilePic: number): Observable<any>{
    this.headers = new HttpHeaders().set('Content-Type', 'application/json').set('Authorization', 'Bearer ' + this.getToken());
    return this.http.put<any>(this.url + 'user/profile-pic/' + this.getUsername(), newProfilePic, {headers: this.headers});
  }

  getPublicProfiles(): Observable<BasicProfile[]>{
    return this.http.get<BasicProfile[]>(this.url + 'user/search/public-profiles');
  }

  getBasicProfile(username: string): Observable<BasicProfile>{
    return this.http.get<BasicProfile>('http://localhost:8081/user/basic-profile/' + username);
  }

  newRequest(requestedUser: string): Observable<any>{
    this.headers = new HttpHeaders().set('Content-Type', 'application/json').set('Authorization', 'Bearer ' + this.getToken());
    return this.http.put<any>(this.url + 'user/request/' + requestedUser, this.getUsername(), {headers: this.headers})
  }

  removeUser(): Observable<any>{
    this.headers = new HttpHeaders().set('Content-Type', 'application/json').set('Authorization', 'Bearer ' + this.getToken());
    return this.http.delete<any>(this.url + 'user/' + this.getUsername(), {headers: this.headers})
  }
}

interface BasicProfile{
  userName: string,
  profilePic: number
}

interface IncomingProfile{
  userName: string,
  password: string,
  profilePicture: number,
  visibility: string,
  buddyNum: number
}



