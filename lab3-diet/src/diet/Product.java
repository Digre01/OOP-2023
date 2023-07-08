package diet;

public class Product implements NutritionalElement {

	String name;
	double calories, proteins, carbs, fat;
	
	
	
	
	
	public Product(String name, double calories, double proteins, double carbs, double fat) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.calories = calories;
		this.proteins = proteins;
		this.carbs = carbs;
		this.fat = fat;
		
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public double getCalories() {
		// TODO Auto-generated method stub
		return calories;
	}
	@Override
	public double getProteins() {
		// TODO Auto-generated method stub
		return proteins;
	}
	@Override
	public double getCarbs() {
		// TODO Auto-generated method stub
		return carbs;
	}
	@Override
	public double getFat() {
		// TODO Auto-generated method stub
		return fat;
	}

	@Override
	public boolean per100g() {
		// TODO Auto-generated method stub
		return false;
	}

}
