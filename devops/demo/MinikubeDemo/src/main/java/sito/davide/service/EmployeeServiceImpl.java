package sito.davide.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sito.davide.dao.TbEmployeeRepository;
import sito.davide.entity.TbEmployee;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
	
	@Autowired
	TbEmployeeRepository empRepo;
	
	@Override
	public List<TbEmployee> getAllEmployee() throws Exception
	{
		log.info("get all employee");
		
		return empRepo.findAll();
		
	}

	@Override
	public TbEmployee getEmployeeByLogicalCode(String logicalCode) throws Exception
	{
		log.info("get employee by logical code");
		return empRepo.findByLogicalCodeIgnoreCase(logicalCode).orElse(null);
	}

	@Override
	public TbEmployee saveEmployee(TbEmployee empl) throws Exception
	{
		log.info("save employee");
		empl.setLogicalCode(UUID.randomUUID().toString());
		empl = empRepo.save(empl);
		return empl;
	}

}
