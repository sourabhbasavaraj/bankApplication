package org.Entities;

import java.time.LocalDate;

import org.Business.AccountAlreadyActivatedException;
import org.Business.UnderAgeException;

public interface ISavings extends IAccount{

	LocalDate getDOB();

	void setDOB(LocalDate dOB);

	String getGender();

	void setGender(String gender);

	String getPhoneNo();

	void setPhoneNo(String phoneNo);

	boolean open() throws UnderAgeException, AccountAlreadyActivatedException, UnderAgeException, AccountAlreadyActivatedException;

}