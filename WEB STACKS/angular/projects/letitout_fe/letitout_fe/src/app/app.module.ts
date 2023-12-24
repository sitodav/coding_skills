import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { HeaderFooterNavbarComponent } from './pages/header-footer-navbar/header-footer-navbar.component';
import { RouterModule, Routes } from '@angular/router';
import { WelcomegridComponent } from './pages/welcomegrid/welcomegrid.component';
import { HeaderfooternavbarAndGridComponent } from './pages/headerfooternavbar-and-grid/headerfooternavbar-and-grid.component';


const routes : Routes = [
  {
    path : "",
    redirectTo : "/welcomeGrid",
    pathMatch : "full"
  },
  {
    path : "welcomeGrid",
    component : WelcomegridComponent
  },
  {
    path : "headerFooterSidebar",
    component : HeaderFooterNavbarComponent
  },
  {
    path : "headerFooterSidebarAndGrid",
    component : HeaderfooternavbarAndGridComponent

  }
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderFooterNavbarComponent,
    WelcomegridComponent,
    HeaderfooternavbarAndGridComponent
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    FormsModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
