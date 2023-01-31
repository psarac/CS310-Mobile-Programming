package com.sabanciuniv.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sabanciuniv.model.Account;
import com.sabanciuniv.model.AccountSummary;
import com.sabanciuniv.model.Message;
import com.sabanciuniv.model.Transaction;
import com.sabanciuniv.model.TransactionPayload;
import com.sabanciuniv.repo.AccountRepository;
import com.sabanciuniv.repo.TransactionRepository;

@RestController
public class MoneyTransactionController {
	
	@Autowired private AccountRepository accountRepository;
	@Autowired private TransactionRepository transactionRepository;
	
	@PostConstruct
	public void init() {
		
		//Test Data
		if(accountRepository.count() == 0 && transactionRepository.count() == 0) {
			
			Account acc1 = new Account("1111", "Jack Johns");
			Account acc2 = new Account("2222", "Henry Williams");
			
			accountRepository.save(acc1);
			accountRepository.save(acc2);
			
			Transaction tr1 = new Transaction(acc1, acc2, 1500.0);
			Transaction tr2 = new Transaction(acc2, acc1, 2500.0);
			
			transactionRepository.save(tr1);
			transactionRepository.save(tr2);
		}
	}
	
	@PostMapping("/account/save")
	public Message<Account> saveAccount(@RequestBody Account acc) {
		if (acc.getId() == null || acc.getOwner() == null) {
			return new Message<Account>("ERROR:missing fields", null);
		}
		else {
			Account accSaved = accountRepository.save(acc);
			return new Message<Account>("SUCCESS", accSaved);
		}
	}
	
	@PostMapping("/transaction/save") 
	public Message<Transaction> saveTransaction(@RequestBody TransactionPayload payload) {
		if (payload.getFromAccountId() == null || payload.getToAccountId() == null || payload.getAmount() == null) {
			return new Message<Transaction>("ERROR:missing fields", null);
		} else {
			try {
				Account from = accountRepository.findById(payload.getFromAccountId()).get();
				Account to = accountRepository.findById(payload.getToAccountId()).get();
			} catch (NoSuchElementException e){
				return new Message<Transaction>("ERROR: account id", null);
			}
			
			Account from = accountRepository.findById(payload.getFromAccountId()).get();
			Account to = accountRepository.findById(payload.getToAccountId()).get();
			Transaction trSaved = transactionRepository.save(new Transaction(from, to, payload.getAmount()));
			return new Message<Transaction>("SUCCESS", trSaved);
			
		}
			
	}
	
	@GetMapping("/account/{accountId}")
	public Message<AccountSummary> accountSummary(@PathVariable String accountId) {
		try {
			Account acc = accountRepository.findById(accountId).get();
		}
		catch (NoSuchElementException e) {
			return new Message<AccountSummary>("ERROR:account doesnt exist!", null);
		}
		
		Account acc = accountRepository.findById(accountId).get();
		List<Transaction> incomingTr = transactionRepository.findByTo(acc);
		List<Transaction> outgoingTr = transactionRepository.findByFrom(acc);
		AccountSummary sum = new AccountSummary(acc.getId(), acc.getOwner(), acc.getCreateDate(), outgoingTr, incomingTr);
		return new Message<AccountSummary>("SUCCESS", sum);
		
	}
	
	@GetMapping("/transaction/to/{accountId}")
	public Message<List<Transaction>> incomingTransactions(@PathVariable String accountId) {
		try {
			Account acc = accountRepository.findById(accountId).get();
		}
		catch (NoSuchElementException e) {
			return new Message<List<Transaction>>("ERROR:account doesn’t exist", null);
		}
		
		Account acc = accountRepository.findById(accountId).get();
		List<Transaction> incomingTr = transactionRepository.findByTo(acc);
		return new Message<List<Transaction>>("SUCCESS", incomingTr);
	}
	
	@GetMapping("/transaction/from/{accountId}")
	public Message<List<Transaction>> outgoingTransactions(@PathVariable String accountId) {
		try {
			Account acc = accountRepository.findById(accountId).get();
		}
		catch (NoSuchElementException e) {
			return new Message<List<Transaction>>("ERROR:account doesn’t exist", null);
		}
		
		Account acc = accountRepository.findById(accountId).get();
		List<Transaction> outgoingTr = transactionRepository.findByFrom(acc);
		return new Message<List<Transaction>>("SUCCESS", outgoingTr);
	}
	
	
		
	
	
	

}
