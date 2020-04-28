import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthenticationService } from './authentication.service';
import { HttpHeaders, HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {
  baseUrl: string;
  constructor(private authenticationService:AuthenticationService,private httpClient:HttpClient) {
    this.baseUrl = environment.baseUrl;
   }

   getAllComapany(){
    let token = "Bearer " + this.authenticationService.getToken();
    const httpOption = {
      headers: new HttpHeaders({
        'content-type': 'application/json',
        'Authorization': token
      })
    };
    return this.httpClient.get(this.baseUrl+"/company-service/stock-market/companies",httpOption);
   }
   getLatestPrice(id){
    let token = "Bearer " + this.authenticationService.getToken();
    const httpOption = {
      headers: new HttpHeaders({
        'content-type': 'application/json',
        'Authorization': token
      })
    };
    return this.httpClient.get(this.baseUrl+"/excel-upload-service/stock-market/stock-exchange-list/"+id,httpOption);
   }
}
