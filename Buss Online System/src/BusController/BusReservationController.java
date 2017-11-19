package BusController;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import BusModel.*;

/**
 * The Class BusReservationController. used to maintain the interaction between
 * the view and the model class contains all the logical method that perform
 * operation action on view console.
 */
public class BusReservationController {

	private static final String COMMA_DELIMITER = ",";

	private DataBase DbCtr;

	private Passanger pax;

	private HashMap<Integer, java.sql.Time> intervals;

	private int start;

	private int end;

	private int freq;

	String Email = null;

	String Name = null;

	String mobile = null;

	private int twoDm[][];

	private int i, j, k = 1;

	private int ans;

	boolean seatbooked = false;

	private HistoryReservation reservationHistory;

	/**
	 * Instantiates a new bus reservation controller that will create the local
	 * database by reading the external database(CSV files)
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public BusReservationController() throws IOException {

		DbCtr = new DataBase();
		intervals = new HashMap<>();
		twoDm = new int[10][4];
		reservationHistory = new HistoryReservation();
		getTimeTableFromCsvToLocalDateBase();
		getRouteDetailsFromCscAndToRoutList();
		getBusDetailsFromCsvAndSaveToListOfBusses();

	}

	/**
	 * Nex pax. get Passenger information after validating them from the view
	 * and create new passenger and then add to bus list and data base
	 *
	 * @param paxInfo
	 *            the pax info as array
	 */
	public void nexPax(String[] paxInfo) {

		for (int i = 0; i < paxInfo.length; i++) {

			if (paxInfo[i].contains("@") && paxInfo[i].contains(".")) {
				Email = paxInfo[i];
			} else if (paxInfo[i].matches(".*\\d+.*")) {
				mobile = paxInfo[i];
			} else if (paxInfo[i].matches("[a-zA-Z]+")) {
				Name = paxInfo[i];
			}
		}
		Passanger pax = new Passanger(Name, mobile, Email);

		for (int i = 0; i < DbCtr.BussesList().size(); i++) {

			if (DbCtr.BussesList().get(i).getSeatNumber() == 0) {

				DbCtr.BussesList().get(i + 1).addPassengerToTheBus(pax);

			} else {
				DbCtr.BussesList().get(i).addPassengerToTheBus(pax);
			}
		}
		DbCtr.addPaxTothePassangerLocalDataBase(Name, pax);

	}

	/**
	 * Check selection validity by compare the selection as (id) to the route id
	 * in rout list
	 *
	 * @param selection
	 *            the selection made by the user
	 * @return true, if successful the selection match any rout in the route
	 *         list
	 */
	public boolean checkSelectionValidaity(String selection) {
		for (Route route : routeList())
			if (route.getrouteId().equals(selection)) {
				return true;
			}
		return false;
	}

	/**
	 * Passenger name who made the current booking.
	 *
	 * @return the string represent the passenger name
	 */
	public String passangerNameWhoMadeTheCurrentBooking() {
		return Name;

	}

	/**
	 * Passenger mobile who made the current booking.
	 *
	 * @return the string represent the mobile number for the passenger who made
	 *         the reservation
	 */
	public String passangerMobileWhoMadeTheCurrentBooking() {
		return mobile;

	}

	/**
	 * Passenger email who made the current booking.
	 *
	 * @return the string represent the email for the passenger who made the
	 *         last booking
	 */
	public String passangerEmaileWhoMadeTheCurrentBooking() {
		return Email;

	}

	/**
	 * Check if the input number or char.
	 *
	 * @param nextLine
	 *            the next line
	 * @return true, if its not number
	 */
	public boolean checkIfTheInputNumberOrChar(String nextLine) {

		if (nextLine.matches("\\d+")) {
			return false;

		} else

			return true;

	}

	/**
	 * Numbering seat. gives number to the 2D arraylist that represent the seat
	 * number from 1 to 40
	 */
	public void numberingSeat() {

		int k = 1;
		for (i = 0; i < 10; i++) {
			for (j = 0; j < 4; j++) {
				twoDm[i][j] = k;
				k++;

			}
		}

	}

	/**
	 * Gets the numbered seat of 2D array
	 *
	 * @return the numbered seat
	 */
	public int[][] getNumberedSeat() {
		return twoDm;
	}

