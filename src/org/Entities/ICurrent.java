package org.Entities;

import org.Business.*;
public interface ICurrent extends IAccount{

	String getCompany();

	void setCompany(String company);

	String getWebsite();

	void setWebsite(String website);

	String getRegistrationNo();

	void setRegistrationNo(String registrationNo);

	boolean open() throws AccountAlreadyActivatedException, RegNoInvalidException;

}