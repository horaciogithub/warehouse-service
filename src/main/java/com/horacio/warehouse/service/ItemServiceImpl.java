package com.horacio.warehouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.horacio.warehouse.dao.ItemDAO;
import com.horacio.warehouse.model.Item;

@Service
public class ItemServiceImpl implements IItemService {
	
	@Autowired
	private ItemDAO itemDao;

	@Override
	public List<Item> findAll() { return (List<Item>) itemDao.findAll(); }

	@Override
	public Item findById(Long id) { return itemDao.findById(id).orElse(null); }

	@Override
	@Transactional
	public Item save(Item item) { return itemDao.save(item); }

	@Override
	@Transactional
	public void deleteById(Long id) { itemDao.deleteById(id); }
}
