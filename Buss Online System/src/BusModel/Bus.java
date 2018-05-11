package BusModel;

import java.util.ArrayList;

/**
 * The Class Bus. demonstrate all the bus fields , Declarations and
 * initialization of all bus fields in addition to getter and setter for all the
 * bus fields
 */
public class Bus {

	private int busSeatNumber;

	private String busRegNum;

	private Route busRoute;

	private ArrayList<Passanger> paxInTheBus;

	/**
	 * Instantiates a new bus.
	 *
	 * @param busRegNum
	 *            the bus reg num
	 * @param busRoute
	 *            the bus route
	 * @param seatNumbers
	 *            , represent default seatNumber
	 */
	public Bus(String busRegNum, Route busRoute) {
		this.busRoute = busRoute;
		this.busRegNum = busRegNum;
		busSeatNumber = 40;
		paxInTheBus = new ArrayList<>();

	}

	/**
	 * Instantiates a new bus.
	 *
	 * @param busRegNum
	 *            the bus reg num
	 * @param busRoute
	 *            the bus route
	 * @param seatNumbers
	 */

	public Bus(String busRegNum, Route busRoute, int busSeatNumber) {

		this.busSeatNumber = busSeatNumber;
		this.busRegNum = busRegNum;
		this.busRoute = busRoute;

		paxInTheBus = new ArrayList<>();
	}

	/**
	 * @return the busSeatNumber
	 */
	public int getBusSeatNumber() {
		return busSeatNumber;
	}

	/**
	 * @param busSeatNumber
	 *            is used to set the busSeatNumber
	 */
	public void setBusSeatNumber(int busSeatNumber) {
		this.busSeatNumber = busSeatNumber;
	}

	/**
	 * @return the busRegNum
	 */
	public String getBusRegNum() {
		return busRegNum;
	}

	/**
	 * @param busRegNum
	 *            the busRegNum to set
	 */
	public void setBusRegNum(String busRegNum) {
		this.busRegNum = busRegNum;
	}

	/**
	 * @return the busRoute
	 */
	public Route getBusRoute() {
		return busRoute;
	}

	/**
	 * @param busRoute
	 *            the busRoute to set
	 */
	public void setBusRoute(Route busRoute) {
		this.busRoute = busRoute;
	}

	/**
	 * @return the paxInTheBus
	 */
	public ArrayList<Passanger> getPaxInTheBus() {
		return paxInTheBus;
	}

	/**
	 * 
	 * @param newPax
	 *            that made the current booking to be added to the bus passenger
	 * 
	 *            list and then increment the bus seat number
	 */
	public void addNewPax(Passanger newPax) {

		paxInTheBus.add(newPax);

		busSeatNumber--;

	}

}
