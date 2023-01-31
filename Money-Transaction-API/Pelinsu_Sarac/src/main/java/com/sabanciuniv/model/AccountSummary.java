package com.sabanciuniv.model;

import java.time.LocalDateTime;
import java.util.List;

public class AccountSummary {
	
	private String id;
	private String owner;
	private LocalDateTime createDate;
	private List<Transaction> transactionsOut;
	private List<Transaction> transactionsIn;
	private double balance;
	
	public AccountSummary() {
		// TODO Auto-generated constructor stub
	}

	public AccountSummary(String id, String owner, LocalDateTime createDate, List<Transaction> transactionsOut,
			List<Transaction> transactionsIn) {
		super();
		this.id = id;
		this.owner = owner;
		this.createDate = createDate;
		this.transactionsOut = transactionsOut;
		this.transactionsIn = transactionsIn;
		this.balance = 0;
		for (Transaction tr: transactionsIn) {
			balance += tr.getAmount();
		}
		for (Transaction tr: transactionsOut) {
			balance -= tr.getAmount();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public List<Transaction> getTransactionsOut() {
		return transactionsOut;
	}

	public void setTransactionsOut(List<Transaction> transactionsOut) {
		this.transactionsOut = transactionsOut;
	}

	public List<Transaction> getTransactionsIn() {
		return transactionsIn;
	}

	public void setTransactionsIn(List<Transaction> transactionsIn) {
		this.transactionsIn = transactionsIn;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
	

}
