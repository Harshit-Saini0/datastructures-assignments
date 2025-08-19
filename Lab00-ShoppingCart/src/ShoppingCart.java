import java.util.ArrayList;

public class ShoppingCart {
	ArrayList<ItemOrder> shoppingCart;
	
	public ShoppingCart() {
		shoppingCart = new ArrayList<ItemOrder>();
	}
	
	public void add(ItemOrder newOrder) {
		if(shoppingCart.contains(newOrder)) {
			shoppingCart.remove(newOrder);
			shoppingCart.add(newOrder);
		}
		else {
			shoppingCart.add(newOrder);
		}
		
	}
	
	public double getTotal() {
		double count = 0;
		for(ItemOrder order: shoppingCart) {
			count += order.getPrice();
		}
		return count;
	}

}
