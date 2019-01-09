package com.moneymoney.account.dao;

import java.sql.SQLException;
import java.util.List;

import com.moneymoney.account.SavingsAccount;
import com.moneymoney.exception.AccountNotFoundException;

public interface SavingsAccountDAO {
	
	SavingsAccount createNewAccount(SavingsAccount account) throws ClassNotFoundException, SQLException;
	SavingsAccount getAccountById(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	SavingsAccount deleteAccount(int accountNumber) throws ClassNotFoundException, SQLException;
	List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException;
	void updateBalance(int accountNumber, double currentBalance) throws ClassNotFoundException, SQLException;
	//void commit() throws SQLException;
	double checkBalance(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;

	SavingsAccount getAccountByName(String accountHolderName) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	boolean updateAccountType(SavingsAccount account) throws SQLException,
			ClassNotFoundException;
	List<SavingsAccount> sortByname() throws ClassNotFoundException, SQLException;
	List<SavingsAccount> sortByBalance() throws ClassNotFoundException, SQLException;
	
}
