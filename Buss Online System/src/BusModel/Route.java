package BusModel;

/**
 * The Class Route. Contains all the Route details that other classes will need
 * it when perform a reservation
 * 
 */
public class Route {

	private String origin;

	private String destination;

	private String tripDuration;

	private String cost;

	private String routeId;

	private TimeTable routeTime;

	/**
	 * Instantiates a new route.
	 */
	public Route() {
		origin = null;
		destination = null;
		tripDuration = null;
		cost = null;
		routeId = null;
		routeTime = new TimeTable();

	}

	/**
	 * Instantiates a new route.
	 *
	 * @param origin
	 *            the origin of a certain trip ( from )
	 * @param destination
	 *            the destination of a certain trip ( To )
	 * @param tripDuration
	 *            the trip duration thats require for specific trip(from - To
	 *            )time
	 * @param cost
	 *            the cost is representing the price of the tkt from origin to
	 *            destination
	 * @param routeId
	 *            the route id represent identification number for each route
	 * @param routeTime
	 *            the route timeTable of this route or the frequency of trip
	 *            time per day
	 */
	public Route(String origin, String destination, String tripDuration, String cost, String routeId,
			TimeTable routeTime) {

		this.origin = origin;
		this.destination = destination;
		this.tripDuration = tripDuration;
		this.cost = cost;
		this.routeId = routeId;
		this.routeTime = routeTime;
	}

	/**
	 * Sets the origin . Its set the starting point(from) for certain route
	 *
	 * @param from
	 *            the new origin that the route start with
	 */
	public void setOrigin(String from) {

		this.origin = from;

	}

	/**
	 * Gets the origin. This method used to get the origin for the route
	 *
	 * @return the origin for certain route
	 */
	public String getOrigin() {

		return origin;

	}

	/**
	 * Sets the destination. its set the end point for the route(To)
	 *
	 * @param destination
	 *            the new destination
	 */
	public void setDestination(String destination) {

		this.destination = destination;

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
	 * Sets the trip duration. is used to set the duration time for certain
	 * route or trip between origin and destination
	 *
	 * @param tripDuration
	 *            the new trip duration is the for certain route between two
	 *            city
	 */
	public void setTripeDuration(String tripDuration) {

		this.tripDuration = tripDuration;
	}

	/**
	 * Sets the route id for the new route
	 *
	 * @param routeDetails
	 *            the new route id
	 */
	public void setrouteId(String routeDetails) {

		this.routeId = routeDetails;
	}

	/**
	 * Sets the cost. set the price of Ticket between two cities
	 *
	 * @param cost
	 *            the new cost to set
	 */
	public void setcost(String cost) {

		this.cost = cost;
	}

	/**
	 * Gets the tripe duration time between two cities (for certain route)
	 *
	 * @return the tripe duration time
	 */
	public String getTripeDuration() {

		return tripDuration;
	}

	/**
	 * Gets the route id for certain route
	 *
	 * @return the route id
	 */
	public String getrouteId() {

		return routeId;
	}

}
