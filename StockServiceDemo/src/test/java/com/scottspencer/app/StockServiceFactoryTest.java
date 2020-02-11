package com.scottspencer.app;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StockServiceFactoryTest {


	@Test
	public void stockServiceFactoryTestSym() {
		StockService testStock = StockServiceFactory.getStockService();
		assertEquals(testStock.getQuote("TSLA").getTickerSymbol(), "TSLA");
		
	}
	
	@Test
	public void stockServiceFactoryTestVal() {
		StockService testStock = StockServiceFactory.getStockService();
		assertTrue(testStock.getQuote("TSLA").getValue() == 780.08);
		
	}
	
	@Test
	public void InstanceOfTest () {
		StockService testStock = StockServiceFactory.getStockService();
		BasicStockService comparisonStock = new BasicStockService();
		assertEquals(comparisonStock, testStock);
	}

}


//StockServiceFactory.getStockService()