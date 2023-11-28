import { Component } from '@angular/core';
import { TestService } from './test.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'start1';

  constructor(private testService : TestService){}
  public testFunction = () =>{
    console.log("hello world");
    this.testService.doSomething("hello");
  }
}
