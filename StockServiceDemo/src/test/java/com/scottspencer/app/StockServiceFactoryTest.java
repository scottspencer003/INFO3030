package com.scottspencer.app;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.scottspencer.service.StockServiceException;
import com.scottspencer.service.StockServiceFactory;

public class StockServiceFactoryTest {


	@Test
	public void stockServiceFactoryTestSym() {
		StockService testStock = StockServiceFactory.getStockService();
		try {
			assertEquals(testStock.getQuote("GOOG").getTickerSymbol(), "GOOG");
		} catch (StockServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void stockServiceFactoryTestVal() {
		StockService testStock = StockServiceFactory.getStockService();
		try {
			assertTrue(testStock.getQuote("GOOG").getValue() == 85.0);
		} catch (StockServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void InstanceOfTest () {
		StockService testStock = StockServiceFactory.getStockService();
		assertTrue(testStock instanceof BasicStockService);
	}

}

