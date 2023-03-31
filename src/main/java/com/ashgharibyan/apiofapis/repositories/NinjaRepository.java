package com.ashgharibyan.apiofapis.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ashgharibyan.apiofapis.models.Ninja;


@Repository
public interface NinjaRepository extends CrudRepository<Ninja, Long>{
	
}

