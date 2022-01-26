package org.Entities;

import java.time.LocalDate;
import java.util.List;

import org.Business.AccountAlreadyActivatedException;
import org.Business.AccountAlreadyClosedException;
import org.Business.AccountInactiveException;
import org.Business.InsufficientBalanceException;
import org.Business.InvalidPinException;
import org.Business.InvalidPrivilageException;
import org.Business.LimitExceededException;
import org.Business.NoTansferLogException;
import org.Business.TransferLog;


public  class Account implements IAccount {
	
	private int AccNo;
	private String name;
	private int pin;
	private double balance;
	private Privilge privilge;
	private boolean IsActive;
	private LocalDate ActivatedDate;
	private LocalDate ClosedDate;
	private double limit;
	private String LTDate=null;
	
	protected Account() {
		
	}
	
	protected Account( String name, int pin, double balance, Privilge privilge) {
		super();
		this.AccNo = IDGenerator.generateId();
		this.name = name;
		this.pin = pin;
		this.balance = balance;
		this.privilge = privilge;
	}

	@Override
	public int getAccNo() {
		return AccNo;
	}

	@Override
	public void setAccNo(int accNo) {
		AccNo = accNo;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getPin() {
		return pin;
	}

	@Override
	public void setPin(int pin) {
		this.pin = pin;
	}

	@Override
	public double getBalance() {
		return balance;
	}

	@Override
	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public Privilge getPrivilge() {
		return privilge;
	}

	@Override
	public void setPrivilge(Privilge privilge) {
		this.privilge = privilge;
	}

	@Override
	public boolean getIsActive() {
		return IsActive;
	}

	@Override
	public void setIsActive(boolean isActive) {
		IsActive = isActive;
	}

	@Override
	public LocalDate getActivatedDate() {
		return ActivatedDate;
	}

	@Override
	public void setActivatedDate(LocalDate d1) {
		ActivatedDate = d1;
	}

	@Override
	public LocalDate getClosedDate() {
		return ClosedDate;
	}

	@Override
	public void setClosedDate(LocalDate d1) {
		ClosedDate = d1;
	}
	
	public double getLimit() {
		return limit;
	}

	public void setLimit(double limit) {
		this.limit = limit;
	}

	public String getLTDate() {
		return LTDate;
	}

	public void setLTDate(String lTDate) {
		LTDate = lTDate;
	}
	
	public void display() {
		System.out.println("Balance: "+this.getBalance());
		System.out.println("Limit: "+this.getLimit());
	}
	
	protected boolean activateAccount() throws AccountAlreadyActivatedException {
		boolean isActivated=false;
		
		if(this.getIsActive()) {
			throw new AccountAlreadyActivatedException("Account is already opened");
		}
		else {
			this.setIsActive(true);
			LocalDate d1=LocalDate.now();
			this.setIsActive(true);	
			this.setActivatedDate(d1);
			isActivated=true;
		}
		return isActivated;
	}
	
	private boolean closeAccount() throws AccountAlreadyClosedException {
		boolean isDeactivated=false;
		
		if(this.getIsActive()) {
			this.setIsActive(false);
			LocalDate d1=LocalDate.now();
			this.setClosedDate(d1);
			isDeactivated=true;
			
		}
		else {
			throw new AccountAlreadyClosedException("Account is already opened");
		}
		return isDeactivated;
	}
	
	protected boolean isAccountActive() throws AccountInactiveException {
		boolean isAccountActivated=false;
		if(this.getIsActive()) {
			isAccountActivated=true;
		}
		else {
			throw new AccountInactiveException("Account is Inactive");
		}
		return isAccountActivated;
	}
	
	protected boolean isBalanceSufficient(double amount) throws InsufficientBalanceException {
		boolean isValid=false;
		if(this.getBalance()>=amount) {
			isValid=true;
		}
		else {
			throw new InsufficientBalanceException("No Sufficient Balance");
		}
		return isValid;
	}
	
	protected boolean isPinValid(int pin) throws InvalidPinException {
		boolean isValid=false;
		if(this.getPin()==pin) {
			isValid=true;
		}
		else {
			throw new InvalidPinException("Pin is Invalid");
		}
		return isValid;
	}
	
	protected boolean isLimitExceeded(double limit,double amount,TransferInfo tf) throws LimitExceededException  {
		boolean isValid = true;
		
		List<TransferInfo> list;
		
		double totalAmtTransfered=0;
		try {
			list = TransferLog.getTransfers(tf);
			for(TransferInfo t:list) {
				totalAmtTransfered += t.getAmount();
			}
		} catch (NoTansferLogException e) {
			
		}
		
		if((totalAmtTransfered+amount) > limit) {
			throw new LimitExceededException("Daily limit exceeded");
		}
		else {
			isValid=false;
		}
		
		return isValid;
	}
	
	
	
	@Override
	public boolean close() throws AccountAlreadyClosedException {
		boolean isClosed = false;
		if(closeAccount()){
			isClosed=true;
		}
		return isClosed;
	}
	
	@Override
	public boolean deposit(double amount) throws AccountInactiveException{
		boolean isDeposited=false;
		if(isAccountActive()) {
			double newBalance=this.getBalance()+amount;
			this.setBalance(newBalance);
			isDeposited=true;
		}
		
		return isDeposited;
	}
	
	@Override
	public boolean withdraw(int pin,double amount) throws AccountInactiveException, InvalidPinException, InsufficientBalanceException {
		boolean isWithdrawn=false;
		
		if(isAccountActive()) 
			if(isPinValid(pin))
				if(isBalanceSufficient(amount)) {
					this.setBalance(this.getBalance()-amount);
					isWithdrawn=true;
				}
		
		return isWithdrawn;
	}
	
	
	
	public boolean Transfer(TransferInfo transferInfo) throws AccountInactiveException, InvalidPrivilageException, InvalidPinException, InsufficientBalanceException, NoTansferLogException, LimitExceededException {
		boolean isTransfered=false;
		IAccount a1=transferInfo.getFromAccount();
		IAccount a2=transferInfo.getToAccount();
		double amount=transferInfo.getAmount();
		int pin=transferInfo.getPin();
		Privilages p=Privilages.getInstance();
		double limit=p.getPrivilages(a1.getPrivilge());
		
		
		if(a1.getIsActive() && a2.getIsActive()) {
			if(!isLimitExceeded(limit,amount,transferInfo)) {
				a1.withdraw(pin, amount);
				a2.deposit(amount);
				TransferLog.logTransfer(transferInfo);
			}
		}
		else {
			throw new AccountInactiveException("Account is Inactive");
		}
				
		
		return isTransfered;
	}
}
