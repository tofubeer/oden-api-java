/**This file was created for Terratap-Technologies-Inc by 
 * Cody Clattenburg, Sam Collins, Martin Suryadi, and Sergio Josue Villegas. 
 * This file is under the protection of the Apache 2.0 License.
 **/

package publicArt;

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
 * <p>The ArtworkList class is used to hold a collection of Artwork objects.
 * The Artworks are to be read in from JSON files containing geographic data
 * about public art and formatted in the oden unified format.</p>
 * 
 * <p>ArtworkList has several useful methods to sort, search, and filter the 
 * Artworks. It also includes multiple ways to read in and access the data.</p>
 * 
 * <p>This class makes use of the Artwork class which is used to represent a 
 * single piece of public art.</p>
 * @author Sam Collins
 * @version 1.0
 */
public class ArtworkList {

	private ArrayList<Artwork> inputArtworks;
	private ArrayList<String> artTypes;
	
	private Artwork[] artworks;
	
	/**
	 * <h1>ArtworkList</h1>
	 * <p>public ArtworkList(Reader json) throws IOException</p>
	 * <p>Creates a new ArtworkList when given a Reader to a JSON file.
	 * For the list to be created properly the JSON file must contain
	 * geographic data (GeoJSON) for public art that is in the oden 
	 * unified format.</p>
	 * @param json - The reader reading in data from a JSON file
	 * @throws IOException if there is a problem with the Reader given
	 * through the parameters
	 */
	public ArtworkList(Reader json) throws IOException {
		
		inputArtworks = new ArrayList<Artwork>();
		JsonReader reader = new JsonReader(json);
		
		try {
			inputArtworks = readArtworks(reader);
		} catch (IOException e) {
			System.err.println("Caught IOException: " + e.getMessage());
		} finally {
			reader.close();
		}
		
		artworks = inputArtworks.toArray(new Artwork[inputArtworks.size()]);
		artTypes = readArtTypes(artworks);
	}
	
	/**
	 * <h1>ArtworkList</h1>
	 * <p>public ArtworkList(String filePath) throws IOException</p>
	 * <p>Creates a new ArtworkList when given a file path (as a String) to a 
	 * folder containing JSON files. The file path must be the full system path 
	 * and the folder must only contain JSON files containing geographic data 
	 * (GeoJSON) for public art that is in the oden unified format.
	 * @param filePath - The file path to the folder containing the JSON files
	 * @throws IOException if there is a problem reading in data from the files
	 */
	public ArtworkList(String filePath) throws IOException {
		
		inputArtworks = new ArrayList<Artwork>();
		File folder = new File(filePath);
		File[] fileList = folder.listFiles();
		
		try {
			
			for (int i = 0; i < fileList.length; i++) {
				
				if (fileList[i].isFile() && fileList[i].getName().endsWith(".json")) {
					
					FileInputStream fileStream = new FileInputStream(fileList[i]);
					ArrayList<Artwork> temp = readArtworks(new JsonReader(new InputStreamReader(fileStream, "UTF-8")));
					inputArtworks.addAll(temp);
				}
			}
		} catch (IOException e) {
			System.err.println("Caught IOException: " + e.getMessage());
		} 
		
		artworks = inputArtworks.toArray(new Artwork[inputArtworks.size()]);
		artTypes = readArtTypes(artworks);
	}
	
	/**
	 * <h1>ArtworkList</h1>
	 * <p>public ArtworkList(InputStream[] inputStreams) throws IOException</p>
	 * <p>Creates a new ArtworkList when given an array of InputStream objects that are 
	 * being reading JSON files. The InputStreams must be from JSON files 
	 * containing geographic data (GeoJSON) for public art that are 
	 * in the oden unified format.
	 * @param inputStreams - The array of InputStreams to the JSON files
	 * @throws IOException if there is a problem reading in data from the files
	 */
	public ArtworkList(InputStream[] inputStreams) throws IOException {
		
		inputArtworks = new ArrayList<Artwork>();
		
		try {
			
			for (int i = 0; i < inputStreams.length; i++) {
									
				ArrayList<Artwork> temp = readArtworks(new JsonReader(new InputStreamReader(inputStreams[i], "UTF-8")));
				inputArtworks.addAll(temp);
			}
		} catch (IOException e) {
			System.err.println("Caught IOException: " + e.getMessage());
		} 
		
		artworks = inputArtworks.toArray(new Artwork[inputArtworks.size()]);
		artTypes = readArtTypes(artworks);
	}
	
