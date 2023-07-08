package delivery;

public class Dish {
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Dish(String name, String restaurantName, float price) {
		super();
		this.name = name;
		this.restaurantName = restaurantName;
		this.price = price;
	}
	String name;
	String restaurantName;
	float price;
}
