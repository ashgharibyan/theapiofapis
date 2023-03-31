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

import com.ashgharibyan.apiofapis.models.Bank;
import com.ashgharibyan.apiofapis.services.BankService;
import com.ashgharibyan.apiofapis.services.UserService;

@Controller
public class BankController {
	
	private final BankService bankService;
	
	@Autowired
	private UserService userServ;

	public BankController(BankService bankService) {
		this.bankService = bankService;
	}

	@GetMapping("/bob/banks")
	public String showAll(Model model) {
		model.addAttribute("banks", bankService.allBanks());
		return "/bank-bob/bankShowAll.jsp";
	}
	
	@GetMapping("/bob/bank/create")
	public String createPage(@ModelAttribute("newBank") Bank bank) {
		return "/bank-bob/bankCreate.jsp";
	}
	
	@PostMapping("/bob/bank/new/process")
	public String createBankProcess(@Valid @ModelAttribute("newBank") Bank bank, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "/bank-bob/bankCreate.jsp";
		}
		
		bankService.createBank(bank);
		return "redirect:/bob/banks";
	}
	
	@DeleteMapping("/bob/bank/{id}/delete")
	public String deleteBank(@PathVariable("id") Long id) {
		bankService.deleteBank(id);
		return "redirect:/bob/banks";
	}
	
	@GetMapping("/bob/bank/{id}/edit")
	public String editPage(@PathVariable("id") Long id, Model model) {
		model.addAttribute("bank", bankService.findBank(id));
		return "/bank-bob/bankEdit.jsp";
	}
	
	@PutMapping("/bob/bank/edit/{id}/process")
	public String editBankProcess(@Valid @ModelAttribute("bank") Bank bank, BindingResult result) {
		if(result.hasErrors()) {
			return "/bank-bob/bankEdit.jsp";
		}
		bankService.updateBank(bank);
		return "redirect:/bob/banks";
	}
	
}
