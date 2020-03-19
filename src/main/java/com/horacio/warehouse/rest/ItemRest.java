package com.horacio.warehouse.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.horacio.warehouse.dto.ItemDTO;
import com.horacio.warehouse.model.Item;
import com.horacio.warehouse.service.IBoxService;
import com.horacio.warehouse.service.IItemService;

@CrossOrigin(
		origins = { "*" }, 
		methods = { 
				RequestMethod.GET, 
				RequestMethod.POST,
				RequestMethod.PUT, 
				RequestMethod.DELETE
				}
		)
@RestController
@RequestMapping("/warehouse/items")
public class ItemRest {

	@Autowired
	private IItemService itemService;
	
	@Autowired
	private IBoxService boxService;
	
	@GetMapping("/all")
	public List<ItemDTO> findAllItems() {
		List<Item> item = itemService.findAll();
		List<ItemDTO> itemsDTO = new ArrayList<>();
				
		for (Item i : item) {
			ItemDTO itemDTO = new ItemDTO();
			itemDTO.setId(i.getId());
			itemDTO.setRef(i.getBox().getRef());
			itemDTO.setUb(i.getBox().getUb());
			itemDTO.setName(i.getName());
			itemDTO.setBrand(i.getBrand());
			itemDTO.setColor(i.getColor());
			itemDTO.setDescription(i.getDescription());
			itemDTO.setAmount(i.getAmount());
			
			itemsDTO.add(itemDTO);
		}
		return itemsDTO;
	}
	
	@GetMapping("/item/{id}")
	public Item findItem(@PathVariable Long id) {
		return itemService.findById(id);
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Item create(@RequestBody Item item) {
		item.setBox(boxService.findByReference(item.getBox().getRef()));
		return itemService.save(item);
	}
	
	@PostMapping("/create/massive") // TODO: Test
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody List<Item> items) {
		
		for (Item i : items) {
			if (i != null) {
				i.setBox(boxService.findByReference(i.getBox().getRef()));
				itemService.save(i);
			}
		}
	}
	
	@PutMapping("/edit/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Item create(@PathVariable Long id, @RequestBody Item item) {
		Item itemBd = itemService.findById(id);
		
		if (item.getAmount() > 0 && item.getAmount() != itemBd.getAmount()) {
			itemBd.setAmount(item.getAmount());
		}
		
		if (item.getBrand() != null && !item.getBrand().equals(itemBd.getBrand())) {
			itemBd.setBrand(item.getBrand());
		}
		
		if (item.getName() != null && !item.getName().equals(itemBd.getName())) {
			itemBd.setName(item.getName());
		}
		
		if (item.getColor() != null && !item.getColor().equals(itemBd.getColor())) {
			itemBd.setColor(item.getColor());
		}
		
		if (item.getDescription() != null && !item.getDescription().equals(itemBd.getDescription())) {
			itemBd.setDescription(item.getDescription());
		}
		
		if (item.getBox() != null) {
			item.setBox(boxService.findById(item.getBox().getId()));
		}
		
		
		return itemService.save(itemBd);
	}
	
	@DeleteMapping("/item/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteItem(@PathVariable Long id) {
		itemService.deleteById(id);
	}
}
