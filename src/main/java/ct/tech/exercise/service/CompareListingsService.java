package ct.tech.exercise.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import ct.tech.exercise.business.Listing;
import ct.tech.exercise.output.FileOutput;

public class CompareListingsService {
	private static FileOutput fileOutput = new FileOutput();
	
	public void checkOldListings(HashMap<Long, Listing> incomingListingInfo, Long shopId) throws FileNotFoundException, IOException {
		fileOutput.setShopId(shopId);
		
		HashMap<Long, Listing> currentListingInfo = new HashMap<Long, Listing>();
		File existingFile = new File("listings/" + shopId + "_listings.txt");
		if(!existingFile.exists()) {
			existingFile.createNewFile();
		}
		
		//Get the old listing id's from the file to compare with the incoming ones
		FileReader fr = new FileReader(existingFile);
		BufferedReader br = new BufferedReader(fr);
		
		String currentLine = br.readLine();
		if(currentLine != null) {
			while(currentLine != null) {
				String[] lineSplit = currentLine.split(",");
				Long currentListingId = Long.parseLong(lineSplit[0]);
				String currentListingTitle = (String) lineSplit[1];
				Listing currentListingEntry = new Listing(currentListingId, currentListingTitle);
				currentListingInfo.put(currentListingId, currentListingEntry);
				//Read the next line
				currentLine = br.readLine();
			}
			br.close();
			compareListings(currentListingInfo, incomingListingInfo);
			
			fileOutput.updateListings(incomingListingInfo);
		} else {
			for(Object obj : incomingListingInfo.values()) {
				Listing listing = (Listing) obj;
				fileOutput.updateListings(listing);
			}
		}
	}
	
	public void compareListings(HashMap<Long, Listing> currentListingInfo, HashMap<Long, Listing> incomingListingInfo) throws FileNotFoundException, IOException {
		//Last File has finished writing, this compare will need to be written to a new file, mark isFinishedWriting true
		fileOutput.setFinishedWriting(true);
		
		Set<Long> currentListingsSet = currentListingInfo.keySet();
		Set<Long> incomingListingSet = incomingListingInfo.keySet();
		
		if(currentListingsSet.equals(incomingListingSet)) {
			//Mark this in the file as no change occurring
			fileOutput.noListingChanges();
		} else {
			for(Long listingId : incomingListingSet) {
				if(!currentListingsSet.contains(listingId)) {
					//Mark this one as added in the file
					Listing listing = (Listing) incomingListingInfo.get(listingId);
					fileOutput.addListingsToFile(listing);
				}
			}
			for(Long listingId : currentListingsSet) {
				if(!incomingListingSet.contains(listingId)) {
					//Mark this one as removed in the file
					Listing listing = (Listing) currentListingInfo.get(listingId);
					fileOutput.removeListingsFromFile(listing);
				}
			}
		}
	}

}
