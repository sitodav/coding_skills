import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { FormsModule, NgForm } from '@angular/forms';
import { User } from '../dto/user.dto';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  @ViewChild("loginForm")
  loginForm : NgForm | undefined;

  public user : User = {};
  
  constructor(private router: Router, private authService: AuthService) {

  }
  ngOnInit(): void {

    if (this.authService.isAuthenticated())
      this.router.navigate(["/home"]);
  }

  loginHandler = () : void => {
     this.authService.login(this.user)
      .subscribe( userWithJwt =>{
        this.authService.setUserData(userWithJwt);
        this.router.navigate(["/home"]);
      });
  }
}
