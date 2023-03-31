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

import com.ashgharibyan.apiofapis.models.Ninja;
import com.ashgharibyan.apiofapis.services.NinjaService;
import com.ashgharibyan.apiofapis.services.UserService;

@Controller
public class NinjaController {
	
	private final NinjaService ninjaService;
	
	@Autowired
	private UserService userServ;

	public NinjaController(NinjaService ninjaService) {
		this.ninjaService = ninjaService;
	}

	@GetMapping("/bob/ninjas")
	public String showAll(Model model) {
		model.addAttribute("ninjas", ninjaService.allNinjas());
		return "/ninja-bob/ninjaShowAll.jsp";
	}
	
	@GetMapping("/bob/ninja/create")
	public String createPage(@ModelAttribute("newNinja") Ninja ninja) {
		return "/ninja-bob/ninjaCreate.jsp";
	}
	
	@PostMapping("/bob/ninja/new/process")
	public String createNinjaProcess(@Valid @ModelAttribute("newNinja") Ninja ninja, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "/ninja-bob/ninjaCreate.jsp";
		}
		
		ninjaService.createNinja(ninja);
		return "redirect:/bob/ninjas";
	}
	
	@DeleteMapping("/bob/ninja/{id}/delete")
	public String deleteNinja(@PathVariable("id") Long id) {
		ninjaService.deleteNinja(id);
		return "redirect:/bob/ninjas";
	}
	
	@GetMapping("/bob/ninja/{id}/edit")
	public String editPage(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ninja", ninjaService.findNinja(id));
		return "/ninja-bob/ninjaEdit.jsp";
	}
	
	@PutMapping("/bob/ninja/edit/{id}/process")
	public String editNinjaProcess(@Valid @ModelAttribute("ninja") Ninja ninja, BindingResult result) {
		if(result.hasErrors()) {
			return "/ninja-bob/ninjaEdit.jsp";
		}
		ninjaService.updateNinja(ninja);
		return "redirect:/bob/ninjas";
	}
	
}
