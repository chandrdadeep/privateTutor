package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.cg.dao.EmployeeDao;
import com.cg.exceptionhandle.*;
import com.cg.model.Employee;
@Service("service")
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
	EmployeeDao dao;

	@Override
	public List<Employee> getEmployee() {
		
		return dao.getEmployees();
	}
	
	@Override
	@Transactional
	public int createEmployee(Employee employee) throws DuplicateEmpIdException {
		int id =0;
		System.out.println("create employee");
		System.out.println("employeeid "+employee.getEmployeeId());
		Employee emp=dao.readEmployee(employee.getEmployeeId());
		
		if(emp!=null){
			System.out.println("Duplicate id");
			if(emp.getEmployeeId()==employee.getEmployeeId()) {
				throw new DuplicateEmpIdException("Employee with "+employee.getEmployeeId() +" already Exist");
			}
		}
		
		id=dao.createEmployee(employee);
		return id;
		}
		
	@Override
	public Employee readEmployee(int employeeId) {
			Employee result = dao.readEmployee(employeeId);
			if(result != null) {
			return result;
			}
			else {
				Employee emptyEmp=new Employee();
				emptyEmp.setEmployeeId(0);
				return emptyEmp;
			}
	}
	@Override
	@Transactional
			public int deleteEmployee(int employeeId) {
				// TODO Auto-generated method stub
				int empid=dao.deleteEmployee(employeeId);
				return empid;
				
			}
	
	@Override
	@Transactional
	public int updateEmployee(Employee employee) {
		
		return dao.updateEmployee(employee);
	}

	

/*	

	
	

	
	}*/

	
	

	

}
