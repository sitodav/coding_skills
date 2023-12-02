import { Component } from '@angular/core';
import { ComponentproviderService } from './componentprovider.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
 
  public selectedType : string = "B" ;
  constructor( public componentProviderService : ComponentproviderService){}
  
  get selectedComponent() //trattato come una variabile (poichè setter typescript)
  {
    return this.componentProviderService.fetchComponent(this.selectedType);
  }
  public handleClick(type : string)
  {
    this.selectedType = type;
    
  }

}
