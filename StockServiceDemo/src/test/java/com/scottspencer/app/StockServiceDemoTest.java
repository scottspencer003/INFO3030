package com.scottspencer.app;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

public class StockServiceDemoTest {

	@Test
	public void testMainNegative() throws ParseException {
		StockServiceDemo.main(null);
	}

}
