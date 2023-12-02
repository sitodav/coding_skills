import { Injectable } from '@angular/core';
import { AComponent } from './a/a.component';
import { BComponent } from './b/b.component';


export class ComponentproviderService {

  constructor() { }
  public fetchComponent(type:string)
  {
    if("A" == type)
    {
      return {
        component: AComponent,
        inputs: {"message" : "I Am In A"}
      }
    }
    else  
    {
      return {
        component: BComponent,
        inputs: {"message" : "I Am In B"}
      }
    }
    
  }
}
