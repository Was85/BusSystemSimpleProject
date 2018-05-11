package BusModel;

import java.util.ArrayList;

import com.mysql.fabric.xmlrpc.base.Array;


/**
 * The Class Route.contains all the Route information (fields), 
 * that the system need when make a reservation
 */
public class Route {

	
	private String origin ;
	

	private String destination ;
	
	private String duration ;
	
	private String time ;
	
	
	/**
	 * Instantiates a new route.
	 */
	
	public Route(){
		
		
	}

	/**
	 * Instantiates a new route.
	 *
	 * @param origin the origin
	 * @param destination the destination
	 * @param duration the duration
	 * @param time the time
	 */

	public Route(String origin, String destination, String duration, String time) {

		this.origin = origin;
		this.destination = destination;
		this.duration = duration;
		this.time = time;
		
		
	}

	/**
	 * Gets the origin.
	 *
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * Sets the origin.
	 *
	 * @param origin the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * Gets the destination.
	 *
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * Sets the destination.
	 *
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * Sets the duration.
	 *
	 * @param duration the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Sets the time.
	 *
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	
	
	
	
	
	
}
