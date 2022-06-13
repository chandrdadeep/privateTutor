package com.cg.service;

import java.util.List;

import com.cg.exceptionhandle.DuplicateEmpIdException;
import com.cg.model.Employee;

public interface EmployeeService {
	public List<Employee> getEmployee();
	public int createEmployee(Employee employee) throws DuplicateEmpIdException;

	public Employee readEmployee(int employeeIdId);
	public int deleteEmployee(int employeeId);
	
    public int updateEmployee(Employee employee);


	
	
    

}
