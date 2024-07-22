
public class ItemOrder {
	Item item;
	int qty;
	public ItemOrder(Item item, int qty) {
		this.item = item;
		this.qty = qty;
	}
	
	public double getPrice() {
		return item.priceFor(qty);
	}
	
	public Item getItem() {
		return item;
	}
	
	public boolean equals(Object obj) {
		if(this.item.equals(((ItemOrder) obj).getItem())) {
			return true;
		}
		return false;
	}
}
