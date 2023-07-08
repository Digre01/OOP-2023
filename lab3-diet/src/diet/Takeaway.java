package diet;

import java.time.LocalTime;
import java.util.*;


/**
 * Represents a takeaway restaurant chain.
 * It allows managing restaurants, customers, and orders.
 */
public class Takeaway {
	public SortedMap<String,Restaurant> restaurants = new TreeMap<>();
	public List<Customer> customers = new LinkedList<>();
	public List<Order> orders = new LinkedList<>();
	public List<Restaurant> opened_r = new LinkedList<>();
	
	/**
	 * Constructor
	 * @param food the reference {@link Food} object with materials and products info.
	 */
	public Takeaway(Food food){
	}

	/**
	 * Creates a new restaurant with a given name
	 *
	 * @param restaurantName name of the restaurant
	 * @return the new restaurant
	 */
	public Restaurant addRestaurant(String restaurantName) {
		Restaurant restaurant = new Restaurant(restaurantName);
		restaurants.put(restaurantName,restaurant);
		
		
		return restaurant;
	}

	/**
	 * Retrieves the names of all restaurants
	 *
	 * @return collection of restaurant names
	 */
	public Collection<String> restaurants() {
		return restaurants.keySet();
	}

	/**
	 * Creates a new customer for the takeaway
	 * @param firstName first name of the customer
	 * @param lastName	last name of the customer
	 * @param email		email of the customer
	 * @param phoneNumber mobile phone number
	 *
	 * @return the object representing the newly created customer
	 */
	public Customer registerCustomer(String firstName, String lastName, String email, String phoneNumber) {
		Customer c = new Customer(firstName,lastName,email,phoneNumber);
		customers.add(c);
		return c;
	}

	/**
	 * Retrieves all registered customers
	 *
	 * @return sorted collection of customers
	 */
	public Collection<Customer> customers(){
		customers.sort(Comparator.comparing(Customer :: getLastName)
				.thenComparing(Customer :: getFirstName));
		return customers;
	}


	/**
	 * Creates a new order for the chain.
	 *
	 * @param customer		 customer issuing the order
	 * @param restaurantName name of the restaurant that will take the order
	 * @param time	time of desired delivery
	 * @return order object
	 */
	public Order createOrder(Customer customer, String restaurantName, String time) {
		Restaurant r = restaurants.get(restaurantName);
		LocalTime ti,tmp;
		int i = 1;
		Boolean flag = false;
		StringBuilder sb = new StringBuilder(time);
		
		
		
		while(sb.length() < 5) {
			
			
			sb.insert(0,"0");
			
		 }
		time = sb.toString();
		
		tmp = ti = LocalTime.parse(time);
		
		while(!r.isOpenAt(time) && !r.isPastClosingTime(time)) {
			i++;
			ti = r.getOpenTime(i);
			int cmp = ti.compareTo(tmp); 
			time = ti.toString();
			
		
			
		}
		if(r.isPastClosingTime(time))
			ti = r.getOpenTime(1);
		
			
		
		
		Order o = new Order(customer,r,ti);
		orders.add(o);
		r.addOrder(o);
		
		return o;
	}

	/**
	 * Find all restaurants that are open at a given time.
	 *
	 * @param time the time with format {@code "HH:MM"}
	 * @return the sorted collection of restaurants
	 */
	public Collection<Restaurant> openRestaurants(String time){
		LocalTime t = LocalTime.parse(time);
		for(Map.Entry<String,Restaurant> entry : restaurants.entrySet()) {
			if(entry.getValue().isOpenAt(time))
				opened_r.add(entry.getValue());
		}
		return opened_r;
	}
}
