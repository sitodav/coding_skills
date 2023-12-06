import { AfterContentInit, Component, ContentChild, Input, OnInit } from '@angular/core';
import { HellifyPipe } from '../a/hellify.pipe';
import { AModule } from '../a/a.module';
import { CommonModule } from '@angular/common';
import { AComponent } from '../a/a.component';

export type myType = "asd" | "what";;


@Component({
  selector: 'app-b',
  templateUrl : "./b.component.html",
  styleUrls: ['./b.component.css'],
  standalone : true,
  imports : [  AModule, CommonModule]
})
export class BComponent implements OnInit, AfterContentInit{

  sizeGrande = false;
  public names : string[]= [];
  _myCustomStyle : Record<string,string> = {};

  @ContentChild(AComponent) aComp = {} as AComponent;
  
  ngOnInit(): void {
    
  }

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
    this.names.push("test");
    console.log(this.names);
  }
  ngAfterContentInit(): void {
    console.log("aaaaa : ",this.aComp.message);
  }
 

  
}
