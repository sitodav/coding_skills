import { Injectable } from '@angular/core';
import { environment } from './environment';

@Injectable({
  providedIn: 'root'
})
export class EnvService {

 
  constructor() { }

  public printEnv()
  {
    console.log("The env is "+environment["type"]);
  }

  public buildUrl(opType : keyof typeof environment )
    {
        let endpointUrl = environment[opType];
        
        if(endpointUrl)
            return environment.beProtocol+"://"+environment.beHost+":"+environment.bePort+"/"+environment.applicationContextPath+"/"+endpointUrl;
        else
            throw "Invalid optype";
    }
}

