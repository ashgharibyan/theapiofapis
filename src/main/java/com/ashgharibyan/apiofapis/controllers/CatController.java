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

import com.ashgharibyan.apiofapis.models.Cat;
import com.ashgharibyan.apiofapis.services.CatService;
import com.ashgharibyan.apiofapis.services.UserService;

@Controller
public class CatController {
	
	private final CatService catService;
	
	@Autowired
	private UserService userServ;

	public CatController(CatService catService) {
		this.catService = catService;
	}

	@GetMapping("/ashgharibyan/cats")
	public String showAll(Model model) {
		model.addAttribute("cats", catService.allCats());
		return "/cat-ashgharibyan/catShowAll.jsp";
	}
	
	@GetMapping("/ashgharibyan/cat/create")
	public String createPage(@ModelAttribute("newCat") Cat cat) {
		return "/cat-ashgharibyan/catCreate.jsp";
	}
	
	@PostMapping("/ashgharibyan/cat/new/process")
	public String createCatProcess(@Valid @ModelAttribute("newCat") Cat cat, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "/cat-ashgharibyan/catCreate.jsp";
		}
		
		catService.createCat(cat);
		return "redirect:/ashgharibyan/cats";
	}
	
	@DeleteMapping("/ashgharibyan/cat/{id}/delete")
	public String deleteCat(@PathVariable("id") Long id) {
		catService.deleteCat(id);
		return "redirect:/ashgharibyan/cats";
	}
	
	@GetMapping("/ashgharibyan/cat/{id}/edit")
	public String editPage(@PathVariable("id") Long id, Model model) {
		model.addAttribute("cat", catService.findCat(id));
		return "/cat-ashgharibyan/catEdit.jsp";
	}
	
	@PutMapping("/ashgharibyan/cat/edit/{id}/process")
	public String editCatProcess(@Valid @ModelAttribute("cat") Cat cat, BindingResult result) {
		if(result.hasErrors()) {
			return "/cat-ashgharibyan/catEdit.jsp";
		}
		catService.updateCat(cat);
		return "redirect:/ashgharibyan/cats";
	}
	
}
