package com.familytree;

import java.util.Scanner;

import com.familytree.dao.AccountDao;
import com.familytree.ui.LoginMenu;
import com.familytree.ui.MainMenu;
import com.familytree.ui.Menu;
import com.familytree.ui.SignupMenu;
import com.familytree.ui.WelcomeMenu;

public class Driver {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		AccountDao accountDao = new AccountDao();
		
		Menu mainMenu = new MainMenu();
		Menu loginMenu = new LoginMenu();
		Menu signupMenu = new SignupMenu();
		
		Menu welcomeMenu = new WelcomeMenu(loginMenu, signupMenu);
		
		welcomeMenu.setScanner(scanner);
		mainMenu.setScanner(scanner);
		loginMenu.setScanner(scanner);
		signupMenu.setScanner(scanner);
		
		loginMenu.setNextMenu(mainMenu);
		signupMenu.setNextMenu(mainMenu);
		
		loginMenu.setPreviousMenu(welcomeMenu);
		signupMenu.setPreviousMenu(welcomeMenu);
		
		loginMenu.setAccountDao(accountDao);
		signupMenu.setAccountDao(accountDao);
		mainMenu.setAccountDao(accountDao);
		
		Menu nextMenu = welcomeMenu;
		
		do {
			nextMenu.displayOptions();
			
			nextMenu = nextMenu.advance();
			
		} while (nextMenu != null);
		
		scanner.close();

	}

}
