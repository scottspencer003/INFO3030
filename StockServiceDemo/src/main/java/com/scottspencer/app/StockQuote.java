package com.scottspencer.app;

public class StockQuote {
	
	private double value;
	private String tickerSymbol;
	
	public StockQuote() {}
	
	public StockQuote(double p_value, String p_tickerSymbol) {
		this.value = p_value;
		this.tickerSymbol = p_tickerSymbol;
	}
	
	public StockQuote(StockQuote p_stockQuote) {
		//method to copy the stockquote 
	}
	
	public String getTickerSymbol() { return tickerSymbol; }
	
	public double getValue() { return this.value; }
	

}
