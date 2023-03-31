package com.ashgharibyan.apiofapis.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashgharibyan.apiofapis.models.Dog;
import com.ashgharibyan.apiofapis.services.DogService;

@RestController
public class DogAPIController {
	private final DogService dogService;

	public DogAPIController(DogService dogService) {
		this.dogService = dogService;
	}

	@RequestMapping("/api/ashgharibyan/dogs")
	public List<Dog> index() {
		return dogService.allDogs();
	}

	@RequestMapping(value = "/api/ashgharibyan/dogs", method = RequestMethod.POST)
	public Dog create(@RequestParam(value = "name") String name, @RequestParam(value = "color") String color) {
		Dog dog = new Dog(name, color);
		return dogService.createDog(dog);
	}

	@RequestMapping("/api/ashgharibyan/dogs/{id}")
	public Object show(@PathVariable("id") Long id) {
		Dog dog = dogService.findDog(id);
		if(dog==null) {
		    HashMap<String, String> err = new HashMap<String, String>();
		    err.put("Error", "Not Found");
		    return err;
		}
		return dog;
	}

	@RequestMapping(value = "/api/ashgharibyan/dogs/{id}", method = RequestMethod.PUT)
	public Dog update(@PathVariable("id") Long id, @RequestParam(value = "name") String name, @RequestParam(value = "color") String color) {
		
		Dog dog = new Dog(name,color);
		dog.setId(id);
		dog = dogService.updateDog(dog);
		return dog;
	}

	@RequestMapping(value = "/api/ashgharibyan/dogs/{id}", method = RequestMethod.DELETE)
	public Object destroy(@PathVariable("id") Long id) {
		Boolean isDeleted = (Boolean) dogService.deleteDog(id);
		HashMap<String, String> msg = new HashMap<String, String>();
	    if(isDeleted){
	    	msg.put("Message", "Successfully deleted!");
	    }else {
	    	msg.put("Error", "No entry at the given id!");
	    }
		return msg; 
	}

}
