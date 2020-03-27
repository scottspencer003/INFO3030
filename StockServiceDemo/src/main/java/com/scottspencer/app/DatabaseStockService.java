package com.scottspencer.app;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.scottspencer.model.Person;
import com.scottspencer.model.PersonQuote;
import com.scottspencer.model.StockQuote;
import com.scottspencer.service.StockServiceException;
import com.scottspencer.util.DatabaseConnectionException;
import com.scottspencer.util.DatabaseUtils;

enum IntervalEnum {
	MINUTE, HOUR, DAY, MONTH;
}

/**
 * An implementation of the StockService interface that gets
 * stock data from a database.
 */
public class DatabaseStockService implements StockService {

    /**
     * Return the current price for a share of stock  for the given symbol
     *
     * @param symbol the stock symbol of the company you want a quote for.
     *               e.g. APPL for APPLE
     * @return a  <CODE>BigDecimal</CODE> instance
     * @throws StockServiceException if using the service generates an exception.
     *                               If this happens, trying the service may work, depending on the actual cause of the
     *                               error.
     */
    @Override
    public StockQuote getQuote(String symbol) throws StockServiceException {
        // todo - this is a pretty lame implementation why?
        List<StockQuote> stockQuotes = new ArrayList<>();
        try {
            Connection connection = DatabaseUtils.getConnection();
            Statement statement = connection.createStatement();
            String queryString = "select * from quotes where symbol = '" + symbol + "'";

            ResultSet resultSet = statement.executeQuery(queryString);
            stockQuotes = new ArrayList<>(resultSet.getFetchSize());
            while(resultSet.next()) {
                String symbolValue = resultSet.getString("symbol");
                Date time = resultSet.getDate("time");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(time);
                double price = resultSet.getDouble("price");
                stockQuotes.add(new StockQuote(price, symbolValue, calendar));
            }

        } catch (DatabaseConnectionException | SQLException exception) {
            throw new StockServiceException(exception.getMessage(), exception);
        }
        if (stockQuotes.isEmpty()) {
            throw new StockServiceException("There is no stock data for:" + symbol);
        }
        return stockQuotes.get(0);
    }

    /**
     * Get a historical list of stock quotes for the provide symbol
     *
     * @param symbol the stock symbol to search for
     * @param start   the date of the first stock quote
     * @param end  the date of the last stock quote
     * @param interval cadence
     * @return a list of StockQuote instances
     * @throws StockServiceException 
     */
    @Override
    public List<StockQuote> getQuote(String symbol, Calendar from, Calendar until, IntervalEnum interval) throws StockServiceException {
    	List <StockQuote> stockQuotes = new ArrayList<>();
    	try {
    		Connection connection = DatabaseUtils.getConnection();
            Statement statement = connection.createStatement();
            Date startDate = new Date(from.getTimeInMillis()); 
            Date endDate = new Date(until.getTimeInMillis());
            String queryString = "SELECT * FROM quotes WHERE symbol = '" + symbol + "' AND time BETWEEN CAST('" + startDate + "' AS DATE) AND CAST('" + endDate + "' AS DATE)";

            ResultSet resultSet = statement.executeQuery(queryString);
            stockQuotes = new ArrayList<>(resultSet.getFetchSize());
                       
            Calendar checkCal = Calendar.getInstance();
            
            while(resultSet.next()) {
    
                String symbolValue = resultSet.getString("symbol");
                Date time = resultSet.getDate("time");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(time);
                double price = resultSet.getDouble("price");

                switch(interval) {
                case MINUTE:
                	if(calendar.after(checkCal)) 
	                	stockQuotes.add(new StockQuote(price, symbolValue, calendar));
	                	checkCal.setTime(time);
	                	checkCal.add(checkCal.MINUTE, 1);
                	break;
                	
                case HOUR:	
                	if(calendar.after(checkCal)) 
	                	stockQuotes.add(new StockQuote(price, symbolValue, calendar));
	                	checkCal.setTime(time);
	                	checkCal.add(checkCal.HOUR, 1);
                	break;
                	
                case DAY:  
                	if(calendar.after(checkCal)) 
	                	stockQuotes.add(new StockQuote(price, symbolValue, calendar));
	                	checkCal.setTime(time);
	                	checkCal.add(checkCal.DAY_OF_YEAR, 1);
                	break;
             
                case MONTH:
                	if(calendar.after(checkCal)) 
	                	stockQuotes.add(new StockQuote(price, symbolValue, calendar));
	                	checkCal.setTime(time);
	                	checkCal.add(checkCal.MONTH, 1);
                	break;
                	
                }

                
                
            }
                  
    	} catch (DatabaseConnectionException | SQLException exception) {
            throw new StockServiceException(exception.getMessage(), exception);
        }
        if (stockQuotes.isEmpty()) {
            throw new StockServiceException("There is no stock data for:" + symbol);
        }
    	
    	
        return stockQuotes;
    }


    @Override
    public void addOrUpdatePerson(Person person) {
        Session session = DatabaseUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(person);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();  // close transaction
            }
        } finally {
            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            }
        }
    }
    
    @Override
    public void addQuoteToPerson(StockQuote quote, Person person) {
        Session session =  DatabaseUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            PersonQuote personQuote = new PersonQuote();
            personQuote.setQuote(quote);
            personQuote.setPerson(person);
            session.saveOrUpdate(personQuote);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();  // close transaction
            }
        } finally {
            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            }
        }
    }
}
