package delivery;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;

public class Restaurant {
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public Boolean addDish(Dish d) {
		if(menu.stream().map(Dish::getName).filter(x -> x.equals(d.getName())).findAny().orElse(null) != null ) {
			return false;
		}
		menu.add(d);
		return true;
		
	}
	
	String name;
	String category;
	Set<Dish> menu = new HashSet<>();
	List<Integer> ratings = new LinkedList<>();
	
	
	public Restaurant (String name, String category) {
		this.name = name;
		this.category = category;
	}
	
	public List<String> getDishesFromPrice(float minPrice, float maxPrice){
		return menu.stream().filter(x -> x.getPrice() >= minPrice && x.getPrice() <= maxPrice).map(Dish::getName).collect(Collectors.toList());
	}
	
	public List<String> getMenu(){
		return menu.stream().map(Dish::getName).sorted().collect(Collectors.toList());
	}
	public void addRating(int rate) {
		this.ratings.add(rate);
	}
	
	public Boolean hasRating() {
		if(ratings.size() == 0)
			return false;
		return true;
	}
	public double getAvgRating() {
		double tot = 0;
		
		for(Integer r : ratings) {
			tot += r;
		}
		return tot/ratings.size();
	}
}
