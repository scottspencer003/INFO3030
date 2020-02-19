package com.scottspencer.app;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class StockServiceDemo 
{
    public static void main( String[] args ) throws ParseException {
    	String stockSymbol = "TSLA";
    	String dateStr = "04/05/2019"; 
    	String dateEnd = "05/05/2019";
    	
    	GregorianCalendar startDate = new GregorianCalendar();
    	GregorianCalendar endDate = new GregorianCalendar();
    	DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    	
    	try {
    	Date sDate = formatter.parse(dateStr);
    	startDate.setTime(sDate);
    	
    	Date eDate = formatter.parse(dateEnd);
    	endDate.setTime(eDate);
    	} 
    	catch (ParseException e) {
    		System.out.println("Invalid date format");
    	}
    	
    	
    	
    
    	
//    	for(int i = 0; i < 12; i++) {
//    	testStock = StockServiceFactory.getStockService().getQuote(stockSymbol, startDate, endDate).get(i);
//        System.out.println(testStock.getQuote("TSLA"));
//    	}
        
//
//    	List<StockService> stocks = StockServiceFactory.getStockService();
//    	
//    	for(StockService stock : stocks) {
//    		double value = stocks.getQuote("TSLA").getValue();
//            System.out.println(value);
//        }
    	
    	
    }
}
