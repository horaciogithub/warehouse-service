package com.horacio.warehouse.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.horacio.warehouse.model.Box;

@Repository
public interface BoxDAO extends CrudRepository<Box, Long>{
	
	@Query("SELECT b FROM Box b WHERE b.ref like %?1")
	Box findByReference(String ref);

}
