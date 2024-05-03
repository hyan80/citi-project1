package com.citi.atm;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class WithdrawalTest {
	
	private Bank bank;

	private Customer customer;
	
	private Withdrawal withdrawal;
	
	@Before
	public void setup() {
		bank = new Bank();
		customer = new Customer();
		customer.setBalance(new BigDecimal("100"));
		withdrawal = new Withdrawal(bank, customer, 1);
	}
	
	
	 @Test public void testCall() throws InterruptedException, ExecutionException {
	  
		 ExecutorService executor = Executors.newFixedThreadPool(1); Future<String>
		 result = executor.submit(withdrawal); String testresult = result.get();
		 assertEquals("Withdrawal is successful", testresult); 
		 
		 executor.shutdown();
	 }
	 
	
	@Test
	public void testCallExeption() throws InterruptedException, ExecutionException {
		customer.setBalance(new BigDecimal("100"));
		withdrawal = new Withdrawal(bank, customer, 100000);
		ExecutorService executor = Executors.newFixedThreadPool(1);
		
		Future<String> result = executor.submit(withdrawal);
		String testresult = result.get();
        assertEquals("withdrawal fails due to insufficient funds", testresult);
        
    	executor.shutdown();
	}
}
