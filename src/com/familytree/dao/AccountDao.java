package com.familytree.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.familytree.exception.AccountNotFound;
import com.familytree.exception.InvalidPassword;
import com.familytree.pojo.Account;
import com.familytree.util.ConnectionFactoryPostgres;

public class AccountDao {
	
	private Logger log = Logger.getRootLogger();
	
	Connection connection = ConnectionFactoryPostgres.getConnection();

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public boolean getAccountByUsername(String username) {
		/**
		 * Connects to the Postgres database to check if the given username is already used by an existing account.
		 * 
		 * @param username		
		 * @return true		returns true if the given username is already being used by an existing account
		 * @return false	returns false if the given username is not being used by any existing accounts
		 * @see SignupMenu
		 */
		
		log.info("Checking to see if the username is taken");
		
		Connection connection = ConnectionFactoryPostgres.getConnection();
		
		try {
			
			log.info("Successfully connected to the database");
			
			String sql = "select * from accounts.accounts where user_name = '" + username + "';";
			
			Statement statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			connection.close();
			
			if (rs.next()) {
				log.warn("Username already taken");
				return true;
			}
			else {
				log.info("Username is available");
				return false;
			}
		
		}
		catch (SQLException e) { // wrapper for any exception or error state the database would throw (not to be confused with wrapper classes)
			log.error("Unable to connect to the database", e);
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	public Account getAccountByUsernameAndPassword(String username, String password) throws AccountNotFound, InvalidPassword {
		/**
		 * Connects to the Postgres database to attempt to retrieve an account with the give username and password.
		 * 
		 * @param username		the username input by the user
		 * @param password		the password input by the user
		 * @return Account account from the database with matching username and password
		 * @exception AccountNotFound
		 * @exception InvalidPassword
		 * @see Account
		 * @see LoginMenu
		 */
		
		log.info("Attempting to retrieve an existing account from the database");
		
		Connection connection = ConnectionFactoryPostgres.getConnection();
		
		Account account = null;
		
		try {
			
			log.info("Successfully connected to the database");
			
			String sql = "select * from accounts.accounts where user_name = '" + username + "';";
			
			Statement statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				
				log.info("Account with matching username found in the database");
				
				if (rs.getString("pass_word").equals(password)) {
					log.info("The given password matches the account's password");
				}
				else {
					log.warn("Invalid password; the given password does not match this account's password");
					throw new InvalidPassword();
				}
				
				account = new Account();
				
				account.setUsername(rs.getString("user_name"));
				account.setPassword(rs.getString("pass_word"));
			
				connection.close();
				
				return account;
			}
			
			log.warn("Account not found; no account with the given username exists");
			throw new AccountNotFound();
			
		}
		catch (SQLException e) { // wrapper for any exception or error state the database would throw (not to be confused with wrapper classes)
			log.error("Unable to connect to the database", e);
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void createAccount(Account account) {
		/**
		 * Creates a new account in the Postgres database.
		 * Uses a prepared statement to protect against SQL injection attacks.
		 * 
		 * @param account	the account to be created
		 * @see Account
		 */
		
		String sql = "insert into accounts.accounts (user_name, pass_word) values (?, ?);";
		
		PreparedStatement preparedStatement;
		
		Connection connection = ConnectionFactoryPostgres.getConnection();
		
		log.info("Attempting to create a new account using a prepared statement");
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, account.getUsername());
			preparedStatement.setString(2, account.getPassword());
			preparedStatement.execute();
			
			connection.close();
			log.info("Successfully created a new account using a prepared statement");
		}
		catch (SQLException e) {
			log.error("Unable to connect to the database and create a new account using a prepared statement", e);
			e.printStackTrace();
		}
		
	}
	
	public void addToFamilyTree(String username, String person, String parent1, String parent2) {
		
		String sql = "insert into familytree.familytree (user_name, person, parent1, parent2) "
				+ "values (?, ?, ?, ?);";
		
		PreparedStatement preparedStatement;
		
		Connection connection = ConnectionFactoryPostgres.getConnection();
		
		log.info("Attempting to create a new account using a prepared statement");
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, person);
			preparedStatement.setString(3, parent1);
			preparedStatement.setString(4, parent2);
			preparedStatement.execute();
			
			connection.close();
			log.info("Successfully added a new person to the family tree");
		}
		catch (SQLException e) {
			log.error("Unable to connect to the database and add a new person to the family tree", e);
			e.printStackTrace();
		}
	}
	
	public void removeFromFamilyTree(String person) {
		
		String sql = "delete from familytree.familytree where person = ?;";
		
		PreparedStatement preparedStatement;
		
		Connection connection = ConnectionFactoryPostgres.getConnection();
		
		log.info("Attempting to create a new account using a prepared statement");
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, person);
			preparedStatement.execute();
			
			connection.close();
			log.info("Successfully removed a person from the family tree");
		}
		catch (SQLException e) {
			log.error("Unable to connect to the database and remove a person from the family tree", e);
			e.printStackTrace();
		}
		
	}
	

	public void getFamilyTree(String username) {
		/**
		 * Connects to the Postgres database and updates the account.
		 * Uses a prepared statement to protect against SQL injection attacks.
		 * 
		 * @param account	the account to be updated
		 * @see Account
		 */
		
		String sql = "select * from familytree.familytree where user_name = ?;";
		
		log.info("Attempting to get your entire family tree from the database");
		
		Connection connection = ConnectionFactoryPostgres.getConnection();
		
		PreparedStatement preparedStatement;
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				String parent1 = rs.getString("parent1");
				String parent2 = rs.getString("parent2");
				String person = rs.getString("person");
				
				System.out.println();
				System.out.println(parent1 + "-" + parent2);
				System.out.println("      |");
				System.out.println("    " +person);
				System.out.println();
			}
			
			connection.close();
			
			log.info("Family tree successfully retrieved from the database");
		}
		catch (SQLException e) {
			log.error("Unable to connect to the database to retrieve the family tree", e);
			e.printStackTrace();
		}
		
	}

	public void deleteAccount(Account account) {
		/**
		 * Connects to the Postgres database to delete the account.
		 * Uses a prepared statement.
		 * 
		 * @param account	the account to be deleted
		 * @see Account
		 */
		
		log.trace("deleteAccount method in AccountDaoPostgres class");
		log.info("Attempting to delete account");
		
		Connection connection = ConnectionFactoryPostgres.getConnection();
		
		String sql = "delete from accounts.accounts where user_name = ?;";
		
		PreparedStatement preparedStatement;
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, account.getUsername());
			preparedStatement.execute();
			
			connection.close();
			log.info("Successfully deleted the account in the database using a prepared statement");
		}
		catch (SQLException e) {
			log.error("Unable to connect to database to delete account using a prepared statement");
			e.printStackTrace();
		}
		
		Connection connection2 = ConnectionFactoryPostgres.getConnection();
		
		String sql2 = "delete from familytree.familytree where user_name = ?;";
		
		PreparedStatement preparedStatement2;
		
		try {
			preparedStatement2 = connection2.prepareStatement(sql2);
			preparedStatement2.setString(1, account.getUsername());
			preparedStatement2.execute();
			
			connection2.close();
			log.info("Successfully deleted the account's family tree data in the database using a prepared statement");
		}
		catch (SQLException e) {
			log.error("Unable to connect to database to delete account's family tree data using a prepared statement");
			e.printStackTrace();
		}

	}
	
	public void printFamilyTree() {
		// TODO finish this
		log.warn("not yet implemented");
		
	}
	
}
