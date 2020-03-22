package com.scottspencer.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.http.annotation.Immutable;

enum IntervalEnum {
	MINUTE, HOUR, DAY, MONTH;
}

@Immutable public class BasicStockService implements StockService {
	
	public BasicStockService() {
	}
	
	public StockQuote getQuote(String tickerSymbol) {
		return new StockQuote(780.08, "TSLA");
	}


	public List<StockQuote> getQuote(@NotNull String symbol, Calendar from, Calendar until, IntervalEnum interval) {

		return createObjects();
	}
	
	private List<StockQuote> createObjects() {
		List<StockQuote> stockHistory = new ArrayList<>();
		
		int year = 2020;
		int month = 0;
		int day = 1;
		double price = 780.08;
		
		for(int i = 0; i < 12; i++) {
			Calendar date = new GregorianCalendar(year, month, day);
			stockHistory.add(new StockQuote(price, "TSLA", date));
			month++;
			price = price + 50/1 + 2;
			
		}
		return stockHistory;
	}

}


