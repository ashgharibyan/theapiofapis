package com.ashgharibyan.apiofapis.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.ashgharibyan.apiofapis.models.Zoom;
import com.ashgharibyan.apiofapis.services.ZoomService;
import com.ashgharibyan.apiofapis.services.UserService;

@Controller
public class ZoomController {
	
	private final ZoomService zoomService;
	
	@Autowired
	private UserService userServ;

	public ZoomController(ZoomService zoomService) {
		this.zoomService = zoomService;
	}

	@GetMapping("/bob/zooms")
	public String showAll(Model model) {
		model.addAttribute("zooms", zoomService.allZooms());
		return "/zoom-bob/zoomShowAll.jsp";
	}
	
	@GetMapping("/bob/zoom/create")
	public String createPage(@ModelAttribute("newZoom") Zoom zoom) {
		return "/zoom-bob/zoomCreate.jsp";
	}
	
	@PostMapping("/bob/zoom/new/process")
	public String createZoomProcess(@Valid @ModelAttribute("newZoom") Zoom zoom, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "/zoom-bob/zoomCreate.jsp";
		}
		
		zoomService.createZoom(zoom);
		return "redirect:/bob/zooms";
	}
	
	@DeleteMapping("/bob/zoom/{id}/delete")
	public String deleteZoom(@PathVariable("id") Long id) {
		zoomService.deleteZoom(id);
		return "redirect:/bob/zooms";
	}
	
	@GetMapping("/bob/zoom/{id}/edit")
	public String editPage(@PathVariable("id") Long id, Model model) {
		model.addAttribute("zoom", zoomService.findZoom(id));
		return "/zoom-bob/zoomEdit.jsp";
	}
	
	@PutMapping("/bob/zoom/edit/{id}/process")
	public String editZoomProcess(@Valid @ModelAttribute("zoom") Zoom zoom, BindingResult result) {
		if(result.hasErrors()) {
			return "/zoom-bob/zoomEdit.jsp";
		}
		zoomService.updateZoom(zoom);
		return "redirect:/bob/zooms";
	}
	
}
