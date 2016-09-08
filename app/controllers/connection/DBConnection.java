package controllers.connection;

import java.sql.Connection;

import play.db.DB;

public class DBConnection {

	public static Connection getConnection() {
		return DB.getConnection();
	}
}
