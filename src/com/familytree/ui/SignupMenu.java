package com.familytree.ui;

import java.util.Scanner;

import com.familytree.dao.AccountDao;
import com.familytree.exception.AccountNotFound;
import com.familytree.exception.InvalidPassword;
import com.familytree.pojo.Account;

public class SignupMenu implements Menu {

	private Menu nextMenu;
	
	private Menu previousMenu;
	
	private Scanner scanner;
	
	private AccountDao accountDao;
	
	private Account account;

	@Override
	public void displayOptions() {

		System.out.println("You have selected signup");
		System.out.println();
		
		String username = "";
		
		boolean usernameTaken = true;
		
		while (usernameTaken == true) {
			
			username = "";
			
			while (username.equals("")) {
				System.out.print("Username: ");
				username = scanner.nextLine();
				System.out.println();
				
			}
			
			usernameTaken = accountDao.getAccountByUsername(username);
			
		}
		
		String password = "";
		while (password.equals("")) {
			System.out.print("Password: ");
			password = scanner.nextLine();
			System.out.println();
		}
		
		Account newAccount = new Account(username, password);
		
		accountDao.createAccount(newAccount);
		
		try {
			newAccount = accountDao.getAccountByUsernameAndPassword(username, password);
			
			nextMenu.setAccount(newAccount);
			
		} catch (AccountNotFound | InvalidPassword e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setPreviousMenu(Menu previousMenu) {
		this.previousMenu = previousMenu;
	}

	@Override
	public Menu getPreviousMenu() {
		return this.previousMenu;
	}

	@Override
	public void setNextMenu(Menu nextMenu) {
		this.nextMenu = nextMenu;
	}

	@Override
	public Menu getNextMenu() {
		return this.nextMenu;
	}

	@Override
	public Menu advance() {
		return nextMenu;
	}

	@Override
	public Scanner getScanner() {
		return this.scanner;
	}

	@Override
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
	
	@Override
	public AccountDao getAccountDao() {
		return this.accountDao;
	}

	@Override
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public Account getAccount() {
		return this.account;
	}

	@Override
	public void setAccount(Account account) {
		this.account = account;
	}
	
	public SignupMenu() {
		super();
	}	

}
