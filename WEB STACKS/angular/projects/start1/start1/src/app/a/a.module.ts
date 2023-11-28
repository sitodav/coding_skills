import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AComponent } from './a.component';
import { BModule } from '../b/b.module';



@NgModule({
  declarations: [
    AComponent
  ],
  imports: [
    CommonModule,
    BModule
  ]
})
export class AModule { }
