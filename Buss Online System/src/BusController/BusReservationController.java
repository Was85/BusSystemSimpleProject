package BusController;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Date;
import java.sql.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import BusModel.*;


/**
 * The Class BusReservationController. used to maintain the interaction between
 * the view and the model class contains all the logical method that perform
 * operation action on view console.
 */
/**
 * @author Waseem
 *
 */
/**
 * @author Waseem
 *
 */

public class BusReservationController {

	
	private BusDataBase db;

	
	private static final String COMMA_DELIMITER = ",";
	
	
	private ArrayList<Route> availableRoutesList;
	
	
	private ArrayList<Bus> availableBusesList;
	
	private ArrayList<String> destinationList;
	
	
	private ResultSet busResult ;
	
	
	private int twoDm[][];


	private int i, j, k = 1;

	
	private int ans;

	
	boolean seatbooked = false;

	
	/**
	 * Instantiates a new bus reservation controller.
	 */
	public BusReservationController() {
		availableRoutesList = new ArrayList<>();
		destinationList = new ArrayList<>();
		availableBusesList = new ArrayList<>();
		db = new BusDataBase();
		getDbResultToRouteObj();
		getDbResultTobusObj();
	}

	/**
	 * Update buses data base , by adding the new bus to the database
	 * 
	 * for exist bus this method will just utilized to update the database
	 *
	 * @param newBus the new bus
	 * @return true, if successful
	 */
	
