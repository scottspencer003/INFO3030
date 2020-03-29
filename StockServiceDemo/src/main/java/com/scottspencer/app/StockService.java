package com.scottspencer.app;

import java.util.Calendar;
import java.util.List;

import org.apache.http.annotation.Immutable;

import com.scottspencer.model.Person;
import com.scottspencer.model.StockQuoteDao;
import com.scottspencer.service.StockServiceException;

@Immutable public interface StockService {
	
	StockQuoteDao getQuote(String symbol) throws StockServiceException;
	
	List<StockQuoteDao> getQuote(String symbol, Calendar from, Calendar until, IntervalEnum interval) throws StockServiceException;
	
	void addOrUpdatePerson(Person person) throws StockServiceException;

	void addQuoteToPerson(StockQuoteDao quote, Person person) throws StockServiceException;

	List<StockQuoteDao> getPersonStocks(Person person);

	List<Person> getPerson() throws StockServiceException;
	
	public Person getSpecificPerson(String firstName, String lastName) throws StockServiceException;

		
}
