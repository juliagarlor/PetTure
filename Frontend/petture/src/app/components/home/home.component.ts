import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Post } from 'src/app/models/post';
import { PostServiceService } from 'src/app/services/post-service.service';
import { NewPostComponent } from '../new-post/new-post.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  postList: Post[] = [];
  constructor(
    private postService: PostServiceService,
    public dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.postService.getPublicPosts().subscribe(data => {
      data.forEach(post => {
        this.postList.push(new Post(post.postId, post.postBody, post.picture));
      });
    })
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(NewPostComponent, {
      width: '600px'
    });
    console.log('dialog open')
  }

}
