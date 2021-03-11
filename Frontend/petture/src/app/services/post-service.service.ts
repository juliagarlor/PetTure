import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Picture } from '../models/picture';
import { Post } from '../models/post';

@Injectable({
  providedIn: 'root'
})
export class PostServiceService {

  url: string = 'http//localhost:8080/';

  constructor(
    private http: HttpClient
  ) { }

  getPublicPosts():Observable<IncomingPost[]>{
    return this.http.get<IncomingPost[]>(this.url + 'post/view/public');
  }

  addNewPost(newPost: any): Observable<any>{
    return this.http.post<any>(this.url + '/post', newPost);
  }

  addAComment(newComment: any): Observable<any>{
    return this.http.post<any>(this.url + 'commentary', newComment);
  }

  getPostAndPic(postId: number): Observable<IncomingPost>{
    return this.http.get<IncomingPost>(this.url + 'post/view/' + postId)
  }
}

interface IncomingPost{
  postId: number,
  postBody: string,
  picture: Picture
}
