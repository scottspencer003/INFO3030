package com.scottspencer.app;

public class StockServiceFactory {
	

	
	static StockService getStockService() {
		return new DatabaseStockService();

	}
	
}
