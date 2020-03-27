package com.scottspencer.app;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

import com.scottspencer.service.StockServiceException;

public class StockServiceDemoTest {

	@Test
	public void testMainNegative() throws StockServiceException {
		StockServiceDemo.main(null);
	}

}
