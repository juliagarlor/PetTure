import { animate, state, style, transition, trigger } from '@angular/animations';
import { Component, Input, OnInit } from '@angular/core';
import { Commentary } from 'src/app/models/commentary';
import { Picture } from 'src/app/models/picture';
import { Post } from 'src/app/models/post';
import { PictureServiceService } from 'src/app/services/picture-service.service';
import { PostServiceService } from 'src/app/services/post-service.service';
import { UserServiceService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-post-card',
  templateUrl: './post-card.component.html',
  styleUrls: ['./post-card.component.css'],
  animations: [ 
    trigger('myInsertRemoveTrigger', [
      transition(':enter', [
        style({ opacity: 0 }),
        animate('300ms', style({ opacity: 1 })),
      ])
      // ,
      // transition(':leave', [
      //   animate('300ms 500ms', style({ opacity: 0 }))
      // ])
    ]),

    trigger('cardFlip', [
    state('default', style({
      transform: 'rotateY(180deg)'
    })),
    state('flipped', style({
      transform: 'none'
    })),
    transition('default => flipped', [
      animate('400ms')
    ]),
    transition('flipped => default', [
      animate('200ms')
    ])
  ])]
})
export class PostCardComponent implements OnInit {

  flipped: string = 'default';
  lick: string = 'Meow';
  lickList: string[] = ['Meow', 'Meow', 'Woof', 'Woof', 'What the fox say', 'Quack', 'Quack'];
  isShown: boolean = false;
  logedUser: string = 'bestFriendForever';
  // @Input() post!: Post;

  post: Post = new Post(0, 'holi', new Picture(0, 'assets/images/garfield.jpg', 'garfield', 0));
  comments: Commentary[] = [new Commentary(0, 'bestFriendForever', 'hola que haseh', this.post.id)];
  newComment: string = '';

  constructor(
    private pictureService: PictureServiceService,
    private userService: UserServiceService,
    private postService: PostServiceService
  ) { }

  ngOnInit(): void {
    // this.logedUser = this.userService.getUsername();
  }

  addALick():void{
    if(!this.isShown){
      const random = Math.floor(Math.random() * this.lickList.length);
      this.lick = this.lickList[random];

      this.post.picture.licks++;
      this.pictureService.addALick(this.post.picture.id);
      this.isShown = true;
    }
  }

  addAComment(): void{
    let commentToAdd = new Commentary(0, this.logedUser, this.newComment, this.post.id);
    this.comments.push(commentToAdd);
    // Call the service to add that comment to the database

    this.newComment = '';
  }

  cardClicked(): void{
    if (this.flipped === 'default') {
      this.flipped = 'flipped';
    } else {
      this.flipped = 'default';
    }
  }
}
