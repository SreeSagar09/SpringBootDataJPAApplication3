package com.app.runner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;

import com.app.model.Employee;
import com.app.repository.EmployeeRepository;

@Component
public class TestRunner implements ApplicationRunner{
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("From run method in TestRunner class.");
		
		System.out.println("----- To get all records by findAll(Example<S> example) method -----");
		Employee employee1 = new Employee();
		employee1.setEmployeeName("Pavan");
		Example<Employee> example1 = Example.of(employee1);
		List<Employee> employeeList1 = employeeRepository.findAll(example1);
		
		employeeList1.forEach(e->{
			System.out.println(e.getEmployeeId()+" --> "+e.getEmployeeName()+"["+e.getEmployeeCode()+"]");
		});
		
		System.out.println("----- To get all records by findAll(Example<S> example) method -----");
		Employee employee2 = new Employee();
		employee2.setDesignation("QA Engineer");
		ExampleMatcher exampleMatcher1 = ExampleMatcher.matchingAny();
		Example<Employee> example2 = Example.of(employee2, exampleMatcher1);
		List<Employee> employeeList2 = employeeRepository.findAll(example2);
		
		employeeList2.forEach(e->{
			System.out.println(e.getEmployeeId()+" --> "+e.getEmployeeName()+"["+e.getEmployeeCode()+"] --> "+e.getDesignation());
		});
		
		System.out.println("----- To get all records by findAll(Example<S> example, Sort sort) method -----");
		Employee employee3 = new Employee();
		employee3.setDesignation("Software Engineer");
		Example<Employee> example3 = Example.of(employee3);
		List<Employee> employeeList3 = employeeRepository.findAll(example3, Sort.by(Order.desc("age")));
		
		employeeList3.forEach(e->{
			System.out.println(e.getEmployeeId()+" --> "+e.getEmployeeName()+"["+e.getEmployeeCode()+"] --> "+e.getDesignation()+" --> "+e.getAge());
		});
		
		System.out.println("----- To get all records by findAll(Example<S> example, Pageable pageable) method -----");
		Employee employee4 = new Employee();
		employee4.setDesignation("Software Engineer");
		Example<Employee> example4 = Example.of(employee3);
		Page<Employee> page1 = employeeRepository.findAll(example4, Pageable.ofSize(1));
		
		page1.forEach(e->{
			System.out.println(e.getEmployeeId()+" --> "+e.getEmployeeName()+"["+e.getEmployeeCode()+"] --> "+e.getDesignation()+" --> "+e.getAge());
		});
		
	}

}
