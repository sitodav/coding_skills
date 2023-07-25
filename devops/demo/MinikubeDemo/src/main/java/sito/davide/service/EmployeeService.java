package sito.davide.service;

import java.util.List;

import sito.davide.entity.TbEmployee;

public interface EmployeeService
{
	public List<TbEmployee> getAllEmployee() throws Exception;
	public TbEmployee getEmployeeByLogicalCode(String logicalCode) throws Exception;
	public TbEmployee saveEmployee(TbEmployee empl) throws Exception;
}
