package com.pie.ingredients;

import java.util.HashMap;

public class IngredientFactory {
	
	public enum IngredientCatalog {
		TANK_DRIVE_2,
		TANK_DRIVE_4
	}
	
	public static Ingredient createIngredient(IngredientCatalog ingredientType, String name, HashMap<String, Object> configData) {
		Ingredient ingredient;
		HashMap<String, Object> ingredientConfig = (HashMap<String, Object>) configData.get("config");
		switch (ingredientType) {
			case TANK_DRIVE_2: {
					ingredient = new TankDrive2(name, ingredientConfig);
					break;
				}
			case TANK_DRIVE_4: {
					ingredient = new TankDrive4(name, ingredientConfig);
					break;
				}
			default:
				throw new IllegalArgumentException("Invalid ingredient type!");
		}
		return ingredient;
	}

}
