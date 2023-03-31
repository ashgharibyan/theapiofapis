package com.ashgharibyan.apiofapis.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashgharibyan.apiofapis.models.Zoom;
import com.ashgharibyan.apiofapis.services.ZoomService;

@RestController
public class ZoomAPIController {
	private final ZoomService zoomService;

	public ZoomAPIController(ZoomService zoomService) {
		this.zoomService = zoomService;
	}

	@RequestMapping("/api/bob/zooms")
	public List<Zoom> index() {
		return zoomService.allZooms();
	}

	@RequestMapping(value = "/api/bob/zooms", method = RequestMethod.POST)
	public Zoom create(@RequestParam(value = "name") String name, @RequestParam(value = "color") String color) {
		Zoom zoom = new Zoom(name, color);
		return zoomService.createZoom(zoom);
	}

	@RequestMapping("/api/bob/zooms/{id}")
	public Object show(@PathVariable("id") Long id) {
		Zoom zoom = zoomService.findZoom(id);
		if(zoom==null) {
		    HashMap<String, String> err = new HashMap<String, String>();
		    err.put("Error", "Not Found");
		    return err;
		}
		return zoom;
	}

	@RequestMapping(value = "/api/bob/zooms/{id}", method = RequestMethod.PUT)
	public Zoom update(@PathVariable("id") Long id, @RequestParam(value = "name") String name, @RequestParam(value = "color") String color) {
		
		Zoom zoom = new Zoom(name,color);
		zoom.setId(id);
		zoom = zoomService.updateZoom(zoom);
		return zoom;
	}

	@RequestMapping(value = "/api/bob/zooms/{id}", method = RequestMethod.DELETE)
	public Object destroy(@PathVariable("id") Long id) {
		Boolean isDeleted = (Boolean) zoomService.deleteZoom(id);
		HashMap<String, String> msg = new HashMap<String, String>();
	    if(isDeleted){
	    	msg.put("Message", "Successfully deleted!");
	    }else {
	    	msg.put("Error", "No entry at the given id!");
	    }
		return msg; 
	}

}
