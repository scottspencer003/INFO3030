package com.scottspencer.app;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import com.scottspencer.model.Person;
import com.scottspencer.model.PersonQuote;
import com.scottspencer.model.StockQuoteDao;
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
    @SuppressWarnings("unchecked")
    public StockQuoteDao getQuote(String symbol) throws StockServiceException {
    	
        Session session = DatabaseUtils.getSessionFactory().openSession();
        List<StockQuoteDao> stockQuotes = new ArrayList<>();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(StockQuoteDao.class);
            
            //Return stocks based on criteria somehow?
            criteria.add(Restrictions.eq("symbol", symbol));

            stockQuotes = criteria.list(); 
           
        } catch (HibernateException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();  // close transaction
            }
            throw new StockServiceException("Could not get stock data. " + e.getMessage(), e);
        } finally {
            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            }
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
    @SuppressWarnings("unchecked")
	@Override
    public List<StockQuoteDao> getQuote(String symbol, Calendar from, Calendar until, IntervalEnum interval) throws StockServiceException {

        Session session = DatabaseUtils.getSessionFactory().openSession();
        List <StockQuoteDao> stockQuotes = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(StockQuoteDao.class);
            criteria.add(Restrictions.eq("symbol", symbol)).add(Restrictions.between("time", from, until));
            stockQuotes = criteria.list();
            
            //Calendar checkCal = Calendar.getInstance();
            
//            while(resultSet.iterator().hasNext()) {
//              
//            String symbolValue = resultSet.iterator().next().getTickerSymbol();
//            Calendar time = resultSet.iterator().next().getDate();
//            Calendar calendar = Calendar.getInstance();
//            checkCal.setTime(time); 
//            double price = resultSet.iterator().next().getValue();
//
//         switch(interval) {
//          case MINUTE:
//          	if(calendar.after(checkCal)) 
//              	stockQuotes.add(new StockQuoteDao(price, symbolValue, calendar));
//              	checkCal.setTime(time); 
//              	checkCal.add(checkCal.MINUTE, 1);
//          	break;
//          	
//          case HOUR:	
//          	if(calendar.after(checkCal)) 
//              	stockQuotes.add(new StockQuoteDao(price, symbolValue, calendar));
//              	checkCal.setTime(time);
//              	checkCal.add(checkCal.HOUR, 1);
//          	break;
//          	
//          case DAY:  
//          	if(calendar.after(checkCal)) 
//              	stockQuotes.add(new StockQuoteDao(price, symbolValue, calendar));
//              	checkCal.setTime(time);
//              	checkCal.add(checkCal.DAY_OF_YEAR, 1);
//          	break;
//       
//          case MONTH:
//          	if(calendar.after(checkCal)) 
//              	stockQuotes.add(new StockQuoteDao(price, symbolValue, calendar));
//              	checkCal.setTime(time);
//              	checkCal.add(checkCal.MONTH, 1);
//          	break;
//          	
//          }
//        }


        } catch (HibernateException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();  // close transaction
            }
            throw new StockServiceException("Could not get historical stock data. " + e.getMessage(), e);
        } finally {
            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            }
        }

        return stockQuotes;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Person> getPerson() throws StockServiceException{
        Session session = DatabaseUtils.getSessionFactory().openSession();
        List<Person> returnValue = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Person.class);

            /**
             * NOTE criteria.list(); generates unchecked warning so SuppressWarnings
             * is used - HOWEVER, this about the only @SuppressWarnings I think it is OK
             * to suppress them - in almost all other cases they should be fixed not suppressed
             */
            returnValue = criteria.list();

        } catch (HibernateException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();  // close transaction
            }
            throw new StockServiceException("Could not get Person data. " + e.getMessage(), e);
        } finally {
            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            }
        }

        return returnValue;

    }
    
    
    
    @Override
    @SuppressWarnings("unchecked")
    public List<StockQuoteDao> getPersonStocks(Person person) {
        Session session =  DatabaseUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        List<StockQuoteDao> stockQuotes = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(PersonQuote.class);
            criteria.add(Restrictions.eq("person", person));
            /**
             * NOTE criteria.list(); generates unchecked warning so SuppressWarnings
             * is used - HOWEVER, this about the only @SuppressWarnings I think it is OK
             * to suppress them - in almost all other cases they should be fixed not suppressed
             */
            List<PersonQuote> list = criteria.list();
            for (PersonQuote personQuote : list) {
            	stockQuotes.add(personQuote.getQuote());
            }
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
    
    public Person getSpecificPerson(String firstName, String lastName) throws StockServiceException {
    	
        Session session = DatabaseUtils.getSessionFactory().openSession();
        List<Person> person = new ArrayList<>();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Person.class);
            
            //Return stocks based on criteria somehow?
            criteria.add(Restrictions.eq("firstName", firstName)).add(Restrictions.eq("lastName", lastName));

            person = criteria.list(); 
            
        } catch (HibernateException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();  // close transaction
            }
            throw new StockServiceException("Could not get stock data. " + e.getMessage(), e);
        } finally {
            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            }
        }
        
        return person.get(0);
    }
    
    @Override
    public void addQuoteToPerson(StockQuoteDao quote, Person person) {
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
