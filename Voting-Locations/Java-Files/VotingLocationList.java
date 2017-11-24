/**This file was created for Terratap-Technologies-Inc by 
 * Cody Clattenburg, Sam Collins, Martin Suryadi, and Sergio Josue Villegas. 
 * This file is under the protection of the Apache 2.0 License.
 **/

package votingLocations;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import com.google.gson.stream.JsonReader;

/**
 * <p>The VotingLocationList class is used to hold a collection of Location objects.
 * The Locations are to be read in from JSON files containing geographic data (GeoJSON)
 * about Voting Locations that are formatted in the oden unified format.</p>
 * 
 * <p>VotingLocationList has several useful methods to sort and search through the List. 
 * It also includes multiple ways to read in and access the data.</p>
 * 
 * <p>This class makes use of the Location class which is used to represent a 
 * single Voting Location.</p>
 * @author Sam Collins
 * @version 1.2
 */
public class VotingLocationList {

	private ArrayList<Location> inputLocations;
	
	private Location[] locations;
	
	/**
	 * <h1>VotingLocationList</h1>
	 * <p>public VotingLocationList(Reader json) throws IOException</p>
	 * <p>Creates a new VotingLocationList when given a Reader to a JSON file.
	 * For the list to be created properly the JSON file must contain
	 * geographic data (GeoJSON) for voting locations that 
	 * is in the oden unified format.</p>
	 * @param json - The reader reading in data from a JSON file
	 * @throws IOException if there is a problem with the Reader given
	 * through the parameters
	 */
	public VotingLocationList(Reader json) throws IOException {
			
		inputLocations = new ArrayList<Location>();
		JsonReader reader = new JsonReader(json);
		
		try {
			inputLocations = readLocations(reader);
		} catch (IOException e) {
			System.err.println("Caught IOException: " + e.getMessage());
		} finally {
			reader.close();
		}
		
		locations = inputLocations.toArray(new Location[inputLocations.size()]);
	}
	
	/**
	 * <h1>VotingLocationList</h1>
	 * <p>public VotingLocationList(String filePath) throws IOException</p>
	 * <p>Creates a new VotingLocationList when given a file path (as a String) to a 
	 * folder containing JSON files. The file path must be the full system path 
	 * and the folder must only contain JSON files containing geographic data 
	 * (GeoJSON) for voting locations that is in the oden unified format.
	 * @param filePath - The file path to the folder containing the JSON files
	 * @throws IOException if there is a problem reading in data from the files
	 */
	public VotingLocationList(String filePath) throws IOException {
			
		inputLocations = new ArrayList<Location>();
		File folder = new File(filePath);
		File[] fileList = folder.listFiles();
		
		try {
			
			FileInputStream fileStream;
			for (int i = 0; i < fileList.length; i++) {
				
				if (fileList[i].isFile() && fileList[i].getName().endsWith(".json")) {
					
					fileStream = new FileInputStream(fileList[i]);
					ArrayList<Location> temp = readLocations(new JsonReader(new InputStreamReader(fileStream, "UTF-8")));
					inputLocations.addAll(temp);
				}
			}
		} catch (IOException e) {
			System.err.println("Caught IOException: " + e.getMessage());
		} 
		
		locations = inputLocations.toArray(new Location[inputLocations.size()]);
	}
	
	/**
	 * <h1>VotingLocationList</h1>
	 * <p>public VotingLocationList(InputStream[] inputStreams) throws IOException</p>
	 * <p>Creates a new VotingLocationList when given an array of InputStream objects that are 
	 * being reading JSON files. The InputStreams must be from JSON files 
	 * containing geographic data (GeoJSON) for voting locations that are 
	 * in the oden unified format.
	 * <p>It is recommended that this constructor be used with Android.</p>
	 * @param inputStreams - The array of InputStreams to the JSON files
	 * @throws IOException if there is a problem reading in data from the files
	 */
	public VotingLocationList(InputStream[] inputStreams) throws IOException {
		
		inputLocations = new ArrayList<Location>();
		
		try {
			
			for (int i = 0; i < inputStreams.length; i++) {
									
				ArrayList<Location> temp = readLocations(new JsonReader(new InputStreamReader(inputStreams[i], "UTF-8")));
				inputLocations.addAll(temp);
			}
		} catch (IOException e) {
			System.err.println("Caught IOException: " + e.getMessage());
		} 
		
		locations = inputLocations.toArray(new Location[inputLocations.size()]);
	}
	
	/**
	 * <h1>getAllLocations</h1>
	 * <p>public Location[] getAllLocation()</p>
	 * <p>Retrieves the full list of Locations as an array.</p>
	 * @return The full list of Locations
	 */
	public Location[] getAllLocations() {
		return locations;
	}
	
	/**
	 * <h1>getClosestLocation</h1>
	 * <p>public Location getClosestLocation(double latitude, double longitude)</p>
	 * <p>Takes in the latitude and longitude as doubles and returns the 
	 * Location in the list that is closest to the given coordinates.</p>
	 * @param latitude - The latitude of the User
	 * @param longitude - The longitude of the User
	 * @return the Location closest to the given latitude and longitude
	 */
	public Location getClosestLocation(double latitude, double longitude) {
		
		Location closest = null;
		double shortestDist = 0;

		if (locations != null) {
			
			closest = locations[0];
			shortestDist = Math.sqrt(Math.pow((locations[0].getLatitude() - latitude), 2) 
					+ Math.pow((locations[0].getLongitude() - longitude), 2));
		}
		
		for (int i = 0; i < locations.length; i++) {
			double currentDist = Math.sqrt(Math.pow((locations[i].getLatitude() - latitude), 2) 
					+ Math.pow((locations[i].getLongitude() - longitude), 2));
			
			if (currentDist < shortestDist) {
				shortestDist = currentDist;
				closest = locations[i];
			}
		}
		
		return closest;
	}

