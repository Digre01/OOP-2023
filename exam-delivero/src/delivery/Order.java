package delivery;

public class Order {
	
	public String[] getDishNames() {
		return dishNames;
	}
	public void setDishNames(String[] dishNames) {
		this.dishNames = dishNames;
	}
	public int[] getQuantities() {
		return quantities;
	}
	public void setQuantities(int[] quantities) {
		this.quantities = quantities;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public int getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public int getDeliveryDistance() {
		return deliveryDistance;
	}
	public void setDeliveryDistance(int deliveryDistance) {
		this.deliveryDistance = deliveryDistance;
	}
	public Boolean isDelivered() {
		return Delivered;
	}
	
	public void setIsDelivered(Boolean f) {
		this.Delivered = f;
	}
	public int getId() {
		return id;
	}
	
	public Order(int id, String[] dishNames, int[] quantities, String customerName, String restaurantName, int deliveryTime,
			int deliveryDistance) {
		super();
		this.id = id;
		this.dishNames = dishNames;
		this.quantities = quantities;
		this.customerName = customerName;
		this.restaurantName = restaurantName;
		this.deliveryTime = deliveryTime;
		this.deliveryDistance = deliveryDistance;
	}
	int id;
	String dishNames[];
	int quantities[];
	String customerName;
	String restaurantName;
	int deliveryTime;
	int deliveryDistance;
	Boolean Delivered = false;
	
	

}
