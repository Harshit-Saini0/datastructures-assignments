
public class Item {
	String name;
	double price;
	int bulkQty;
	double bulkPrice;
	
	public Item(String name, double price) {
		 this(name, price, 0, 0);
	}
	
	public Item(String name, double price, int bulkQty, double bulkPrice) {
		if(price < 0 || bulkQty < 0 || bulkPrice < 0) throw new IllegalArgumentException("error");
		else {
		this.name = name;
		this.price = price;
		this.bulkPrice = bulkPrice;
		this.bulkQty = bulkQty;
		}
	}
	
	
	public double priceFor(int quantity) {
		if(quantity < 0) throw new IllegalArgumentException("error");
		else if(bulkPrice != 0 && quantity >= bulkQty) {
			return quantity * bulkPrice;
		}
		else {
			return quantity * price;
		}
	}
	
	public boolean equals(Object obj) {
		if(name.equals(((Item) obj).name)) {
			return true;
		}
		return false;
		
	}
	
	public String toString() {
		if(bulkPrice > 0) {
			return name + ", $" + price + " (" + bulkQty + "+ for " + bulkPrice + " each)";
		}
		else {
			return name + ", $" + price;
		}
	}

}
