import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { HomeModule } from './home/home.module';
import { DetaillsComponent } from './detaills/detaills.component';
import { DetailsModule } from './detaills/details.module';
import { RouterModule, Routes } from '@angular/router';



const routes: Routes = [
  {
    path:'',
    redirectTo:'home',
    pathMatch:'full'
  },
  {
    path:'home',
    loadChildren:()=> import('./home/home.module').then(m=> m.HomeModule)
  },
  {
    path:'details',
    component : DetaillsComponent
  },
];

 
@NgModule({
  declarations: [
    AppComponent  
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes) 
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule { }
