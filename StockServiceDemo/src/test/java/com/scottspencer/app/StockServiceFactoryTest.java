package com.scottspencer.app;

import static org.junit.Assert.*;

import org.junit.Test;

public class StockServiceFactoryTest {

	@Test
	public void stockServiceFactoryTest() {
		double val = 780.08;
		StockQuote compareQuote = new StockQuote(780.08, "TSLA");
		
		assertEquals(StockServiceFactory.getStockService().getQuote("TSLA").getValue(), compareQuote.getValue());
	}

}


//StockServiceFactory.getStockService()