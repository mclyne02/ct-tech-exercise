package ct.tech.exercise.output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import ct.tech.exercise.business.Listing;

public class FileOutput {
	
	private static String outgoingFileName;
	private static String outgoingUpdatesFileName;
	private static File outgoingFile;
	private static File outgoingUpdatesFile;
	private Long shopId;
	private boolean isFinishedWriting;
	
	//Overloaded method for writing listings when the file is empty/first run
	public void updateListings(Listing listing) {
		outgoingFileName = "listings/" + shopId + "_listings.txt";
		outgoingFile = new File(outgoingFileName);
		try {
			FileWriter fw;
			fw = new FileWriter(outgoingFile.getAbsoluteFile(), true);
			String listingRecordForFile = listing.getListingId() + "," + listing.getTitle() + "\n";
			fw.write(listingRecordForFile);
			fw.close();
			
			addListingsToFile(listing);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//Overloaded method for writing listings when an update has occurred
	public void updateListings(HashMap<Long, Listing> listingsMap) {
		outgoingFileName = "listings/" + shopId + "_listings.txt";
		outgoingFile = new File(outgoingFileName);
		try {
			FileWriter fw;
			fw = new FileWriter(outgoingFile.getAbsoluteFile(), false);
			for(Long key : listingsMap.keySet()) {
				Listing listing = (Listing) listingsMap.get(key);
				String listingRecordForFile = listing.getListingId() + "," + listing.getTitle() + "\n";
				fw.write(listingRecordForFile);
			}
			fw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void noListingChanges() {
		outgoingUpdatesFileName = "listings/" + shopId + "_listingsUpdates.txt";
		outgoingUpdatesFile = new File(outgoingUpdatesFileName);
		
		try {
			FileWriter fw;
			fw = new FileWriter(outgoingUpdatesFile.getAbsoluteFile(), false);
			String listingRecordForFile = "No Changes since last sync";
			fw.write(listingRecordForFile);
			fw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addListingsToFile(Listing listing) throws FileNotFoundException, IOException {
		outgoingUpdatesFileName = "listings/" + shopId + "_listingsUpdates.txt";
		outgoingUpdatesFile = new File(outgoingUpdatesFileName);
		
		try {
			FileWriter fw;
			if(isFinishedWriting) {
				fw = new FileWriter(outgoingUpdatesFile.getAbsoluteFile(), false);
				isFinishedWriting = false;
			} else {
				fw = new FileWriter(outgoingUpdatesFile.getAbsoluteFile(), true);
			}
			String listingUpdatedRecordForFile = "+ added listing " + listing.getListingId() + " " + "\"" + listing.getTitle() + "\"" + "\n";
			//System.out.print("Record to be inserted: " + listingRecordForFile);
			fw.write(listingUpdatedRecordForFile);
			fw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void removeListingsFromFile(Listing listing) throws FileNotFoundException, IOException {
		outgoingUpdatesFileName = "listings/" + shopId + "_listingsUpdates.txt";
		outgoingUpdatesFile = new File(outgoingUpdatesFileName);
		
		try {
			FileWriter fw;
			if(isFinishedWriting) {
				fw = new FileWriter(outgoingUpdatesFile.getAbsoluteFile(), false);
				isFinishedWriting = false;
			} else {
				fw = new FileWriter(outgoingUpdatesFile.getAbsoluteFile(), true);
			}
			String listingUpdateRecordForFile = "- removed listing " + listing.getListingId() + " " + "\"" + listing.getTitle() + "\"" + "\n";
			//System.out.print("Record to be removed: " + listingRecordForFile);
			fw.write(listingUpdateRecordForFile);
			fw.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	//GETTERS AND SETTERS
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public boolean isFinishedWriting() {
		return isFinishedWriting;
	}
	public void setFinishedWriting(boolean isFinishedWriting) {
		this.isFinishedWriting = isFinishedWriting;
	}

}
