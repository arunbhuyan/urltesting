import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  constructor(private http: HttpClient) {
   
  }

  getCompanies(){
    return this.http.get("companyapi/api/v1.0/market/company/getall");
  }

  getCompany(companyId:string){
    return this.http.get(`companyapi/api/v1.0/market/company/info/${companyId}`);
  }

  validCompany(companyId: string){
    return this.http.get(`companyapi/api/v1.0/market/company/${companyId}`);
  }
}
