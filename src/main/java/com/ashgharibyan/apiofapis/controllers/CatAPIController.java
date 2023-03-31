package com.ashgharibyan.apiofapis.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashgharibyan.apiofapis.models.Cat;
import com.ashgharibyan.apiofapis.services.CatService;

@RestController
public class CatAPIController {
	private final CatService catService;

	public CatAPIController(CatService catService) {
		this.catService = catService;
	}

	@RequestMapping("/api/ashgharibyan/cats")
	public List<Cat> index() {
		return catService.allCats();
	}

	@RequestMapping(value = "/api/ashgharibyan/cats", method = RequestMethod.POST)
	public Cat create(@RequestParam(value = "name") String name, @RequestParam(value = "color") String color) {
		Cat cat = new Cat(name, color);
		return catService.createCat(cat);
	}

	@RequestMapping("/api/ashgharibyan/cats/{id}")
	public Object show(@PathVariable("id") Long id) {
		Cat cat = catService.findCat(id);
		if(cat==null) {
		    HashMap<String, String> err = new HashMap<String, String>();
		    err.put("Error", "Not Found");
		    return err;
		}
		return cat;
	}

	@RequestMapping(value = "/api/ashgharibyan/cats/{id}", method = RequestMethod.PUT)
	public Cat update(@PathVariable("id") Long id, @RequestParam(value = "name") String name, @RequestParam(value = "color") String color) {
		
		Cat cat = new Cat(name,color);
		cat.setId(id);
		cat = catService.updateCat(cat);
		return cat;
	}

	@RequestMapping(value = "/api/ashgharibyan/cats/{id}", method = RequestMethod.DELETE)
	public Object destroy(@PathVariable("id") Long id) {
		Boolean isDeleted = (Boolean) catService.deleteCat(id);
		HashMap<String, String> msg = new HashMap<String, String>();
	    if(isDeleted){
	    	msg.put("Message", "Successfully deleted!");
	    }else {
	    	msg.put("Error", "No entry at the given id!");
	    }
		return msg; 
	}

}
