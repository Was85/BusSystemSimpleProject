package BusModel;

import java.util.ArrayList;

/**
 * The Class Bus. demonstrate all the bus fields , Declarations and
 * initialization of all bus fields in addition to getter and setter for all the
 * bus fields
 */
public class Bus {

	private int busSeatNumber = 40;

	private String busRegNum;

	private Route busRoute;

	private ArrayList<Passanger> pax;

	/**
	 * Instantiates a new bus.
	 *
	 * @param busRegNum
	 *            the bus reg num
	 * @param busRoute
	 *            the bus route
	 */
	public Bus(String busRegNum, Route busRoute) {

		this.busRegNum = busRegNum;
		pax = new ArrayList<>();
		this.busRoute = busRoute;

	}

	/**
	 * Instantiates a new bus. assign null to bus fields
	 */
	public Bus() {

		busRegNum = null;
		busRoute = new Route();
		pax = new ArrayList<>();

	}

	/**
	 * Sets the bus reg number.
	 *
	 * @param busRegNum
	 *            the new bus reg number
	 */
	public void setBusRegNumber(String busRegNum) {

		this.busRegNum = busRegNum;

	}

	/**
	 * Gets the bus reg number.
	 *
	 * @return the bus reg number
	 */
	public String getBusRegNumber() {

		return busRegNum;

	}

	/**
	 * Sets the route for each single bus that has been created
	 *
	 * @param route
	 *            the new route too set
	 */
	public void setRoute(Route route) {

		this.busRoute = route;

	}

	/**
	 * Gets the bus route.
	 *
	 * @return the bus route
	 */
	public Route getBusRoute() {

		return busRoute;

	}

	/**
	 * Gets the seat number.
	 *
	 * @return the seat number of bus
	 */
	public int getSeatNumber() {

		return busSeatNumber;
	}

	/**
	 * Sets the seat number of the new bus if desired
	 *
	 * @param busSeatNumber
	 *            the seat number
	 */
	public void setSeatNumber(int busSeatNumber) {
		this.busSeatNumber = busSeatNumber;
	}

	/**
	 * Adds the passenger to the bus.
	 *
	 * @param passenger
	 *            adding the passenger to the bus
	 */
	public void addPassengerToTheBus(Passanger passenger) {

		pax.add(passenger);
	}

}
