package com.citi.atm;

public class WithdrawExeption extends RuntimeException{
	public WithdrawExeption(String message) {
		super(message);
	}
}
