package com.scottspencer.app;

import java.util.Calendar;
import java.util.List;

import org.apache.http.annotation.Immutable;

@Immutable public interface StockService {
	
	StockQuote getQuote(String symbol) throws StockServiceException;
	
	List<StockQuote> getQuote(String symbol, Calendar from, Calendar until, IntervalEnum interval);

		
}
