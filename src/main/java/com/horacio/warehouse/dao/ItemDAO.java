package com.horacio.warehouse.dao;

import org.springframework.data.repository.CrudRepository;

import com.horacio.warehouse.model.Item;

public interface ItemDAO extends CrudRepository<Item, Long> {

}
