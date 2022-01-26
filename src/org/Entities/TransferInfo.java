package org.Entities;

public class TransferInfo {
	
	private IAccount fromAccount;
	private IAccount toAccount;
	private int pin;
	private double amount;
	private Modes mode;
	
	public TransferInfo(IAccount fromAccount, IAccount toAccount, int pin, double amount, Modes mode) {
		super();
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.pin = pin;
		this.amount = amount;
		this.mode = mode;
	}

	public IAccount getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(IAccount fromAccount) {
		this.fromAccount = fromAccount;
	}

	public IAccount getToAccount() {
		return toAccount;
	}

	public void setToAccount(IAccount toAccount) {
		this.toAccount = toAccount;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Modes getMode() {
		return mode;
	}

	public void setMode(Modes mode) {
		this.mode = mode;
	}
	
	
	
}
