import { Component, EventEmitter, Inject, Input, OnInit, Output } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Picture } from 'src/app/models/picture';
import { Post } from 'src/app/models/post';
import { DialogData } from '../home/home.component';

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
})
export class NewPostComponent implements OnInit {

  caption: string = '';
  uploaded: boolean = false;
  uploadedPic: string = 'assets/images/perry.jpg';
  newPost: Post = new Post(0, '', new Picture(0, '', '', 0));
  @Input() username!: string;
  @Output() newPostEvent = new EventEmitter();

  constructor(
    public dialogRef: MatDialogRef<NewPostComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

  ngOnInit(): void {
  }

  onFileChanged(event: any): void {
    if (event.target.files && event.target.files[0]) {
      var reader = new FileReader();

      reader.readAsDataURL(event.target.files[0]);

      reader.onload = (event) => {
        let result = event.target?.result?.toString();
        if(result != undefined){
          this.uploadedPic = result;
          console.log(this.uploadedPic)
        }
      }
    }
  }

  postNew(): void{
    console.log(this.caption);
    console.log(this.username);
    let newPic = new Picture(0, this.uploadedPic, this.username, 0);
    this.newPost = new Post(0, this.caption, newPic);
    this.dialogRef.close(this.newPost);
  }
}
