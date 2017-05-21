import { NgModule } from '@angular/core';
import { Router }  from '@angular/router';
import {
  Component,
  Input,
  Directive,
  HostListener,
  OnInit
} from '@angular/core';

import {AccessService} from "../../services/access.service";

@Directive({
  selector: '[access]',
  exportAs: 'access',
  providers: [
    AccessService
  ]
})
export class AccessDirective implements OnInit{
  @Input() path: string;
  accessBool: boolean;

  constructor(private router: Router, private accessService: AccessService) {
    this.accessBool = false;
  }

  ngOnInit(){
    this.accessService.getAccess(this.path).subscribe(
                access => {
                    this.accessBool = true;
                },
                error => {
                    this.router.navigate(['/error', {path: this.path}]);
                }
            )
  }
}
