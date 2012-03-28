package ca.weiway.model;

public class VideoGame {
	private String name;
	private double price;
	private double rating;
	private int ratingVotes;
	private String retailer;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getRating() {
		return rating;
	}
	
	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getRetailer() {
		return retailer;
	}

	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}
	

	public int getRatingVotes() {
		return ratingVotes;
	}

	public void setRatingVotes(int ratingVotes) {
		this.ratingVotes = ratingVotes;
	}
}
