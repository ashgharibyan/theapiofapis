package com.ashgharibyan.apiofapis.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ashgharibyan.apiofapis.models.Dog;


@Repository
public interface DogRepository extends CrudRepository<Dog, Long>{
	
}

