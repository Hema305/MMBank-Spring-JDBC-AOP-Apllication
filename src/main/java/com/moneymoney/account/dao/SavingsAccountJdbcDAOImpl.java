package com.moneymoney.account.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.moneymoney.account.SavingsAccount;
import com.moneymoney.exception.AccountNotFoundException;

@Repository
@Primary
public class SavingsAccountJdbcDAOImpl implements SavingsAccountDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public SavingsAccount createNewAccount(SavingsAccount account) throws ClassNotFoundException, SQLException {
		jdbcTemplate.update("INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?)", new Object[] {
				account.getBankAccount().getAccountNumber(),
				account.getBankAccount().getAccountHolderName(),
				account.getBankAccount().getAccountBalance(),
				account.isSalary(), null, "SA"
		});
		return account;
	}

	@Override
	public SavingsAccount getAccountById(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		// TODO Auto-generated method stub
		 return jdbcTemplate.queryForObject("SELECT* FROM ACCOUNT where accountNumber=?",new Object[]{accountNumber},new SavingsAccountMapper());
		 
	}

	@Override
	public SavingsAccount deleteAccount(int accountNumber) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		jdbcTemplate.update("DELETE FROM ACCOUNT WHERE accountNumber=?",new Object[]{accountNumber});
		return null;
	
		
	}

	@Override
	public List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException {
		
		return jdbcTemplate.query("SELECT * FROM ACCOUNT",	new SavingsAccountMapper());
	}

	@Override
	public void updateBalance(int accountNumber, double currentBalance) throws ClassNotFoundException, SQLException {
		jdbcTemplate.update(("UPDATE ACCOUNT SET accountBalance =? where accountNumber=?"),new Object[]{currentBalance,accountNumber});
		
	}

	@Override
	public double checkBalance(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		return jdbcTemplate.queryForObject("SELECT accountBalance FROM account where accountNumber=?",new Object[]{accountNumber},Double.class);
		
	}

	@Override
	public SavingsAccount getAccountByName(String accountHolderName)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		return jdbcTemplate.queryForObject("SELECT* FROM ACCOUNT where accountHolderName=?",new Object[]{accountHolderName},new SavingsAccountMapper());
	}

	@Override
	public boolean updateAccountType(SavingsAccount account) throws SQLException, ClassNotFoundException {
		jdbcTemplate.update(("UPDATE ACCOUNT SET accountHolderName=?,salaried=? where accountNumber=?"),new Object[] {account.getBankAccount().getAccountHolderName(),
				account.isSalary(),
				account.getBankAccount().getAccountNumber()});
		return false;
	}


	@Override
	public List<SavingsAccount> sortByname() throws ClassNotFoundException, SQLException{
	  return jdbcTemplate.query("SELECT * FROM account order By accountHolderName",new Object[] {},new SavingsAccountMapper());
		 
	
		
	}

	@Override
	public List<SavingsAccount> sortByBalance() throws ClassNotFoundException, SQLException {
		
	 return jdbcTemplate.query("SELECT * FROM account order By salaried",new Object[] {},new SavingsAccountMapper());
	}

}
