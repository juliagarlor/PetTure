import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';
import { Picture } from '../models/picture';
import { Profile } from '../models/profile';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(
    private http: HttpClient,
    private cookies: CookieService
  ) { }

  login(user: any): Observable<any>{
    return this.http.post('http://localhost:8080/user/auth/login', user);
  }

  setToken(token: string, username: string){
    this.cookies.set("token", token);
    this.cookies.set("username", username);
    console.log(username);
    console.log(this.cookies.get("username"));
  }

  getToken(){
    return this.cookies.get("token");
  }

  getUsername(){
    return this.cookies.get("username");
  }

  getProfile(): Observable<IncomingProfile>{
    return this.http.get<IncomingProfile>('http://localhost:8080/user/search/' + this.getUsername());
  }

  getRequests(): Observable<BasicProfile[]>{
    return this.http.get<BasicProfile[]>('http://localhost:8080/user/requests/'+ this.getUsername());
  }

  removeRequest(requestId: string): Observable<any>{
    return this.http.put<any>('http://localhost:8080/user/remove/request/' + this.getUsername, requestId);
  }

  addABuddy(buddyId: string): Observable<any>{
    return this.http.put<any>('http://localhost:8080/user/buddy/' + this.getUsername, buddyId);
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



