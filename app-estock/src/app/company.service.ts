import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  private API_URL = environment.API_URL;
  constructor(private http: HttpClient) {
   
  }

  getCompanies(){
    return this.http.get(`${this.API_URL}/api/v1.0/market/company/getall`);
  }

  getCompany(companyId:string){
    return this.http.get(`${this.API_URL}/api/v1.0/market/company/info/${companyId}`);
  }

  validCompany(companyId: string){
    return this.http.get(`companyapi/api/v1.0/market/company/${companyId}`);
  }
}
