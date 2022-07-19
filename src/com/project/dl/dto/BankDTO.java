package com.project.dl.dto;

public class BankDTO 
{
	public int acc_number;
	public String name;
	public int pin;
	public String balance;
	public BankDTO()
	{
		
	}
	public BankDTO(int acc_number, String name, int pin, String balance) {
		super();
		this.acc_number = acc_number;
		this.name = name;
		this.pin = pin;
		this.balance = balance;
	}
	public int getAcc_number() {
		return acc_number;
	}
	public void setAcc_number(int acc_number) {
		this.acc_number = acc_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	
}
