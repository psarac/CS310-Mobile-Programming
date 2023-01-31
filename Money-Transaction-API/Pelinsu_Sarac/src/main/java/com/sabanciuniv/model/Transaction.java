package com.sabanciuniv.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Transaction {
	
	@Id private String id;
	@DBRef private Account from;
	@DBRef private Account to;
	private LocalDateTime createDate;
	private double amount;
	
	public Transaction() {
		createDate = LocalDateTime.now();
	}

	public Transaction(String id, Account from, Account to, double amount) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.createDate = LocalDateTime.now();
		this.amount = amount;
	}

	public Transaction(Account from, Account to, double amount) {
		super();
		this.from = from;
		this.to = to;
		this.amount = amount;
		this.createDate = LocalDateTime.now();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Account getFrom() {
		return from;
	}

	public void setFrom(Account from) {
		this.from = from;
	}

	public Account getTo() {
		return to;
	}

	public void setTo(Account to) {
		this.to = to;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
