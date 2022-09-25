package com.jpa.test.cont;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jpa.test.model.Emplye;
import com.jpa.test.service.ServicEm;

@Controller
@RequestMapping("/")
public class Control {
	
	@Autowired
	private ServicEm ser;
	
	  @RequestMapping
	  public String getAllEmployees(Model model) 
	  {
	    List<Emplye> list = ser.getAllEmployees();
	 
	    model.addAttribute("employees", list);
	    return "list_employees";
	  }
		
	  @RequestMapping(path = {"/edit", "/edit/{id}"})
	  public String editEmployeeById(Model model, @PathVariable("id") Optional<Integer> id) 
	  {
	    if (id.isPresent()) {
	      Emplye entity = ser.getEmployeeById(id.get());
	      model.addAttribute("employee", entity);
	    } else {
	      model.addAttribute("employee", new Emplye());
	    }
	    return "add-edit-employee";
	  }
	  
	  @RequestMapping(path = {"/createEmployee"})
	  public String createEmployee(Emplye empl) 
	  {
	    
	      ser.createEmployee(empl);
	    return "redirect:/";
	  }
	  
	  @RequestMapping(path = {"/delete/{id}"})
	  public String deleteEmployee(@PathVariable("id") int id) 
	  {
	      ser.deleteEmployee(id);
	      return "redirect:/";
	  }
	  
}
