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

import com.ashgharibyan.apiofapis.models.Burger;
import com.ashgharibyan.apiofapis.services.BurgerService;
import com.ashgharibyan.apiofapis.services.UserService;

@Controller
public class BurgerController {
	
	private final BurgerService burgerService;
	
	@Autowired
	private UserService userServ;

	public BurgerController(BurgerService burgerService) {
		this.burgerService = burgerService;
	}

	@GetMapping("/ashgharibyan/burgers")
	public String showAll(Model model) {
		model.addAttribute("burgers", burgerService.allBurgers());
		return "/burger-ash/burgerShowAll.jsp";
	}
	
	@GetMapping("/ashgharibyan/burger/create")
	public String createPage(@ModelAttribute("newBurger") Burger burger) {
		return "/burger-ash/burgerCreate.jsp";
	}
	
	@PostMapping("/ashgharibyan/burger/new/process")
	public String createBurgerProcess(@Valid @ModelAttribute("newBurger") Burger burger, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "/burger-ash/burgerCreate.jsp";
		}
		
		burgerService.createBurger(burger);
		return "redirect:/ashgharibyan/burgers";
	}
	
	@DeleteMapping("/ashgharibyan/burger/{id}/delete")
	public String deleteBurger(@PathVariable("id") Long id) {
		burgerService.deleteBurger(id);
		return "redirect:/ashgharibyan/burgers";
	}
	
	@GetMapping("/ashgharibyan/burger/{id}/edit")
	public String editPage(@PathVariable("id") Long id, Model model) {
		model.addAttribute("burger", burgerService.findBurger(id));
		return "/burger-ash/burgerEdit.jsp";
	}
	
	@PutMapping("/ashgharibyan/burger/edit/{id}/process")
	public String editBurgerProcess(@Valid @ModelAttribute("burger") Burger burger, BindingResult result) {
		if(result.hasErrors()) {
			return "/burger-ash/burgerEdit.jsp";
		}
		burgerService.updateBurger(burger);
		return "redirect:/ashgharibyan/burgers";
	}
	
}
