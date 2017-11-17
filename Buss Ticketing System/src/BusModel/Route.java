package BusModel;

public class Route {

	private String origin;
	private String destination;
	private String tripDuration;
	private String cost;
	private String routeId;
	private TimeTable routeTime;

	public Route() {
		origin = null;
		destination = null;
		tripDuration = null;
		cost = null;
		routeId = null;
		routeTime = new TimeTable();

	}

	public Route(String origin, String destination, String tripDuration, String cost, String routeId,
			TimeTable routeTime) {

		this.origin = origin;
		this.destination = destination;
		this.tripDuration = tripDuration;
		this.cost = cost;
		this.routeId = routeId;
		this.routeTime = routeTime;
	}

	public void setOrigin(String from) {

		this.origin = from;

	}

	public String getOrigin() {

		return origin;

	}

	public void setDestination(String destination) {

		this.destination = destination;

	}

	public String getDestination() {

		return destination;

	}

	public void setTripeDuration(String tripDuration) {

		this.tripDuration = tripDuration;
	}

	public void setrouteId(String routeDetails) {

		this.routeId = routeDetails;
	}

	public void setcost(String cost) {

		this.cost = cost;
	}

	public String getTripeDuration() {

		return tripDuration;
	}

	public String getrouteId() {

		return routeId;
	}

}
