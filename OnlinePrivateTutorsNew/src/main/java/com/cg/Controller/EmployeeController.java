package com.cg.Controller;

import java.util.List;

import com.cg.exceptionhandle.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.cg.model.Employee;
import com.cg.service.EmployeeService;
import com.cg.service.EmployeeServiceImpl;

@RestController
@CrossOrigin
@RequestMapping(path = "/emp")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(path = "/getEmp", produces = "application/json")
	public List<Employee> getAllEmployees() {
		List<Employee> empList = employeeService.getEmployee();
		return empList;
	}

	@PostMapping(value = "/addEmp", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) throws DuplicateEmpIdException{ {
		System.out.println("add employee called");
		int id = employeeService.createEmployee(employee);
		if (id>0) {
			return ResponseEntity.ok(employee);
		}else {
			throw new DuplicateEmpIdException("id exist");
		}
	}}

	@DeleteMapping(path = "{empId}", produces = "application/json")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("empId") int empId) throws NoSuchEmployeeException {
		Employee result = employeeService.readEmployee(empId);
		System.out.println("Employee id is " + result.getEmployeeId());
		ResponseEntity<Employee> response = null;
		try {
			if (result.getEmployeeId() > 0) {

				employeeService.deleteEmployee(empId);
				response = new ResponseEntity<Employee>(result, HttpStatus.OK);
				return response;
			} else {
				System.out.println("Inside else exception");
				throw new NoSuchEmployeeException("Employee with this ID Not Found");
			}
		} catch (NoSuchEmployeeException e) {
			System.out.println(e);
			
			 ResponseEntity response1=new ResponseEntity("this employee doesnont exist", HttpStatus.ACCEPTED);
			 return response1;
		}
		
	}
			

	@PutMapping(path = "{empId}", consumes = "application/json")
	public ResponseEntity<Employee> update(@PathVariable("empId") int empId, @RequestBody Employee employee) {
		int result = employeeService.updateEmployee(employee);
		if (result != 0) {
			return ResponseEntity.status(200).build();
		} else {
			return ResponseEntity.status(400).build();
		}
	}

	/*@ExceptionHandler(DuplicateEmpIdException.class)  
	public ResponseEntity<ExceptionResponse> handleDuplicateEmpIdException(DuplicateEmpIdException e) {
		ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("CONFLICT");
        response.setErrorMessage(e.getMessage());
 

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.CONFLICT);
		
	}*/
	

	/*@ExceptionHandler(DuplicateEmpIdException.class)
	public ResponseEntity<ExceptionResponse> handleDuplicateEmpIdException(DuplicateEmpIdException e) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("CONFLICT");
		response.setErrorMessage(e.getMessage());

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.CONFLICT);

	} */
	  
}
	
