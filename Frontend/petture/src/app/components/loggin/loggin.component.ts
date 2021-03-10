import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { UserServiceService } from 'src/app/services/user-service.service';
import { RegisterComponent } from '../register/register.component';

@Component({
  selector: 'app-loggin',
  templateUrl: './loggin.component.html',
  styleUrls: ['./loggin.component.css']
})
export class LogginComponent implements OnInit {

  form: FormGroup;

  username: FormControl;
  password: FormControl;

  constructor(
    private userService: UserServiceService,
    private router: Router,
    public dialog: MatDialog
  ) { 
    this.username = new FormControl('', [Validators.required]);
    this.password = new FormControl('', [Validators.required]);

    this.form = new FormGroup({
      username: this.username,
      password: this.password
    });
  }

  ngOnInit(): void {
  }

  onSubmit(form: FormGroupDirective): void {
    const user = {username: this.username.value, password: this.password.value};
    console.log(user);
    this.userService.login(user).subscribe(data => {
      this.userService.setToken(data.accessToken, data.username);
      this.router.navigateByUrl('/home');
    }, error => {
      // cambia esto cuando puedas
      alert(error);
    });
    form.resetForm();
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(RegisterComponent, {
      width: '40%'
    });
  }
}
