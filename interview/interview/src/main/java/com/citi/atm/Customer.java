package com.citi.atm;

import java.math.BigDecimal;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Customer {
	//Assume that each customer has a unique id
	private Long id;

	private Integer account;
	// Assume that balance can't be less than zero
	private BigDecimal balance;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public BigDecimal getBalance() {
        return new BigDecimal("10000");
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    @Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(id, other.id);
	}
}

