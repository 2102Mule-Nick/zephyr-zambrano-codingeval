package com.familytree.ui;

import java.util.Scanner;

import com.familytree.dao.AccountDao;
import com.familytree.pojo.Account;

public interface Menu {
	
	public void displayOptions();
	
	public void setPreviousMenu(Menu previousMenu);
	
	public Menu getPreviousMenu();
	
	public void setNextMenu(Menu nextMenu);
	
	public Menu getNextMenu();
	
	public Menu advance();
	
	public Scanner getScanner();
	
	public void setScanner(Scanner scanner);
	
	public AccountDao getAccountDao();
	
	public void setAccountDao(AccountDao accountDao);

	public Account getAccount();
	
	public void setAccount(Account account);
	
}
