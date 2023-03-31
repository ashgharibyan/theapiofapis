package com.ashgharibyan.apiofapis.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ashgharibyan.apiofapis.models.Dog;
import com.ashgharibyan.apiofapis.repositories.DogRepository;

@Service
public class DogService {
    // adding the object repository as a dependency
    private final DogRepository dogRepository;

    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    // returns all the entries
    public List<Dog> allDogs() {
        return (List<Dog>) dogRepository.findAll();
    }

    // create
    public Dog createDog(Dog b) {
        return dogRepository.save(b);
    }

    // retrieves by id
    public Dog findDog(Long id) {
        Optional<Dog> optionalDog = dogRepository.findById(id);
        if (optionalDog.isPresent()) {
            return optionalDog.get();
        } else {
            return null;
        }
    }
    
    // updates
    public Dog updateDog(Dog dog) {
        return dogRepository.save(dog);

    }
    
    // return true if deleted, returns false if there is not entry with that id
    public Boolean deleteDog(Long id) {
        if (findDog(id) == null) {
            return false;
        }
        dogRepository.deleteById(id);
        return true;
    }
}
