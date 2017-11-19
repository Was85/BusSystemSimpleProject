package BusModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Class DataBase. is a local dataBase inside the project used to 
 * store all the imported external database when the program start
 * in addition to a list of all previous booking have been made by the user
 */
public class DataBase {

	private ArrayList<Bus> Busses;
	
	private ArrayList<Route> Routes;
	
	private ArrayList<TimeTable> timeTables;
	
	private HashMap<String, Passanger> passangers;
	
	private ArrayList<HistoryReservation> historyBookingList;

	/**
	 * Instantiates a new data base, contains all the lists that needed in project
	 */
	public DataBase() {
		Busses = new ArrayList<>();
		Routes = new ArrayList<>();
		timeTables = new ArrayList<>();
		passangers = new HashMap<>();
		historyBookingList = new ArrayList<>();
	}

	/**
	 * Adds the booking to historybooking list.
	 *
	 * @param booking  the booking made by the user instantly that will get updated
	 * by controller
	 */
	public void addBookingToHistoryList(HistoryReservation booking) {
		historyBookingList.add(booking);
	}

	/**
	 * Reservation history list, used to get the historylist of
	 * all the old reservation 
	 *
	 * @return the  array list  contains history of all booking that made before.
	 */
	public ArrayList<HistoryReservation> reservationHistoryList() {

		return historyBookingList;
	}

	/**
	 * Adds the route to route list inside the local database.
	 *
	 * @param route the route that will be added to the routeList in the local database
	 */
	public void addRouteToRouteList(Route route) {

		Routes.add(route);

	}

	/**
	 * Adds the bus to busses list inside the local database.
	 *
	 * @param bus  the bus that will be added to the bussed List in the local database
	 */
	public void addBusToBussesList(Bus bus) {

		Busses.add(bus);

	}

	/**
	 * Adds the time table thats already created inside the controller 
	 * to the TimeTable list in the local database.
	 *
	 * @param times the timeTable that will be added to the TimeTable list
	 */
	public void addTimeTableToList(TimeTable times) {

		timeTables.add(times);

	}

	

	/**
	 * Adds the new passenger who made booking to the passenger list
	 * in the local data base.
	 *
	 * @param name the name of the passenger that will be treated as key in the list
	 * @param pax the same passenger but as object with all its field 
	 */
	public void addPaxTothePassangerLocalDataBase(String name, Passanger pax) {
		passangers.put(name, pax);
	}

	/**
	 * Pax list. is to get the list of all the passengers that we have in local dataBase
	 *
	 * @return the passengers list from the local database 
	 */
	public HashMap<String, Passanger> paxList() {

		return passangers;
	}

	/**
	 * Route list.
	 *
	 * @return the array list
	 */
	public ArrayList<Route> routeList() {
		return Routes;
	}

	/**
	 * Busses list.Is to get the list of all the busses that we have in local dataBase
	 *
	 * @return the list of all busses 
	 */
	public ArrayList<Bus> BussesList() {
		return Busses;
	}

	/**
	 * Time table list.Is to get the list of all the Time frequency list 
	 * for each single bus that we have in local dataBase.
	 *
	 * @return the list contains all timeTable details:
	 * ( Start time , end time and frequency)
	 */
	public ArrayList<TimeTable> timeTableList() {

		return timeTables;
	}

}
