import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Course } from '../dto/course.dto';
import { Observable } from 'rxjs';
import { EnvService } from 'src/environments/env.service';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  constructor(private httpClient : HttpClient, private envService : EnvService) { }

  public getAllCourses = () : Observable<Course[]> => {
    return this.httpClient.get<Course[]>(this.envService.buildUrl("coursesEndpoint"));
  }


}
