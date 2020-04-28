import { Component, OnInit } from '@angular/core';
import * as Highcharts from 'highcharts';
import { UploadDataService } from '../services/upload-data.service';
import { CompanyService } from '../services/company.service';
import { Company } from '../model/Company';
import * as FileSaver from 'file-saver';
const EXCEL_EXTENSION = '.csv';
import HC_exportData from 'highcharts/modules/export-data';
HC_exportData(Highcharts)

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements OnInit {
  companies: Company[] = [];
  companyOne: string;
  companyTwo: string;
  dataLoaded: Promise<boolean>;
  stockData: any[];
  chart: Highcharts.Chart;
  clickFlag:boolean;
  public options: any = {
    chart: {
      type: 'line',

    },
    title: {
      text: 'Select Company code First'
    },
    credits: {
      enabled: false
    },
    tooltip: {
      formatter: function () {
        return 'x: ' + Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +
          ' y: ' + this.y.toFixed(2);
      }
    },
    xAxis: {
      type: 'datetime',
      labels: {
        formatter: function () {
          return Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.value);
        }
      },
      title:{
        text:"Date And Time"
      }
    },
    responsive: {
      rules: [{
        condition: {
          maxWidth: 500
        },
        chartOptions: {
          legend: {
            layout: 'horizontal',
            align: 'center',
            verticalAlign: 'bottom'
          }
        }
      }]
    }
  }
  constructor(private uploadService: UploadDataService, private companyService: CompanyService) { }

  ngOnInit() {
    this.companyService.getAllComapany().subscribe((response: Company[]) => {
      this.companies = response;
      this.dataLoaded = Promise.resolve(true);
      this.chart = Highcharts.chart('showdata', this.options);
    })
  }

  filterSelectedData(companyCode: string) {
    this.clickFlag=true;
    this.companyOne = companyCode;
    this.options.title.text = "Stock-Details of " + companyCode;
    this.uploadService.getStockDetails(companyCode).subscribe((response: any) => {
      this.stockData = response;
      let data: any[] = [];
      this.stockData.forEach((item) => {
        let point: any[] = [];
        point.push(Date.parse(item.date.split("T", 1) + "T" + item.time));
        point.push(item.currentPrice);
        data.push(point);
        data.sort((n1, n2) => {
          if (n1[0] > n2[0]) {
            return 1;
          } else {
            return -1;
          }
        });
        this.chart.destroy();
        this.chart = Highcharts.chart('showdata', this.options);
        this.chart.addSeries({
          name: companyCode,
          data: data,
          type: "line"
        }, true, true);
        this.chart.redraw()
      });

    });
  }

  downlaodExcel() {
    this.saveAsExcelFile(this.chart.getCSV(), "StockFile");
  }

  private saveAsExcelFile(buffer: any, fileName: string): void {
    const data: Blob = new Blob([buffer], {
      type: "text/csv"
    });
    FileSaver.saveAs(data, fileName + '_export_' + new Date().getTime() + EXCEL_EXTENSION);
  }

}

