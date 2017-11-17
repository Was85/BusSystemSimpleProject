package BusModel;

public class Passanger {

	private String name;
	private String email;
	private String mobile;

	public Passanger() {

		this.name = null;
		this.email = null;
		this.mobile = null;

	}

	public Passanger(String name, String email, String mobile) {

		this.name = name;
		this.email = email;
		this.mobile = mobile;

	}

	public void setPaxName(String name) {

		this.name = name;

	}

	public String getPaxName() {

		return name;

	}

	public void setPaxMobileNumber(String mobile) {

		this.mobile = mobile;

	}

	public String getPaxMobileNumber() {

		return mobile;

	}

	public void setPaxEmail(String email) {

		this.email = email;

	}

	public String getPaxEmail() {

		return email;

	}

}