	/**
	 * Book seat in the bus
	 *
	 * @param seatNumber
	 *            the seat number selected by the user
	 * @return the seat number if the seat is booked correctly
	 */
	public int bookSeat(String seatNumber) {

		ans = Integer.parseInt(seatNumber);
		k = 1;

		// while (!(seatbooked)) {

		for (i = 0; i < 10; i++) {
			for (j = 0; j < 4; j++) {
				if (k == ans) {
					if (twoDm[i][j] == 0) {
						return 0;
					} else {
						twoDm[i][j] = 0;
						seatbooked = true;

					}
				}
				k++;
			}

		}
		// }
		if (seatbooked) {
			return ans;
		} else {
			return 0;
		}

	}

	/**
	 * Gets the route details from CSV and to the rout list in the local data
	 * base Route Creation and initialization
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */

	public void getRouteDetailsFromCscAndToRoutList() throws IOException {
		{

			BufferedReader br = null;

			// Reading the csv file
			br = new BufferedReader(new FileReader("/Users/waseem/workspace/Buss Online System/Route.csv"));

			String line = "";
			// Read to skip the header
			br.readLine();
			// Reading from the second line

			while ((line = br.readLine()) != null) {
				Route route = new Route();
				String[] RouteDetails = line.split(COMMA_DELIMITER);

				route.setOrigin(RouteDetails[0]);
				route.setDestination(RouteDetails[1]);
				route.setTripeDuration(RouteDetails[2]);
				route.setcost(RouteDetails[3]);
				route.setrouteId(RouteDetails[4]);
				DbCtr.addRouteToRouteList(route);

			}

			br.close();
		}
	}

	/**
	 * Gets the bus details from CSV and save to list of busses in the local
	 * database busses Creation and initialization
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */

	public void getBusDetailsFromCsvAndSaveToListOfBusses() throws IOException {
		{

			BufferedReader br = null;

			// Reading the csv file
			br = new BufferedReader(new FileReader("/Users/waseem/workspace/Buss Online System/Bus.csv"));

			String line = "";
			// Read to skip the header
			br.readLine();
			// Reading from the second line

			while ((line = br.readLine()) != null) {
				Bus bus = new Bus();
				String[] BusDetails = line.split(COMMA_DELIMITER);

				bus.setBusRegNumber(BusDetails[0]);

				Route route = getRouteForCertainBus(BusDetails[1]);
				bus.setRoute(route);
				DbCtr.addBusToBussesList(bus);

			}
			br.close();
		}
	}

	/**
	 * Gets the route for certain bus by compare the route id selected by the
	 * user with the route id for each bus
	 *
	 * @param routid
	 *            the routid
	 * @return the busroute for certain bus
	 */
	public Route getRouteForCertainBus(String routid) {
		Route busRoute = null;
		for (Route route : DbCtr.routeList()) {
			if (route.getrouteId().equals(routid))
				busRoute = route;
		}
		return busRoute;
	}

	/**
	 * Gets the time table from csv to local date base. time table creatipon and
	 * initial
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */

	public void getTimeTableFromCsvToLocalDateBase() throws IOException {

		BufferedReader br = null;

		// Reading the csv file
		br = new BufferedReader(new FileReader("/Users/waseem/workspace/Buss Online System/TimeTable.csv"));
		String line = "";
		// Read to skip the header
		br.readLine();
		// Reading from the second line

		while ((line = br.readLine()) != null) {

			TimeTable time = new TimeTable();
			String[] TimeTablaeDetails = line.split(COMMA_DELIMITER);

			time.setId(TimeTablaeDetails[0]);
			time.setStartDate(Integer.parseInt(TimeTablaeDetails[1]));
			time.setEndTime(Integer.parseInt(TimeTablaeDetails[2]));
			time.setFrequency(Integer.parseInt(TimeTablaeDetails[3]));

			DbCtr.addTimeTableToList(time);
		}
		br.close();
	}

	// potential destination list

	/**
	 * Potential destination for selected origin. as the system work by suggest
	 * the potential destination for any selected origin
	 *
	 * @param selectionMade
	 *            the selection made by the user for the origin (from)
	 * @return the hash map contains all the destination(To) for certain
	 *         origin(from)
	 */
	public HashMap<String, String> potentialDestinationForSelectedOrigin(String selectionMade) {
		HashMap<String, String> potentialDestination = new HashMap<>();

		String originslection = allOriginList().get(selectionMade);

		for (Map.Entry<String, String> entry : allOriginList().entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();

			if (value.equals(originslection)) {

				String destination = destinationList().get(key);

				potentialDestination.put(key, destination);
			}

		}

		return potentialDestination;
	}

