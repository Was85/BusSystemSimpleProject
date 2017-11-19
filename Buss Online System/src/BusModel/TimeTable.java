package BusModel;

/**
 * The Class TimeTable. used in creating the time table frequency for each trip,
 * the three attributes used to specify the start time for the first bus and the
 * end time and the frequency of busses per day(every 30 mint or every 1 hour)
 */
public class TimeTable {

	private int startingTime;

	private int endingTime;

	private int frequency;

	private String iD;

	/**
	 * Instantiates a new time table.
	 */
	public TimeTable() {

		startingTime = 0;
		endingTime = 0;
		frequency = 0;
		iD = null;

	}

	/**
	 * Instantiates a new time table.
	 *
	 * @param startingTime
	 *            the starting time is indicating the first bus for certain
	 *            route will be at certain time(StartingTime)
	 * @param endingTime
	 *            the ending time indicating the last bus for certain route
	 * @param frequency
	 *            the frequency indicating the repetition of busses for single
	 *            route (every n minutes bus available)
	 * @param id
	 *            the id for each timeTable there is an id used to distinguish
	 *            between timetable used in the system
	 */
	public TimeTable(int startingTime, int endingTime, int frequency, String id) {

		this.startingTime = startingTime;
		this.endingTime = endingTime;
		this.frequency = frequency;
		this.iD = id;

	}

	/**
	 * Sets the start date.
	 *
	 * @param startingTime
	 *            the new start date for certain route(first bus start at
	 *            <StartingTime>)
	 */
	public void setStartDate(int startingTime) {

		this.startingTime = startingTime;

	}

	/**
	 * Sets the id. setting id for each timeTable
	 *
	 * @param iD
	 *            the new id
	 */
	public void setId(String iD) {

		this.iD = iD;

	}

	/**
	 * Gets the id of the timeTable
	 *
	 * @return the id
	 */
	public String getId() {

		return iD;

	}

	/**
	 * Sets the end time for certain route(last bus will be at <EndTime>)
	 *
	 * @param endingTime
	 *            the new end time
	 */
	public void setEndTime(int endingTime) {

		this.endingTime = endingTime;

	}

	/**
	 * Sets the frequency time for each bus (repetition of busses )
	 *
	 * @param frequency
	 *            the new frequency
	 */
	public void setFrequency(int frequency) {

		this.frequency = frequency;

	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public int getStartDate() {

		return startingTime;

	}

	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	public int getEndTime() {

		return endingTime;

	}

	/**
	 * Gets the frequency.
	 *
	 * @return the frequency
	 */
	public int getFrequency() {

		return frequency;

	}

}
