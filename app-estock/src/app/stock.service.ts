import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StockService {
  private API_URL = environment.API_URL;
  constructor(private http:HttpClient) { 
  }

  getCompanyStock(companyCode:string){
   return this.http.get(`${this.API_URL}/api/v1.0/market/stock/get/${companyCode}`);
  }
  getCompanyStockRange(filterDetails: any){
    return this.http.get(`${this.API_URL}/api/v1.0/market/stock/get/${filterDetails.code}/${filterDetails.startDate}/${filterDetails.endDate}`);
  }
}
