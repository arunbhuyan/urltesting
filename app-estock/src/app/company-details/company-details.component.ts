import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import { take, switchMap } from 'rxjs/operators';
import { CompanyService } from '../company.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-company-details',
  templateUrl: './company-details.component.html',
  styleUrls: ['./company-details.component.css']
})
export class CompanyDetailsComponent implements OnInit {
  
  companyCode='';
  // companyService: CompanyService;
  companyDetails$:Observable<any>=of({name: 'test'});
  // router: Router;
  //companySearchForm: FormGroup;
  // companyDetails: FormGroup
  // companyCode: FormControl = new FormControl();
  // companyName: FormControl = new FormControl();
  // companyCeo: FormControl = new FormControl();
  // companyTurnover: FormControl = new FormControl();
  // companyWebsite: FormControl = new FormControl();
  constructor(fb: FormBuilder,private snackBar:MatSnackBar, private router: Router,private route: ActivatedRoute,private companyService: CompanyService) { 
    // this.companyDetails = fb.group({
    //   code: this.companyCode,
    //   name: this.companyName,
    //   ceo: this.companyCeo,
    //   turnover: this.companyTurnover,
    //   website: this.companyWebsite
    // });
    // this.companySearchForm = fb.group({
    //   companyCode: new FormControl()
    // })
  }

  ngOnInit(): void {
    this.companyDetails$ = this.route.paramMap.pipe(
      switchMap(params => {
        this.companyCode = params.get('id') || '';
        return this.companyService.getCompany(this.companyCode);
      })
    );
  }
  onSubmit(form: any){
    this.companyService.validCompany(form.value.companyCode).pipe(take(1)).subscribe(data =>{
      this.router.navigate(['/company/', form.value.companyCode]);
    },error=>{
      this.snackBar.open('Company with company code not available', '', {
        duration: 2000,
        horizontalPosition:'end',
        verticalPosition: 'top'})
    });
  }

 
}
