import { Component, OnInit } from '@angular/core';
import { Picture } from 'src/app/models/picture';
import { Profile } from 'src/app/models/profile';
import { PictureServiceService } from 'src/app/services/picture-service.service';
import { UserServiceService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  profile: Profile = new Profile('perryThePlaTypus', '', 'assets/images/perry.jpg', '', 0, []);

  constructor(
    private pictureService: PictureServiceService,
    private userService: UserServiceService
  ) { }

  ngOnInit(): void {
    this.userService.getProfile().subscribe(data => {
      this.profile = data;
    })

    // this.pictureService.getPicsByUser(username).subscribe(result => {
    //   this.pictureList = result;
    // })
  }

}
