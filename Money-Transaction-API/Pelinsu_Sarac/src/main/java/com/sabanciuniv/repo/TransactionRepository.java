package com.sabanciuniv.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sabanciuniv.model.Account;
import com.sabanciuniv.model.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String>{
	
	public List<Transaction> findByTo(Account acc);
	public List<Transaction> findByFrom(Account acc);

}
