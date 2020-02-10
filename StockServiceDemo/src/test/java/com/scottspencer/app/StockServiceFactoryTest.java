package com.scottspencer.app;

import static org.junit.Assert.*;

import org.junit.Test;

public class StockServiceFactoryTest {

	@Test
	public void stockServiceFactoryTest() {
		double var = 780.08;
		StockQuote compareQuote = new StockQuote();
		assertEquals(compareQuote.getValue(), var);
	}

}


//StockServiceFactory.getStockService()