import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AComponent } from './a.component';
import { HellifyPipe } from './hellify.pipe';



@NgModule({
  declarations: [
    AComponent
    
    
  ],
  imports: [
    CommonModule, 
  ],
  exports : [AComponent ]
})
export class AModule { }
