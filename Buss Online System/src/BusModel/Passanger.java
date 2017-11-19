package BusModel;

/**
 * The Class Passenger. contains all the passenger information (fields), 
 * that the system need when make a reservation.
 */
public class Passanger {

	private String name;
	
	private String email;
	
	private String mobile;

	/**
	 * Instantiates a new passenger.
	 */
	public Passanger() {

		this.name = null;
		this.email = null;
		this.mobile = null;

	}

	/**
	 * Instantiates a new passanger.
	 *
	 * @param name   the name of the new passenger
	 * @param email  the email of the new passenger
	 * @param mobile the mobile of the passenegr 
	 */
	public Passanger(String name, String email, String mobile) {

		this.name = name;
		this.email = email;
		this.mobile = mobile;

	}

	/**
	 * Sets the passenger name.
	 *
	 * @param name of the new passenger name
	 */
	public void setPaxName(String name) {

		this.name = name;

	}

	/**
	 * Gets the passenger name.
	 *
	 * @return the passenger name
	 */
	public String getPaxName() {

		return name;

	}

	/**
	 * Sets the passenger mobile number.
	 *
	 * @param mobile the new passenger mobile number
	 */
	public void setPaxMobileNumber(String mobile) {

		this.mobile = mobile;

	}

	/**
	 * Gets the passenger mobile number.
	 *
	 * @return the passenger mobile number
	 */
	public String getPaxMobileNumber() {

		return mobile;

	}

	/**
	 * Sets the passenger email.
	 *
	 * @param email the new passenger email
	 */
	public void setPaxEmail(String email) {

		this.email = email;

	}

	/**
	 * Gets the passenger email.
	 *
	 * @return the passenger email
	 */
	public String getPaxEmail() {

		return email;

	}

}