	/**
	 * <h1>getAllArtworks</h1>
	 * <p>public Artwork[] getAllArtworks()</p>
	 * <p>Retrieves the full list of Artworks as an array.</p>
	 * @return The full list of Artworks
	 */
	public Artwork[] getAllArtworks() {
		return artworks;
	}
	
	/**
	 * <h1>getArtTypes</h1>
	 * <p>public String[] getArtTypes</p>
	 * <p>Retrieves the list of art types as an array of Strings.</p>
	 * @return The list of art types
	 */
	public String[] getArtTypes() {
		return artTypes.toArray(new String[artTypes.size()]);
	}
	
	/**
	 * <h1>getClosestArtwork</h1>
	 * <p>public Artwork getClosestArtwork(double latitude, double longitude)</p>
	 * <p>Takes in the latitude and longitude as doubles and returns the 
	 * Artwork in the list that is closest to the given coordinates.</p>
	 * @param latitude - The latitude of the User
	 * @param longitude - The longitude of the User
	 * @return the Artwork closest to the given latitude and longitude
	 */
	public Artwork getClosestArtwork(double latitude, double longitude) {
		
		Artwork closest = null;
		double shortestDist = 0;

		if (artworks != null) {
			
			closest = artworks[0];
			shortestDist = Math.sqrt(Math.pow((artworks[0].getLatitude() - latitude), 2) 
					+ Math.pow((artworks[0].getLongitude() - longitude), 2));
		}
		
		for (int i = 0; i < artworks.length; i++) {
			double currentDist = Math.sqrt(Math.pow((artworks[i].getLatitude() - latitude), 2) 
					+ Math.pow((artworks[i].getLongitude() - longitude), 2));
			
			if (currentDist < shortestDist) {
				shortestDist = currentDist;
				closest = artworks[i];
			}
		}
		
		return closest;
	}
	
