package com.rvz.transfer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uid;
	
	private String username;
	
	private int accountnumber;
	
	private int  amount;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(int accountnumber) {
		this.accountnumber = accountnumber;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public User(Integer uid, String username, int accountnumber, int amount) {
		super();
		this.uid = uid;
		this.username = username;
		this.accountnumber = accountnumber;
		this.amount = amount;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", accountnumber=" + accountnumber + ", amount=" + amount
				+ "]";
	}
	
	
	
	
	

}
