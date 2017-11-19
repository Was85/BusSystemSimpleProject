package BusModel;

/**
 * The Class HistoryReservation is used to save unique details for each single
 * new reservation has been made to DataBase both local and External(CSV in this
 * case) for further use.
 */
public class HistoryReservation {

	private String routeId;

	private String passangerName;

	private String busId;

	private String seatNumber;

	private String travelTime;

	private String travelDate;

	/**
	 * Instantiates a new history reservation.
	 */
	public HistoryReservation() {

		routeId = null;
		passangerName = null;
		busId = null;
		seatNumber = null;
		travelTime = null;
		travelDate = null;

	}

	/**
	 * Gets the route id for certain booking inside the Historylist
	 *
	 * @return the routeId
	 */
	public String getRouteId() {
		return routeId;
	}

	/**
	 * Sets the route id for the new booking that made by the customer
	 *
	 * @param routeId
	 *            the routeId of the new reservation to set
	 */
	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	/**
	 * Gets the passenger name from the history ,maybe used for search by name
	 *
	 * @return the passangerName
	 */
	public String getPassangerName() {
		return passangerName;
	}

	/**
	 * Sets the passenger name of the passenger that who made the last booking
	 * on system
	 *
	 * @param passangerName
	 *            the passangerName who made the reservation to set
	 */
	public void setPassangerName(String passangerName) {
		this.passangerName = passangerName;
	}

	/**
	 * Gets the bus id for certain book from the history database
	 *
	 * @return the busId it will return the busID for a certain book thats
	 *         already made before
	 */
	public String getBusId() {
		return busId;
	}

	/**
	 * Sets the bus id for the passenger that who made the last booking on
	 * system which bus assign for his trip
	 *
	 * @param busId
	 *            the busId to set
	 */
	public void setBusId(String busId) {
		this.busId = busId;
	}

	/**
	 * Gets the seat number for a certain reservation that made in past
	 *
	 * @return the seatNumber of passenger
	 */
	public String getSeatNumber() {
		return seatNumber;
	}

	/**
	 * Sets the seat number.
	 *
	 * @param seatNumber
	 *            the seatNumber of the passenger who made the reservation to
	 *            set
	 */
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	/**
	 * Gets the travel time for certain reservation from history.
	 *
	 * @return the travelTime (departure time) for a specific reservation from
	 *         history
	 */
	public String getTravelTime() {
		return travelTime;
	}

	/**
	 * Sets the travel time of the passenger that who made the last reservation
	 * on system
	 *
	 * @param travelTime
	 *            the travelTime to set in history list
	 */
	public void setTravelTime(String travelTime) {
		this.travelTime = travelTime;
	}

	/**
	 * Gets the travel date of certain reservation from history list
	 *
	 * @return the travelDate
	 */
	public String getTravelDate() {
		return travelDate;
	}

	/**
	 * Sets the travel date of the last booking made on system by the passenger
	 *
	 * @param travelDate
	 *            the travelDate to set
	 */
	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}

}