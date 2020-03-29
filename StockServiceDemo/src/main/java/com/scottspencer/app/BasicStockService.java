package com.scottspencer.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.http.annotation.Immutable;

import com.scottspencer.model.Person;
import com.scottspencer.model.StockQuoteDao;
import com.scottspencer.service.StockServiceException;



@Immutable public class BasicStockService implements StockService {
	
	public BasicStockService() {
	}
	
	
	public StockQuoteDao getQuote(String tickerSymbol) {
		return new StockQuoteDao(780.08, "TSLA");
	}


	public List<StockQuoteDao> getQuote(@NotNull String symbol, Calendar from, Calendar until, IntervalEnum interval) {

		return createObjects();
	}
	
	private List<StockQuoteDao> createObjects() {
		List<StockQuoteDao> stockHistory = new ArrayList<>();
		
		int year = 2020;
		int month = 0;
		int day = 1;
		double price = 780.08;
		
		for(int i = 0; i < 12; i++) {
			Calendar date = new GregorianCalendar(year, month, day);
			stockHistory.add(new StockQuoteDao(price, "TSLA", date));
			month++;
			price = price + 50/1 + 2;
			
		}
		return stockHistory;
	}

	@Override
	public void addOrUpdatePerson(Person person) throws StockServiceException {
		
	}

	@Override
	public void addQuoteToPerson(StockQuoteDao quote, Person person) {
		
	}

}


