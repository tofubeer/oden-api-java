/**This file was created for Terratap-Technologies-Inc by 
 * Cody Clattenburg, Sam Collins, Martin Suryadi, and Sergio Josue Villegas. 
 * This file is under the protection of the Apache 2.0 License.
 **/

package publicArt;

/**
 * <p>The Artwork class is used to represent a piece of Public Art. 
 * It holds the information for the Artwork's latitude, longitude, name, 
 * address, artist's name, art type, summary, and description.</p>
 * 
 * <p>The Artwork class contains getters used to retrieve the various 
 * pieces of information about the Artwork.</p>
 * 
 * <p>This class is to be used in conjunction with the ArtworkList class
 * which extracts data from a Json file that is then passed to the Artwork
 * constructor.</p>
 * 
 * @author Sam Collins
 * @version 1.0
 */
public class Artwork {
	
	private double latitude;
	private double longitude;
	private String name;
	private String address;

	private String artistName;
	private String artType;
	private String summary;
	private String description;

	/**
	 * <h1>Artwork</h1>
	 * <p>Artwork(double latitude, double longitude, String name, String address,
			String artistName, String artType, String summary, String description)</p>
	 * <p>Creates a new Station when given latitude, longitude, name, address, 
	 * artistName, artType, summary, and description.</p>
	 * @param latitude - The latitude of the Artwork
	 * @param longitude - The longitude of the Artwork
	 * @param name - The name of the Artwork
	 * @param address - The address of the Artwork
	 * @param artistName - The Name of the Artist
	 * @param artType - The art type of the Artwork
	 * @param summary - The summary of the Artwork
	 * @param description - The description of the Artwork
	 */
	public Artwork(double latitude, double longitude, String name, String address,
			String artistName, String artType, String summary, String description) {
		
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.address = address;
		
		this.artistName = artistName;
		this.artType = artType;
		this.summary = summary;
		this .description = description;
		
	}
	
	/**
	 * <h1>displayArtwork</h1>
	 * <p>displayArtwork()</p>
	 * <p>Prints a formatted description of the Artwork to System.out</p>
	 */
	public void displayArtwork() {
		System.out.println("Name: " + this.getName() + "\nLatitude: " + this.getLatitude()
				+ "\nLongitude: " + this.getLongitude() + "\nAddress: " + this.getAddress()
				+ "\nArtist Name: " + this.getArtistName() + "\nArt Type: " + this.getArtType()
				+ "\nSummary: " + this.getSummary() + "\nDescription: " + this.getDescription());
	}

	/**
	 * <h1>getLatitude</h1>
	 * <p>getLatitude()</p>
	 * <p>Retrieves the latitude of the Artwork.</p>
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * <h1>getLongitude</h1>
	 * <p>getLongitude()</p>
	 * <p>Retrieves the longitude of the Artwork.</p>
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * <h1>getName</h1>
	 * <p>getName()</p>
	 * <p>Retrieves the name of the Artwork.</p>
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * <h1>getAddress</h1>
	 * <p>getAddress()</p>
	 * <p>Retrieves the address of the Artwork.</p>
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * <h1>getArtistName</h1>
	 * <p>getArtistName()</p>
	 * <p>Retrieves the name of the Artist.</p>
	 * @return the artist name
	 */
	public String getArtistName() {
		return artistName;
	}

	/**
	 * <h1>getArtType</h1>
	 * <p>getArtType()</p>
	 * <p>Retrieves the art type of the Artwork.</p>
	 * @return the art type
	 */
	public String getArtType() {
		return artType;
	}

	/**
	 * <h1>getSummary</h1>
	 * <p>getSummary()</p>
	 * <p>Retrieves the summary of the Artwork.</p>
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * <h1>getDescription</h1>
	 * <p>getDescription()</p>
	 * <p>Retrieves the description of the Artwork.</p>
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
}
