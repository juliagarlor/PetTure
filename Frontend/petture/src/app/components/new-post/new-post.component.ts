import { Component, EventEmitter, Inject, Input, OnInit, Output } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Picture } from 'src/app/models/picture';
import { Post } from 'src/app/models/post';
import { PictureServiceService } from 'src/app/services/picture-service.service';
import { DialogData } from '../home/home.component';

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
})
export class NewPostComponent implements OnInit {

  caption: string = '';
  uploaded: boolean = false;

  selectedFile: any;
  imgURL: any;
  receivedImageData: any;
  base64Data: any;
  convertedImage: any;

  newPost: {} = {};
  @Input() username!: string;
  @Output() newPostEvent = new EventEmitter();

  constructor(
    private pictureService: PictureServiceService,
    public dialogRef: MatDialogRef<NewPostComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

  ngOnInit(): void {
    this.imgURL = 'assets/images/default.jpg'
  }

  onFileChanged(event: any): void {

    this.selectedFile = event.target.files[0];
    let reader = new FileReader();
    reader.readAsDataURL(this.selectedFile);
    reader.onload = (event2) => {
      this.imgURL = reader.result;
    };
  }

  postNew(): void{
    const uploadData = new FormData();
    uploadData.append('myFile', this.selectedFile, this.selectedFile.name);
    
    this.pictureService.upload(uploadData).subscribe(res => {
      this.receivedImageData = res;
      this.base64Data = this.receivedImageData.pic;
      this.convertedImage = 'data:image/jpeg;base64,' + this.base64Data;

      this.newPost = {postBody: this.caption, pictureId: this.receivedImageData.picId, userName: this.username};
      this.dialogRef.close(this.newPost);
    }, err => console.log('Error occured during saving: ' + err));
  }
}
