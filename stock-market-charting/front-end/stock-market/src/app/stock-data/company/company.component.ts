import { Component, OnInit } from '@angular/core';
import { CompanyService } from '../services/company.service';
import { Company } from '../model/Company';
import { empty } from 'rxjs';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit {
  companies:Company[];
  companiesFilter:Company[];
  search:string;
  emptyFlag:boolean;
  searchFlag:boolean;
  constructor(private companyService:CompanyService) { }

  ngOnInit() {
    
    this.companyService.getAllComapany().subscribe((response:Company[])=>{
      this.companies = response;
      this.companiesFilter = response;
    })
  }
  seachCompany(){
    this.searchFlag = true;
      this.companies = this.companiesFilter;
      this.companies = this.companies.filter((company:Company)=>
       company.name.toLocaleLowerCase().indexOf(this.search.toLocaleLowerCase())!=-1);
       if(this.companies.length==0){
        this.companies = this.companiesFilter;
        this.companies = this.companies.filter((company:Company)=>
         company.companyCode.toString().toLocaleLowerCase().indexOf(this.search.toLocaleLowerCase())!=-1);
        if(this.companies.length==0){
         this.emptyFlag =true;
        }else{
          this.emptyFlag=false;
        }
       }else{
         this.emptyFlag=false;
       }
  }

}
