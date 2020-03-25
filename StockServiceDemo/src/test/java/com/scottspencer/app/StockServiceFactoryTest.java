package com.scottspencer.app;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StockServiceFactoryTest {


	@Test
	public void stockServiceFactoryTestSym() {
		StockService testStock = StockServiceFactory.getStockService();
		assertEquals(testStock.getQuote("GOOG").getTickerSymbol(), "GOOG");
		
	}
	
	@Test
	public void stockServiceFactoryTestVal() {
		StockService testStock = StockServiceFactory.getStockService();
		assertTrue(testStock.getQuote("GOOG").getValue() == 85.0);
		
	}
	
	@Test
	public void InstanceOfTest () {
		StockService testStock = StockServiceFactory.getStockService();
		assertTrue(testStock instanceof BasicStockService);
	}

}

