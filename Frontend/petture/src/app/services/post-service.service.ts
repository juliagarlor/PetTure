import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Picture } from '../models/picture';
import { Post } from '../models/post';
import { UserServiceService } from './user-service.service';

@Injectable({
  providedIn: 'root'
})
export class PostServiceService {

  url: string = 'http://localhost:8080/';
  headers = new HttpHeaders();

  constructor(
    private http: HttpClient,
    private userService: UserServiceService
  ) {
  }

  getPublicPosts():Observable<IncomingPost[]>{
    return this.http.get<IncomingPost[]>(this.url + 'post/view/public');
  }

  addNewPost(newPost: any): Observable<IncomingPost>{
    this.headers = new HttpHeaders().set('Content-Type', 'application/json').set('Authorization', 'Bearer ' + this.userService.getToken());
    return this.http.post<IncomingPost>(this.url + 'post', newPost, {headers: this.headers});
  }

  addAComment(newComment: any): Observable<any>{
    this.headers = new HttpHeaders().set('Content-Type', 'application/json').set('Authorization', 'Bearer ' + this.userService.getToken());
    return this.http.post<any>(this.url + 'commentary', newComment, {headers: this.headers});
  }

  addALick(postId: number): Observable<any>{
    this.headers = new HttpHeaders().set('Content-Type', 'application/json').set('Authorization', 'Bearer ' + this.userService.getToken());
    return this.http.put<any>(this.url + '/licks/' + postId, '', {headers: this.headers});
  }

  getPostAndPic(postId: number): Observable<IncomingPost>{
    return this.http.get<IncomingPost>(this.url + 'post/view/' + postId)
  }

  getCommentsInPost(postId: number): Observable<IncomingComment[]>{
    return this.http.get<IncomingComment[]>(this.url + 'commentaries/' + postId)
  }

  getPostsByUser(username: string): Observable<IncomingPost[]>{
    return this.http.get<IncomingPost[]>(this.url + 'post/view/by-user/' + username);
  }
}

interface IncomingPost{
  postId: number,
  postBody: string,
  pictureId: number,
  userName: string,
  licks: number
}

interface IncomingComment{
  commentaryId: number,
  userName: string,
  commentBody: string,
  postId: number
}
