import { Component } from '@angular/core';
import { CoursesService } from '../service/courses.service';
import { Course } from '../dto/course.dto';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  public courses : Course[] = [];
  constructor(private courseService : CoursesService){}
 
  public fetchCourses = () : void=>
  {
    this.courseService.getAllCourses().subscribe(
      {
        next : courses =>{
          this.courses = courses;
        },
        error : (r) =>{
          console.log("Error from be "+r);
        }
      }
        
    )
  }
}
