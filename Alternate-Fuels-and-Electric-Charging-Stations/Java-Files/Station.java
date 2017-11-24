/**This file was created for Terratap-Technologies-Inc by 
 * Cody Clattenburg, Sam Collins, Martin Suryadi, and Sergio Josue Villegas. 
 * This file is under the protection of the Apache 2.0 License.
 **/

package alternateFuelStations;

/**
 * <p>The Station class is used to represent an alternative fueling station. 
 * It holds the information for the station's latitude, longitude, name, 
 * fuel type, address, and accessibility.</p>
 * 
 * <p>The Station class contains getters used to retrieve the various 
 * pieces of information about the Station.</p>
 * 
 * <p>This class is to be used in conjunction with the StationList class
 * which extracts data from a Json file that is then passed to the Station
 * constructor.</p>
 * 
 * @author Sam Collins
 * @version 1.0
 */
public class Station {

	private double latitude;
	private double longitude;
	private String name;
	
	private String fuelType;
	private String address;
	private String access;
	
	/**
	 * <h1>Station</h1>
	 * <p>Station(double latitude, double longitude, String name,
	 * String fuelType, String address, String access)</p>
	 * <p>Creates a new Station when given latitude, longitude, name, fuel type,
	 * address, and accessibility.</p>
	 * @param latitude - The latitude of the Station
	 * @param longitude - The longitude of the Station
	 * @param name - The name of the Station
	 * @param fuelType - The fuel type of the Station
	 * @param address - The address of the Station
	 * @param access - The accessibility of the Station
	 */
	public Station(double latitude, double longitude, String name,
			String fuelType, String address, String access) {
			
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.fuelType = fuelType;
		this.address = address;
		this.access = access;
	}
	
	/**
	 * <h1>displayStation</h1>
	 * <p>displayStation()</p>
	 * <p>Prints a formatted description of the Station to System.out</p>
	 */
	public void displayStation() {
		System.out.println("Name: " + this.getName() + "\nLatitude: " + this.getLatitude()
				+ "\nLongitude: " + this.getLongitude() + "\nFuel Type: " + this.getFuelType()
				+ "\nAddress: " + this.getAddress() + "\nAccess: " + this.getAccess());
	}

	/**
	 * <h1>getLatitude</h1>
	 * <p>getLatitude()</p>
	 * <p>Retrieves the latitude of the Station.</p>
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * <h1>getLongitude</h1>
	 * <p>getLongitude()</p>
	 * <p>Retrieves the longitude of the Station.</p>
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * <h1>getName</h1>
	 * <p>getName()</p>
	 * <p>Retrieves the name of the Station.</p>
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * <h1>getFuelType</h1>
	 * <p>getFuelType()</p>
	 * <p>Retrieves the fuel type of the Station.</p>
	 * @return the fuelType
	 */
	public String getFuelType() {
		return fuelType;
	}

	/**
	 * <h1>getAddress</h1>
	 * <p>getAddress()</p>
	 * <p>Retrieves the address of the Station.</p>
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * <h1>getAccess</h1>
	 * <p>getAccess()</p>
	 * <p>Retrieves the accessibility of the Station.</p>
	 * @return the access
	 */
	public String getAccess() {
		return access;
	}

}
