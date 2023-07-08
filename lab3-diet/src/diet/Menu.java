package diet;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a complete menu.
 * 
 * It can be made up of both packaged products and servings of given recipes.
 *
 */
public class Menu implements NutritionalElement {
	
	String name;
	Food food;
	double calories = 0.0;
	double proteins = 0.0;
	double carbs = 0.0;
	double fat = 0.0;
	private List<Ingredient> list = new LinkedList<>();
	
	public Menu(String name, Food food) {
		this.name = name;
		this.food = food;
	}
	
	
	
	/**
	 * Adds a given serving size of a recipe.
	 * The recipe is a name of a recipe defined in the {@code food}
	 * argument of the constructor.
	 * 
	 * @param recipe the name of the recipe to be used as ingredient
	 * @param quantity the amount in grams of the recipe to be used
	 * @return the same Menu to allow method chaining
	 */
    public Menu addRecipe(String recipe, double quantity) {
    	NutritionalElement r = food.getRecipe(recipe);
    	Ingredient i = new Ingredient(r,quantity);
  
		list.add(i);
		
		
		
		return this;
	}

	/**
	 * Adds a unit of a packaged product.
	 * The product is a name of a product defined in the {@code food}
	 * argument of the constructor.
	 * 
	 * @param product the name of the product to be used as ingredient
	 * @return the same Menu to allow method chaining
	 */
    public Menu addProduct(String product) {
    	NutritionalElement p = food.getProduct(product);
    	Ingredient i = new Ingredient(p,null);
    	list.add(i);
		return this;
	}

	@Override
	public String getName() {
		return name;
	}

	/**
	 * Total KCal in the menu
	 */
	@Override
	public double getCalories() {	
	
		for(Ingredient i : list) {
			if(i.getQuantity() != null) {
				calories += i.getNutritionalElement().getCalories()/100 * i.getQuantity();
			}
			else
				calories += i.getNutritionalElement().getCalories();
		}

		
		return calories;
	}

	/**
	 * Total proteins in the menu
	 */
	@Override
	public double getProteins() {
		for(Ingredient i : list) {
			if(i.getQuantity() != null) {
				proteins += i.getNutritionalElement().getProteins()/100 * i.getQuantity();
			}
			else
				proteins += i.getNutritionalElement().getProteins();
		}
		
		
		return proteins;
	}

	/**
	 * Total carbs in the menu
	 */
	@Override
	public double getCarbs() {
		
		for(Ingredient i : list) {
			if(i.getQuantity() != null) {
				carbs += i.getNutritionalElement().getCarbs()/100 * i.getQuantity();
			}
			else
				carbs += i.getNutritionalElement().getCarbs();
		}
		
		return carbs;
	}

	/**
	 * Total fats in the menu
	 */
	@Override
	public double getFat() {
		for(Ingredient i : list) {
			if(i.getQuantity() != null) {
				fat += i.getNutritionalElement().getFat()/100 * i.getQuantity();
			}
			else
				fat += i.getNutritionalElement().getFat();
		}
		return fat;
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Menu} class it must always return {@code false}:
	 * nutritional values are provided for the whole menu.
	 * 
	 * @return boolean indicator
	 */
	@Override
	public boolean per100g() {
		return false;
	}
	
	public void print() {
		for(Ingredient i : list) {
			System.out.println(i.getNutritionalElement().getName());
		}
	}
	
}