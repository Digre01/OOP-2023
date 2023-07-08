package diet;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.LinkedList;
import java.util.List;


/**
 * Represents a recipe of the diet.
 * 
 * A recipe consists of a a set of ingredients that are given amounts of raw materials.
 * The overall nutritional values of a recipe can be computed
 * on the basis of the ingredients' values and are expressed per 100g
 * 
 *
 */
public class Recipe implements NutritionalElement {
	
	/**
	 * Adds the given quantity of an ingredient to the recipe.
	 * The ingredient is a raw material.
	 * 
	 * @param material the name of the raw material to be used as ingredient
	 * @param quantity the amount in grams of the raw material to be used
	 * @return the same Recipe object, it allows method chaining.
	 */
	
	
	private String name;
	private Food food;
	private double weight = 0;
	private SortedMap<String,NutritionalElement> materials;
	double calories, proteins, carbs, fat;
	private List<Ingredient> ingredients = new LinkedList<>();

	

	
	public Recipe(String name, Food food) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.food = food;
		
	}



	public Recipe addIngredient(String material, double quantity) {
		NutritionalElement r = food.getRawMaterial(material);
		ingredients.add(new Ingredient(r,quantity));
		weight += quantity;
		
		
		
		
		return this;
		
	}

	@Override
	public String getName() {
		return name;
	}

	
	@Override
	public double getCalories() {
		double tot = 0;
		int quantity = 0;

		for (Ingredient i: ingredients) {
			tot += i.getNutritionalElement().getCalories() / 100 * i.getQuantity();
		}
		if(weight < 100)
			calories = tot;
		else {
			calories = tot * 100/weight;
		}
		
		
		
		return calories;
	}
	

	@Override
	public double getProteins() {
		
		double tot = 0;
		
		for (Ingredient i: ingredients) {
			tot += i.getNutritionalElement().getProteins()/ 100 * i.getQuantity();
		}
		
		if(weight < 100)
			proteins = tot;
		else {
			proteins = tot * 100/weight;
		}
		
		
		
		return proteins;
	}

	@Override
	public double getCarbs() {
		double tot = 0;

		for (Ingredient i: ingredients) {
			tot += i.getNutritionalElement().getCarbs()/ 100 * i.getQuantity();
		}
		if(weight < 100)
			carbs = tot;
		else {
			carbs = tot * 100/weight;
		}
		
	
		return carbs;
	}

	@Override
	public double getFat() {
		double tot = 0;

		for (Ingredient i: ingredients) {
			tot += i.getNutritionalElement().getFat()/ 100 * i.getQuantity();
		}
		if(weight < 100)
			fat = tot;
		else {
			fat = tot * 100/weight;
		}
		
		return fat;
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Recipe} class it must always return {@code true}:
	 * a recipe expresses nutritional values per 100g
	 * 
	 * @return boolean indicator
	 */
	@Override
	public boolean per100g() {
		return true;
	}
	
}
