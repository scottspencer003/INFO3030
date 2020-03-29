package com.scottspencer.service;

import java.util.List;

import org.apache.http.annotation.Immutable;

import com.scottspencer.app.StockService;
import com.scottspencer.model.Person;
import com.scottspencer.model.StockQuoteDao;

@Immutable public interface UserStockService {
	
	void addOrUpdatePerson(Person person) throws StockServiceException;
	
	List<Person> getPerson() throws StockServiceException;
	
	public void addQuoteToPerson(StockService testStock, Person person);
	
	public List<StockQuoteDao> getPersonStocks(Person person);

	void addQuoteToPerson(StockQuoteDao quote, Person person) throws StockServiceException;

	public Person getSpecificPerson(String firstName, String lastName) throws StockServiceException;
	

}
