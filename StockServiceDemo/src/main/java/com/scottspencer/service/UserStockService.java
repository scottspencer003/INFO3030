package com.scottspencer.service;

import java.util.List;

import org.apache.http.annotation.Immutable;

import com.scottspencer.model.Person;

@Immutable public interface UserStockService {
	
	void addOrUpdatePerson(Person person) throws StockServiceException;
	
	List<Person> getPerson() throws StockServiceException;
	

}
