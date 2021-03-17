package com.familytree.ui;

import java.util.Scanner;

import com.familytree.dao.AccountDao;
import com.familytree.exception.AccountNotFound;
import com.familytree.exception.InvalidPassword;
import com.familytree.pojo.Account;

public class LoginMenu implements Menu {
	
	private Menu nextMenu;
	
	private Menu previousMenu;
	
	private Scanner scanner;
	
	private AccountDao accountDao;
	
	private Account account;

	@Override
	public void displayOptions() {

		System.out.println("You have selected login");
		System.out.println();
		
		String username = "";
		while (username.equals("")) {
			System.out.print("Please enter your username: ");
			username = scanner.nextLine();
			System.out.println();
		}
		
		String password = "";
		while (password.equals("")) {
			System.out.print("Please enter your password: ");
			password = scanner.nextLine();
			System.out.println();
		}
		
		Account account = null;
		
		try {
			account = accountDao.getAccountByUsernameAndPassword(username, password);
		}
		catch (AccountNotFound e) {
			System.out.println("No account with that username exists; please try logging in again or create a new account.");
		}
		catch (InvalidPassword e) {
			System.out.println("Invalid password; please try again.");
		}
		finally {
			System.out.println();
		}
		
		if (account != null) {
			nextMenu.setAccount(account);
		}
		else {
			nextMenu = previousMenu;
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
	
	public LoginMenu() {
		super();
	}

}
