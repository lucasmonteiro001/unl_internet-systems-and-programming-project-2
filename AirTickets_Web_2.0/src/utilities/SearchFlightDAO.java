package utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Flight;
import model.User;
//TODO update user
public class SearchFlightDAO {
	private final String HOST_ADDRESS = "cse.unl.edu";
	private final String USER_DATABASE = "mmoraesg";
	private final String USERNAME = "mmoraesg";
	private final String PASSWORD = "haremsc4r3m";
	private final String CSE_DATABASE = "cse464";

	/*
	 * We are going to use a CRUD to access the Objects at the Database
	 */
	public SearchFlightDAO() {

	}

	
	public Flight readFlight(Flight flight) {
		// (String host, String db, String user, String password){
		JDBCHelper jdbc = new JDBCHelper(HOST_ADDRESS, USER_DATABASE, USERNAME,
				PASSWORD);
		ArrayList<Object> param = new ArrayList<Object>();
		/**
		 * param.add("Hello"); param.add(35767); param.add(4.0);
		 * param.add(Timestamp.valueOf("2014-09-30 11:41:00"));
		 */
		param.add(flight.getId());
		ResultSet rs1 = jdbc
				.queryDB(
						"SELECT * FROM cse464.flights WHERE cse464.flights.id =?;",
						param);

		try {
			if (rs1 != null && rs1.next()) {
				Flight returnFlight = new Flight();
				returnFlight.setArrival(rs1.getDate ("arrival"));
				returnFlight.setBusinessClassReserved(rs1.getInt ("business_reserved"));
				returnFlight.setDeparture(rs1.getDate("departure"));
				returnFlight.setDestination(rs1.getString("destination"));
				returnFlight.setEconomyClassReserved(rs1.getInt ("economy_reserved"));
				returnFlight.setFirstClassReserved(rs1.getInt ("first_class_reserved"));
				returnFlight.setOperator(rs1.getString("operator"));
				returnFlight.setPlane(rs1.getInt("plane"));
				returnFlight.setSource(rs1.getString ("source"));
				
				return returnFlight;
			}
			jdbc.conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList <Flight> readMultipleFlights(Flight flight) {
		// (String host, String db, String user, String password){
		JDBCHelper jdbc = new JDBCHelper(HOST_ADDRESS, USER_DATABASE, USERNAME,
				PASSWORD);
		ArrayList<Object> param = new ArrayList<Object>();
		/**
		 * param.add("Hello"); param.add(35767); param.add(4.0);
		 * param.add(Timestamp.valueOf("2014-09-30 11:41:00"));
		 */
		param.add(flight.getSource());
		param.add(flight.getDestination());
		param.add(flight.getDeparture());
		param.add(flight.getArrival());

		ResultSet rs1 = jdbc
				.queryDB(
	"SELECT * FROM cse464.flights WHERE source = ? AND destination = ? AND departure = ? AND arrival = ?;",
						param);

		try {
			if (rs1 != null) {
				ArrayList <Flight> flightArray = new ArrayList <Flight>();
				while (rs1.next()) {
					Flight returnFlight = new Flight();
					returnFlight.setArrival(rs1.getDate ("arrival"));
					returnFlight.setBusinessClassReserved(rs1.getInt ("business_reserved"));
					returnFlight.setDeparture(rs1.getDate("departure"));
					returnFlight.setDestination(rs1.getString("destination"));
					returnFlight.setEconomyClassReserved(rs1.getInt ("economy_reserved"));
					returnFlight.setFirstClassReserved(rs1.getInt ("first_class_reserved"));
					returnFlight.setOperator(rs1.getString("operator"));
					returnFlight.setPlane(rs1.getInt("plane"));
					returnFlight.setSource(rs1.getString ("source"));
					flightArray.add(returnFlight);
				}
					
				return flightArray;
			}
			jdbc.conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


}