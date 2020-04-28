import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavBarComponent } from './stock-data/nav-bar/nav-bar.component';
import { FileUploadModule } from 'ng2-file-upload';
import { DataUploadComponent } from './stock-data/data-upload/data-upload.component';
import { LoginComponent } from './stock-data/site/login/login.component';
import { SignupComponent } from './stock-data/site/signup/signup.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HomepageComponent } from './stock-data/homepage/homepage.component';
import { UserDetailsComponent } from './stock-data/site/user-details/user-details.component';
import { CompanyComponent } from './stock-data/company/company.component';
import { CompanyViewComponent } from './stock-data/company-view/company-view.component';
import { ChartComponent } from './stock-data/chart/chart.component';
import { ChartCompareComponent } from './stock-data/chart-compare/chart-compare.component';
@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    DataUploadComponent,
    LoginComponent,
    SignupComponent,
    HomepageComponent,
    UserDetailsComponent,
    CompanyComponent,
    CompanyViewComponent,
    ChartComponent,
    ChartCompareComponent
  ],
  imports: [
    BrowserModule,
    FileUploadModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
