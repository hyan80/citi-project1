package com.citi.atm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ATM {
	private static Logger logger = LoggerFactory.getLogger(ATM.class);
	
    //ATM withdrawal amount limit
    private final static int MAXIMUM_WITHDRAWAL = 1000;
  
    public static void main(String ...args) {
        Bank bank = new Bank();
        int account = 1;
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        int amount = 0;
        int amount2 = 0;
        Scanner input = new Scanner(System.in);
        //Use the amount entered by user to generate two customers for two threads
        System.out.println("Enter Amount to be withdrawn: ");
        amount = input.nextInt();
        
        if (isValidAmount(amount)) {
            customer1.setAccount(account++);
            customer2.setAccount(account);

            Withdrawal withdrawal1 = new Withdrawal(bank, customer1, amount);
            Withdrawal withdrawal2 = new Withdrawal(bank, customer2, amount2);
            List<Callable<String>> withdrawalList = new ArrayList<>();
            withdrawalList.add(withdrawal1);
            withdrawalList.add(withdrawal2);
            ExecutorService executor = Executors.newFixedThreadPool(2);
            try {
                executor.invokeAll(withdrawalList);
                executor.shutdown();
                if (!executor.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
            	logger.debug(e.getMessage());
                executor.shutdownNow();
            }
        }
    }

    private static boolean isValidAmount(int amount) {
        boolean valid = false;
        if(amount <= 0) {
        	logger.info("ATM amount should be greater than zero");
            System.out.println("ATM amount should be greater than zero");
        } else if(amount % 10 != 0){
        	logger.info("Please enter the amount in multiples of 10");
            System.out.println("Please enter the amount in multiples of 10");
        } else if(amount > MAXIMUM_WITHDRAWAL) {
        	logger.info("Bank cash limit exceeds.");
            System.out.println("Bank cash limit exceeds.");
        } else
            valid = true;

        return valid;
    }
}

