import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  user:string;
  navbarIcon:string;
  constructor(private authenticateService:AuthenticationService) { }

  ngOnInit() {
    this.navbarIcon = "unfold_more";
  }
  logout(){
    this.authenticateService.setLogeInFlag(false);
    this.authenticateService.setLogedInUser("");
    this.authenticateService.setRole("");
  }
  isLogin(){
    this.user = this.authenticateService.getLogedInUser();
    return this.authenticateService.islogin();
  }
  logedInUser(){
    return this.authenticateService.getLogedInUser();
  }
  dropDownButtonClick(){
    if(this.navbarIcon == "unfold_more"){
    this.navbarIcon = "unfold_less";
    }else{
      this.navbarIcon = "unfold_more";
    }
  }
}
