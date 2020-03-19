package com.horacio.warehouse.service;

import java.util.List;

import com.horacio.warehouse.model.Box;

public interface IBoxService {
	
	public List<Box> findAll();
	public Box findById(Long id);
	public Box findByReference(String ref);
	
	public Box save(Box box);
	
	public void deleteById(Long id);
}
