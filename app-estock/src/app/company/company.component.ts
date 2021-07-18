import { Component, OnInit } from '@angular/core';
import { CompanyService } from '../company.service';
import {take} from 'rxjs/operators'
const ELEMENT_DATA: any[] = [
  {code:"6745",name:"company2",ceo:"Aruns",turnover:"10",website:"www122"},
  {code:"6745",name:"company2",ceo:"Aruns",turnover:"10",website:"www122"},
  {code:"6745",name:"company2",ceo:"Aruns",turnover:"10",website:"www122"},
];
@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit {
  
  displayedColumns: string[] = ['code', 'name', 'ceo', 'turnover', 'website'];
  dataSource:any = ELEMENT_DATA
  companyService:CompanyService;
  constructor(companyService: CompanyService) { 
    this.companyService = companyService;
  }

  ngOnInit(): void {
    this.companyService.getCompanies().pipe(take(1)).subscribe(data=>this.dataSource = data)
  }
  
}
