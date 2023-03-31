package com.ashgharibyan.apiofapis.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ashgharibyan.apiofapis.models.Burger;
import com.ashgharibyan.apiofapis.repositories.BurgerRepository;

@Service
public class BurgerService {
    // adding the object repository as a dependency
    private final BurgerRepository burgerRepository;

    public BurgerService(BurgerRepository burgerRepository) {
        this.burgerRepository = burgerRepository;
    }

    // returns all the entries
    public List<Burger> allBurgers() {
        return (List<Burger>) burgerRepository.findAll();
    }

    // create
    public Burger createBurger(Burger b) {
        return burgerRepository.save(b);
    }

    // retrieves by id
    public Burger findBurger(Long id) {
        Optional<Burger> optionalBurger = burgerRepository.findById(id);
        if (optionalBurger.isPresent()) {
            return optionalBurger.get();
        } else {
            return null;
        }
    }
    
    // updates
    public Burger updateBurger(Burger burger) {
        return burgerRepository.save(burger);

    }
    
    // return true if deleted, returns false if there is not entry with that id
    public Boolean deleteBurger(Long id) {
        if (findBurger(id) == null) {
            return false;
        }
        burgerRepository.deleteById(id);
        return true;
    }
}
