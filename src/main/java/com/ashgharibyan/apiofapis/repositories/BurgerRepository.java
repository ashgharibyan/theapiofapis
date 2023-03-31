package com.ashgharibyan.apiofapis.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ashgharibyan.apiofapis.models.Burger;


@Repository
public interface BurgerRepository extends CrudRepository<Burger, Long>{
	
}

