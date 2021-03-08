import { Component, OnInit } from '@angular/core';
import { Picture } from 'src/app/models/picture';
import { Profile } from 'src/app/models/profile';
import { PictureServiceService } from 'src/app/services/picture-service.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  pictureList: Picture[] = [];
  profile: Profile = new Profile('perryThePlaTypus', '', 'assets/images/perry.jpg', '', 0, 0);

  constructor(
    private pictureService: PictureServiceService
  ) { }

  ngOnInit(): void {
    this.pictureService.getPicsByUser('perryThePlatypus').subscribe(result => {
      this.pictureList = result;
    })
  }

}
