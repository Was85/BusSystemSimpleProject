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

	public BusReservationController() throws IOException {

		DbCtr = new DataBase();
		intervals = new HashMap<>();
		twoDm = new int[10][4];
		reservationHistory = new HistoryReservation();
		getTimeTableFromCsvToLocalDateBase();
		getRouteDetailsFromCscAndToRoutList();
		getBusDetailsFromCsvAndSaveToListOfBusses();

	}

	// get Pax information from the view and creat new passanger and then add to
	// bus list and data base

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

	public boolean checkSelectionValidaity(String selection) {
		for (Route route : routeList())
			if (route.getrouteId().equals(selection)) {
				return true;
			}
		return false;
	}

	public String passangerNameWhoMadeTheCurrentBooking() {
		return Name;

	}

	public String passangerMobileWhoMadeTheCurrentBooking() {
		return mobile;

	}

	public String passangerEmaileWhoMadeTheCurrentBooking() {
		return Email;

	}

	public boolean checkIfTheInputNumberOrChar(String nextLine) {

		if (nextLine.matches("\\d+")) {
			return false;

		} else

			return true;

	}

	public void numberingSeat() {

		int k = 1;
		for (i = 0; i < 10; i++) {
			for (j = 0; j < 4; j++) {
				twoDm[i][j] = k;
				k++;

			}
		}

	}

	public int[][] getNumberedSeat() {
		return twoDm;
	}

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

	// Route Creation and initialization
	public void getRouteDetailsFromCscAndToRoutList() throws IOException {
		{

			BufferedReader br = null;

			// Reading the csv file
			br = new BufferedReader(new FileReader("/Users/waseem/workspace/Buss Ticketing System/Route.csv"));

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

	// busses Creation and initialization
	public void getBusDetailsFromCsvAndSaveToListOfBusses() throws IOException {
		{

			BufferedReader br = null;

			// Reading the csv file
			br = new BufferedReader(new FileReader("/Users/waseem/workspace/Buss Ticketing System/Bus.csv"));

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

	public Route getRouteForCertainBus(String routid) {
		Route busRoute = null;
		for (Route route : DbCtr.routeList()) {
			if (route.getrouteId().equals(routid))
				busRoute = route;
		}
		return busRoute;
	}

	// time table creatipon and initial
	public void getTimeTableFromCsvToLocalDateBase() throws IOException {

		BufferedReader br = null;

		// Reading the csv file
		br = new BufferedReader(new FileReader("/Users/waseem/workspace/Buss Ticketing System/TimeTable.csv"));

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

	public HashMap<String, String> destinationList() {
		HashMap<String, String> destinationList = new HashMap<String, String>();

		for (Route route : DbCtr.routeList()) {
			String destination = route.getDestination();
			String id = route.getrouteId();

			destinationList.put(id, destination);

		}

		return destinationList;

	}

	public HashMap<String, String> allOriginList() {
		HashMap<String, String> allOriginList = new HashMap<String, String>();

		for (Route route : DbCtr.routeList()) {
			String destination = route.getOrigin();
			String id = route.getrouteId();

			allOriginList.put(id, destination);

		}

		return allOriginList;

	}

	public HashMap<String, String> allDestinationList() {
		HashMap<String, String> allDestinaionList = new HashMap<String, String>();

		for (Route route : DbCtr.routeList()) {
			String destination = route.getOrigin();
			String id = route.getrouteId();

			allDestinaionList.put(id, destination);

		}

		return allDestinaionList;

	}

	// original list
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

	public Route getTheRouteSelected(String id) {
		for (Route route : DbCtr.routeList()) {

			route.getrouteId().equals(id);
			return route;
		}
		return null;

	}

	public ArrayList<Route> routeList() {

		return DbCtr.routeList();

	}

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

	public HashMap<Integer, java.sql.Time> TimeFrequencyList() {

		return intervals;

	}

	// replace with your start date string
	public String calculateArrivalTime(String DepartureTime) {

		SimpleDateFormat departingTime = new SimpleDateFormat(" HH:mm:ss");

		Date d;
		try {
			d = departingTime.parse("DepartureTime");
		} catch (ParseException e) {
			return "Wrong Format";
		}
		Calendar DepTime = new GregorianCalendar();
		DepTime.setTime(d);
		DepTime.add(Calendar.HOUR, 2);
		Date ArrivalTime = DepTime.getTime();

		return ArrivalTime.toString();

	}

	/**
	 * @return the reservationHistory
	 */
	public HistoryReservation getReservationHistory() {
		return reservationHistory;
	}

	/**
	 * @param reservationHistory
	 *            the reservationHistory Details to set
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

	public String getbusRegNumForCertainRoute(String routeId) {
		String busRegId = null;
		for (Bus bus : DbCtr.BussesList()) {

			if (bus.getBusRoute().getrouteId().equals(routeId)) {
				busRegId = bus.getBusRegNumber();
			}

		}

		return busRegId;
	}

	public String convertDateToString(Date date) {
		DateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
		return dateformat.format(date);
	}

	public void writeRouteListFromSystemToTheExternalFile() throws IOException {

		FileWriter pw = new FileWriter("/Users/waseem/workspace/Buss Ticketing System/ReservationHistory.csv", true);
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
