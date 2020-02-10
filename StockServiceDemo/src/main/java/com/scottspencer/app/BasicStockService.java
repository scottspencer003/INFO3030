package com.scottspencer.app;

public class BasicStockService implements StockService {
	
	public BasicStockService() {
		new StockQuote(780.08, "TSLA");
	}
	
	public StockQuote getQuote(String tickerSymbol) {
		return new StockQuote();
	}

}
