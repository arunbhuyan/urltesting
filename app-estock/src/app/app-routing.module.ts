import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CompanyDetailsComponent } from './company-details/company-details.component';
import { CompanyComponent } from './company/company.component';

const routes: Routes = [
  { path: 'allCompany', component: CompanyComponent },
  { path: 'company/:id', component: CompanyDetailsComponent },
  { path: '',   redirectTo: '/allCompany', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
