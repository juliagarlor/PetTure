import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Profile } from 'src/app/models/profile';
import { UserServiceService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  value = '';
  loggedUser: string = ''; 
  suggestedUsers: {userName: string, profilePic: number, requestable: boolean}[] = [];
  unrequestable: string[] = [];

  constructor(
    private userService: UserServiceService
  ) { }

  ngOnInit(): void {
    this.loggedUser = this.userService.getUsername();
    this.unrequestable.push(this.loggedUser);
    this.userService.getBuddies(this.loggedUser).subscribe(buddies => {
      buddies.forEach(buddy => {
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

          this.userService.getPublicProfiles().subscribe(data => {
            data.forEach(profile => {
              this.suggestedUsers.push({userName: profile.userName, profilePic: profile.profilePic, requestable: !this.unrequestable.includes(profile.userName)});
            });
          })
        })
      })
    })
  }

  sendRequest(requestTo: string, i: number){
    this.userService.newRequest(requestTo).subscribe(data => {
      this.suggestedUsers[i].requestable = false;
    })
  }

  search(event: KeyboardEvent):void{
    if(event.key == 'Enter'){
      this.userService.getBasicProfile(this.value).subscribe(data => {
        this.suggestedUsers = [];
        this.suggestedUsers.push({userName: data.userName, profilePic: data.profilePic, requestable: !this.unrequestable.includes(data.userName)})
      })
    }
  }
}
