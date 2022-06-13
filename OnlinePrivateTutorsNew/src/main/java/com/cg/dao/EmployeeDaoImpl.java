package com.cg.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.model.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	@PersistenceContext
	EntityManager em;

	@Override
	public List<Employee> getEmployees() {
		TypedQuery<Employee> e = em.createQuery("select e from Employee e", Employee.class);
		return e.getResultList();
	}

	@Override
	public int createEmployee(Employee employee) {
		em.persist(employee);
		return employee.getEmployeeId();
	}

	@Override
	public Employee readEmployee(int employeeId) {

		return em.find(Employee.class, employeeId);
	}

	public int deleteEmployee(int employeeId) {
		// TODO Auto-generated method stub
		Employee e = em.find(Employee.class, employeeId);
		if (e != null) {
			em.remove(e);
		}
		return e.getEmployeeId();


	}
	@Override public int updateEmployee(Employee employee) { 
		em.merge(employee);
		return employee.getEmployeeId();
		}
		 }

