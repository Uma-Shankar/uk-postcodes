package controllers.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controllers.models.Location;

public class LocationService {
	
	final static String TABLE_NAME = "postcodelatlng";

	public static Location getLatAndLogByPostalCode(String postalCode) throws SQLException {
		
		String query = "SELECT * FROM "+ TABLE_NAME + " WHERE postcode = '" + postalCode + "'";
		
		ResultSet rs = DBService.getResultSet(query);
		
		Location location = new Location();
		
		while (rs.next() ) {
			location.lat = rs.getString( "latitude");
	    	location.lon = rs.getString( "longitude");
	    	location.postalCode = rs.getString( "postcode");
	    }
		 
		return location;
	}
	
	public static List<String> nearestPostalCode(String postalCode, String radius) throws SQLException {
		
		Location location = getLatAndLogByPostalCode(postalCode);
		
		String lat = location.lat;
		String lon = location.lon;
		
		List<String> nearestPostalCode = new ArrayList<String>();
		
		String query = "SELECT postcode, ACOS( SIN( RADIANS( `latitude` ) ) * SIN( RADIANS( " + lat 
	    		+" ) ) + COS( RADIANS( `latitude` ) ) * COS( RADIANS( " + lat +" )) * COS( RADIANS( `longitude` ) - RADIANS(" 
	    		+ lon + " )) ) * 6380 AS `distance` FROM `postcodelatlng` WHERE ACOS( SIN( RADIANS( `latitude` ) ) * SIN( RADIANS( " 
	    		+ lat + " ) ) + COS( RADIANS( `latitude` ) ) * COS( RADIANS( "  + lat + " )) * COS( RADIANS( `longitude` ) - RADIANS( " + lon + " )) ) * 6380 < " + radius +" ORDER BY `distance`";
		
		ResultSet rs = DBService.getResultSet(query);
		
		while (rs.next() ) {
			nearestPostalCode .add(rs.getString( "postcode"));
	    }
		
		return nearestPostalCode;
	    
	}
}
