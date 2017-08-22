package ct.tech.exercise.business;

import java.util.List;

public class Shop {
	
	private int shopId;
	private String shopName;
	private List<Listing> listings;
	
	//GETTERS AND SETTERS
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public List<Listing> getListings() {
		return listings;
	}
	public void setListings(List<Listing> listings) {
		this.listings = listings;
	}
}
