import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Picture } from 'src/app/models/picture';
import { Post } from 'src/app/models/post';
import { Profile } from 'src/app/models/profile';
import { PictureServiceService } from 'src/app/services/picture-service.service';
import { PostServiceService } from 'src/app/services/post-service.service';
import { UserServiceService } from 'src/app/services/user-service.service';
import { PostCardComponent } from '../post-card/post-card.component';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ProfileComponent implements OnInit {

  profile: Profile = new Profile('perryThePlaTypus', '', 'assets/images/perry.jpg', '', 0, []);
  postList: Post[] = [];

  constructor(
    private pictureService: PictureServiceService,
    private userService: UserServiceService,
    private postService: PostServiceService,
    public dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.userService.getProfile().subscribe(data => {
      this.profile = new Profile(
        data.userName, data.password, data.profilePicture, data.visibility, data.buddyNum, data.pics
      )
      this.postService.getPostsByUser(data.userName).subscribe(result => {
        result.forEach(post => {
          this.postList.push(new Post(post.postId, post.postBody, 
            new Picture(post.picture.picId, post.picture.pictureName, post.picture.userName, post.picture.licks)));
        });
      })
    })
  }

  onFileChanged(event: any): void {
    if (event.target.files && event.target.files[0]) {
      var reader = new FileReader();

      reader.readAsDataURL(event.target.files[0]);

      reader.onload = (event) => {
        let result = event.target?.result?.toString();
        if(result != undefined){
          this.profile.setprofilePicture = result;
          this.userService.updateProfilePic(result);
        }
      }
    }
  }

  openPic(post: Post): void{
    const dialogRef = this.dialog.open(PostCardComponent, {
      width: '599px',
      panelClass: 'dialog'
    });
    dialogRef.componentInstance.post = post;
    dialogRef.componentInstance.logedUser = this.profile.getuserName;
  }
}
