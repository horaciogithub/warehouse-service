package com.horacio.warehouse.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ITEM")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int amount;
	private String brand;
	private String name;
	private String color;
	private String description;
	
	@ManyToOne
    @JoinColumn(name = "box_id")
	private Box box;
	
	public Item() {}


	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public int getAmount() { return amount; }
	public void setAmount(int amount) { this.amount = amount; }

	public String getBrand() { return brand; }
	public void setBrand(String brand) { this.brand = brand; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getColor() { return color; }
	public void setColor(String color) { this.color = color; }

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	
		public Box getBox() { return box; }
	public void setBox(Box box) { this.box = box; }
	
}
