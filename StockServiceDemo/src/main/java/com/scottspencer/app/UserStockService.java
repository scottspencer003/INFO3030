package com.scottspencer.app;

import java.util.List;

import org.apache.http.annotation.Immutable;

@Immutable public interface UserStockService {
	
	void addOrUpdatePerson(Person person) throws StockServiceException;
	
	List<Person> getPerson() throws StockServiceException;
	

}
