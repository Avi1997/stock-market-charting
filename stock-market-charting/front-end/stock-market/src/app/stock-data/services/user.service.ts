import { Injectable } from '@angular/core';
import { User } from '../model/User';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseUrl: string;

  constructor(private httpClient:HttpClient,private authenticationService:AuthenticationService) { 
    this.baseUrl = environment.baseUrl;
  }
  createUser(user) {
    let usernew: User = user;
    return this.httpClient.post(this.baseUrl+"/authentication-service/stock-market/sign-up", usernew);
  }

  getUserDetails(){
    let token = "Bearer " + this.authenticationService.getToken();
    const httpOption = {
      headers: new HttpHeaders({
        'content-type': 'application/json',
        'Authorization': token
      })
    };
    return this.httpClient.get(this.baseUrl+"/authentication-service/stock-market/user/"+ this.authenticationService.getLogedInUser(),httpOption)
  }
  updateUserDetails(user){
    let token = "Bearer " + this.authenticationService.getToken();
    const httpOption = {
      headers: new HttpHeaders({
        'content-type': 'application/json',
        'Authorization': token
      })
    };
    return this.httpClient.post(this.baseUrl+"/authentication-service/stock-market/user",user,httpOption)
  }
}
