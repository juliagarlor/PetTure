import { Component, OnInit } from '@angular/core';
import { Picture } from 'src/app/models/picture';
import { Post } from 'src/app/models/post';
import { Profile } from 'src/app/models/profile';
import { PictureServiceService } from 'src/app/services/picture-service.service';
import { PostServiceService } from 'src/app/services/post-service.service';
import { UserServiceService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  profile: Profile = new Profile('perryThePlaTypus', '', 'assets/images/perry.jpg', '', 0, []);
  postList: Post[] = [];

  constructor(
    private pictureService: PictureServiceService,
    private userService: UserServiceService,
    private postService: PostServiceService
  ) { }

  ngOnInit(): void {
    this.userService.getProfile().subscribe(data => {
      this.profile = new Profile(
        data.userName, data.password, data.profilePicture, data.visibility, data.buddyNum, data.pics
      )
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

  openPic(postId: number): void{
    this.postService.getPostAndPic(postId);
    // ahora esto pasaselo a un post-card dentro de un dialog o algo así
  }
}
