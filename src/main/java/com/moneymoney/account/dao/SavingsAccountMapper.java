package com.moneymoney.account.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.moneymoney.account.SavingsAccount;

public class SavingsAccountMapper implements RowMapper<SavingsAccount> {

	@Override
	public SavingsAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		int accountNumber = rs.getInt("accountNumber");
		String accountHolderName = rs.getString("accountHolderName");
		double accountBalance = rs.getDouble(3);
		boolean salary = rs.getBoolean("salaried");
		SavingsAccount savingsAccount = new SavingsAccount(accountNumber, accountHolderName, accountBalance, salary);
		return savingsAccount;
	}

	
	/*
	 * @Override public Object extractData(ResultSet rs) throws SQLException,
	 * DataAccessException { int accountNumber = rs.getInt("accountNumber"); String
	 * accountHolderName = rs.getString("accountHolderName"); double accountBalance
	 * = rs.getDouble(3); boolean salary = rs.getBoolean("salaried"); SavingsAccount
	 * savingsAccount = new SavingsAccount(accountNumber, accountHolderName,
	 * accountBalance, salary); return savingsAccount;
	 * 
	 * }
	 */

}
