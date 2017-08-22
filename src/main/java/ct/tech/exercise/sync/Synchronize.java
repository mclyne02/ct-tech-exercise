package ct.tech.exercise.sync;


import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import ct.tech.exercise.service.ShopListingService;
import ct.tech.exercise.service.ShopService;

public class Synchronize {

	public static void main(String[] args) throws IOException, ParseException {
		//Simulate a call to get a list of shops
		String shopFileName = "openapi.etsy.com2.json";
		ShopService shopService = new ShopService();
		List<Long> shopsList =  shopService.getShopsJson(shopFileName);
		
		//Simulate a call to get the listings for the list of shops
		ShopListingService shopListingService = new ShopListingService();
		shopListingService.getShopListings(shopsList);
	}
}
