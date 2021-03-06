package com.familytree.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConnectionFactoryPostgres {

	Logger log = Logger.getRootLogger();
	
	public static String URL;
	
	public static String USERNAME;
	
	public static String PASSWORD;
	
	// only instance of the class that can ever exist inside the program
	// because instance is static and constructor is private
	private static ConnectionFactoryPostgres connectionFactory = null;
	
	private ConnectionFactoryPostgres() {
		
		// our database is run on localhost, but a real database would have a real url location
		// 5432 is the default postgres port
		// ? in a url script is for the query parameters (example: username = username)
		
		URL = "jdbc:postgresql://" + System.getenv("FAMILY_TREE_DB_URL") + ":5432/" 
				+ "family_tree_database" + "?";
		
		USERNAME = System.getenv("FAMILY_TREE_DB_USERNAME");
		
		PASSWORD = System.getenv("FAMILY_TREE_DB_PASSWORD");
		
	}
	
	public Connection createConnection() {
		
		// issue with this version of the postgres driver where it doesn't actually load the driver
		// you have to specifically tell it to load this driver into memory
		try {
			Class.forName("org.postgresql.Driver");
		}
		catch (ClassNotFoundException e) {
			System.out.println("Failed to load Driver");
			e.printStackTrace();
		}
		
		log.info("URL : " + URL);
		
		try {
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}
		catch (SQLException e) {
			log.error("Failed to connect to DB", e);
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static synchronized Connection getConnection() {
		
		// synchronized keeps program thread safe by making multiple calls wait
		
		if (connectionFactory == null) {
			connectionFactory = new ConnectionFactoryPostgres();
		}
		
		return connectionFactory.createConnection();
		
	}
	
}
