package org.Entities;

import org.Business.*;
import java.time.LocalDate;

public interface IAccount {

	int getAccNo();

	void setAccNo(int accNo);

	String getName();

	void setName(String name);

	int getPin();

	void setPin(int pin);

	double getBalance();

	void setBalance(double balance);

	Privilge getPrivilge();

	void setPrivilge(Privilge privilge);

	boolean getIsActive();

	void setIsActive(boolean isActive);

	LocalDate getActivatedDate();

	void setActivatedDate(LocalDate d1);

	LocalDate getClosedDate();

	void setClosedDate(LocalDate d1);

	boolean close() throws AccountAlreadyClosedException;

	boolean deposit(double amount) throws AccountInactiveException;

	boolean withdraw(int pin, double amount) throws AccountInactiveException, InvalidPinException, InsufficientBalanceException;
	
	boolean Transfer(TransferInfo transferInfo) throws AccountInactiveException, InvalidPrivilageException, InvalidPinException, InsufficientBalanceException, NoTansferLogException, LimitExceededException ;
}