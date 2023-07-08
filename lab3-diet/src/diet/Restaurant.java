package diet;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Function;

import diet.Order.OrderStatus;

/**
 * Represents a restaurant class with given opening times and a set of menus.
 */
public class Restaurant {
	
	private String name; 
	private int numberOfTimeSlot = 0;
	private SortedMap<Integer,LocalTime> openingHours = new TreeMap<>();
    private SortedMap<Integer,LocalTime> closingHours = new TreeMap<>();
	private SortedMap<String,Menu> menus = new TreeMap<>();
	private List<Order> orders = new LinkedList<>();
	private int numberOfOrders = 0;
	/**
	 * retrieves the name of the restaurant.
	 *
	 * @return name of the restaurant
	 */
	
	public Restaurant(String name) {
		this.name = name;
	}
	
	
	public String getName() {
		return name;
	}

	/**
	 * Define opening times.
	 * Accepts an array of strings (even number of elements) in the format {@code "HH:MM"},
	 * so that the closing hours follow the opening hours
	 * (e.g., for a restaurant opened from 8:15 until 14:00 and from 19:00 until 00:00,
	 * arguments would be {@code "08:15", "14:00", "19:00", "00:00"}).
	 *
	 * @param hm sequence of opening and closing times
	 */
	public void setHours(String ... hm) {
		
		numberOfTimeSlot++;
		int i = 1;
		
		for(String s : hm) {
			if((i++ % 2) != 0) {
				LocalTime op = LocalTime.parse(s);
				openingHours.put(numberOfTimeSlot,op);
				
			}
			else {
				LocalTime cl = LocalTime.parse(s);
				closingHours.put(numberOfTimeSlot,cl);
				numberOfTimeSlot++;
				
				
			}
		}
	}

	/**
	 * Checks whether the restaurant is open at the given time.
	 *
	 * @param time time to check
	 * @return {@code true} is the restaurant is open at that time
	 */
	public boolean isOpenAt(String time){
		LocalTime Time = LocalTime.parse(time);
		int i;
		
		for (SortedMap.Entry<Integer,LocalTime> entry : openingHours.entrySet()) {
			LocalTime o = entry.getValue();	
			int cmp = Time.compareTo(o);
			
			
			
			if(cmp >= 0) {
				i = entry.getKey();
				LocalTime c = closingHours.get(i);
				cmp = Time.compareTo(c);
				
				if(cmp < 0) {
					return true;
				}
			}
			
		}
		
		
		return false;
	}
	
	public boolean isPastClosingTime(String time) {
		LocalTime Time = LocalTime.parse(time);
		LocalTime t = closingHours.get(closingHours.size());
				
		if(Time.compareTo(t) >= 0)
			return true;
		return false;
		
	}
	public boolean isClosingTime(String time, int index) {
		LocalTime Time = LocalTime.parse(time);
		LocalTime t = closingHours.get(index);
				
		if(Time.compareTo(t) == 0)
			return true;
		return false;
		
	}

	/**
	 * Adds a menu to the list of menus offered by the restaurant
	 *
	 * @param menu	the menu
	 */
	public void addMenu(Menu menu) {
		menus.put(menu.getName(), menu);
	}

	/**
	 * Gets the restaurant menu with the given name
	 *
	 * @param name	name of the required menu
	 * @return menu with the given name
	 */
	public Menu getMenu(String name) {
		Menu m = menus.get(name);
		
		
		return menus.get(name);
	}
	
	public LocalTime getOpenTime(int key) {
		LocalTime time = openingHours.get(key);
		
		
		
		
		
		return time;
	}

	/**
	 * Retrieve all order with a given status with all the relative details in text format.
	 *
	 * @param status the status to be matched
	 * @return textual representation of orders
	 */
	public String ordersWithStatus(OrderStatus status) {
		StringBuffer b = new StringBuffer();
		
		
		/*
		Order o;
		
		for(SortedMap.Entry entry : orders.entrySet()) {
			o = (Order) entry.getValue();
			if(o.getStatus() == status) {
				b.append(entry.getValue());
			}
		} */
		
		orders.sort(Comparator.comparing((Order o)->o.getRestaurant().getName()));
		orders.sort(Comparator.comparing((Order o)->o.getUser().toString())
				.thenComparing(Order::getDeliveryTime));
		

		
		
		
		
		
		for (Order o: orders) {
			if (o.getStatus() == status){
				b.append(o);
			}
		}
		
		
	

		
		
		
		
		return b.toString();
	}
	
	public void addOrder(Order o) {
		
		orders.add(o);
	}
	
}
