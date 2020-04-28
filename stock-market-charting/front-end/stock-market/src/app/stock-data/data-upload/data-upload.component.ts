import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { FileUploader } from 'ng2-file-upload';
import { HttpClient } from '@angular/common/http';
import { Summary } from '../model/Summary';
import { UploadDataService } from '../services/upload-data.service';

@Component({
  selector: 'app-data-upload',
  templateUrl: './data-upload.component.html',
  styleUrls: ['./data-upload.component.css']
})
export class DataUploadComponent implements OnInit {
  summary:Summary;
  uploadFlag:boolean=false;
  invalidFormatFlag: boolean;
  constructor(private uploadService:UploadDataService) { }

  ngOnInit() {
    this.summary={
      noOfRecords:null,
      companyName:"",
      minDate:null,
      maxDate:null,
      stockExchange:""
    }
  }
  
  fileChange(event) {
    let fileList: FileList = event.target.files;
    if (fileList.length > 0) {
      let file: File = fileList[0];
      let formData: FormData = new FormData();
      formData.append('uploadFile', file, file.name);     
      this.uploadService.uploadFile(formData).subscribe((response)=>{
                this.uploadFlag =true
                this.getSummary();
              },(error)=>{
                this.invalidFormatFlag=true;
              }
        
        )
    }
  } 

  getSummary(){
    this.uploadService.getSummary().subscribe((response:Summary)=>{
      this.summary=response;
    });
  }

}
