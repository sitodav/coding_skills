import { Component, EventEmitter, Input, Output } from '@angular/core';
import { NodeStyleEventEmitter } from 'rxjs/internal/observable/fromEvent';

@Component({
  selector: 'app-a',
  template: '<p>{{message}} works on A</p>',
  styleUrls: ['./a.component.css']
})
export class AComponent {

  @Input()
  public message = "";
 
  
}
