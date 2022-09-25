package com.jpa.test.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.test.model.Emplye;
import com.jpa.test.repo.Repos;


@Service
public class ServicEm {

	
	@Autowired
	private Repos repo;
	
	public List < Emplye > getAllEmployees() {
        return repo.findAll();
    }
	
	
	public void saveEmployee(Emplye employee) {
		repo.save(employee);
    }
	
	public Emplye getEmployeeById(int id) 
	  {
	    Optional<Emplye> employee = repo.findById(id);
	      return employee.get();
	    
	  }
	
	public Emplye createEmployee(Emplye emplye)
	{

		if(emplye.getId() == 0)
		{			
			 emplye=repo.save(emplye);
			 return emplye;
		}
		else
		{ 
			Optional<Emplye> emly=repo.findById(emplye.getId());
		
		  if(emly.isPresent())
		  {
			  Emplye emp=emly.get();
			  emp.setFirstName(emplye.getFirstName());
			  emp.setLastName(emplye.getLastName());
			  emp.setDateOfBirth(emplye.getDateOfBirth());
			  emp.setUserName(emplye.getUserName());
			  emp.setPassword(emplye.getPassword());
			  emp.setEmail(emplye.getEmail());
			  
			  emp=repo.save(emp);
			  return emp;
		  }
		  else
		  {
			  emplye=repo.save(emplye);
				
				return emplye;
		  }		
			
		}
		
	}
	
	public void deleteEmployee(int id)
	{	System.out.println("trrrrrr");
		Optional<Emplye> employee = repo.findById(id);
	     
	    if(employee.isPresent()) 
	    {
	      repo.deleteById(id);
	    }
	}
	
	
}
