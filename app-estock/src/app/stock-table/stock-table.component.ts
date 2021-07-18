import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators'
import { StockService } from '../stock.service';
const ELEMENT_DATA: any[] = [
  {companyCode: "1367", startDate: "2021-07-04T16:57:54.701+00:00",price: "99.89"},
  {companyCode: "1367", startDate: "2021-07-04T16:57:54.701+00:00",price: "99.89"},
  {companyCode: "1367", startDate: "2021-07-04T16:57:54.701+00:00",price: "99.89"}
];
@Component({
  selector: 'app-stock-table',
  templateUrl: './stock-table.component.html',
  styleUrls: ['./stock-table.component.css']
})
export class StockTableComponent implements OnInit {
  private _companyCode:string = '';
  stockService: StockService;
  stockDetails$: Observable<any>;

  @Input() set companyCode(value: string) {
    this._companyCode = value;
    this.stockFilter.controls.code.setValue(value);
    this.stockDetails$ = this.stockService.getCompanyStock(value).pipe(
      map((e:any) => this.mapStocktoDataTable(e)));
  }
  stockFilter: FormGroup;
  companyFilterCode: FormControl = new FormControl(null, Validators.required);
  stockStartDate: FormControl = new FormControl(null, Validators.required);
  stockEndDate: FormControl = new FormControl(null, Validators.required);
  displayedColumns: string[] = ['price', 'startDate', 'startTime'];
  
  dataSource = ELEMENT_DATA.map(ele =>{ return {price: ele.price, startDate: ele.startDate, startTime: ele.startDate}});

  constructor(fb: FormBuilder, stockService: StockService) { 
     this.stockFilter = fb.group({
      code: this.companyFilterCode,
      startDate: this.stockStartDate,
      endDate: this.stockEndDate
    });
    this.stockService = stockService;
    this.stockDetails$ = this.stockService.getCompanyStock(this._companyCode);
  }

  ngOnInit(): void {
  }
  getMaxCost(data: []) {
    const array = data ? data : ELEMENT_DATA;
    return Math.max(...array.map(t => t.price));
    //return array.map(t => Math.floor(t.price)).reduce((acc, value) => acc > value ? acc : value, 0);
  }
  getMinCost(data: []) {
    const array = data ? data : ELEMENT_DATA;
    return Math.min(...array.map(t => t.price));
    //return array.map(t => Math.floor(t.price)).reduce((acc, value) => acc > value ? value : acc);
  }
  getAverageCost(data: []) {
    const array = data ? data : ELEMENT_DATA;
    return (array.map(t => Math.floor(t.price)).reduce((acc, value) => acc + value, 0))/array.length;
  }

  onSubmit(){
    this.stockDetails$ = this.stockService.getCompanyStockRange(this.stockFilter.value).pipe(
      map((e:any) => this.mapStocktoDataTable(e)));
  }

  mapStocktoDataTable(array: []){
    return array.map((ele:any) =>{ return {price: ele.price, startDate: ele.startDate, startTime: ele.startDate}});
  }
}
