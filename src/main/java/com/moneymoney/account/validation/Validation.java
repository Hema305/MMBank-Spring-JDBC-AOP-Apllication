package com.moneymoney.account.validation;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.moneymoney.account.SavingsAccount;

@Aspect
@Component
public class Validation {
	Logger logger = Logger.getLogger(Validation.class.getName());



	@Around("execution(* com.moneymoney.account.service.SavingsAccountServiceImpl.withdraw(..))")
	public void log2(ProceedingJoinPoint pjp) throws Throwable {
		Object[] params = pjp.getArgs();
		SavingsAccount account=(SavingsAccount)params[0];
		double currentBalance = account.getBankAccount().getAccountBalance();
	
		if ((Double)params[1]>0 && currentBalance>(Double)params[1]) {
			pjp.proceed();
			logger.info("withdrawing succesfully ");
			
		} else {
			logger.info("Invalid amount or insufficient funds");

		}

	}
	@Around("execution(* com.moneymoney.account.service.SavingsAccountServiceImpl.deposit(..))")
	public void log1(ProceedingJoinPoint pjp) throws Throwable {
	
		Object[] params = pjp.getArgs();
		
		if ((Double)params[1]>0) {
			pjp.proceed();
			logger.info("deposited succesfully ");
			
		} else {
			logger.info("Invalid amount");

		}

		
	}

}
