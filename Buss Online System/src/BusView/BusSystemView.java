package BusView;

import BusModel.*;
import BusController.*;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 * The Class BusSystemView , reading the input of the user from the console and
 * printout all the responses for the user request after interacting with system
 * model through bus controller
 */
public class BusSystemView {

	private static int[] linrow = null;

	Date date;

	Calendar cal = Calendar.getInstance();

	private boolean originCall = false;

	private boolean destinationCall = false;

	private boolean seatBooked = false;

	private boolean timeSelectionDone = false;

	private boolean passangerInfoDone = false;

	String selectionMade = null;

	int mainOption = 0;

	boolean casePriority = false;

	private String selectedRouteID;

	private BusReservationController reservationCTR;

	private String inputDate;

	private String selectedTime;

	private String seatNumber;

	private boolean printTicket;

	private boolean printTkt;

	private boolean routeSelectionDone = false;

	/**
	 * Instantiates a new bus system view. with creation of new system
	 * controller and start reading the input
	 * 
	 */
	public BusSystemView() {

		welcomeMessage();

		try {
			reservationCTR = new BusReservationController();
		} catch (IOException e) {
			System.out.println("Error occured when trying to in Creat controller !");
		}

		readingUserInput();

	}

	/**
	 * Welcome message display when the program start
	 */
	public void welcomeMessage() {

		System.out.println("******************\n " + "****************************\n"
				+ "*****************************************\n" + " Welcome to the Online Bus Reservation System");
		System.out.println(" Please Make your Choice From The Lis Below by Enering its Number ! ");
		System.out.println("");
		System.out.println("Main Page");
		System.out.println(" 1. Origin List\n" + " 2. help\n" + " 3. Exit\n");

	}

	/**
	 * Back main page. printout the message that indicate the user moved to the
	 * first step MainPage
	 */
	public void backMainPage() {
		System.out.println("Main Page");
		System.out.println(" 1. Origin List\n" + " 2. help\n" + " 3. Exit\n");

	}

	/**
	 * Specify date for your trip. printout the message thats ask the user to
	 * input Trip Date
	 */
	public void specifyDateForYourTrip() {

		System.out.println("Please Enter Your Travelling Date as : YYYY/MM/DD ");
	}

	/**
	 * Reading user input. used to read the user input from the console
	 * continuously and specify which message should be print out on console for
	 * the user and which stage the user process right now within it reservation
	 * process and call the methods consequently
	 *
	 */

	public void readingUserInput() {

		System.out.println("Please Enter Your Choice number:");

		Scanner scan = new Scanner(System.in);

		while (scan.hasNext()) {

			if (routeSelectionDone == false) {
				String input = scan.nextLine();
				if (reservationCTR.checkIfTheInputNumberOrChar(input)) {
					selectionMade(input);

				} else {
					try {
						int option = Integer.parseInt(input);
						mainOption(option);
					} catch (Exception e) {
						System.out.println("Wrong input try Again !");
					}
				}

			}
			if (routeSelectionDone == true && timeSelectionDone == false) {
				specifyDateForYourTrip();
				try {
					inputDate = scan.nextLine().substring(0, 10);
				} catch (Exception e) {
					System.out.println("\n Please Enetr Correct Format As Suggested above !");
					continue;
				}
			}

			if (reservationCTR.catchInputDate(inputDate) == null && seatBooked == false && selectedRouteID != null) {
				System.out.println("\n You are Trying to enter Earlier date ! ");

			} else if (seatBooked == false && routeSelectionDone == true)

			{

				date = reservationCTR.catchInputDate(inputDate);
				cal.setTime(date);
				System.out.println(" Time Table For the Selected Date : \n");
				gettingTimeTable(selectedRouteID);
				System.out.println("\n Select the Time by Enetering its Number !");

				while (!(timeSelectionDone)) {
					try {

						selectedTime = (setTheSelectedTimeFromTimeList(Integer.parseInt(scan.nextLine())));
						System.out.println(selectedTime);
						timeSelectionDone = true;
					} catch (Exception e) {
						System.out.println("Sorry wrong Input ! please Enter Valid number!");
					}

				}

				if (timeSelectionDone == true && seatBooked == false) {
					System.out.println("\n Book your seat from the list below !");
					creatSeatMap();
					String input = scan.nextLine();
					seatBooking(input);

					System.out.println(" Update Seat Map \n");
					printUpdateSeatMap();

					System.out.println(" 0 Indicate the Booked Seat \n !");

					System.out.println(
							"Please Enter Your Information \n 1- Name , Mobile , Email Address  ...Respectivly !");

				}

			}

			if (timeSelectionDone == true && seatBooked == true) {
				String info[] = new String[3];
				try {
					info = scan.nextLine().split(",");
				} catch (Exception e) {
					System.out.println("Too much information ! please insert just name , mobile , email ");
				}
				passangerInformation(info);
				passangerInfoDone = true;
			}

			if (passangerInfoDone) {
				System.out.println("Your Booking done ! if you want to print the Ticket Please just enter N ");
				printTicket = true;
			}

			if (printTicket && scan.nextLine().equals("N")) {
				PrintingTicket();
				printTkt = true;

				System.out.println("***********************************\n***********************************");
				System.out.println(" \n if you like to book a new ticket please Enter (new) key word ");
				System.out.println(
						"\n Please note when complet the reservation just enter (Update) to fetch your book to the System dataBase");

			}

			if (printTkt && scan.nextLine().equals("new")) {
				originCall = false;
				destinationCall = false;
				seatBooked = false;
				timeSelectionDone = false;
				passangerInfoDone = false;
				routeSelectionDone = false;
				backMainPage();
			}
			String input = scan.nextLine();
			if (input.equals("Update") && printTkt == true) {
				updateHistoryReservationByController();
				try {
					reservationCTR.writeRouteListFromSystemToTheExternalFile();
				} catch (IOException e) {
					System.out.println("Error in writing to the  CSV file");
				}
			}
		}
	}

