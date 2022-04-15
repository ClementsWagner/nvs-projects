import { HttpClient } from '@angular/common/http';
import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';


@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent{
  password: string = "";
  showSpinner: any;
  email: string = "";

  constructor(private authService: AuthService, private router: Router) {
  }

  login() {
    this.authService.login(this.email, this.password);
    this.router.navigateByUrl("/cars")
  }
}
