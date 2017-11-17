package BusModel;

import java.util.ArrayList;

public class Bus {
	private int busSeatNumber = 40;
	private String busRegNum;
	private Route busRoute;
	private ArrayList<Passanger> pax;

	public Bus(String busRegNum, Route busRoute) {

		this.busRegNum = busRegNum;
		pax = new ArrayList<>();
		this.busRoute = busRoute;

	}

	public Bus() {

		busRegNum = null;
		busRoute = new Route();
		pax = new ArrayList<>();

	}

	public void setBusRegNumber(String busRegNum) {

		this.busRegNum = busRegNum;

	}

	public String getBusRegNumber() {

		return busRegNum;

	}

	public void setRoute(Route route) {

		this.busRoute = route;

	}

	public Route getBusRoute() {

		return busRoute;

	}

	public int getSeatNumber() {

		return busSeatNumber;
	}

	public void setSeatNumber(int busSeatNumber) {
		this.busSeatNumber = busSeatNumber;
	}

	public void addPassengerToTheBus(Passanger p1) {

		pax.add(p1);
	}

}
