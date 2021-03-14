import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserServiceService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  logged: boolean = true;

  constructor(
    private userService: UserServiceService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.logged = this.userService.getToken().length > 0;
  }

  signOut(): void{
    this.userService.signOut();
    this.router.navigateByUrl('/');
  }

}
