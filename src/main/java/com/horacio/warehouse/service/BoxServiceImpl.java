package com.horacio.warehouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.horacio.warehouse.dao.BoxDAO;
import com.horacio.warehouse.model.Box;

@Service
public class BoxServiceImpl implements IBoxService {

	@Autowired
	private BoxDAO boxDao;

	@Override
	public List<Box> findAll() { return (List<Box>) boxDao.findAll(); }

	@Override
	public Box findById(Long id) { return boxDao.findById(id).orElse(null); }
	
	@Override
	public Box findByReference(String ref) { return boxDao.findByReference(ref); }
	
	@Override
	@Transactional
	public Box save(Box box) { return boxDao.save(box); }

	@Override
	@Transactional
	public void deleteById(Long id) { boxDao.deleteById(id); }

}
