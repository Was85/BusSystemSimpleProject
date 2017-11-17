package BusModel;

import java.util.ArrayList;
import java.util.HashMap;

public class DataBase {

	private ArrayList<Bus> Busses;
	private ArrayList<Route> Routes;
	private ArrayList<TimeTable> timeTables;
	private HashMap<String, Passanger> passangers;
	private ArrayList<HistoryReservation> historyBookingList;

	public DataBase() {
		Busses = new ArrayList<>();
		Routes = new ArrayList<>();
		timeTables = new ArrayList<>();
		passangers = new HashMap<>();
		historyBookingList = new ArrayList<>();
	}

	public void addBookingToHistoryList(HistoryReservation booking) {
		historyBookingList.add(booking);
	}

	public ArrayList<HistoryReservation> reservationHistoryList() {

		return historyBookingList;
	}

	public void addRouteToRouteList(Route route) {

		Routes.add(route);

	}

	public void addBusToBussesList(Bus bus) {

		Busses.add(bus);

	}

	public void addTimeTableToList(TimeTable times) {

		timeTables.add(times);

	}

	// return Lists

	public void addPaxTothePassangerLocalDataBase(String name, Passanger pax) {
		passangers.put(name, pax);
	}

	public HashMap<String, Passanger> paxList() {

		return passangers;
	}

	public ArrayList<Route> routeList() {
		return Routes;
	}

	public ArrayList<Bus> BussesList() {
		return Busses;
	}

	public ArrayList<TimeTable> timeTableList() {

		return timeTables;
	}

}
