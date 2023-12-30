package sito.davide.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import sito.davide.dto.CourseDTO;
import sito.davide.service.CoursesService;

@RestController
@RequestMapping("/courses")
@Tag(name = "Courses", description = "Auth management APIs")
public class CoursesController
{

	private static Logger log = LoggerFactory.getLogger(CoursesController.class);
	
	@Autowired
	private CoursesService courseService;
	
	@GetMapping("")
	public ResponseEntity<List<CourseDTO>> getAllCourses() throws Exception
	{
		return ResponseEntity.ok(courseService.getAllCourses());
	}
	
}
