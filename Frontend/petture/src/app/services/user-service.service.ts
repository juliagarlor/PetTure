import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(
    private http: HttpClient
  ) { }

  getRequests(userName: string): Observable<BasicProfile[]>{
    return this.http.get<BasicProfile[]>('http://localhost:8080/user/'+ userName + '/requests')
  }
}

interface BasicProfile{
  userName: string,
  profilePic: string
}