	/**
	 * <h1>sortByDistance</h1>
	 * <p>public Location[] sortByDistance(final double latitude, final double longitude)</p>
	 * <p>Takes in the latitude and longitude as doubles and returns the list of Locations
	 * sorted from closest to farthest away from the given coordinates. The list is returned
	 * as an array of Locations.</p>
	 * @param latitude - The latitude of the User
	 * @param longitude - The longitude of the User
	 * @return the list of Locations sorted by their distance to the given latitude and longitude
	 */
	public Location[] sortByDistance(final double latitude, final double longitude) {
		Location[] sortedLocations = locations;
		
		Arrays.sort(sortedLocations, new Comparator<Location>() {
			@Override
			public int compare(Location first, Location second) {

				double firstDist = Math.sqrt(Math.pow((first.getLatitude() - latitude), 2) 
						+ Math.pow((first.getLongitude() - longitude), 2));
				
				double secondDist = Math.sqrt(Math.pow((second.getLatitude() - latitude), 2) 
						+ Math.pow((second.getLongitude() - longitude), 2));
				
				if (firstDist < secondDist) {
					return -1;
				} else if (firstDist > secondDist) {
					return 1;
				} else {
					return 0;
				}
			}
		});
		
		return sortedLocations;
	}
	
	/**
	 * <h1>searchByName</h1>
	 * <p>public Location[] searchByName(String name)</p>
	 * <p>Takes in a String and returns an array of all
	 * locations that have a name containing the keyword given.</p>
	 * <p>This method returns <b>null</b> if no results are found.</p>
	 * @param name - The name of the Location being searched for
	 * @return all the locations that have a name containing the given keyword
	 */
	public Location[] searchByName(String name) {
		
		ArrayList<Location> searchLocations = new ArrayList<Location>();

		for (int i = 0; i < locations.length; i++) {
			if (locations[i].getName().toLowerCase().contains(name.toLowerCase())) {
				searchLocations.add(locations[i]);
			}
		}
		
		if (searchLocations.size() > 0) {
			return searchLocations.toArray(new Location[searchLocations.size()]);
		}
		
		return null;
	}
	
	/**
	 * <h1>searchByAddress</h1>
	 * <p>public Location[] searchByAddress(String address)</p>
	 * <p>Takes in a String and returns an array of all
	 * locations that have an address containing the keyword given.</p>
	 * <p>This method returns <b>null</b> if no results are found.</p>
	 * @param address - The address of the Location being searched for
	 * @return all the locations that have an address containing the given keyword
	 */
	public Location[] searchByAddress(String address) {
		
		ArrayList<Location> searchLocations = new ArrayList<Location>();

		for (int i = 0; i < locations.length; i++) {
			if (locations[i].getAddress().toLowerCase().contains(address.toLowerCase())) {
				searchLocations.add(locations[i]);
			}
		}
		
		if (searchLocations.size() > 0) {
			return searchLocations.toArray(new Location[searchLocations.size()]);
		}
		
		return null;
	}
	
	private ArrayList<Location> readLocations(JsonReader reader) throws IOException {
		ArrayList<Location> readLocations = new ArrayList<Location>();

		reader.beginArray();
		while (reader.hasNext()) {
			readLocations.add(readLocation(reader));
		}
		reader.endArray();
		
		return readLocations;
	}
	
	private Location readLocation(JsonReader reader) throws IOException {
		ArrayList<Double> coordinates = null;
		String name = null;
		String address = null;
		
		reader.beginObject(); //Start of each location in json file
		while (reader.hasNext()) {
			String json = reader.nextName();
			if (json.equals("geometry")) { // Start of geometry section
				reader.beginObject();
				while (reader.hasNext()) {
					if (reader.nextName().equals("coordinates")) {
						coordinates = readCoordinates(reader); //Reads through the coordinate array in the json file
					} else {
						reader.skipValue(); //Ignores "type" : "feature"
					}
				}
				reader.endObject();
			} else if (json.equals("properties")) { //Start of properties section
				reader.beginObject();
				while (reader.hasNext()) {
					String property = reader.nextName();
					if (property.equals("nm")) {		//nm = "name"
						name = reader.nextString();
					} else if (property.equals("adr")) {//adr = "address"
						address = reader.nextString();
					} else {
						reader.skipValue();
					}
				}
				reader.endObject();
			} else {
				reader.skipValue();
			}
		}
		reader.endObject();
		
		return new Location(coordinates.get(0), coordinates.get(1), name, address);
	}
	
	private ArrayList<Double> readCoordinates(JsonReader reader) throws IOException {
		ArrayList<Double> coordinates = new ArrayList<Double>();
	
		reader.beginArray();
		while (reader.hasNext()) {
			coordinates.add(reader.nextDouble());
		}
		reader.endArray();
		return coordinates;
	}
}
