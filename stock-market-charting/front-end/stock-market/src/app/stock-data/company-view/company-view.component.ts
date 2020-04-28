import { Component, OnInit, Input } from '@angular/core';
import { Company } from '../model/Company';
import { CompanyService } from '../services/company.service';
import { Exchange } from '../model/Exchange';

@Component({
  selector: 'app-company-view',
  templateUrl: './company-view.component.html',
  styleUrls: ['./company-view.component.css']
})
export class CompanyViewComponent implements OnInit {
  @Input() companies:Company[];
  @Input() emptyFlag:boolean;
  @Input() searchFlag:boolean;
  constructor(private companyService:CompanyService) {
   }
  latestArray:Exchange[]=[];
  ngOnInit() {
  }

  latestStockPrice(id){
    this.companyService.getLatestPrice(id).subscribe((response:Exchange[])=>{
      this.latestArray = response;
    })
  }

}
