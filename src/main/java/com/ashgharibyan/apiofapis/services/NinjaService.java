package com.ashgharibyan.apiofapis.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ashgharibyan.apiofapis.models.Ninja;
import com.ashgharibyan.apiofapis.repositories.NinjaRepository;

@Service
public class NinjaService {
    // adding the object repository as a dependency
    private final NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }

    // returns all the entries
    public List<Ninja> allNinjas() {
        return (List<Ninja>) ninjaRepository.findAll();
    }

    // create
    public Ninja createNinja(Ninja b) {
        return ninjaRepository.save(b);
    }

    // retrieves by id
    public Ninja findNinja(Long id) {
        Optional<Ninja> optionalNinja = ninjaRepository.findById(id);
        if (optionalNinja.isPresent()) {
            return optionalNinja.get();
        } else {
            return null;
        }
    }
    
    // updates
    public Ninja updateNinja(Ninja ninja) {
        return ninjaRepository.save(ninja);

    }
    
    // return true if deleted, returns false if there is not entry with that id
    public Boolean deleteNinja(Long id) {
        if (findNinja(id) == null) {
            return false;
        }
        ninjaRepository.deleteById(id);
        return true;
    }
}
