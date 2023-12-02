import { Pipe, PipeTransform } from "@angular/core";

@Pipe({name : "hellyfi", pure : false, standalone : true})
export class HellifyPipe implements PipeTransform
{
    transform(value: any, ...args: any[]) {
        
        return value+"kkk"+args[0];
        //value è l'elemento da trasformare (quello che nella pipe viene prima di | )
        //args è opzionale e sono gli input della pipe, quelli che vengono dopo nomepipe : 
        //quindi l'uso sarà [value] | hellify : [args...]
    }
    
}