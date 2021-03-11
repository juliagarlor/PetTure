import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Picture } from '../models/picture';

@Injectable({
  providedIn: 'root'
})
export class PictureServiceService {

  url: string = 'http://localhost:8080/';

  constructor(
    private http: HttpClient
  ) { }

  getPicsByUser(userName: string): Observable<Picture[]>{
    return this.http.get<Picture[]>(this.url + 'pics/' + userName);
  }

  addALick(picId: number):Observable<any>{
    return this.http.put<any>(this.url + 'pic/' + picId, '');
  }
}
