package com.mycompany.app;

import static org.junit.Assert.*;

import org.junit.Test;

public class FuelCostTest {

	@Test
	public void testCount() {
		FuelCost Feb = new FuelCost(4);
		assertTrue(Feb.calculateCost() == 138.84);
	}
	
	public void testGallonCost() {
		FuelCost Mar = new FuelCost(2.99, 5);
		assertTrue(Mar.calculateCost() == 194.35);
	}
	
	public void testGallons() {
		FuelCost Apr = new FuelCost(3.05, 5, 11.0);
		assertTrue(Apr.calculateCost() == 167.75);
	}
}
