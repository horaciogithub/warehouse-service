package com.horacio.warehouse.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "BOX")
public class Box {	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	private String ref;
	
	private String ub;
	private String description;
	
	@OneToMany(mappedBy = "box", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Item> items;
	
	public Box() {}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	public String getRef() { return ref; }
	public void setRef(String ref) { this.ref = ref; }

	public String getUb() { return ub; }
	public void setUb(String ub) { this.ub = ub; }

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	
	public List<Item> getItems() { return items; }
	public void addItem(Item item) {
		
		if(items == null) items = new ArrayList<>(); 
		items.add(item);
		
	}
	
}
