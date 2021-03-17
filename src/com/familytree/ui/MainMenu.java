package com.familytree.ui;

import java.util.Scanner;

import com.familytree.dao.AccountDao;
import com.familytree.pojo.Account;

public class MainMenu implements Menu {
	
	private Scanner scanner;
	
	private Menu previousMenu;
	
	private Menu nextMenu;
	
	private AccountDao accountDao;
	
	private Account account;

	@Override
	public void displayOptions() {
		
		System.out.println("Welcome " + account.getUsername() + "!");
		System.out.println();
		
		String selection = "";
		System.out.println();
		
		boolean quit = false;
		
		while (quit == false) {
			
			System.out.println();
			System.out.println("What would you like to do?");
			System.out.println("a: add family member");
			System.out.println("r: remove family member");
			System.out.println("p: print family tree");
			System.out.println("d: delete account");
			System.out.println("q: quit program");
			
			System.out.println();

			System.out.print("Please type in your selection: ");
			selection = scanner.nextLine();
			System.out.println();
			
			if (selection.equals("a") || selection.equals("A")) {
				System.out.println("Add to the family tree");
				System.out.println();
				
				String person = "";
				while (person.equals("")) {
					System.out.print("Name of the person: ");
					person = scanner.nextLine();
					System.out.println();
				}
				
				String parent1 = "";
				while (parent1.equals("")) {
					System.out.print("Parent 1: ");
					parent1 = scanner.nextLine();
					System.out.println();
				}
				
				String parent2 = "";
				while (parent2.equals("")) {
					System.out.print("Parent 2: ");
					parent2 = scanner.nextLine();
					System.out.println();
				}
				
				accountDao.addToFamilyTree(account.getUsername(), person, parent1, parent2);
				
			}
			else if (selection.equals("r") || selection.equals("R")) {
				System.out.println("Remove from family tree");
				System.out.println();
				
				String person = "";
				while (person.equals("")) {
					System.out.print("Name of the person: ");
					person = scanner.nextLine();
					System.out.println();
				}
				
				accountDao.removeFromFamilyTree(person);
			}
			else if (selection.equals("p") || selection.equals("P")) {
				System.out.println("Your Family Tree");
				System.out.println();
				
				accountDao.getFamilyTree(account.getUsername());
				
			}
			else if (selection.equals("d") || selection.equals("D")) {
				quit = deleteAccount(selection);
			}
			else if(selection.equals("q") || selection.equals("Q")) {
				quit = quit(selection);
			}
			else {
				System.out.println("Invalid input; please try again.");
				System.out.println();
			}
			
		}
		
	}
	
	public boolean deleteAccount(String selection) {
		
		System.out.println("Are you sure you want to delete your account? Type 'DELETE ACCOUNT' to confirm.");
		System.out.println("Hit the enter key to cancel account deletion.");
		System.out.print("Your selection: ");
		selection = scanner.nextLine();
		System.out.println();
		
		if (selection.equals("DELETE ACCOUNT")) {
			accountDao.deleteAccount(account);
			
			System.out.println("Account successfully deleted. Have a nice day!");
			System.out.println();
			
			return true;
		}
		else {
			System.out.println("Account deletion cancelled");
			System.out.println();
			return false;
		}
		
	}
	
	public boolean quit(String selection) {
		System.out.println("Are you sure you want to quit? (y / n)");
		System.out.print("Your selection: ");
		String confirmationSelection = scanner.nextLine();
		System.out.println();

		if (confirmationSelection.equals("y") || confirmationSelection.equals("Y")) {
			System.out.println("Thank you and have a great day!");
			return true;
		}
		else if (confirmationSelection.equals("n") || confirmationSelection.equals("N")) {
			return false;
		}
		else {
			System.out.println("Invalid selection; please try again.");
			System.out.println();
			return false;
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

	public MainMenu() {
		super();
	}

}
