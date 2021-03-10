import { Component, OnInit } from '@angular/core';
import { UserServiceService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-incoming-requests',
  templateUrl: './incoming-requests.component.html',
  styleUrls: ['./incoming-requests.component.css']
})
export class IncomingRequestsComponent implements OnInit {

  userRequests: {userName: string, profilePic: string}[] = [];

  constructor(
    private userService: UserServiceService
  ) { }

  ngOnInit(): void {
    this.userService.getRequests().subscribe(data => {
      this.userRequests = data;
    })
  }

  addBuddy(userName: string){
    console.log('En construcción')
  }

  removeRequest(userName: string){
    console.log('En construcción')
  }
}
