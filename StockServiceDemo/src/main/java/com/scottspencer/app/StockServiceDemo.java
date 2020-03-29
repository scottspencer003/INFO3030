package com.scottspencer.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringReader;
import java.util.Collection;

import com.scottspencer.model.Person;
import com.scottspencer.model.PersonQuote;
import com.scottspencer.model.StockQuoteDao;
import com.scottspencer.model.Stocks;
import com.scottspencer.service.StockServiceException;
import com.scottspencer.service.StockServiceFactory;
import com.scottspencer.service.UserStockService;

import java.util.Iterator;

public class StockServiceDemo 
{
    public static void main( String[] args ) throws ParseException, StockServiceException, FileNotFoundException, JAXBException {
    	
//    	Scanner sc = new Scanner(System.in);
//    	int choice;	
//
//    		System.out.println("Please select one of the following options by typing the number and pressing enter.");
//    		System.out.println("Option 1: Check current stock price.");
//    		System.out.println("Option 2: Return stock prices for a range of dates.");
//    		System.out.println("Option 3: Update client stocks.");
//    		System.out.println("Option 4: Upload stock data from file.");
//    	    while (!sc.hasNextInt()) {
//    	        System.out.println("Invalid choice. Please enter a number 1-4 that corresponds to your choice and press enter.");
//    	        sc.next();
//    	    } 
//    	    choice = sc.nextInt();
//    	    if(choice < 1 || choice > 4) {
//    	    	System.out.println("Invalid choice. Please enter a number 1-4 that corresponds to your choice and press enter.");
//    	        sc.next();
//    	        choice = sc.nextInt();
//    	    } else if(choice == 1) {
//    	    	System.out.println("Add option 1");
//    	    } else if(choice == 2) {
//    	    	StockHistory();
//    	    } else if(choice == 3) {
//    	    	System.out.println("Add option 2");
//    	    } else if(choice == 4) {
//    	    	loadXMLData();
//    	    }
    
    		
//    	StockHistory();
    	
//    	String stockSymbol = "GOOG";
//    	StockService testStock = StockServiceFactory.getStockService();
//    	System.out.println(testStock.getQuote(stockSymbol).getTickerSymbol());
//    	System.out.println(testStock.getQuote(stockSymbol).getValue());
//    	
//    	loadXMLData();
    	
    	String stockSymbol = "GOOG";
    	String firstName = "mason";
    	String lastName = "spencer";
    	Person person = new Person();
    	StockQuoteDao quote = new StockQuoteDao();

    	
    	StockService testStock = StockServiceFactory.getStockService();
    	person = testStock.getSpecificPerson(firstName, lastName);
//    	quote = testStock.getQuote(stockSymbol);
//
//		testStock.addQuoteToPerson(quote, person);
	
    	Iterator<StockQuoteDao> stockIterator = testStock.getPersonStocks(person).iterator();
    	
    	while(stockIterator.hasNext()) {
    		
    		String sym = stockIterator.next().getTickerSymbol();
    		System.out.println("Stock symbol: " + sym + "\n");
    	}

		
    }
    
    
    //Get stock history 
    public static void StockHistory() throws StockServiceException {
    	
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
    	    	Iterator<StockQuoteDao> stockIterator = testStock.getQuote(stockSymbol, startDate, endDate, interval).iterator();
    	    	while(stockIterator.hasNext()) {
    	    		StockQuoteDao currentVal = stockIterator.next();
    	    		
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
					e.printStackTrace();
				}
    		}
    	} else {
    		System.out.println("A stock symbol must be provided");
    	}
    	
    }//close stock history method
    
    public static void loadXMLData() throws JAXBException, FileNotFoundException {
    	
    	//Read the xml file
    	InputStream inStream = new FileInputStream("src/main/resources/xml/stock_info.xml");
    	JAXBContext jaxbContext = JAXBContext.newInstance(Stocks.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Stocks quote = (Stocks) unmarshaller.unmarshal(inStream);
    	
        System.out.println(quote.toString());
        
        // here is how to go from Java to XML
        JAXBContext context = JAXBContext.newInstance(Stocks.class);
        Marshaller marshaller = context.createMarshaller();
        //for pretty-print XML in JAXB
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(quote, System.out);
    }
    

    
}