	/**
	 * Destination list.
	 *
	 * @return the hash map contains all destination list
	 */
	public HashMap<String, String> destinationList() {
		HashMap<String, String> destinationList = new HashMap<String, String>();

		for (Route route : DbCtr.routeList()) {
			String destination = route.getDestination();
			String id = route.getrouteId();

			destinationList.put(id, destination);

		}

		return destinationList;

	}

	/**
	 * All origin list.
	 *
	 * @return the hash map contains all the origin list
	 */
	public HashMap<String, String> allOriginList() {
		HashMap<String, String> allOriginList = new HashMap<String, String>();

		for (Route route : DbCtr.routeList()) {
			String destination = route.getOrigin();
			String id = route.getrouteId();

			allOriginList.put(id, destination);

		}

		return allOriginList;

	}

	/**
	 * All destination list.
	 *
	 * @return the hash map
	 */
	/*
	 * public HashMap<String, String> allDestinationList() { HashMap<String,
	 * String> allDestinaionList = new HashMap<String, String>();
	 * 
	 * for (Route route : DbCtr.routeList()) { String destination =
	 * route.getOrigin(); String id = route.getrouteId();
	 * 
	 * allDestinaionList.put(id, destination);
	 * 
	 * }
	 * 
	 * return allDestinaionList;
	 * 
	 * }
	 */

	/**
	 * Origin list. used to get all the origin list without duplication
	 *
	 * @return the hash map contains all the origin list without any duplication
	 */
	public HashMap<String, String> OriginList() {
		HashMap<String, String> routeMap = new HashMap<>();
		HashMap<String, String> temp = new HashMap<>();

		for (Route route : DbCtr.routeList()) {
			String id = route.getrouteId();
			String origin = route.getOrigin();

			routeMap.put(id, origin);

		}

		Set<String> keys = routeMap.keySet(); // The set of keys in the map.

		Iterator<String> keyIter = keys.iterator();

		while (keyIter.hasNext()) {
			String key = keyIter.next();
			String value = routeMap.get(key);
			temp.put(value, key);
		}
		routeMap = new HashMap<String, String>();
		keys = temp.keySet(); // The set of keys in the map.

		keyIter = keys.iterator();

		while (keyIter.hasNext()) {
			String key = keyIter.next();
			String value = temp.get(key);
			routeMap.put(value, key);
		}

		return routeMap;
	}

	// Return specific route

	/**
	 * Gets the route selected by the user through searching inside route list
	 * and get route that have the same Id
	 *
	 * @param id
	 *            the id of the selected route
	 * @return the route matched the user id request
	 */
	public Route getTheRouteSelected(String id) {
		for (Route route : DbCtr.routeList()) {

			route.getrouteId().equals(id);
			return route;
		}
		return null;

	}

	/**
	 * Route list.
	 *
	 * @return the array list contains all the route
	 */
	public ArrayList<Route> routeList() {

		return DbCtr.routeList();

	}

	/**
	 * Time list for the selected route. by comparing the timetable id with the
	 * route id selected by the user and get the matched timetable for certain
	 * route.
	 *
	 * @param selectedRouteID
	 *            the selected route ID
	 * @return the hash map that contain all the time frequency for the intend
	 *         route
	 */
	public HashMap<Integer, java.sql.Time> timeListForTheSelectedRoute(String selectedRouteID) {

		for (Iterator<TimeTable> iterator = DbCtr.timeTableList().iterator(); iterator.hasNext();) {
			TimeTable timing = iterator.next();
			if (timing.getId().equals(selectedRouteID)) {

				start = timing.getStartDate();
				end = timing.getEndTime();
				freq = timing.getFrequency();

			}
		}

		return CreatTimeList(start, end, freq);

	}

	/**
	 * Create time list. its received three parameter (Start , end ,frequency)
	 * and create Time list accordingly
	 *
	 * @param start
	 *            the start time for the bus (first bus at 9:am)
	 * @param end
	 *            the end time for the bus (Last bus at 18:00pm)
	 * @param frequency
	 *            the frequency of busses ( bus every 30 mint)
	 * @return the hash map contains all the timing list based on the three
	 *         parameter received.
	 */
	@SuppressWarnings("deprecation")
	public HashMap<Integer, java.sql.Time> CreatTimeList(int start, int end, int frequency) {

		java.sql.Time startTime = new java.sql.Time(start, 0, 0);
		java.sql.Time endTime = new java.sql.Time(end, 0, 0);
		int i = 1;
		intervals.put(i, startTime);
		Calendar cal = Calendar.getInstance();
		cal.setTime(startTime);
		while (cal.getTime().before(endTime)) {
			i++;
			cal.add(Calendar.MINUTE, frequency);
			intervals.put(i, new java.sql.Time(cal.getTimeInMillis()));

		}

		return intervals;
	}

