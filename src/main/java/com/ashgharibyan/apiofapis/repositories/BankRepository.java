package com.ashgharibyan.apiofapis.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ashgharibyan.apiofapis.models.Bank;


@Repository
public interface BankRepository extends CrudRepository<Bank, Long>{
	
}

