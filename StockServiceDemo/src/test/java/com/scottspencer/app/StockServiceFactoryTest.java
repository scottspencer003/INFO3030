package com.scottspencer.app;

import static org.junit.Assert.*;

import org.junit.Test;

public class StockServiceFactoryTest {

	@Test
	public void stockServiceFactoryTest() {
		StockQuote compareQuote = new StockQuote();
		assertTrue(compareQuote.getValue() == 780.08);
	}

}


//StockServiceFactory.getStockService()