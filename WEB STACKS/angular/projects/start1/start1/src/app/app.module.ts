import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BComponent } from './b/b.component'; 
import { AComponent } from './a/a.component';
import { AModule } from './a/a.module';
import { ComponentproviderService } from './componentprovider.service';
import { HellifyPipe } from './a/hellify.pipe'; 

@NgModule({
  declarations: [
    AppComponent  ,
      
  
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule ,
    AModule,
     BComponent //standalone component importata direttamente negli imports (NEGLI IMPORTS NON VANNO PIU' SOLO I MODULI !!!!)
  ],
  providers : [ComponentproviderService],
 
  bootstrap: [AppComponent]
})
export class AppModule { }
