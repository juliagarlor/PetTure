import { Component, OnInit } from '@angular/core';
import { Picture } from 'src/app/models/picture';
import { PictureServiceService } from 'src/app/services/picture-service.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  pictureList: Picture[] = [];

  constructor(
    private pictureService: PictureServiceService
  ) { }

  ngOnInit(): void {
    this.pictureService.getPicsByUser('perryThePlatypus').subscribe(result => {
      this.pictureList = result;
    })
  }

}
