package BusModel;

public class TimeTable {

	private int startingTime;
	private int endingTime;
	private int frequency;
	private String iD;

	public TimeTable() {

		startingTime = 0;
		endingTime = 0;
		frequency = 0;
		iD = null;

	}

	public TimeTable(int startingTime, int endingTime, int frequency, String id) {

		this.startingTime = startingTime;
		this.endingTime = endingTime;
		this.frequency = frequency;
		this.iD = id;
		// this.year = year;
		// this.month = month;
		// this.day = day;

	}

	public void setStartDate(int startingTime) {

		this.startingTime = startingTime;

	}

	public void setId(String iD) {

		this.iD = iD;

	}

	public String getId() {

		return iD;

	}

	public void setEndTime(int endingTime) {

		this.endingTime = endingTime;

	}

	public void setFrequency(int frequency) {

		this.frequency = frequency;

	}

	public int getStartDate() {

		return startingTime;

	}

	public int getEndTime() {

		return endingTime;

	}

	public int getFrequency() {

		return frequency;

	}

}
