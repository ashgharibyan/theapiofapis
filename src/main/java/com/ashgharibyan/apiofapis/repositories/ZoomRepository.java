package com.ashgharibyan.apiofapis.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ashgharibyan.apiofapis.models.Zoom;


@Repository
public interface ZoomRepository extends CrudRepository<Zoom, Long>{
	
}

