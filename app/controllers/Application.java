package controllers;


import java.sql.SQLException;
import java.util.List;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.node.ObjectNode;

import controllers.models.Location;
import controllers.service.LocationService;

/**
 * @author valore
 *
 */ 
public class Application extends Controller {

	public static Result index() throws SQLException {
		
		return ok("Welcome to UK Postal Code Servie");
	}
	
	public static Result getLatAndLog() throws SQLException {
		 
		ObjectNode result = Json.newObject();
		System.out.println("HHI");
		System.out.println(request().getQueryString("postalCode"));
		  
		String postalCode = request().getQueryString("postalCode");
		

		Location location = LocationService.getLatAndLogByPostalCode(postalCode);
		 
		if(location != null) {
			result.put("IsSucess", true);
			result.put("message", "location");
			result.putPOJO("locations", Json.toJson(location));
		} else {
			result.put("IsSucess", false);
			result.put("message", "Invalid PostalCode");
		}

		System.out.println("HHI");
		System.out.println(result);
		
		return ok(result);
	}
	
	public static Result nearestPostalCode() throws SQLException {
		
		String postalCode = request().getQueryString("postalCode");
		String radius = request().getQueryString("radius");
		
		ObjectNode result = Json.newObject();
		
		List<String> nearestCodes = LocationService.nearestPostalCode(postalCode, radius);
		
		if(nearestCodes.size() > 0) {
			result.put("IsSucess", true);
			result.put("message", "Postal Code list");
			result.putPOJO("locations", Json.toJson(nearestCodes));
		} else {
			result.put("IsSucess", false);
			result.put("message", "Invalid PostalCode");
		}
		
		return ok(result);
	}
}
