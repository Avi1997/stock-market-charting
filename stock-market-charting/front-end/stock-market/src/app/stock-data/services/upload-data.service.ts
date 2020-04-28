import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class UploadDataService {
  baseUrl:string;
  constructor(private httpClient:HttpClient,private authenticationService:AuthenticationService) {
    this.baseUrl =environment.baseUrl;
   }
  uploadFile(formData){
    let token = "Bearer " + this.authenticationService.getToken();
      return this.httpClient.post(this.baseUrl+"/excel-upload-service/stock-market/upload-stock-data",formData);
  }
  getSummary(){
    let token = "Bearer " + this.authenticationService.getToken();
    const httpOption = {
      headers: new HttpHeaders({
        'content-type': 'application/json',
        'Authorization': token
      })
    };
    return this.httpClient.get(this.baseUrl+"/excel-upload-service/stock-market/summary",httpOption);
  }

  getStockDetails(companyCode){
    let token = "Bearer " + this.authenticationService.getToken();
    const httpOption = {
      headers: new HttpHeaders({
        'content-type': 'application/json',
        'Authorization': token
      })
    };
    return this.httpClient.get(this.baseUrl+"/excel-upload-service/stock-market/stock-details/"+companyCode,httpOption);
  }
}
