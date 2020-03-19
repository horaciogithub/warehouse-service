package com.horacio.warehouse.service;

import java.util.List;

import com.horacio.warehouse.model.Item;

public interface IItemService {

	public List<Item> findAll();
	public Item findById(Long id);
	
	public Item save(Item item);
	
	public void deleteById(Long id);
}
