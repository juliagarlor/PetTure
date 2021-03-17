import { Component, OnInit } from '@angular/core';
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
  suggestedUsers: {userName: string, profilePic: number}[] = [];

  constructor(
    private userService: UserServiceService
  ) { }

  ngOnInit(): void {
    this.loggedUser = this.userService.getUsername();
    this.userService.getPublicProfiles().subscribe(data => {
      data.forEach(profile => {
        this.suggestedUsers.push({userName: profile.userName, profilePic: profile.profilePic});
      });
    })
  }

  sendRequest(requestTo: string, i: number){
    // Por ahora no uso el indice para nada
    this.userService.newRequest(requestTo).subscribe(data => {
    })
  }

  search(event: KeyboardEvent):void{
    if(event.key == 'Enter'){
      this.userService.getBasicProfile(this.value).subscribe(data => {
        this.suggestedUsers = [];
        this.suggestedUsers.push({userName: data.userName, profilePic: data.profilePic})
      })
    }
  }
}
