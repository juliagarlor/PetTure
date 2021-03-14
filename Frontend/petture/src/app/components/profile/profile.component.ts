import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { BehaviorSubject } from 'rxjs';
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
  profile: Profile = new Profile('perryThePlaTypus', '', 1, '', 0);
  postList: Post[] = [];
  pictures: any[] = [];

  public selectedFile: any;
  imgURL: any;
  receivedImageData: any;
  base64Data: any;
  convertedImage: any;

  constructor(
    private pictureService: PictureServiceService,
    private userService: UserServiceService,
    private postService: PostServiceService,
    public dialog: MatDialog
  ) {
   }

  ngOnInit(): void {
    this.userService.getProfile().subscribe(data => {
      this.profile = new Profile(
        data.userName, data.password, data.profilePicture, data.visibility, data.buddyNum)
      this.postService.getPostsByUser(data.userName).subscribe(result => {
        result.forEach(post => {
          this.postList.push(new Post(post.postId, post.postBody, post.pictureId, post.userName, post.licks));
          this.pictures.push(this.getImage(post.pictureId));
        });
      })
    })
  }

  onFileChanged(event: any): void {
    console.log('Event:')
    console.log(event)

    this.selectedFile = event.target.files[0];

    // Displaying the selected image:
    let reader = new FileReader();
    reader.readAsDataURL(this.selectedFile);
    reader.onload = (event2) => {
      this.imgURL = reader.result;
    };

    // Uploading image:
    const uploadData = new FormData();
    uploadData.append('myFile', this.selectedFile, this.selectedFile.name);

    this.userService.updateProfilePic(uploadData).subscribe(data => {
      console.log('Result:')
      console.log(data);
      this.receivedImageData = data;
      this.base64Data = this.receivedImageData.pic;
      this.convertedImage = 'data:image/jpeg;base64,' + this.base64Data;
    }, err => console.log('Error Occured during saving: ' + err)
    );
  }

  openPic(post: Post): void{
    const dialogRef = this.dialog.open(PostCardComponent, {
      width: '599px',
      panelClass: 'dialog'
    });
    dialogRef.componentInstance.post = post;
    dialogRef.componentInstance.logedUser = this.profile.getuserName;
  }

  getImage(picId: number): any{
    let image: any;
    let retrievedResponse: any;

    this.pictureService.getImage(picId)
    .subscribe(
      res => {
        //Necessary to process the image
        retrievedResponse = res;
        this.base64Data = retrievedResponse.picByte;
        image = 'data:image/jpeg;base64,' + this.base64Data;
        return image;
      }
    );
  }
}
