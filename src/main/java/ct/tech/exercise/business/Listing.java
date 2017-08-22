package ct.tech.exercise.business;

public class Listing {
	
	public Listing(Long listingId, String title) {
		this.listingId = listingId;
		this.title = title;
	}
	
	private Long listingId;
	private String title;
	
	//GETTERS AND SETTERS
	public Long getListingId() {
		return listingId;
	}
	public void setListingId(Long listingId) {
		this.listingId = listingId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
