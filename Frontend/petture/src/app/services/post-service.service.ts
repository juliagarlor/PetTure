import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Picture } from '../models/picture';
import { Post } from '../models/post';

@Injectable({
  providedIn: 'root'
})
export class PostServiceService {

  constructor(
    private http: HttpClient
  ) { }

  getPublicPosts():Observable<IncomingPost[]>{
    return this.http.get<IncomingPost[]>("http//localhost:8080/post/view/public");
  }
}

interface IncomingPost{
  postId: number,
  postBody: string,
  picture: Picture
}
