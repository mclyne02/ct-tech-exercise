package ct.tech.exercise.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ct.tech.exercise.business.Listing;

public class ShopListingService {
	
	public void getShopListings(List<Long> shopIdList) throws IOException, ParseException {
		
		int shopIdListSize = shopIdList.size();
		for(int i = 0; i < shopIdListSize; i++) {
			
			/*
			 * SIMUALTE CALLING THE SERVICE TO GET THE LISTINGS
			 * 
			 * Below are two test cases to run. The first listingsForShopId JSONArray is to simulate calling
			 * a service to return the listings for the given shop id. The second listingForShopId JSON Array
			 * is to simulate if you called the same service again and got back a newly updated set 
			 * of listings. 
			 * 
			 * For testing, comment one line or the other
			 */
			//Test case one (Simulate call to service that would return listings
			JSONArray listingsForShopId = callServiceForListings((Long)shopIdList.get(i));
			//Test case two (Simulate a call to service that would return different/updated listings
			//JSONArray listingsForShopId = callServiceForListingsUpdates((Long)shopIdList.get(i));
			
			//Create a hash map for later comparisons
			HashMap<Long, Listing> incomingListingInfo = new HashMap<Long, Listing>();
			
			int listingsForShopIdSize = listingsForShopId.size();
			for(int j = 0; j < listingsForShopIdSize; j++) {
				JSONObject currentListingJson = (JSONObject) listingsForShopId.get(j);
				Long listingId = (Long) currentListingJson.get("listing_id");
				String title = (String) currentListingJson.get("title");
				Listing incomingListing = new Listing(listingId, title);
				incomingListingInfo.put(listingId, incomingListing);
			}
			
			CompareListingsService compareListings = new CompareListingsService();
			compareListings.checkOldListings(incomingListingInfo, shopIdList.get(i));
		}
	}
	
	//Service Call Test Case 1
	public JSONArray callServiceForListings(Long shopId) throws FileNotFoundException, IOException, ParseException {
		String fileName = "jsonListings/" + shopId + "_listings.json";
		File listingsForShopFile = new File(fileName);
		
		//Create the JSONObject for the listings returned for the given shopId
		JSONObject listingsForShopJson = getListingsJson(listingsForShopFile);
		JSONArray listingsForShopJsonArray = (JSONArray) listingsForShopJson.get("results");
		
		return listingsForShopJsonArray;
	}

	//Service Call Test Case 2
	public JSONArray callServiceForListingsUpdates(Long shopId) throws FileNotFoundException, IOException, ParseException {
		String fileName = "jsonListingsUpdates/" + shopId + "_listings_updates.json";
		File listingsForShopFile = new File(fileName);
		
		//Create the JSONObject for the listings returned for the given shopId
		JSONObject listingsForShopJson = getListingsJson(listingsForShopFile);
		JSONArray listingsForShopJsonArray = (JSONArray) listingsForShopJson.get("results");
		
		return listingsForShopJsonArray;
	}
	
	public JSONObject getListingsJson(File listingsFile) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		JSONObject listing = new JSONObject();
		
		Object obj = parser.parse(new FileReader(listingsFile));
		listing = (JSONObject) obj;
		
		return listing;
	}
}
;