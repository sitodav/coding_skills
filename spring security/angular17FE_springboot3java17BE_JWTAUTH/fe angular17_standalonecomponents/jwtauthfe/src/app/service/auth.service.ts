import { Injectable } from '@angular/core';
import { User } from '../dto/user.dto';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { EnvService } from 'src/environments/env.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private userDataKey = "userData";
  constructor(private httpClient : HttpClient, private envService : EnvService) { }


  public login = (user : User) : Observable<User> => {
    return this.httpClient.post<User>(this.envService.buildUrl("loginEndpoint"), user );
  }

  public isAuthenticated  = () : boolean =>
  {
    let userDataObjsAsStr = localStorage.getItem(this.userDataKey);
    if(!userDataObjsAsStr || !JSON.parse(userDataObjsAsStr) || !JSON.parse(userDataObjsAsStr).jwt)
      return false;

    return true;
  }

  public getToken = () : string | null => 
  {
    let userDataObjsAsStr = localStorage.getItem(this.userDataKey);
    if(userDataObjsAsStr && JSON.parse(userDataObjsAsStr) && JSON.parse(userDataObjsAsStr).jwt)
    {
      return JSON.parse(userDataObjsAsStr).jwt;
    }
    return null;
  }

  public getUserData  = () : string  | null =>
  {
    
    let userDataObjsAsStr =  localStorage.getItem(this.userDataKey)  ;
    if(userDataObjsAsStr)
      return JSON.parse(userDataObjsAsStr);
    else return null;
  }

  public setUserData(user : User )
  {
    localStorage.setItem(this.userDataKey,JSON.stringify(user));
  }


  public logout = ( ) : Observable<User> => {
     return this.httpClient.post<User>(this.envService.buildUrl("logoutEndpoint"),{} );
    

  }

  public clearUserData = () : void =>{
    localStorage.removeItem(this.userDataKey);
  }
}
