package com.ashgharibyan.apiofapis.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ashgharibyan.apiofapis.models.Cat;


@Repository
public interface CatRepository extends CrudRepository<Cat, Long>{
	
}

