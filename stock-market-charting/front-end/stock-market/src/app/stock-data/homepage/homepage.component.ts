import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  fileName:string;
  constructor(private authenticateService:AuthenticationService) {
    this.fileName="student";
  }
   

  ngOnInit() {
  }

  isLogin(){
  return this.authenticateService.islogin();  
  }

  getRole(){
    return this.authenticateService.getRole();
  }

}
