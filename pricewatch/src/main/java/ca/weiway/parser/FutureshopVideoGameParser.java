package ca.weiway.parser;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.enterprise.context.ApplicationScoped;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import ca.weiway.model.VideoGame;

@ApplicationScoped
public class FutureshopVideoGameParser {
	
	private DecimalFormat format = new DecimalFormat("$#0.00");
	
	public VideoGame parse(String url) throws IOException, ParseException {
		Document doc = Jsoup.connect(url).get();
		
		if(doc == null) {
			return null;
		}
		
		String name = getText("#ctl00_CC_ctl00_PD_lblProductTitle", doc);
		
		double price = format.parse(getText(".pricing dd", doc)).doubleValue();
		double rating = Double.valueOf(getText(".rating span", doc).trim().split(" ")[0]);
		String ratingVotesStr = getText(".customer-text", doc).trim();
		int ratingVotes = Integer.valueOf(ratingVotesStr.split(" ")[2]);
		
		VideoGame result = new VideoGame();
		result.setName(name);
		result.setPrice(price);
		result.setRating(rating);
		result.setRatingVotes(ratingVotes);
		result.setRetailer("FutureShop");
		
		return result;
	}
	
	private String getText(String query, Document doc) {
		Elements elements = doc.select(query);
		if(elements != null && elements.size() > 0) {
			return elements.last().text();
		} else {
			return null;
		}
	}
	
	public static void main(String[] args) {
		FutureshopVideoGameParser parser = new FutureshopVideoGameParser();
		try {
			VideoGame result = 
					parser.parse("http://www.futureshop.ca/en-CA/product/alien-vs-predator-playstation-3/10138171.aspx");
			System.out.println(result.getName() + ", " + result.getPrice() + ", " + result.getRating() + ", " + result.getRatingVotes() + ", " + result.getRetailer());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
