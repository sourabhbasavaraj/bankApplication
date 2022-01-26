package org.Business;

import org.Entities.IAccount;
import org.Entities.Modes;

public interface AccManager {
	
	public boolean open() ;
	public boolean close();
	public boolean withdraw(int pin,double amount)  ;
	public boolean deposit(double amount) ;
	public void transfer(IAccount a,double amt,Modes m) throws RegNoInvalidException ;
	
}
