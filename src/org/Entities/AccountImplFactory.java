package org.Entities;

import org.Business.InvalidAccountTypeException;

public class AccountImplFactory {

	private AccountImplFactory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static IAccount createAccount(String type) throws InvalidAccountTypeException {
		IAccount a=null;
		if(type.equals("Savings")) {
			a=new Savings();
		}
		else if(type.equals("Current")) {
			a=new Current();
		}
		else {
			throw new InvalidAccountTypeException("Account type is Invalid");
		}
		a.setAccNo(IDGenerator.generateId());
		return a;
	}
	
}
