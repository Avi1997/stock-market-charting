import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './stock-data/site/login/login.component';
import { SignupComponent } from './stock-data/site/signup/signup.component';
import { HomepageComponent } from './stock-data/homepage/homepage.component';
import { UserDetailsComponent } from './stock-data/site/user-details/user-details.component';
import { DataUploadComponent } from './stock-data/data-upload/data-upload.component';
import { CompanyComponent } from './stock-data/company/company.component';
import { ChartComponent } from './stock-data/chart/chart.component';
import { GaurdGuard } from './stock-data/gaurd.guard';
import { ChartCompareComponent } from './stock-data/chart-compare/chart-compare.component';


const routes: Routes = [{path:"",component:HomepageComponent},
{path:"login",component:LoginComponent},
{path:"login/:info-flag",component:LoginComponent},
{path:"sign-up",component:SignupComponent},
{path:"user",component:UserDetailsComponent,canActivate:[GaurdGuard]},
{path:"upload",component:DataUploadComponent,canActivate:[GaurdGuard]},
{path:"company",component:CompanyComponent,canActivate:[GaurdGuard]},
{path:"chart",component:ChartComponent,canActivate:[GaurdGuard]},
{path:"compare-chart",component:ChartCompareComponent,canActivate:[GaurdGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
