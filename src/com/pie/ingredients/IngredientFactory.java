package com.pie.ingredients;

import java.util.HashMap;

public class IngredientFactory {
	
	public enum IngredientCatalog {
		TANK_DRIVE_2
	}
	
	public static Ingredient createIngredient(IngredientCatalog ingredientType, String name, HashMap<String, Object> configData) {
		Ingredient ingredient;
		switch (ingredientType) {
		case TANK_DRIVE_2:
			ingredient = new TankDrive2(name, configData);
			break;
		default:
			throw new IllegalArgumentException("Invalid ingredient type!");
		}
		return ingredient;
	}

}
