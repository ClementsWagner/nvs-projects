import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserRest } from 'src/app/service/user-rest.service';
import { User } from "src/app/model/user";
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent{



  signUpForm = new FormGroup({
    email: new FormControl("", [Validators.required, Validators.email]),
    password: new FormControl("", [Validators.required, Validators.minLength(8)]),
    confirmPassword: new FormControl("", Validators.required),
    });

  constructor(private userApi: UserRest, private auth: AuthService, private router: Router) {

  }

  signUp(){
    console.log("Signup pressed\n");

    if(this.checkIfMatchingPasswords()){
      this.createUser()
    }
  }

  createUser(){
    let newUser: User
    this.userApi.createUser(this.getFormfield("email")?.value, this.getFormfield("password")?.value)
      .subscribe(value => {
        console.log(`User created: ${value} \n`)
        this.auth.login(this.getFormfield("email")?.value, this.getFormfield("password")?.value)
        console.log("User authenticated")
        this.router.navigateByUrl("/cars")
      })
  }

  checkIfMatchingPasswords(){
    var password = this.getFormfield('passwordKey')?.value
    var confirmPassword = this.getFormfield('passwordConfirmationKey')?.value
    return password==confirmPassword
  }

  getFormfield(fieldName: string){
    return this.signUpForm.get(fieldName)
  }

  isFieldValid(fieldName: string){
    return !this.getFormfield(fieldName)?.valid && (this.getFormfield(fieldName)?.touched && this.getFormfield(fieldName)?.dirty)
  }

}
