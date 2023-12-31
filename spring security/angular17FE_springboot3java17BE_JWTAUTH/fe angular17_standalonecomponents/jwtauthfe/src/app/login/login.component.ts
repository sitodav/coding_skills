import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  constructor(private router: Router, private authService: AuthService) {

  }
  ngOnInit(): void {

    if (this.authService.isAuthenticated())
      this.router.navigate(["/home"]);
  }
}
