package BusModel;

import java.sql.*;

/**
 * The Class BusDataBase. Declarations and initialization of all DataBase fields
 * in addition to getter and setter for all the fields
 */
public class BusDataBase {

	private Connection dBConnection;

	private Statement myStmet;

	private ResultSet routeRes;

	private ResultSet busRes;

	private String dbUrl = "jdbc:mysql://localhost:3306/busdatabase?verifyServerCertificate=false&useSSL=true";

	private String dbUserName = "root";

	private String dbPass = "Mysql123456";

	/**
	 * Instantiates a new bus data base.
	 */
	public BusDataBase() {

		getDefaultConnectionToDb();
		defaultstatementForRoute();
		defaultstatementForBuses();

	}

	/**
	 * Gets the default connection to db.
	 *
	 * @return the default connection to db
	 */
	public Connection getDefaultConnectionToDb() {

		try {
			dBConnection = DriverManager.getConnection(dbUrl, dbUserName, dbPass);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return dBConnection;
	}

	/**
	 * DefaultStatement for route query from database.
	 */
	private void defaultstatementForRoute() {
		try {
			myStmet = dBConnection.createStatement();
			routeRes = myStmet.executeQuery("select * from route");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	/**
	 * DefaultStatement for buses query from database.
	 */
	private void defaultstatementForBuses() {
		try {
			myStmet = dBConnection.createStatement();
			busRes = myStmet.executeQuery("select * from buses");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	/**
	 * Db route result.
	 *
	 * @return the result set
	 */
	public ResultSet dbRouteResult() {

		return routeRes;
	}

	/**
	 * Db buses result.
	 *
	 * @return the result set
	 */
	public ResultSet dbBusesResult() {

		return busRes;
	}

}
