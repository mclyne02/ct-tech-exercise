package ct.tech.exercise.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ShopService {
	
	public List<Long> getShopsJson(String fileName) {
		JSONParser parser = new JSONParser();
		JSONObject response = new JSONObject();
		List<Long> shopIdListForSync = new ArrayList<Long>();
		
		try {
			Object obj = parser.parse(new FileReader("openapi.etsy.com2.json"));
			response = (JSONObject) obj;

			JSONArray shops = (JSONArray) response.get("results");
			
			//Loop through the shops and get the ones with more than one listing
			int shopsSize = shops.size();
			for(int i = 0; i < shopsSize; i++) {
				JSONObject jsonShop = (JSONObject) shops.get(i);
				Long shopId = (Long) jsonShop.get("shop_id");
				if((Long)jsonShop.get("listing_active_count") > 1) {
					shopIdListForSync.add(shopId);
				}
			}
			
			//Get the listing for the shop
			//ShopListingService shopListingService = new ShopListingService();
			//shopListingService.getShopListings(shopIdListForSync);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ParseException e) {
			e.printStackTrace();
		}
		return shopIdListForSync;
	}

}
