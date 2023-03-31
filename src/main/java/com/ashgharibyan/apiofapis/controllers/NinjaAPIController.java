package com.ashgharibyan.apiofapis.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashgharibyan.apiofapis.models.Ninja;
import com.ashgharibyan.apiofapis.services.NinjaService;

@RestController
public class NinjaAPIController {
	private final NinjaService ninjaService;

	public NinjaAPIController(NinjaService ninjaService) {
		this.ninjaService = ninjaService;
	}

	@RequestMapping("/api/bob/ninjas")
	public List<Ninja> index() {
		return ninjaService.allNinjas();
	}

	@RequestMapping(value = "/api/bob/ninjas", method = RequestMethod.POST)
	public Ninja create(@RequestParam(value = "name") String name, @RequestParam(value = "belt") String belt) {
		Ninja ninja = new Ninja(name, belt);
		return ninjaService.createNinja(ninja);
	}

	@RequestMapping("/api/bob/ninjas/{id}")
	public Object show(@PathVariable("id") Long id) {
		Ninja ninja = ninjaService.findNinja(id);
		if(ninja==null) {
		    HashMap<String, String> err = new HashMap<String, String>();
		    err.put("Error", "Not Found");
		    return err;
		}
		return ninja;
	}

	@RequestMapping(value = "/api/bob/ninjas/{id}", method = RequestMethod.PUT)
	public Ninja update(@PathVariable("id") Long id, @RequestParam(value = "name") String name, @RequestParam(value = "belt") String belt) {
		
		Ninja ninja = new Ninja(name,belt);
		ninja.setId(id);
		ninja = ninjaService.updateNinja(ninja);
		return ninja;
	}

	@RequestMapping(value = "/api/bob/ninjas/{id}", method = RequestMethod.DELETE)
	public Object destroy(@PathVariable("id") Long id) {
		Boolean isDeleted = (Boolean) ninjaService.deleteNinja(id);
		HashMap<String, String> msg = new HashMap<String, String>();
	    if(isDeleted){
	    	msg.put("Message", "Successfully deleted!");
	    }else {
	    	msg.put("Error", "No entry at the given id!");
	    }
		return msg; 
	}

}
