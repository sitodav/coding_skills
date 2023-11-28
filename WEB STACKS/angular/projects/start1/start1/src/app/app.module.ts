import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TestService } from './test.service';
import { BComponent } from './b/b.component';
import { AModule } from './a/a.module';

@NgModule({
  declarations: [
    AppComponent,
    BComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AModule
  ],
 
  bootstrap: [AppComponent]
})
export class AppModule { }
