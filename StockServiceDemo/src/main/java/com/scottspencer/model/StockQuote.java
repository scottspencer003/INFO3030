package com.scottspencer.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.http.annotation.Immutable;

@Entity
@Table(name="quotes")
@Immutable public class StockQuote {
	
	private int id;
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
	@Basic
    @Column(name = "symbol", nullable = false, insertable = true, updatable = true)
	public String getTickerSymbol() { return tickerSymbol; }
	
    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }
	
    @Basic
    @Column(name = "price", nullable = false, insertable = true, updatable = true, length = 256)
	public double getValue() { return this.value; }
    
    @Basic
    @Column(name = "time", nullable = false, insertable = true, updatable = true, length = 256)
	public Calendar getDate() {
		return date;
	}

	public void setDate(GregorianCalendar date) {
		this.date = date;
	}
	

}