	/**
	 * Catch input date as string convert it to DATE and compare if the selected
	 * date before the current Date.
	 *
	 * @param receivedDate
	 *            the received date is the input date
	 * @return the date if the date is valid otherwise return null
	 */
	public Date catchInputDate(String receivedDate) {

		DateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
		Date date = null;
		try {
			int[] dateDetails = new int[3];
			Calendar cal = new GregorianCalendar();
			date = dateformat.parse(receivedDate);
			Date today = cal.getTime();

			if (date.after(today)) {

				return date;

			} else
				return null;

		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * Time frequency list.
	 *
	 * @return the hash map contains the timelist
	 */
	public HashMap<Integer, java.sql.Time> TimeFrequencyList() {

		return intervals;

	}

	/**
	 * Calculate arrival time.
	 *
	 * @param DepartureTime
	 *            the departure time
	 * @param tripDuration
	 *            the trip duration time
	 * @return the string representing arrival time
	 */

	public String calculateArrivalTime(String DepartureTime, int tripDuration) {

		SimpleDateFormat departingTime = new SimpleDateFormat("HH:mm:ss");

		Date d;
		try {
			d = departingTime.parse(DepartureTime);
		} catch (ParseException e) {
			return "Wrong Format";
		}
		Calendar DepTime = new GregorianCalendar();
		DepTime.setTime(d);
		DepTime.add(Calendar.HOUR, tripDuration);
		Date ArrivalTime = DepTime.getTime();

		return ArrivalTime.getHours()+":"+ ArrivalTime.getMinutes();

	}

	/**
	 * Gets the reservation history.
	 *
	 * @return the reservationHistory
	 */
	public HistoryReservation getReservationHistory() {
		return reservationHistory;
	}

	/**
	 * Sets the reservation history to the local data base.
	 *
	 * @param routeId
	 *            the route id
	 * @param passangerName
	 *            the passenger name
	 * @param busId
	 *            the bus id
	 * @param seatNumber
	 *            the seat number
	 * @param travelTime
	 *            the travel time
	 * @param travelDate
	 *            the travel date
	 */

	public void setReservationHistoryToTheLocalDataBase(String routeId, String passangerName, String busId,
			String seatNumber, String travelTime, String travelDate) {
		HistoryReservation reservation = new HistoryReservation();

		reservation.setRouteId(routeId);
		reservation.setPassangerName(passangerName);
		reservation.setBusId(busId);
		reservation.setSeatNumber(seatNumber);
		reservation.setTravelTime(travelTime);
		reservation.setTravelDate(travelDate);

		DbCtr.addBookingToHistoryList(reservation);
	}

	/**
	 * Gets the bus regnum for certain route.
	 *
	 * @param routeId
	 *            the route id
	 * @return the bus regnum for certain route
	 */
	public String getbusRegNumForCertainRoute(String routeId) {
		String busRegId = null;
		for (Bus bus : DbCtr.BussesList()) {

			if (bus.getBusRoute().getrouteId().equals(routeId)) {
				busRegId = bus.getBusRegNumber();
			}

		}

		return busRegId;
	}

	/**
	 * Convert date to string.
	 *
	 * @param date
	 *            the date
	 * @return the string
	 */
	public String convertDateToString(Date date) {
		DateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
		return dateformat.format(date);
	}

	/**
	 * Write route list from system to the external file.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void writeRouteListFromSystemToTheExternalFile() throws IOException {

		FileWriter pw = new FileWriter("/Users/waseem/workspace/Buss Online System/ReservationHistory.csv", true);
		StringBuilder sb = new StringBuilder();

		for (HistoryReservation book : DbCtr.reservationHistoryList()) {
			sb.append(book.getRouteId());
			sb.append(',');
			sb.append(book.getPassangerName());
			sb.append(',');
			sb.append(book.getBusId());
			sb.append(',');
			sb.append(book.getSeatNumber());
			sb.append(',');
			sb.append(book.getTravelTime());
			sb.append(',');
			sb.append(book.getTravelDate());
			sb.append("\n");

		}

		pw.write(sb.toString());
		pw.close();

	}
}
