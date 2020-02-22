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
    	//String dateStr = ""; 
    	String dateEnd = "05/05/2019";
    	
    	StockService testStock = StockServiceFactory.getStockService();
    	
    	Calendar startDate = new GregorianCalendar();
    	Calendar endDate = new GregorianCalendar();
    	DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    	
    	if(!(stockSymbol == "" || stockSymbol == null)) {
    		if(!((dateStr == "" || dateStr == null ) || (dateEnd == "" || dateEnd == null ))) {
    	    	try {
    	        	Date sDate = formatter.parse(dateStr);
    	        	startDate.setTime(sDate);
    	        	
    	        	Date eDate = formatter.parse(dateEnd);
    	        	endDate.setTime(eDate);
    	        	} 
    	        	catch (ParseException e) {
    	        		System.out.println("Invalid date format");
    	        	}
    	    	
    	    	System.out.println("Stock history for " + stockSymbol + ":\n");
    	    	for(int i = 0; i < 10; i++) {
    	    		int month = testStock.getQuote(stockSymbol, startDate, endDate).get(i).getDate().get(Calendar.MONTH) + 1;
    	    		int day = testStock.getQuote(stockSymbol, startDate, endDate).get(i).getDate().get(Calendar.DAY_OF_MONTH);
    	    		int year = testStock.getQuote(stockSymbol, startDate, endDate).get(i).getDate().get(Calendar.YEAR);
    	    		double value = testStock.getQuote(stockSymbol, startDate, endDate).get(i).getValue();
    	    		
    	    		System.out.println("Date: " + month + "/" + day + "/" + year);
    	    		System.out.println("Stock value: " + value + "\n");
    	    		//System.out.println(testStock.getQuote(stockSymbol, startDate, endDate).get(i).getDate().get(Calendar.MONTH));
    	    	}
    		} else {
    			System.out.println("The current value of " + stockSymbol + " is: ");
    			System.out.println(testStock.getQuote(stockSymbol).getValue());
    		}
    	} else {
    		System.out.println("A stock symbol must be provided");
    	}
    	
    }
}


//try {
//Date sDate = formatter.parse(dateStr);
//startDate.setTime(sDate);
//
//Date eDate = formatter.parse(dateEnd);
//endDate.setTime(eDate);
//} 
//catch (ParseException e) {
//	System.out.println("Invalid date format");
//}

//long days = (endDate.getTime() - startDate.getTime())/86400000;
//long days = ((endDate.getTimeInMillis() - startDate.getTimeInMillis())/86400000);
//System.out.println(days);


//for(int i = 0; i < 10; i++) {
//	System.out.println(testStock.getQuote(stockSymbol, startDate, endDate).get(i).getValue());
//	//System.out.println(testStock.getQuote(stockSymbol, startDate, endDate).get(i).getDate().get(Calendar.MONTH));
//
//}

