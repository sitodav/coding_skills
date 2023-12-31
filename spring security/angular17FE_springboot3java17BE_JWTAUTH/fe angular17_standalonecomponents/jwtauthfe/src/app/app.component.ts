import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './shared/header/header.component';
import { AuthService } from './service/auth.service';
import { User } from './dto/user.dto';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  standalone : true,
  imports: [HeaderComponent, CommonModule, RouterOutlet , ]
})
export class AppComponent implements OnInit{
  
  title = 'jwtauthfe';
  constructor(private authService : AuthService) {}

  ngOnInit(): void {
    let user : User = {
      "username" : "sitodskij51.56511116726211",
      "password" : "sitodskij26.32544165859626"
    };

    this.authService.login(user)
      .subscribe(userResponse =>{
        this.authService.setUserData(userResponse);
      })
  }
}

