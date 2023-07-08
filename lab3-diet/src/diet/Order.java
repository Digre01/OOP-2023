package diet;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Represents and order issued by an {@link Customer} for a {@link Restaurant}.
 *
 * When an order is printed to a string is should look like:
 * <pre>
 *  RESTAURANT_NAME, USER_FIRST_NAME USER_LAST_NAME : DELIVERY(HH:MM):
 *  	MENU_NAME_1->MENU_QUANTITY_1
 *  	...
 *  	MENU_NAME_k->MENU_QUANTITY_k
 * </pre>
 */
public class Order {
	
	private Customer customer;
	private Restaurant restaurant;
	private LocalTime time;
	private OrderStatus status = OrderStatus.ORDERED; 
	private PaymentMethod paymentMethod = PaymentMethod.CASH;
	
	
	private SortedMap<String,Integer> menus = new TreeMap<>();
	
	public Order(Customer customer, Restaurant restaurant, LocalTime t) {
		this.customer = customer;
		this.restaurant = restaurant;
		this.time = t;
		
		
	}

	/**
	 * Possible order statuses
	 */
	public enum OrderStatus {
		ORDERED, READY, DELIVERED
	}

	/**
	 * Accepted payment methods
	 */
	public enum PaymentMethod {
		PAID, CASH, CARD
	}

	/**
	 * Set payment method
	 * @param pm the payment method
	 */
	
	
	
	public void setPaymentMethod(PaymentMethod pm) {
		paymentMethod = pm;
	}

	/**
	 * Retrieves current payment method
	 * @return the current method
	 */
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * Set the new status for the order
	 * @param os new status
	 */
	public void setStatus(OrderStatus os) {
		status = os;
	}

	/**
	 * Retrieves the current status of the order
	 *
	 * @return current status
	 */
	public OrderStatus getStatus() {
		return status;
	}

	/**
	 * Add a new menu to the order with a given quantity
	 *
	 * @param menu	menu to be added
	 * @param quantity quantity
	 * @return the order itself (allows method chaining)
	 */
	public Order addMenus(String menu, int quantity) {
		
		menus.put(menu, quantity);
		return this;
	}
	
	@Override
	public String toString() {
		StringBuffer s = new StringBuffer();
		s.append(restaurant.getName()).append(", ").append(customer.getFirstName() +" " + customer.getLastName()).append(" : (").append(time).append("):\n");
		
		for(Map.Entry<String,Integer> m : menus.entrySet()) {
			s.append("\t").append(m.getKey()).append("->").append(m.getValue().toString()).append("\n");
		}
		
		return s.toString();
		
	}

	public Restaurant getRestaurant() {
		// TODO Auto-generated method stub
		return restaurant;
	}
	 Customer getUser() {
		return customer;
	}
	 LocalTime getDeliveryTime() {
		return time;
	}
}
