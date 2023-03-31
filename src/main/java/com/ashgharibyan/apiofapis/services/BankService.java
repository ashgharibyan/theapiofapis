package com.ashgharibyan.apiofapis.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ashgharibyan.apiofapis.models.Bank;
import com.ashgharibyan.apiofapis.repositories.BankRepository;

@Service
public class BankService {
    // adding the object repository as a dependency
    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    // returns all the entries
    public List<Bank> allBanks() {
        return (List<Bank>) bankRepository.findAll();
    }

    // create
    public Bank createBank(Bank b) {
        return bankRepository.save(b);
    }

    // retrieves by id
    public Bank findBank(Long id) {
        Optional<Bank> optionalBank = bankRepository.findById(id);
        if (optionalBank.isPresent()) {
            return optionalBank.get();
        } else {
            return null;
        }
    }
    
    // updates
    public Bank updateBank(Bank bank) {
        return bankRepository.save(bank);

    }
    
    // return true if deleted, returns false if there is not entry with that id
    public Boolean deleteBank(Long id) {
        if (findBank(id) == null) {
            return false;
        }
        bankRepository.deleteById(id);
        return true;
    }
}
