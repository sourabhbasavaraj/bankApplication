package org.View;

import java.time.LocalDate;
import java.util.List;

import org.Business.AccountAlreadyActivatedException;
import org.Business.AccountAlreadyClosedException;
import org.Business.AccountInactiveException;
import org.Business.InsufficientBalanceException;
import org.Business.InvalidAccountTypeException;
import org.Business.InvalidPinException;
import org.Business.InvalidPrivilageException;
import org.Business.LimitExceededException;
import org.Business.NoTansferLogException;
import org.Business.RegNoInvalidException;
import org.Business.TransferLog;
import org.Business.UnderAgeException;
import org.Entities.AccountImplFactory;
import org.Entities.IAccount;
import org.Entities.ICurrent;
import org.Entities.ISavings;
import org.Entities.Modes;
import org.Entities.Privilge;
import org.Entities.TransferInfo;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		IAccount acc1=null;
		IAccount acc2=null;
		
		try {
			acc1=AccountImplFactory.createAccount("Savings");
			acc2=AccountImplFactory.createAccount("Current");
		}
		catch(InvalidAccountTypeException e) {
			System.out.println(e);
		}
		
				
		ISavings s1=(ISavings)acc1;
		ICurrent c1=(ICurrent)acc2;
		
		s1.setDOB(LocalDate.of(2000, 8, 14));
		s1.setPin(1234);
		s1.setPrivilge(Privilge.PREMIUM);
		c1.setRegistrationNo("17121A04L6");
		s1.setBalance(600000);
		c1.setBalance(20000);
		

		try {
			s1.open();
		} catch (UnderAgeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (AccountAlreadyActivatedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			c1.open();
		} catch (AccountAlreadyActivatedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RegNoInvalidException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	try {
			s1.deposit(2000);
		} catch (AccountInactiveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			s1.withdraw(1234, 1000);
		} catch (AccountInactiveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPinException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InsufficientBalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		TransferInfo tf=new TransferInfo(s1,c1,1234,80000.0,Modes.IMPS);
		try {
			s1.Transfer(tf);
		} catch (AccountInactiveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPrivilageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPinException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InsufficientBalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoTansferLogException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LimitExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("The savings account balance is: "+s1.getBalance());
		System.out.println("The current account balance is: "+c1.getBalance());
		
		TransferInfo tf2=new TransferInfo(s1,c1,1234,200000000.0,Modes.IMPS);
		try {
			s1.Transfer(tf2);
		} catch (AccountInactiveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPrivilageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPinException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InsufficientBalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoTansferLogException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LimitExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("The savings account balance after transfer is: "+ s1.getBalance());
		System.out.println("The current account balance after transfer is: "+ c1.getBalance());
	}

}
