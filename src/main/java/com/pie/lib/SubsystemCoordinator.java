package com.pie.lib;

import java.util.HashMap;
import java.util.Vector;

import com.pie.ingredients.Ingredient;
import com.pie.ingredients.IngredientFactory;
import com.pie.ingredients.IngredientFactory.IngredientCatalog;

public class SubsystemCoordinator {
	
	private Vector<Ingredient> subsystems;
	private static SubsystemCoordinator instance;
	
	private SubsystemCoordinator () {
		this.subsystems = new Vector<Ingredient>();
	}
	
	public static SubsystemCoordinator getInstance() {
		if (instance == null) {
			instance = new SubsystemCoordinator();
		}
		return instance;
	}
	
	public void updateSubsystems() {
		for (Ingredient subsystem : subsystems) {
			subsystem.update();
		}
	}
	
	public void stopSubsystems() {
		for (Ingredient subsystem : subsystems) {
			subsystem.disable();
		}
	}
	
	public void addSubsytem(Ingredient subsystem) {
		subsystems.addElement(subsystem);
	}
	
	public void populateSubsystems(HashMap<String, Object> subSysDefs) {
		subSysDefs.entrySet().forEach((entry) -> {
			String name = entry.getKey();
			HashMap<String, Object> configData = (HashMap<String, Object>) entry.getValue();
			IngredientCatalog ingredientType = IngredientCatalog.valueOf(((String) configData.get("type")).toUpperCase());
			Ingredient subsystem = IngredientFactory.createIngredient(ingredientType, name, configData);
			this.addSubsytem(subsystem);
		});
	}
	
	// TODO: Add removeSubsystem()
}
