package com.familytree.ui;

import java.util.Scanner;

import com.familytree.dao.AccountDao;
import com.familytree.pojo.Account;


public class WelcomeMenu implements Menu {
	
	private Scanner scanner;
	
	private Menu loginMenu;
	
	private Menu signupMenu;
	
	private Menu previousMenu;
	
	private Menu nextMenu;
	
	@Override
	public void displayOptions() {
		
		System.out.println("Welcome to the family tree application!");
		System.out.println();
		
		System.out.println("Would you like to log in or sign up? ('l' or log in, 's' for sign up, 'q' to quit'): ");
		System.out.print("Your selection: ");
		String selection = scanner.nextLine();
		System.out.println();
		
		if (selection.equals("l") || selection.equals("L")) {
			nextMenu = loginMenu;
		}
		else if (selection.equals("s") || selection.equals("S")) {
			nextMenu = signupMenu;
		}
		else if (selection.equals("q") || selection.equals("Q")) {
			System.out.println("Exiting program. Have a nice day!");
			System.out.println();
			nextMenu = null;
		}
		else {
			System.out.println("Invalid selection; please try again.");
			System.out.println();
			nextMenu = this;
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
		return this.nextMenu;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAccountDao(AccountDao accountDao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account getAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAccount(Account account) {
		// TODO Auto-generated method stub
		
	}

	public WelcomeMenu() {
		super();
	}

	public WelcomeMenu(Menu loginMenu, Menu signupMenu) {
		super();
		this.loginMenu = loginMenu;
		this.signupMenu = signupMenu;
	}
	
	

}
