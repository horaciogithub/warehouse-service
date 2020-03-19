package com.horacio.warehouse.dto;

public class ItemDTO {

	private String ref;
	private String ub;
	private Long id;
	private int amount;
	private String brand;
	private String name;
	private String color;
	private String description;
	
	public String getRef() { return ref; }
	public void setRef(String ref) { this.ref = ref; }
	
	public String getUb() { return ub; }
	public void setUb(String ub) { this.ub = ub; }
	
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
	
}
