package diet;

public class Ingredient{
	
		public NutritionalElement getNutritionalElement() {
		return ne;
	}

	public void setNe(NutritionalElement ne) {
		this.ne = ne;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

		NutritionalElement ne;
		Double quantity;
		
		public Ingredient(NutritionalElement ne, Double quantity) {
			this.ne = ne;
			this.quantity = quantity;
		}
		
		

		
		
}
	

