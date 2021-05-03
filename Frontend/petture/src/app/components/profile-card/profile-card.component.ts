import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PictureServiceService } from 'src/app/services/picture-service.service';
import { UserServiceService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-profile-card',
  templateUrl: './profile-card.component.html',
  styleUrls: ['./profile-card.component.css']
})
export class ProfileCardComponent implements OnInit {

  @Input() userProfile!: {userName:string, profilePic:number};
  profilePic: any;
  retrievedResponse: any;
  base64Data: any;

  constructor(
    private pictureService: PictureServiceService,
    private userService: UserServiceService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.pictureService.getImage(this.userProfile.profilePic).subscribe(data => {
      this.retrievedResponse = data;
      this.base64Data = this.retrievedResponse.pic;
      this.profilePic = 'data:image/jpeg;base64,' + this.base64Data;
    })
  }

  openProfile(username: string): void{
    this.userService.updateProfileToCheck(username);
    this.router.navigateByUrl('/profile');
  }
}
