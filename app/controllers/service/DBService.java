package controllers.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.connection.DBConnection;

public class DBService {

	final static String TABLE_NAME = "postcodelatlng";
	
	static Connection connection = DBConnection.getConnection();
	
	public static ResultSet getResultSet(String query) throws SQLException {
		
		PreparedStatement stm = connection.prepareStatement(query);
		
		ResultSet rs = stm.executeQuery(query);
		
		return rs;
	}
}
