import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Picture } from '../models/picture';

@Injectable({
  providedIn: 'root'
})
export class PictureServiceService {

  constructor(
    private http: HttpClient
  ) { }

  getPicsByUser(userName: string): Observable<Picture[]>{
    return this.http.get<Picture[]>('http://localhost:8080/pics/' + userName);
  }

  addALick(picId: number):Observable<any>{
    return this.http.put<any>("http://localhost:8080/pic/" + picId, '');
  }
}
