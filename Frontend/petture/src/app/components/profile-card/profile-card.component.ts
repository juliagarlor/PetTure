import { Component, Input, OnInit } from '@angular/core';
import { PictureServiceService } from 'src/app/services/picture-service.service';

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
    private pictureService: PictureServiceService
  ) { }

  ngOnInit(): void {
    this.pictureService.getImage(this.userProfile.profilePic).subscribe(data => {
      this.retrievedResponse = data;
      this.base64Data = this.retrievedResponse.pic;
      this.profilePic = 'data:image/jpeg;base64,' + this.base64Data;
    })
  }

}
