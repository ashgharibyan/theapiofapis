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

import com.ashgharibyan.apiofapis.models.Dog;
import com.ashgharibyan.apiofapis.services.DogService;
import com.ashgharibyan.apiofapis.services.UserService;

@Controller
public class DogController {
	
	private final DogService dogService;
	
	@Autowired
	private UserService userServ;

	public DogController(DogService dogService) {
		this.dogService = dogService;
	}

	@GetMapping("/ashgharibyan/dogs")
	public String showAll(Model model) {
		model.addAttribute("dogs", dogService.allDogs());
		return "/dog-ashgharibyan/dogShowAll.jsp";
	}
	
	@GetMapping("/ashgharibyan/dog/create")
	public String createPage(@ModelAttribute("newDog") Dog dog) {
		return "/dog-ashgharibyan/dogCreate.jsp";
	}
	
	@PostMapping("/ashgharibyan/dog/new/process")
	public String createDogProcess(@Valid @ModelAttribute("newDog") Dog dog, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "/dog-ashgharibyan/dogCreate.jsp";
		}
		
		dogService.createDog(dog);
		return "redirect:/ashgharibyan/dogs";
	}
	
	@DeleteMapping("/ashgharibyan/dog/{id}/delete")
	public String deleteDog(@PathVariable("id") Long id) {
		dogService.deleteDog(id);
		return "redirect:/ashgharibyan/dogs";
	}
	
	@GetMapping("/ashgharibyan/dog/{id}/edit")
	public String editPage(@PathVariable("id") Long id, Model model) {
		model.addAttribute("dog", dogService.findDog(id));
		return "/dog-ashgharibyan/dogEdit.jsp";
	}
	
	@PutMapping("/ashgharibyan/dog/edit/{id}/process")
	public String editDogProcess(@Valid @ModelAttribute("dog") Dog dog, BindingResult result) {
		if(result.hasErrors()) {
			return "/dog-ashgharibyan/dogEdit.jsp";
		}
		dogService.updateDog(dog);
		return "redirect:/ashgharibyan/dogs";
	}
	
}
