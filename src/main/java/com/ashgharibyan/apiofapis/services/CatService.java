package com.ashgharibyan.apiofapis.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ashgharibyan.apiofapis.models.Cat;
import com.ashgharibyan.apiofapis.repositories.CatRepository;

@Service
public class CatService {
    // adding the object repository as a dependency
    private final CatRepository catRepository;

    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    // returns all the entries
    public List<Cat> allCats() {
        return (List<Cat>) catRepository.findAll();
    }

    // create
    public Cat createCat(Cat b) {
        return catRepository.save(b);
    }

    // retrieves by id
    public Cat findCat(Long id) {
        Optional<Cat> optionalCat = catRepository.findById(id);
        if (optionalCat.isPresent()) {
            return optionalCat.get();
        } else {
            return null;
        }
    }
    
    // updates
    public Cat updateCat(Cat cat) {
        return catRepository.save(cat);

    }
    
    // return true if deleted, returns false if there is not entry with that id
    public Boolean deleteCat(Long id) {
        if (findCat(id) == null) {
            return false;
        }
        catRepository.deleteById(id);
        return true;
    }
}
