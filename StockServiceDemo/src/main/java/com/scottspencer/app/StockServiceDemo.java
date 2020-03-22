package com.scottspencer.app;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Iterator;

public class StockServiceDemo 
{
    public static void main( String[] args ) throws ParseException, StockServiceException {
    	String stockSymbol;
    	String dateStr; 
    	String dateEnd;
    	String inter;
    	IntervalEnum interval = IntervalEnum.HOUR;
    	
    	StockService testStock = StockServiceFactory.getStockService();
    	
    	Calendar startDate = new GregorianCalendar();
    	Calendar endDate = new GregorianCalendar();
    	DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    	
    	try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Please enter the stock you would like to check");
			stockSymbol = sc.nextLine(); 
			System.out.println("Please enter the start date. Use format mm/dd/yyyy");
			dateStr = sc.nextLine(); 
			System.out.println("Please enter the end date. Use format mm/dd/yyyy");
			dateEnd = sc.nextLine(); 
			System.out.println("Please enter the interval. Available options are minute, hour, day, month");
			inter = sc.nextLine();
		} 
    	if(inter == "minute") {
    		interval = IntervalEnum.MINUTE;
    	} else if(inter == "hour") {
    		interval = IntervalEnum.HOUR;
    	} else if(inter == "day") {
    		interval = IntervalEnum.DAY;
    	} else if(inter == "month") {
    		interval = IntervalEnum.MONTH;
    	}
    	
//    	try {
//			System.out.println("Current price: " + testStock.getQuote(stockSymbol).getValue());
//		} catch (StockServiceException e) {
//			e.printStackTrace();
//		}
    	
    	
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
    	    	Iterator<StockQuote> stockIterator = testStock.getQuote(stockSymbol, startDate, endDate, interval).iterator();
    	    	while(stockIterator.hasNext()) {
    	    		StockQuote currentVal = stockIterator.next();
    	    		
    	    		Calendar calendar = currentVal.getDate();
    	    		
    	    		int month = calendar.get(Calendar.MONTH) + 1;
    	    		int year = calendar.get(Calendar.YEAR);
    	    		int day = calendar.get(Calendar.DAY_OF_MONTH);
    	    		double value = currentVal.getValue();    	
    	    		
    	    		System.out.println("Date: " + month + "/" + day + "/" + year);
    	    		System.out.println("Stock value: " + value + "\n");

    	    	}
    	    	
    		} else {
    			System.out.println("The current value of " + stockSymbol + " is: ");
    			try {
					System.out.println(testStock.getQuote(stockSymbol).getValue());
    				
				} catch (StockServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	} else {
    		System.out.println("A stock symbol must be provided");
    	}
    	
    }
}


