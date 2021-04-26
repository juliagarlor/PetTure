import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Picture } from '../models/picture';
import { UserServiceService } from './user-service.service';

@Injectable({
  providedIn: 'root'
})
export class PictureServiceService {

  url: string = 'http://localhost:8080/';
  headers = new HttpHeaders();

  constructor(
    private http: HttpClient,
    private userService: UserServiceService
  ) { }

  upload(file: FormData): Observable<any>{
    // this.headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.userService.getToken());
    return this.http.post<any>('http://localhost:8082/pic', file
    // , {headers: this.headers}
    )
  }

  getImage(id: number): Observable<FormData> {
    // this.headers = new HttpHeaders().set('Content-Type', 'multipart/form-data').set('Authorization', 'Bearer ' + this.userService.getToken());
    return this.http.get<FormData>('http://localhost:8082/pic/' + id);
  }
}
