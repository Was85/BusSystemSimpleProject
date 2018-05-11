package BusView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



import BusController.*;

import BusModel.*;


/**
 * The Class BusSystemView.
 * 
 * 
 * 	This class representing a View for this project (GUI substitution). 
 * 
 */
public class BusSystemView {
	
	
	private String origin;
	
	
	private String destination;
	
	
	private BusReservationController controller;
	
	
	private boolean originSelected = false;
	
	
	private boolean destinationSelected = false;
	
	
	private boolean nameCheck = false, mobileCheck = false;
	
	
	private int PaxNumberPerBook;
	
	
	private String name;
	
	
	private String mobile;
	
	
	private boolean busAvailableNow = false;
	
	
	private boolean paxAddedToBus = false;
	
	
	private boolean reservationDone = false;

	/**
	 * welcome message 
	 * Instantiates a new bus system view, create the controller 
	 * 
	 * print originList once its start , and fetch continuously the user input.
	 * 
	 */
	public BusSystemView() {
		welcomeMessage();
		controller = new BusReservationController();
		printOutOriginList();
		catchUserInput();

	}

	

	/**
	 * Welcome message.
	 */
	public void welcomeMessage() {

		System.out.println("Welcome To 24 Online Bus System \n ");
		System.out.println("Please Select Your Origin from the list below : \n");

	}

	/**
	 * Prints the out origin list.
	 */
	
	public void printOutOriginList() {

		for (int i = 0; i < controller.originList().size(); i++) {

			System.out.println((i + 1) + ". " + controller.originList().get(i) + "\n");
		}
	}

	/**
	 * Printout The  destination list.
	 *
	 * @param origin the origin selected by the user , we receive it here to find out the 
	 * available destinations for the selected origin.
	 */
	public void printOutDestinationList(String origin) {
		int destinationSize = controller.destinationListForCertainOrigin(origin).size();

		System.out.println("Destination list ");
		for (int i = 0; i < destinationSize; i++) {

			System.out.println((i + 1) + ". " + controller.destinationListForCertainOrigin(origin).get(i));
		}
	}

	/**
	 * Catch user input. reading console input to perform 
	 * all the user selections and orders.
	 */
	public void catchUserInput() {
		while (true) {

			if (originSelected == false || destinationSelected == false) {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

				int options = 0;
				try {
					options = Integer.parseInt(bufferedReader.readLine());
				} catch (NumberFormatException | IOException e) {
					System.out.println("Please Enter number from the list ... ");
					continue;
				}

				switch (options) {

				case 1:
					inputIndexAction(options);
					originSelected = true;
					break;

				case 2:

					inputIndexAction(options);
					originSelected = true;
					break;

				case 3:

					inputIndexAction(options);
					originSelected = true;
					break;

				case 4:

					inputIndexAction(options);
					originSelected = true;
					break;
				case 5:
					inputIndexAction(options);
					originSelected = true;
					break;

				case 6:
					inputIndexAction(options);
					originSelected = true;

					break;

				case 7:

					inputIndexAction(options);
					originSelected = true;
					break;

				case 8:
					inputIndexAction(options);
					originSelected = true;
					break;

				case 0:

					System.exit(0);
					break;

				default:

					break;

				}

			} else if (originSelected && destinationSelected ) {

				if (confirmBook()) {

					Passanger newPax = controller.newPax(name, mobile);
					Route selectedRoute = controller.selectedRouteDetails(origin, destination);

					if (controller.checkBusAvailability(selectedRoute) == null) {

						// No recent bus just add new one to the system

						
						Bus busA = controller.newBus(controller.randRegistrationBusNumber(),
								controller.selectedRouteDetails(origin, destination));

						busA.addNewPax(newPax);
						controller.addBusToAvailableBusesLst(busA);
						busAvailableNow = controller.updateBusesDataBase(busA);
						paxAddedToBus = true;

					} else if (paxAddedToBus == false) {
					// already has bus for the same route !

						
						Bus theBus = controller.checkBusAvailability(selectedRoute);
						//System.out.println("----" + theBus.getBusSeatNumber() + "---" + theBus.getBusRegNum());

					// check if the exist bus is full, if its create new bus
					//  and add the Pax to it and update the DB.
						
						if (theBus.getBusSeatNumber() == 0) {

							System.out.println("Buss full will create new one !! ");
							Bus anotherBus = controller.newBus(controller.randRegistrationBusNumber(),
									controller.selectedRouteDetails(theBus.getBusRoute().getOrigin(),
											theBus.getBusRoute().getDestination()));

							anotherBus.addNewPax(newPax);
							controller.addBusToAvailableBusesLst(anotherBus);
							controller.updateBusesDataBase(anotherBus);

							paxAddedToBus = true;

						} else {
							// there are available seat on the current bus , just add the pax to it and update the DB.

							theBus.addNewPax(newPax);
							controller.addBusToAvailableBusesLst(theBus);
							controller.updateBusesDataBase(theBus);
							
							paxAddedToBus = true;
							
						}

					}

				}
			}

			// reservation Confirmed ! 
			if (paxAddedToBus) {
				System.out.println(" Your Reservation has been Confirme ! \n ");
				System.out.println(" Please enter (n) for new booking \t  , (p) to print the ticket \t , (x) for exit !");
				System.out.println(" ");
			
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
				String choices = null;
				try {
					
				
					choices = bufferedReader.readLine();
					
					}
				 catch (IOException e) {
					
					System.out.println("Please enter valid choice ");
				}
				switch (choices) {

				case "n":
					printOutOriginList();
					setDefault();
					
					break;
				case "x":
					System.exit(0);
					break;
				case "p":
					printTicketDetails();
					break;
				default:

					break;

				}
			}
		}

	}

