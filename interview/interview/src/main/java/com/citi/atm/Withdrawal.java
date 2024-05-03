package com.citi.atm;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Withdrawal implements Callable<String> {
	private static Logger logger = LoggerFactory.getLogger(Withdrawal.class);
	
    private Bank bank;
    private Customer customer;
    private int withdrawalAmount;

    public Withdrawal(Bank bank, Customer customer, int withdrawalAmount){
        this.bank = bank;
        this.customer = customer;
        this.withdrawalAmount = withdrawalAmount;
    }
    
    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(int withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    @Override
    public String call() throws WithdrawExeption {
        try{
            bank.wthdraw(customer, withdrawalAmount);
        } catch (Exception e){
        	logger.debug(e.getMessage());
            return ("withdrawal fails due to insufficient funds");
        }
        return "Withdrawal is successful";
    }
}

