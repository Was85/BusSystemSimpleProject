package BusModel;

public class HistoryReservation {

	private String routeId;
	private String passangerName;
	private String busId;
	private String seatNumber;
	private String travelTime;
	private String travelDate;

	public HistoryReservation() {

		routeId = null;
		passangerName = null;
		busId = null;
		seatNumber = null;
		travelTime = null;
		travelDate = null;

	}

	/**
	 * @return the routeId
	 */
	public String getRouteId() {
		return routeId;
	}

	/**
	 * @param routeId
	 *            the routeId to set
	 */
	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	/**
	 * @return the passangerName
	 */
	public String getPassangerName() {
		return passangerName;
	}

	/**
	 * @param passangerName
	 *            the passangerName to set
	 */
	public void setPassangerName(String passangerName) {
		this.passangerName = passangerName;
	}

	/**
	 * @return the busId
	 */
	public String getBusId() {
		return busId;
	}

	/**
	 * @param busId
	 *            the busId to set
	 */
	public void setBusId(String busId) {
		this.busId = busId;
	}

	/**
	 * @return the seatNumber
	 */
	public String getSeatNumber() {
		return seatNumber;
	}

	/**
	 * @param seatNumber
	 *            the seatNumber to set
	 */
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	/**
	 * @return the travelTime
	 */
	public String getTravelTime() {
		return travelTime;
	}

	/**
	 * @param travelTime
	 *            the travelTime to set
	 */
	public void setTravelTime(String travelTime) {
		this.travelTime = travelTime;
	}

	/**
	 * @return the travelDate
	 */
	public String getTravelDate() {
		return travelDate;
	}

	/**
	 * @param travelDate
	 *            the travelDate to set
	 */
	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}

}