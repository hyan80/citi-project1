package com.citi.atm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class BankTest {
	private Bank bank;
	private Customer customer;
	
	@Before
	public void setup() {
		bank = new Bank();
		customer = new Customer();
		customer.setBalance(new BigDecimal("100"));
	}

	@Test
	public void testWithdraw() {
		String result = bank.wthdraw(customer, 20);
		
		assertEquals("withdrawal is successful", result);
	}
	
	@Test(expected = WithdrawExeption.class)
	public void testWithdrawException() {
		String result = bank.wthdraw(customer, 20000);
	}
}
