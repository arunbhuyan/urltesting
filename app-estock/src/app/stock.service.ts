import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StockService {
  constructor(private http:HttpClient) { 
  }

  getCompanyStock(companyCode:string){
   return this.http.get(`stockapi/api/v1.0/market/stock/get/${companyCode}`);
  }
  getCompanyStockRange(filterDetails: any){
    return this.http.get(`stockapi/api/v1.0/market/stock/get/${filterDetails.code}/${filterDetails.startDate}/${filterDetails.endDate}`);
  }
}
