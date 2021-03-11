import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
})
export class NewPostComponent implements OnInit {

  caption: string = '';
  uploaded: boolean = false;
  uploadedPic: string | null | ArrayBuffer | undefined = 'assets/images/perry.jpg';

  constructor(
  ) { }

  ngOnInit(): void {
  }

  onFileChanged(event: any): void {
    if (event.target.files && event.target.files[0]) {
      var reader = new FileReader();

      reader.readAsDataURL(event.target.files[0]);

      reader.onload = (event) => {
        this.uploadedPic = event.target?.result;
      }
    }
  }
}
