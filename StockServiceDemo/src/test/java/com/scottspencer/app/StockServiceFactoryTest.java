package com.scottspencer.app;

import static org.junit.Assert.*;

import org.junit.Test;

public class StockServiceFactoryTest {

	@Test
	public void stockServiceFactoryTest() {
		
		StockQuote compareQuote = new StockQuote(780.08, "TSLA");
		BasicStockService test = (BasicStockService) StockServiceFactory.getStockService();
		assertTrue(compareQuote.getValue() == test.);
	}

}


//StockServiceFactory.getStockService()