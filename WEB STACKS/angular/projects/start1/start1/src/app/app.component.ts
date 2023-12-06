import { AfterContentInit, AfterViewInit, Component, ContentChild, OnInit, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { ComponentproviderService } from './componentprovider.service';
import { BComponent } from './b/b.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, AfterViewInit{
 
  public selectedType : string = "B" ;
  public visualizzaB = true;
  constructor( public componentProviderService : ComponentproviderService){}
  
  ngAfterViewInit(): void {
  }

 

  @ViewChildren(BComponent) viewFiglie= {} as QueryList<BComponent>;
  
  ngOnInit(): void {
   

  }


  
  get selectedComponent() //trattato come una variabile (poichè setter typescript)
  {
    return this.componentProviderService.fetchComponent(this.selectedType);
  }
  public handleClick(type : string)
  {
    this.selectedType = type;
    
  }

}
