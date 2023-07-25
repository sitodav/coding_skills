package sito.davide.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sito.davide.entity.TbEmployee;
import sito.davide.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeAPI
{
	private static final Logger log = LoggerFactory.getLogger(EmployeeAPI.class);
	
	@Autowired
	EmployeeService empService;
	
	@GetMapping
	public ResponseEntity<List<TbEmployee>> getAll() throws Exception
	{
		log.info("getAll");
		try
		{
			return ResponseEntity.ok(empService.getAllEmployee());
		}catch(Exception ex)
		{
			log.error("Error", ex);
			throw ex;
		}
		
	}
	
	@GetMapping("/{empLogicalCode}")
	public ResponseEntity<TbEmployee> getByLogicalCode(@PathVariable("empLogicalCode") String empLogicalCode) throws Exception
	{
		log.info("getByLogicalCode");
		try
		{
			return ResponseEntity.ok(empService.getEmployeeByLogicalCode(empLogicalCode));
		}catch(Exception ex)
		{
			log.error("Error", ex);
			throw ex;
		}
		
	}
	
	@PostMapping
	public ResponseEntity<TbEmployee> saveEmployee(@RequestBody TbEmployee emp) throws Exception
	{
		log.info("saveEmployee");
		try
		{
			return ResponseEntity.ok(empService.saveEmployee(emp));
		}catch(Exception ex)
		{
			log.error("Error", ex);
			throw ex;
		}
		
	}
}