	/**
	 * <h1>sortByDistance</h1>
	 * <p>public Artwork[] sortByDistance(final double latitude, final double longitude)</p>
	 * <p>Takes in the latitude and longitude as doubles and returns the list of Artworks
	 * sorted from closest to farthest away from the given coordinates. The list is returned
	 * as an array of Artworks.</p>
	 * @param latitude - The latitude of the User
	 * @param longitude - The longitude of the User
	 * @return the list of Artworks sorted by their distance to the given latitude and longitude
	 */
	public Artwork[] sortByDistance(final double latitude, final double longitude) {
		Artwork[] sortedArtworks = artworks;
		
		Arrays.sort(sortedArtworks, new Comparator<Artwork>() {
			@Override
			public int compare(Artwork first, Artwork second) {

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
		
		return sortedArtworks;
	}
	
	/**
	 * <h1>searchByName</h1>
	 * <p>public Artwork[] searchByName(String name)</p>
	 * <p>Takes in a String and returns an array of all
	 * artworks that have a name containing the keyword given.</p>
	 * @param name - The name of the Artwork being searched for
	 * @return all the artworks that have a name containing the given keyword
	 */
	public Artwork[] searchByName(String name) {
		
		ArrayList<Artwork> searchArtworks = new ArrayList<Artwork>();

		for (int i = 0; i < artworks.length; i++) {
			if (artworks[i].getName().toLowerCase().contains(name.toLowerCase())) {
				searchArtworks.add(artworks[i]);
			}
		}
		
		if (searchArtworks.size() > 0) {
			return searchArtworks.toArray(new Artwork[searchArtworks.size()]);
		}
		
		return null;
	}
	
	/**
	 * <h1>searchByAddress</h1>
	 * <p>public Artwork[] searchByAddress(String address)</p>
	 * <p>Takes in a String and returns an array of all
	 * artworks that have an address containing the keyword given.</p>
	 * @param address - The address of the Artwork being searched for
	 * @return all the artworks that have an address containing the given keyword
	 */
	public Artwork[] searchByAddress(String address) {
		ArrayList<Artwork> searchArtworks = new ArrayList<Artwork>();

		for (int i = 0; i < artworks.length; i++) {
			if (artworks[i].getAddress().toLowerCase().contains(address.toLowerCase())) {
				searchArtworks.add(artworks[i]);
			}
		}
		
		if (searchArtworks.size() > 0) {
			return searchArtworks.toArray(new Artwork[searchArtworks.size()]);
		}
		
		return null;
	}
	
	/**
	 * <h1>searchByArtistName</h1>
	 * <p>public Artwork[] searchByArtistName(String name)</p>
	 * <p>Takes in a String representing the artists name and returns 
	 * an array of all artworks that have an artist name containing the keyword given.</p>
	 * @param artistName - The artist name of the Artwork being searched for
	 * @return all the artworks that have an artist name containing the given keyword
	 */
	public Artwork[] searchByArtistName(String artistName) {
		
		ArrayList<Artwork> searchArtworks = new ArrayList<Artwork>();

		for (int i = 0; i < artworks.length; i++) {
			if (artworks[i].getArtistName().toLowerCase().contains(artistName.toLowerCase())) {
				searchArtworks.add(artworks[i]);
			}
		}
		
		if (searchArtworks.size() > 0) {
			return searchArtworks.toArray(new Artwork[searchArtworks.size()]);
		}
		
		return null;
	}
	
	/**
	 * <h1>filterByArtType</h1>
	 * <p>public Artwork[] filterByArtTypes()</p>
	 * <p>Takes in a art type as a String and returns an array of 
	 * all artworks with the given fuel type.</p>
	 * <p>The art type entered must be one of the already existing art types
	 * stored in the "artTypes" list in this class. To view the existing art
	 * types call the getArtTypes() method.</p>
	 * @param artType - The art type the artworks are filtered by
	 * @return a list of all artworks with the art type specified
	 */
	public Artwork[] filterByArtType(String artType) {
		
		if (!artTypes.contains(artType)) {
			return null;
		}
		
		ArrayList<Artwork> filterArtworks = new ArrayList<Artwork>();
		
		for (int i = 0; i < artworks.length; i++) {
			if (artworks[i].getArtType().equalsIgnoreCase(artType)) {
				filterArtworks.add(artworks[i]);
			}
		}
		
		if (filterArtworks.size() > 0) {
			return filterArtworks.toArray(new Artwork[filterArtworks.size()]);
		}
		
		return null;
	}
	
	
	
	private ArrayList<String> readArtTypes(Artwork[] artworks) {
		ArrayList<String> artTypes = new ArrayList<String>();
		
		if (artworks != null) {
			artTypes.add(artworks[0].getArtType());
		}

		for (int i = 0; i < artworks.length; i++) {
				
			if (!artTypes.contains(artworks[i].getArtType())) {
				artTypes.add(artworks[i].getArtType());
			}
		}
		
		return artTypes;
	}
	
	private ArrayList<Artwork> readArtworks(JsonReader reader) throws IOException {
		ArrayList<Artwork> readArtworks = new ArrayList<Artwork>();

		reader.beginArray();
		while (reader.hasNext()) {
			readArtworks.add(readArtwork(reader));
		}
		reader.endArray();
		
		return readArtworks;
	}
	
	private Artwork readArtwork(JsonReader reader) throws IOException {
		ArrayList<Double> coordinates = null;
		String name = null;
		String address = null;
		String artistName = null;
		String artType = null;
		String summary = null;
		String description = null;
		
		reader.beginObject(); //Start of each artwork in json file
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
					if (property.equals("nm")) {
						name = reader.nextString();
					} else if (property.equals("adr")) {
						address = reader.nextString();
					} else if (property.equals("aNm")) {
						artistName = reader.nextString();
					} else if (property.equals("type")) {
						artType = reader.nextString();
					} else if (property.equals("summ")) {
						summary = reader.nextString();
					} else if (property.equals("desc")) {
						description = reader.nextString();
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
		
		return new Artwork(coordinates.get(0), coordinates.get(1), name, address, artistName,
				artType, summary, description);
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
