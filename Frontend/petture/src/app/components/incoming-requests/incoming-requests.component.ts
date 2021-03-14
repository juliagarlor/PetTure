import { Component, OnInit } from '@angular/core';
import { UserServiceService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-incoming-requests',
  templateUrl: './incoming-requests.component.html',
  styleUrls: ['./incoming-requests.component.css']
})
export class IncomingRequestsComponent implements OnInit {

  userRequests: {userName: string, profilePic: number}[] = [];

  constructor(
    private userService: UserServiceService
  ) { }

  ngOnInit(): void {
    this.userService.getRequests().subscribe(data => {
      data.forEach(profile => {
        this.userRequests.push({userName: profile.userName, profilePic: profile.profilePic})
      });
    })
  }

  addBuddy(buddy: string, index: number){
    this.userService.addABuddy(buddy).subscribe(data => {
          this.userRequests.splice(index, 1);
    });
  }

  removeRequest(request: string, index: number){
    this.userService.removeRequest(request).subscribe(data => {
          this.userRequests.splice(index, 1);
    });
  }
}
