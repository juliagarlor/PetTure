import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { UserServiceService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-buddies',
  templateUrl: './buddies.component.html',
  styleUrls: ['./buddies.component.css']
})
export class BuddiesComponent implements OnInit {
  username: string = '';
  loggedUser: string = '';
  buddies: {userName: string, profilePic: number, requestable: boolean}[] = [];
  unrequestable: string[] = [];

  constructor(
    private userService: UserServiceService
  ) { }

  ngOnInit(): void {
    this.loggedUser = this.userService.getUsername();
    this.userService.currentProfileToCheck.subscribe(profileName => {
      this.username = profileName;

    // this.username = this.loggedUser;
      this.userService.getBuddies(this.loggedUser).subscribe(myBuddies => {
        myBuddies.forEach(buddy => {
          this.unrequestable.push(buddy.userName);
        })
        this.userService.getRequests().subscribe(requests => {
          requests.forEach(requests => {
            this.unrequestable.push(requests.userName);
          })
          this.userService.getRequested().subscribe(requested => {
            requested.forEach(requestedUser => {
              this.unrequestable.push(requestedUser);
            })

            this.userService.getBuddies(this.username).subscribe(usersBuddies => {
              usersBuddies.forEach(buddy => {
                this.buddies.push({userName: buddy.userName, profilePic: buddy.profilePic, requestable: !this.unrequestable.includes(buddy.userName)});
              })
            })
          })
        })
      })
    })
  }

  sendRequest(requestTo: string, i: number){
    this.userService.newRequest(requestTo).subscribe(data => {
      this.buddies[i].requestable = false;
    })
  }
}
