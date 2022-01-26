package org.Entities;

import org.Business.AccountAlreadyActivatedException;
import org.Business.RegNoInvalidException;

public class Current extends Account implements ICurrent {
	
	private String company;
	private String website;
	private String registrationNo;
	
	protected Current() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Current( String name, int pin, double balance, Privilge privilge, String company, String website,
			String registrationNo) {
		super(name, pin, balance, privilge);
		this.company = company;
		this.website = website;
		this.registrationNo = registrationNo;
	}

	@Override
	public String getCompany() {
		return company;
	}

	@Override
	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String getWebsite() {
		return website;
	}

	@Override
	public void setWebsite(String website) {
		this.website = website;
	}

	@Override
	public String getRegistrationNo() {
		return registrationNo;
	}

	@Override
	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public boolean validateRegistrationNumber() throws RegNoInvalidException {
		boolean isValid=false;
		if(this.getRegistrationNo() != null) {
			isValid=true;
		}
		else {
			throw new RegNoInvalidException("Company not registered");
		}
		return isValid;
	}
	
	@Override
	public boolean open() throws AccountAlreadyActivatedException, RegNoInvalidException {
		// TODO Auto-generated method stub
		boolean isOpened=false;

		if(validateRegistrationNumber()) {
			activateAccount();
			isOpened=true;
		}
		return isOpened;
	}
	
}
