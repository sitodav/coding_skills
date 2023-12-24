import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {


  @ViewChild("formReference")
  formObject : any;

  infos : {
    name : string,
    surname : string
    email : string
  } = { name : "", surname : "", email : ""};

  

  clickHandler( )
  {
    let formObjAsNgForm = this.formObject as NgForm;
    console.log("printing form object");
    console.log(formObjAsNgForm);
    console.log("\nprinting controls on the form");
    for(let k in this.infos)
    {
      console.log(formObjAsNgForm.control.controls[k]);
    }
    console.log("\nprinting controls values on the form");
    for(let k in this.infos)
    {
      console.log(formObjAsNgForm.control.controls[k].value);
    }
    console.log("\nprinting binded values (two way) on the infos object in the component");
    for(let k in this.infos)
    {
      console.log(this.infos[k as keyof typeof this.infos] );
    }


    let obj = {name : "asd", surname : "abcd"};
    let objKeys = Object.keys(obj);

    for(let i in objKeys)
    {
      console.log(obj[objKeys[i] as keyof typeof obj])
    }

    
 
  }


}
