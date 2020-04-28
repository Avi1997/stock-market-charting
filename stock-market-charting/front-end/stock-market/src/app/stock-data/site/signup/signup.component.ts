import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { User } from '../../model/User';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupForm: FormGroup;
  user: User;
  checkpass: boolean = false;
  userPresent: boolean = false;
  successMsg: boolean;
  
  
  constructor(private userService:UserService) { }

  ngOnInit() {
    this.signupForm = new FormGroup({
      userName: new FormControl(null, [Validators.required, Validators.minLength(4), Validators.maxLength(15)]),
      password: new FormControl(null, [Validators.required, Validators.minLength(4), Validators.maxLength(15)]),
      confirmPassword: new FormControl(null, [Validators.required]),
      contactNo: new FormControl(null, [Validators.required, Validators.minLength(10), Validators.maxLength(10)]),
      emailId: new FormControl(null, [Validators.required, Validators.email]),
      userType:new FormControl()
      });
  }
  checkIfMatchingPasswords() {
    if (this.signupForm.value.password == this.signupForm.value.confirmPassword) {
      this.checkpass = true;
    } else {
      this.checkpass = false;
    }
  }
  changeValue() {
    this.userPresent = false;
  }
  userAdd(signupForm) {
    this.user ={
      username:signupForm.value.userName,
      password:signupForm.value.password,
      contactNumber:signupForm.value.contactNo,
      email:signupForm.value.emailId,
      role:signupForm.value.userType
    }
   this.userService.createUser(this.user).subscribe((response)=>{
    this.successMsg = true;
   },(error)=>{
    this.userPresent = true;
   });
  }
}
