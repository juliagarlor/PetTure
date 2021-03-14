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

  getPicsByUser(userName: string): Observable<Picture[]>{
    return this.http.get<Picture[]>(this.url + 'pics/' + userName);
  }

  addALick(picId: number):Observable<any>{
    this.headers = new HttpHeaders().set('Content-Type', 'application/json').set('Authorization', 'Bearer ' + this.userService.getToken());
    return this.http.put<any>(this.url + 'pic/' + picId, '', {headers: this.headers});
  }

  upload(file: FormData): Observable<any>{
    console.log('uploadData in upload:')
    console.log(file.get('myFile'));
    this.headers = new HttpHeaders().set('Content-Type', 'multipart/form-data').set('Authorization', 'Bearer ' + this.userService.getToken());
    return this.http.post<any>('http://localhost:8082/pic', file)
  }

  getImage(id: number): Observable<FormData> {
    return this.http.get<FormData>(this.url + 'pic/' + id);
  }
}
