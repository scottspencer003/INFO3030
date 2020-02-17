package com.scottspencer.app;

import java.util.Calendar;

public interface StockService {
	
	StockQuote getQuote(String symbol);
	
	List<StockQuote> getQuote(String symbol, Calendar from, Calendar until);
		
}
