package com.ashgharibyan.apiofapis.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashgharibyan.apiofapis.models.Burger;
import com.ashgharibyan.apiofapis.services.BurgerService;

@RestController
public class BurgerAPIController {
	private final BurgerService burgerService;


	public BurgerAPIController(BurgerService burgerService) {
		this.burgerService = burgerService;
	}

	@RequestMapping("/api/ashgharibyan/burgers")
	public List<Burger> index() {
		return burgerService.allBurgers();
	}

	@RequestMapping(value = "/api/ashgharibyan/burgers", method = RequestMethod.POST)
	public Burger create(@RequestParam(value = "name") String name, @RequestParam(value = "ingridient") String ingridient) {
		Burger burger = new Burger(name, ingridient);
		return burgerService.createBurger(burger);
	}

	@RequestMapping("/api/ashgharibyan/burgers/{id}")
	public Object show(@PathVariable("id") Long id) {
		Burger burger = burgerService.findBurger(id);
		if(burger==null) {
		    HashMap<String, String> err = new HashMap<String, String>();
		    err.put("Error", "Not Found");
		    return err;
		}
		return burger;
	}

	@RequestMapping(value = "/api/ashgharibyan/burgers/{id}", method = RequestMethod.PUT)
	public Burger update(@PathVariable("id") Long id, @RequestParam(value = "name") String name, @RequestParam(value = "ingridient") String ingridient) {
		
		Burger burger = new Burger(name,ingridient);
		burger.setId(id);
		burger = burgerService.updateBurger(burger);
		return burger;
	}

	@RequestMapping(value = "/api/ashgharibyan/burgers/{id}", method = RequestMethod.DELETE)
	public Object destroy(@PathVariable("id") Long id) {
		Boolean isDeleted = (Boolean) burgerService.deleteBurger(id);
		HashMap<String, String> msg = new HashMap<String, String>();
	    if(isDeleted){
	    	msg.put("Message", "Successfully deleted!");
	    }else {
	    	msg.put("Error", "No entry at the given id!");
	    }
		return msg; 
	}

	
	
}
