import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(
    private http: HttpClient,
    private cookies: CookieService
  ) { }

  login(user: any): Observable<any>{
    return this.http.post('http://localhost:8080/login', user);
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

  getRequests(userName: string): Observable<BasicProfile[]>{
    return this.http.get<BasicProfile[]>('http://localhost:8080/user/'+ userName + '/requests')
  }
}

interface BasicProfile{
  userName: string,
  profilePic: string
}

// {
//   "username": "perryThePlatypus",
//   "roles": [
//       "USER"
//   ],
//   "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwZXJyeVRoZVBsYXR5cHVzIiwiaWF0IjoxNjE1MzA4NTAyLCJleHAiOjE2MTUzOTQ5MDJ9.5u3iowBIP0j8klTd4Pc8jJYVv_aR-a6LtJPBEXERR1XdSQhaAVLb8CENHEFxA1zLQ0tz8nhx51yeHhZUW6-FHg",
//   "tokenType": "Bearer"
// }
