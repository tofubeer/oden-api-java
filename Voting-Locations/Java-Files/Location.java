/**This file was created for Terratap-Technologies-Inc by 
 * Cody Clattenburg, Sam Collins, Martin Suryadi, and Sergio Josue Villegas. 
 * This file is under the protection of the Apache 2.0 License.
 **/

package votingLocations;

/**
 * <p>The Location class is used to represent a Voting Location. 
 * It holds the information for the location's latitude, longitude, name, 
 * and address.</p>
 * 
 * <p>The Location class contains getters used to retrieve the various 
 * pieces of information about the Location.</p>
 * 
 * <p>This class is to be used in conjunction with the VotingLocationList class
 * which extracts data from a Json file that is then passed to the Location
 * constructor.</p>
 * 
 * @author Sam Collins
 * @version 1.0
 */
public class Location {

	private double latitude;
	private double longitude;
	private String name;
	private String address;
	
	/**
	 * <h1>Location</h1>
	 * <p>Location(double latitude, double longitude, String name, String address)</p>
	 * <p>Creates a new Station when given latitude, longitude, name, and address.</p>
	 * @param latitude - The latitude of the Voting Location
	 * @param longitude - The longitude of the Voting Location
	 * @param name - The name of the Voting Location
	 * @param address - The address of the Voting Location
	 */
	public Location(double latitude, double longitude, String name, String address) {
			
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.address = address;
	}
	
	/**
	 * <h1>displayLocation</h1>
	 * <p>displayLocation()</p>
	 * <p>Prints a formatted description of the Voting Location to System.out</p>
	 */
	public void displayLocation() {
		System.out.println("Name: " + this.getName() + "\nLatitude: " + this.getLatitude()
				+ "\nLongitude: " + this.getLongitude() + "\nAddress: " + this.getAddress());
	}

	/**
	 * <h1>getLatitude</h1>
	 * <p>getLatitude()</p>
	 * <p>Retrieves the latitude of the Voting Location.</p>
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * <h1>getLongitude</h1>
	 * <p>getLongitude()</p>
	 * <p>Retrieves the longitude of the Voting Location.</p>
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * <h1>getName</h1>
	 * <p>getName()</p>
	 * <p>Retrieves the name of the Voting Location.</p>
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * <h1>getAddress</h1>
	 * <p>getAddress()</p>
	 * <p>Retrieves the address of the Voting Location.</p>
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
}
