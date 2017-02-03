package com.pie.ingredients;

public abstract class Ingredient {
	
	/**
	 * Cookable is the basic interface for all Ingredients to update with IOPackets and disable for stopping.
	 */
	public abstract void update();
	public abstract void disable();
	
	private String name;
	
	public Ingredient(String name) {
		this.setName(name);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
