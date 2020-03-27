package com.scottspencer.app;

import java.util.Calendar;
import java.util.List;

import org.apache.http.annotation.Immutable;

import com.scottspencer.model.Person;
import com.scottspencer.model.StockQuote;
import com.scottspencer.service.StockServiceException;

@Immutable public interface StockService {
	
	StockQuote getQuote(String symbol) throws StockServiceException;
	
	List<StockQuote> getQuote(String symbol, Calendar from, Calendar until, IntervalEnum interval) throws StockServiceException;
	
	void addOrUpdatePerson(Person person) throws StockServiceException;

	void addQuoteToPerson(StockQuote quote, Person person);
		
}