	/**
	 * Passenger information. used to receive the passenger information from the
	 * console and pass it to the controller to perform further operation.
	 *
	 * @param info
	 *            the info of certain passenger
	 */
	public void passangerInformation(String[] info) {

		reservationCTR.nexPax(info);

	}

	/**
	 * Seat booking. used to receive the number of seat assign by the user from
	 * the console and pass it to the controller to check the availability and
	 * return back and printout two message either the seat number or seat is
	 * already booked
	 *
	 * @param input
	 *            the input seat number of the passenger
	 */
	public void seatBooking(String input) {

		if (reservationCTR.bookSeat(input) == 0) {
			System.out.println("This Seat Already Booked pleas try another Seat !");
		} else {
			seatNumber = input;
			System.out.println(" Seat NUmber (" + input + ") Booked successfuly ");
			seatBooked = true;

		}
	}

	// return the selected frequency time from the list

	/**
	 * Sets the the selected time from time list based on the selected rout id ,
	 * passing the route id to the controller to get the timetable for the
	 * selected route id
	 *
	 * @param selectedTime
	 *            the selected number from the list that corresponding the
	 *            desired time
	 * @return the string, the selected time from the time list .
	 */
	public String setTheSelectedTimeFromTimeList(int selectedTime) {
		return reservationCTR.timeListForTheSelectedRoute(selectedRouteID).get(selectedTime).toString();
	}

	/**
	 * Prints the update seat map. print the updated seat map of the bus after
	 * book 1 seat
	 */
	public void printUpdateSeatMap() {

		int[][] l = reservationCTR.getNumberedSeat();
		for (int[] row : l) {
			linrow = row;

			for (int x : linrow) {
				System.out.print(x);
				System.out.print("\t");
			}
			System.out.println();
		}

	}

	/**
	 * Create seat map. used to printout the seat map on the console after
	 * getting numbered bus seats from the controller.
	 */
	public void creatSeatMap() {
		if (printTicket) {
			int[][] l = reservationCTR.getNumberedSeat();
			for (int[] row : l) {
				linrow = row;

				for (int x : linrow) {
					System.out.print(x);
					System.out.print("\t");
				}
				System.out.println();
			}
			System.out.println(" 0 is indicate that the seat already booked !");
		} else {
			reservationCTR.numberingSeat();

			int[][] l = reservationCTR.getNumberedSeat();
			for (int[] row : l) {
				linrow = row;

				for (int x : linrow) {
					System.out.print(x);
					System.out.print("\t");
				}
				System.out.println();
			}
		}

	}

