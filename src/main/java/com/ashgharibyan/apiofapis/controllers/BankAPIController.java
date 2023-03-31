package com.ashgharibyan.apiofapis.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashgharibyan.apiofapis.models.Bank;
import com.ashgharibyan.apiofapis.services.BankService;

@RestController
public class BankAPIController {
	private final BankService bankService;

	public BankAPIController(BankService bankService) {
		this.bankService = bankService;
	}

	@RequestMapping("/api/bob/banks")
	public List<Bank> index() {
		return bankService.allBanks();
	}

	@RequestMapping(value = "/api/bob/banks", method = RequestMethod.POST)
	public Bank create(@RequestParam(value = "name") String name, @RequestParam(value = "address") String address) {
		Bank bank = new Bank(name, address);
		return bankService.createBank(bank);
	}

	@RequestMapping("/api/bob/banks/{id}")
	public Object show(@PathVariable("id") Long id) {
		Bank bank = bankService.findBank(id);
		if(bank==null) {
		    HashMap<String, String> err = new HashMap<String, String>();
		    err.put("Error", "Not Found");
		    return err;
		}
		return bank;
	}

	@RequestMapping(value = "/api/bob/banks/{id}", method = RequestMethod.PUT)
	public Bank update(@PathVariable("id") Long id, @RequestParam(value = "name") String name, @RequestParam(value = "address") String address) {
		
		Bank bank = new Bank(name,address);
		bank.setId(id);
		bank = bankService.updateBank(bank);
		return bank;
	}

	@RequestMapping(value = "/api/bob/banks/{id}", method = RequestMethod.DELETE)
	public Object destroy(@PathVariable("id") Long id) {
		Boolean isDeleted = (Boolean) bankService.deleteBank(id);
		HashMap<String, String> msg = new HashMap<String, String>();
	    if(isDeleted){
	    	msg.put("Message", "Successfully deleted!");
	    }else {
	    	msg.put("Error", "No entry at the given id!");
	    }
		return msg; 
	}

}
