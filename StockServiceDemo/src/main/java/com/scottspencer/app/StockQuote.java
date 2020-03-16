package com.scottspencer.app;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.http.annotation.Immutable;

@Immutable public class StockQuote {
	
	private double value;
	private String tickerSymbol;
	private Calendar date;
	
	public StockQuote() {
		//this.value = 780.08;
		//this.tickerSymbol = "TSLA";
	}
	
	public StockQuote(double p_value, String p_tickerSymbol) {
		this.value = p_value;
		this.tickerSymbol = p_tickerSymbol;
	}
		
	public StockQuote(StockQuote p_stockQuote) {
		this.value = p_stockQuote.value;
		this.tickerSymbol = p_stockQuote.tickerSymbol;
	}
	
	public StockQuote(double p_value, String p_tickerSymbol, Calendar p_date) {
		this.value = p_value;
		this.tickerSymbol = p_tickerSymbol;
		this.date = p_date;
	}

	public String getTickerSymbol() { return tickerSymbol; }
	
	public double getValue() { return this.value; }

	public Calendar getDate() {
		return date;
	}

	public void setDate(GregorianCalendar date) {
		this.date = date;
	}
	

}