	/**
	 * Getting Timetable .use d to printout the list of time for selected rout
	 *
	 * @param selectedRouteID
	 *            the selected route ID used to get the timelist for this rout
	 */
	private void gettingTimeTable(String selectedRouteID) {
		int i = 1;
		System.out.println("   Year      Day       Month      Time \n");
		for (int timelist : reservationCTR.timeListForTheSelectedRoute(selectedRouteID).keySet()) {

			System.out.println(i + "- " + cal.get(Calendar.YEAR) + "  "
					+ cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()) + " "
					+ cal.get(Calendar.DAY_OF_MONTH) + "  "
					+ cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + "  "
					+ reservationCTR.timeListForTheSelectedRoute(selectedRouteID).get(timelist));
			i++;
		}

	}

	/**
	 * Selection made. is used to handle the user input if its non integer and
	 * printout error message if the input is not valid
	 *
	 * @param selection
	 *            the selection of the user
	 */
	public void selectionMade(String selection) {

		if (reservationCTR.checkSelectionValidaity(selection)) {
			originDestinationSelectionChoice(selection);
		} else
			System.out.println("Please Enter Correct Selection from the corresponding  charcter list !");

	}

	/**
	 * Main option. used to handle the user input if its number and printout
	 * error message for invalid selection in addition to print help message and
	 * exit message
	 * 
	 * @param option
	 *            the option input of the user
	 */
	public void mainOption(int option) {

		switch (option) {
		case 1:
			if (originCall == false) {
				printOriginList();
				originCall = true;

			} else
				System.out.println("Enter The corresponding charcter to pick a route");
			break;

		case 2:

			System.out.println(
					"Tips!\n" + " 1- choose your origin\n" + " 2- Select destination from the destination list");
			break;
		case 3:
			System.out.println("Thank you for using SDA2 online Bus System");

			break;

		case 0:

			originCall = false;
			destinationCall = false;
			// StepProgress = 0;
			backMainPage();

			break;
		default:
			System.out.println("Please input valid number from the list !");
		}
	}


	/**
	 * Origin destination selection choice. used to receive the selection of the
	 * user and assign the destination list and specify route id based on origin
	 * and destination
	 *
	 * @param selection
	 *            the selection
	 */
	public void originDestinationSelectionChoice(String selection) {

		if (destinationCall == false && originCall == true) {

			printdestinationList(selection);
			destinationCall = true;
		} else if (destinationCall == true && originCall == true) {
			selectedRouteID = selection;
			routeSelectionDone = true;
			Route routeSelected = reservationCTR.getTheRouteSelected(selection);

		}

	}

	/**
	 * Seat number selection. used to printout a message asking the user to
	 * enter seat number
	 *
	 * @return the string
	 */
	public String seatNumberSelection() {

		return ("Enter a Seat number to reserve: ");
	}

	/**
	 * Seat booked already. printout message indicate that the seat booked
	 * already
	 *
	 * @return the string
	 */
	public String seatBookedAlready() {

		return ("That seat has already been reserved");
	}

	/**
	 * Prints the origin list.
	 */
	public void printOriginList() {
		System.out.println("The Origin List\n" + "******************* ");
		int i = 0;
		for (String id : reservationCTR.OriginList().keySet()) {

			i++;

			System.out.println(" " + i + "-" + reservationCTR.OriginList().get(id) + " >>>" + id);

		}
		System.out.println("******************* ");
		System.out.println("******** Enter 0 to back to the Main Page ******** ");
	}

	/**
	 * Printdestination list.
	 *
	 * @param selectionMade
	 *            the selection made
	 */
	public void printdestinationList(String selectionMade) {

		System.out.println("The Destinations List for the selected origin\n" + "******************* ");
		int i = 0;
		for (String destination : reservationCTR.potentialDestinationForSelectedOrigin(selectionMade).keySet()) {

			i++;
			System.out.println(
					" " + i + "-" + reservationCTR.potentialDestinationForSelectedOrigin(selectionMade).get(destination)
							+ ">>>>" + destination);

		}
		System.out.println("******************* ");
		System.out.println("******** Enter 0 to back to the Main Page ******** ");
	}

	/**
	 * Printing ticket.
	 */
	public void PrintingTicket() {

		System.out.print("*******************************************************");

		System.out.println("\n Bus Ticketing System ");

		System.out.print("*******************************************************");

		System.out.println("\n Trip Details : \n");

		System.out.println("From : " + reservationCTR.getTheRouteSelected(selectedRouteID).getOrigin()
				+ " ------------- " + "To : " + reservationCTR.getTheRouteSelected(selectedRouteID).getDestination());

		System.out.println(" \n Trip Date is :" + reservationCTR.convertDateToString(date) + "\n TripDeparture at : "
				+ selectedTime.substring(0, 5) + " ***** " + "  Arrival Time " + reservationCTR.calculateArrivalTime(selectedTime,Integer.parseInt(reservationCTR.getTheRouteSelected(selectedRouteID).getTripeDuration().replaceAll("[^0-9]", "")))
				+ "  **** Trip Duration is : "
				+ reservationCTR.getTheRouteSelected(selectedRouteID).getTripeDuration());

		System.out.println("\n Passanger Details :\n");

		System.out.println("Name : " + reservationCTR.passangerNameWhoMadeTheCurrentBooking() + "\n" + "Mobile : "
				+ reservationCTR.passangerMobileWhoMadeTheCurrentBooking() + "\n" + "Email :"
				+ reservationCTR.passangerEmaileWhoMadeTheCurrentBooking());

		System.out.println(" \n Your Seat Number is  " + seatNumber + " ---------  Bus Number "
				+ reservationCTR.getbusRegNumForCertainRoute(selectedRouteID));

	}

	/**
	 * Update history reservation by controller.
	 */
	public void updateHistoryReservationByController() {
		reservationCTR.setReservationHistoryToTheLocalDataBase(selectedRouteID,
				reservationCTR.passangerNameWhoMadeTheCurrentBooking(),
				reservationCTR.getbusRegNumForCertainRoute(selectedRouteID), seatNumber, selectedTime,
				reservationCTR.convertDateToString(date));
	}

	/**
	 * Save your book to the csv data base.
	 */
	public void SaveYourBookToTheCsvDataBase() {
		try {
			reservationCTR.writeRouteListFromSystemToTheExternalFile();
		} catch (IOException e) {
			System.out.println(" Error in writing to the DataBase ! ");
		}
	}

}
