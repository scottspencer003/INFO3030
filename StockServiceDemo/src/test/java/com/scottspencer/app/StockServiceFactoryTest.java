package com.scottspencer.app;

import static org.junit.Assert.*;

import org.junit.Test;


import com.scottspencer.service.StockServiceException;
import com.scottspencer.service.StockServiceFactory;

public class StockServiceFactoryTest {


    @Test
    public void testFactory() {
    	StockService testStock = StockServiceFactory.getStockService();
        assertNotNull("Make sure factory works", testStock);
    }

}

