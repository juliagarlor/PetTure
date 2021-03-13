import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormGroupDirective } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { UserServiceService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  form: FormGroup;

  username: FormControl;
  password: FormControl;

  constructor(
    private userService: UserServiceService,
    private dialogRef: MatDialogRef<RegisterComponent>
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
    const user = {userName: this.username.value, password: this.password.value, profilePicture: 'default.jpg', visibility: 'PUBLIC'};
    this.userService.register(user).subscribe(data => {
          form.resetForm();
    this.onNoClick();
    })
  }

  onNoClick(): void{
    this.dialogRef.close();
  }
}
