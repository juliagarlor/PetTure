import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Picture } from 'src/app/models/picture';
import { Post } from 'src/app/models/post';
import { PictureServiceService } from 'src/app/services/picture-service.service';
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
  pictures: any[] = [];
  username: string = 'soyYoLaQueSigueAquí';

  retrievedResponse: any;
  base64Data: any;
  image: any;
  constructor(
    private userService: UserServiceService,
    private postService: PostServiceService,
    private pictureService: PictureServiceService,
    public dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.username = this.userService.getUsername();
    this.postService.getPublicPosts().subscribe(data => {
      data.forEach(post => {
        this.postList.push(new Post(post.postId, post.postBody, post.pictureId, post.userName, post.licks));
        this.pictureService.getImage(post.pictureId).subscribe(result => {
          this.retrievedResponse = result;
          this.base64Data = this.retrievedResponse.pic;
          this.pictures.push('data:image/jpeg;base64,' + this.base64Data);
        })
      });
    })
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(NewPostComponent, {
      width: '600px'
    });
    dialogRef.componentInstance.username = this.username;

    dialogRef.afterClosed().subscribe(result => {
      if(result != undefined){
        this.postService.addNewPost(result).subscribe(data => {
          this.postList.push(new Post(data.postId, data.postBody, data.pictureId, data.userName, data.licks));
            
            this.pictureService.getImage(data.pictureId).subscribe(res => {
              this.retrievedResponse = res;
              this.base64Data = this.retrievedResponse.pic;
              this.pictures.push('data:image/jpeg;base64,' + this.base64Data);
            })
        });
      }
    });
  }
}