	/**
	 * Sets the default value for all conditions in order to perform a new book !
	 * and clear the Destination list (ArrayList) from the previous booking. 
	 */
	public void setDefault() {

		originSelected = false;
		destinationSelected = false;
		paxAddedToBus = false;
		mobileCheck=false ;
		nameCheck=false ;
		controller.clearDestinationList();
	}

	/**
	 * Print on screen the ticket details.
	 */
	public void printTicketDetails() {
		System.out.println("---------------------------------------------------");
		System.out.println("   Passanger Name :\t (" + name + ") ---------- Mobile : (" + mobile+")");

		System.out.println("\n  " + " Origin : (" + origin+")" + " ------  Destination (" + destination+")"+" ------  Bus Reg Number (" + controller.findWhichBusByName(name)+")");

		
		System.out.println();

	}

	/**
	 * inputIndexAction. this method used to match the console input with arrayList index
	 * as default the ArrayList start from index 0
	 *
	 * @param options the options
	 */
	private void inputIndexAction(int options) {

		if (!originSelected) {
			origin = controller.originList().get(options - 1);
			printOutDestinationList(origin);

		} else if (originSelected && !destinationSelected) {
			destination = controller.destinationList().get(options - 1);
			selectedRouteDetail();

		}

	}

	/**
	 * Confirm book. This method is to allow the user to enter the name and mobile 
	 * and validate both , as  booking confirmation step after selecting the origin
	 * and destination .
	 *
	 * @return true, if successful 
	 */
	public boolean confirmBook() {

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		while (!mobileCheck) {
			if (!nameCheck) {
				try {
					String tempName = bufferedReader.readLine();
					if (controller.validateUserName(tempName)) {
						name = tempName;
						nameCheck = true;
						System.out.println("Please Enter your mobile Number>>>...");

					} else {
						if (!nameCheck) {
							System.out.println("Please Enter Valid Name ...");
							continue;
						}
					}
				} catch (IOException e) {

					System.out.println("Input Erro !!");
				}

			} else {

				try {
					String tempMobile = bufferedReader.readLine();

					if (controller.validateMobileNumber(tempMobile)) {

						mobile = tempMobile;
						mobileCheck = true;

					} else {
						System.out.println("Enter Valid Number ");
					}

				} catch (IOException e) {
					System.out.println("Input Error !!");
				}

			}

		}
		if (nameCheck && mobileCheck) {
			return true;
		} else
			return false;

	}

	/**
	 * Selected route detail for the user.
	 */
	private void selectedRouteDetail() {
		Route myRoute = controller.selectedRouteDetails(origin, destination);
		System.out.println("Your Tripe Details shown below :");
		System.out.println(" Origin \t\t Destination \t\t Time \t\t Duration \t\t ");
		System.out.println("------------------------------------------------------------------------ \n");
		System.out.println(myRoute.getOrigin() + "\t" + myRoute.getDestination() + "  \t      " + myRoute.getTime()
				+ "\t\t" + myRoute.getDuration() + "h ");
		System.out.println("");
		System.err.println("  To confirm the Booking Enter Name of the Pax and proced  !  ");
		destinationSelected = true;
	}

	

}