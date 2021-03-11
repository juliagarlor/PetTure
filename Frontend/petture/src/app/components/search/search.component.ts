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
  // haz que traiga los 5 primeros profiles publicos y guardalos aqui:
  suggestedUsers: {username: string, profilePicture: string}[] = [];

  constructor(
    private userService: UserServiceService
  ) { }

  ngOnInit(): void {
    this.loggedUser = this.userService.getUsername();
  }

}
