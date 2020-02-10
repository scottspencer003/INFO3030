package com.scottspencer.app;

public class BasicStockService implements StockService {
	
	public BasicStockService() {
	}
	
	public StockQuote getQuote(String tickerSymbol) {
		return new StockQuote();
	}

}
