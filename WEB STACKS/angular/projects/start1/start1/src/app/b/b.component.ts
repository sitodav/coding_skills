import { Component, Input } from '@angular/core';
import { HellifyPipe } from '../a/hellify.pipe';
import { AModule } from '../a/a.module';
import { CommonModule } from '@angular/common';

export type myType = "asd" | "what";;


@Component({
  selector: 'app-b',
  templateUrl : "./b.component.html",
  styleUrls: ['./b.component.css'],
  standalone : true,
  imports : [  AModule, CommonModule]
})
export class BComponent {

  sizeGrande = false;
  _myCustomStyle : Record<string,string> = {};
 
  get myCustomStyle()
  {
    return {
      'font-size' : this.sizeGrande ? "25px" : "10px",
      'font-weight' : this.sizeGrande ? "bold" : ""};
  }

  public test(t : myType)
  {
     console.log(t);
  }

  @Input()
  public message : string = "";

  constructor()
  {
    this.test("what");
  }

  
}
