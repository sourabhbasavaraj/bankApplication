package org.Entities;

import java.time.LocalDate;
import java.time.Period;

import org.Business.AccountAlreadyActivatedException;
import org.Business.UnderAgeException;

public class Savings extends Account implements ISavings{
	
	private LocalDate DOB;
	private String gender;
	private String phoneNo;

	protected Savings() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Savings( String name, int pin, double balance, Privilge privilge, LocalDate dOB, String gender,
			String phoneNo) {
		super( name, pin, balance, privilge);
		this.DOB = dOB;
		this.gender = gender;
		this.phoneNo = phoneNo;
	}

	@Override
	public LocalDate getDOB() {
		return DOB;
	}

	@Override
	public void setDOB(LocalDate dOB) {
		this.DOB = dOB;
	}

	@Override
	public String getGender() {
		return gender;
	}

	@Override
	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String getPhoneNo() {
		return phoneNo;
	}

	@Override
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	protected boolean validateAge() throws UnderAgeException {
		boolean isValid=false;
		
		LocalDate currentDate=LocalDate.now();
		Period diff=Period.between(DOB,currentDate) ;
		
		if(diff.getYears()>18) {
			isValid=true;
		}
		else {
			throw new UnderAgeException("You are below 18");
		}
		return isValid;
	}
	
	
	
	@Override
	public boolean open() throws UnderAgeException, AccountAlreadyActivatedException {
		boolean isOpened=false;
		if(validateAge()) {
			activateAccount();
			isOpened=true;
		}
		return isOpened;
	}

		
}
