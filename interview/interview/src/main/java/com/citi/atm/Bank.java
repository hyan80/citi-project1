package com.citi.atm;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bank {
	private static Logger logger = LoggerFactory.getLogger(Bank.class);
	
    //Assume that there is predefined maximum withdrawal amount
    private final static int MAXIMUM_WITHDRAWAL = 1000;
    
    public String wthdraw(Customer customer, int withdrawalamount) throws WithdrawExeption {
        synchronized (customer) {
            BigDecimal balance = customer.getBalance();
            if (balance.intValue() <= withdrawalamount) {
            	logger.info("the balanace is insufficient. balance: " + balance.intValue() + "; withdrawal: " + withdrawalamount);
                System.out.println("Sorry! the balanace is insufficient.");
                throw new WithdrawExeption("withdrawal fails due to insufficient funds");
            } else {
                balance = balance.subtract(new BigDecimal(withdrawalamount));
                logger.info("Customer account balance is updated");
                System.out.println("Please collect your money and remove the card");
            }
            return "withdrawal is successful";
        }
    }
}
