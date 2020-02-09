package com.scottspencer.app;

import static org.junit.Assert.*;

import org.junit.Test;

public class StockServiceFactoryTest {

	@Test
	public void stockServiceFactoryTest() {
		StockQuote testQuote = new StockQuote(780.08, "TSLA");
		assertEquals(StockServiceFactory.getStockService(), testQuote);
	}

}
