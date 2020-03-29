package com.scottspencer.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}StockQuote"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	    "StockQuoteDao"

})
@XmlRootElement(name = "Stocks")
public class Stocks implements XMLDomainObject {

	@XmlElement(required = true)
	private List<StockQuoteDao> StockQuoteDao;
	
	public List<StockQuoteDao> getStock() {
        if (StockQuoteDao == null) {
        	StockQuoteDao = new ArrayList<StockQuoteDao>();
        }
        return this.StockQuoteDao;
	}
	
//	@XmlElement(required = true)
//	protected StockQuoteDao StockQuote;
//	
//	public StockQuoteDao getStockQuote() {	
//		return StockQuote;
//	}
//	
//	public void setStockQuote(StockQuoteDao value) {
//		this.StockQuote = value;
//	}

//	@XmlElement(required = true)
//	private ArrayList<StockQuoteDao> StockQuote;
//	
//	public ArrayList<StockQuoteDao> getStockQuote() {
//		return StockQuote;
//	}
	
	
	
    @Override
    public String toString() {
        return "Stocks{" +
                "StockQuote= " + StockQuoteDao +
                '}';
    }
}
