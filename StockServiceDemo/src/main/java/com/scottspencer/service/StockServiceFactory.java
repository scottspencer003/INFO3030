package com.scottspencer.service;

import com.scottspencer.app.DatabaseStockService;
import com.scottspencer.app.StockService;

public class StockServiceFactory {
	

	
	public static StockService getStockService() {
		return new DatabaseStockService();

	}
	
}
