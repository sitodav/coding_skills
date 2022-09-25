package sito.dskij.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sito.dskij.dto.StudentDTO;

@RestController
@RequestMapping("api/v1/student")
public class StudentApi {

	/*MOCK*/
	private static List<StudentDTO> studentsService = Arrays.asList
															( 		new StudentDTO("Mario","Rossi",1L), 
																	new StudentDTO("Andrea","Esposito",2L), 
																	new StudentDTO("Josh","Borg",3L)
															);
	
	@PreAuthorize("hasAuthority('STUDENT_READ')") 
	@GetMapping("")
	public ResponseEntity<List<StudentDTO>> getAllStudents()
	{
		return ResponseEntity.ok(studentsService);
	}
	
	@PreAuthorize("hasAuthority('STUDENT_READ')")
	@GetMapping("/{studentId}")
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable(name="studentId" ) Long studentId)
	{
		StudentDTO stud = this.studentsService
				.stream()
				.filter(stude -> stude.getId().equals(studentId))
				.findAny()
				.orElse(null);
		
		return ResponseEntity.ok(stud);
	}
	
	@PreAuthorize("hasAuthority('STUDENT_WRITE')")
	@PostMapping("/{studentId}")
	public ResponseEntity<String> saveStudent(@RequestBody StudentDTO student)
	{
		
		return ResponseEntity.ok("OK");
	}
	
}
