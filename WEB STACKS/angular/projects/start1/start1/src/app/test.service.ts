import { Injectable } from '@angular/core';
 
import { BModule } from './b/b.module';

 
export class TestService {

  constructor() { }

  doSomething = (msg : string) : void =>{
    console.log(msg);
  }
}
