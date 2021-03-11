import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Picture } from 'src/app/models/picture';
import { Post } from 'src/app/models/post';
import { PostServiceService } from 'src/app/services/post-service.service';
import { UserServiceService } from 'src/app/services/user-service.service';
import { NewPostComponent } from '../new-post/new-post.component';

export interface DialogData{
  newPost: Post;
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  postList: Post[] = [];
  username: string = 'soyYoLaQueSigueAquí';
  constructor(
    private userService: UserServiceService,
    private postService: PostServiceService,
    public dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.username = this.userService.getUsername();
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
    dialogRef.componentInstance.username = this.username;
    console.log('dialog open');

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      if(result != undefined){
        this.postList.push(result);
        // llama al service para que incluya el nuevo post
      }
    });
  }
}
