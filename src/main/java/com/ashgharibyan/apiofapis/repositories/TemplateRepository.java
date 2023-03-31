package com.ashgharibyan.apiofapis.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ashgharibyan.apiofapis.models.Template;

@Repository
public interface TemplateRepository extends CrudRepository<Template, Long>{
	
}