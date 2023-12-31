import { ApplicationConfig } from '@angular/core';
import { Routes, provideRouter } from '@angular/router';

import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { LoggedInGuard } from './guards/logged.guard';
import { authInterceptor } from './interceptor/auth.interceptor';


const routes: Routes = [
   {
      path : "",
      redirectTo : "home",
      pathMatch : "full"
   },
   {
      path : "home",
      component : HomeComponent,
      canActivate: [LoggedInGuard]
   },
   {
      path : "login",
      component : LoginComponent
   }
];


export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes) ,
    provideHttpClient(withInterceptors([
      authInterceptor 
  ]))]
};