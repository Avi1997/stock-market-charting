import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../model/User';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {
  user:User;
  contactFlag:boolean;
  submitFlag:boolean;
  signupForm: FormGroup;
  passwordFlag:boolean;
  successMsg:boolean;
  constructor(private userService:UserService) { }

  ngOnInit() {
    this.submitFlag=false;
    this.successMsg=false;
    this.passwordFlag = false;
    this.signupForm = new FormGroup({
      password: new FormControl('', [Validators.required, Validators.minLength(4), Validators.maxLength(15)]),
      contactNo: new FormControl('', [Validators.required, Validators.minLength(10), Validators.maxLength(10)])
    });
    this.user ={
      username:"",
      password:"",
      role:null,
      email:"",
      contactNumber:""
    }
    this.contactFlag=false;
    this.userService.getUserDetails().subscribe((response:User)=>{
      this.user = response;
      this.signupForm.setValue({
        password:"****",
        contactNo:response.contactNumber
      });
    });
  }
  changeContactFlag(){
    this.contactFlag = !this.contactFlag;
    this.submitFlag = this.contactFlag;
  }
  changePasswordFlag(){
    this.passwordFlag = !this.passwordFlag;
    this.submitFlag = this.passwordFlag;
    this.signupForm.patchValue({password:""});
  }
  changeDetails(){
    if(this.contactFlag){
    this.user.contactNumber = this.signupForm.value.contactNo;
    }
    if(this.passwordFlag){
      this.user.password = this.signupForm.value.password;
    }
    this.userService.updateUserDetails(this.user).subscribe((response:boolean)=>{
      this.successMsg =response;
    })

  }
  changeFlag(){
    this.successMsg = false;
    this.passwordFlag =false;
    this.contactFlag = false;
  }

}