	public boolean updateBusesDataBase(Bus newBus) {
		
		try {
			
		Connection connection = db.getDefaultConnectionToDb();
		
		  Calendar calendar = Calendar.getInstance();
	      Date purchaseDate = new Date(calendar.getTime().getTime());
	      
	      
	      // the mySql insert statement
	      String query = " insert into buses (regNumber, origin, destination, seatnumbers, purchaseddate)"
	        +   " values (?, ?, ?, ?, ?) on duplicate key update"+" seatnumbers = values(seatnumbers)";
	    	
	      // create the mySql insert preparedStatement
	      PreparedStatement preparedStmt = connection.prepareStatement(query);
	      preparedStmt.setString (1, newBus.getBusRegNum());
	      preparedStmt.setString (2, newBus.getBusRoute().getOrigin());
	      preparedStmt.setString   (3, newBus.getBusRoute().getDestination());
	      preparedStmt.setInt(4,newBus.getBusSeatNumber());
	      preparedStmt.setDate(5,purchaseDate);   

	      // execute the preparedStatement
	      preparedStmt.executeUpdate();
	  
	      connection.close();
		return true ;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
return false ;
	}

	
	
	/**
	 * Gets the db result to route obj.
	 *
	 * @return the db result to route obj
	 */
	public void getDbResultToRouteObj() {
		ResultSet res = db.dbRouteResult();
		int rowcount = 0;
		try {
			if (res.last()) {
				rowcount = res.getRow();
				res.beforeFirst(); // not rs.first() because the rs.next() below
									// will move on, missing the first element
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			while (res.next()) {

				String origin = res.getString(2);
				String destination = res.getString(3);
				String duration = res.getString(4);
				String time = res.getString(5);

				Route route = new Route(origin, destination, duration, time);

				availableRoutesList.add(route);

				// System.out.println("from "+origin+" To " +destination+"
				// Duration " + duration + " at clock "+ time);
				// System.out.println(" ");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	
	/**
	 * Gets the db result tobus obj.
	 *
	 * @return the db result tobus obj
	 */
	public void getDbResultTobusObj() {
		ResultSet res = db.dbBusesResult();
		int rowcount = 0;
		try {
			if (res.last()) {
				rowcount = res.getRow();
				res.beforeFirst(); // not rs.first() because the rs.next() below
									// will move on, missing the first element
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			while (res.next()) {

				String regNumber = res.getString(1);
				String origin = res.getString(2);
				String destination = res.getString(3);
				int seatNumbers = res.getInt(4);

				Route theRoute =null;
				for(int i = 0 ; i < availableRoutesList.size();i++){
					if(availableRoutesList.get(i).getOrigin()== origin && availableRoutesList.get(i).getDestination() == destination){
						theRoute =availableRoutesList.get(i);
					}
				}
				
				Bus bus = new Bus(regNumber,theRoute,seatNumbers);

				availableBusesList.add(bus);

				// System.out.println("from "+origin+" To " +destination+"
				// Duration " + duration + " at clock "+ time);
				// System.out.println(" ");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Selected route details.
	 *
	 * @param origin the origin
	 * @param destination the destination
	 * @return the route
	 */
	public Route selectedRouteDetails(String origin, String destination) {

		for (int i = 0; i < availableRouteList().size(); i++) {

			if (origin.equals(availableRouteList().get(i).getOrigin())
					&& destination.equals(availableRouteList().get(i).getDestination())) {

				return availableRouteList().get(i);
			}

		}
		return null;

	}

	/**
	 * Validate user name.
	 *
	 * @param userName the user name
	 * @return true, if successful
	 */
	public boolean validateUserName(String userName) {
		Pattern usrNamePtrn = Pattern.compile("^[\\p{L} .'-]+$");

		Matcher match = usrNamePtrn.matcher(userName);
		if (match.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * Validate mobile number.
	 *
	 * @param mobileNumber the mobile number
	 * @return true, if successful
	 */
	public  boolean validateMobileNumber(String mobileNumber) {
		Pattern usrNamePtrn = Pattern.compile("[0-9]+");

		Matcher match = usrNamePtrn.matcher(mobileNumber);
		if (match.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * Available route list.
	 *
	 * @return the array list
	 */
	public ArrayList<Route> availableRouteList() {

		return availableRoutesList;
	}

	// return origin list from available Routes list

	/**
	 * Origin list.
	 *
	 * @return the array list
	 */
	public ArrayList<String> originList() {
		ArrayList<String> originList = new ArrayList<>();
		for (int i = 0; i < availableRoutesList.size(); i++) {
			String origin = availableRoutesList.get(i).getOrigin();
			originList.add(origin);
		}
		return originList;
	}

	/**
	 * Destination list for certain origin.
	 *
	 * @param origin the origin
	 * @return the array list
	 */
	// return destination based on origin selection
	public ArrayList<String> destinationListForCertainOrigin(String origin) {

		for (int i = 0; i < availableRoutesList.size(); i++) {
			if (origin.equals(availableRoutesList.get(i).getOrigin())) {
				String destination = availableRoutesList.get(i).getDestination();
				destinationList.add(destination);
			}

		}
		return destinationList;
	}

	/**
	 * Destination list.
	 *
	 * @return the array list
	 */
	public ArrayList<String> destinationList() {

		return destinationList;
	}
	
	/**
	 * Clear destination list. in order to create a new booking 
	 */
	public void clearDestinationList(){
		
		destinationList.clear();
	}

	/**
	 * New pax.
	 *
	 * @param paxName the pax name
	 * @param paxMobileNumber the pax mobile number
	 * @return the passanger
	 */
	public Passanger newPax(String paxName, String paxMobileNumber) {

		Passanger passenger = new Passanger(paxName, paxMobileNumber);

		return passenger;
	}

	/**
	 * New bus.
	 *
	 * @param regNumber the reg number
	 * @param routeSelected the route selected
	 * @return the bus
	 */
	public Bus newBus(String regNumber, Route routeSelected) {

		Bus busA = new Bus(regNumber, routeSelected);

		return busA;
	}

	/**
	 * Rand registration bus number. creat random regNumber once the 
	 * system creat new bus
	 *
	 * @return the string
	 */
	public String randRegistrationBusNumber() {

		String regNum = "";
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 1; i < 11; i++) {
			list.add(new Integer(i));
		}

		Collections.shuffle(list);
		for (int i = 0; i < 3; i++) {
			regNum += list.get(i);

		}

		return regNum;

	}

	

	

	
	/**
	 * Check bus availability.
	 *
	 * @param selectedRoute the selected route
	 * @return the bus
	 */
	public Bus checkBusAvailability(Route selectedRoute) {

		Connection dbConnetcion = db.getDefaultConnectionToDb();

		try {
			Statement mystat = dbConnetcion.createStatement();

			busResult = mystat.executeQuery("SELECT * FROM buses");

			if (busResult.next()==false) {
				dbConnetcion.close();
				return null;
			} else {
				busResult.beforeFirst();
				while (busResult.next()) {
					if (selectedRoute.getOrigin().equals(busResult.getString("origin"))
							&& selectedRoute.getDestination().equals(busResult.getString("destination"))) {
						
						String regNumber   =busResult.getString("regNumber");
						String origin      =busResult.getString("origin");
						String destination =busResult.getString("destination");
						int seatNumber     =busResult.getInt("seatnumbers");
						Bus theBus = new Bus (regNumber,selectedRouteDetails(origin, destination),seatNumber);
						
						
						
						dbConnetcion.close();
						return theBus ;
						
						
						 
					}

				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
		

	}
	
	
	/**
	 * Gets the bus result from database
	 *
	 * @return the bus result
	 */
	public ResultSet getBusResult (){
		
		
		return busResult ;
	}

	
	
	
	
	
	/**
	 * Buses list.
	 *
	 * @return the array list
	 */
	public ArrayList<Bus> busesList(){
		
		
		return availableBusesList ;
	}
	
	/**
	 * Find the bus number for the passenger
	 *
	 * @param name the name
	 * @return the string
	 */
	public String findWhichBusByName(String name){
		
		
		
		for(Bus b : availableBusesList){
			
			for(int j = 0 ; j < b.getPaxInTheBus().size();j++){
				if(b.getPaxInTheBus().get(j).getPaxName().equals(name)){
					return b.getBusRegNum();
				}
			}
			
			
		}
		return null;
				
		
	}
	
	
	public void addBusToAvailableBusesLst (Bus bus){
		
		availableBusesList.add(bus);
	}
	
	

	
	/**
	 * Book seat. To make the set as window and alies line 
	 * 
	 *
	 * @param seatNumber the seat number
	 * @return the int
	 */
	public int bookSeat(String seatNumber) {

		ans = Integer.parseInt(seatNumber);
		k = 1;

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
	 * Numbering seat. Unused for the time being till we get the GUI
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

	

}
