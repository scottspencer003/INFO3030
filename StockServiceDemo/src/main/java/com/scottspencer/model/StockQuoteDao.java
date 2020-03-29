package com.scottspencer.model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.http.annotation.Immutable;

/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="symbol" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="price" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="time" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
   "symbol",
   "price",
   "time"
})

@Entity
@Table(name="quotes")

@XmlRootElement(name = "StockQuoteDao")
@Immutable public class StockQuoteDao implements XMLDomainObject {
	
	@XmlTransient
	@Id
	@Column(name = "id")
	private int id;
	
	@Basic
	@Column(name = "price")
//	@XmlAttribute(name = "price")
	
	private double price;
	
	@Basic
    @Column(name = "symbol")
//	@XmlAttribute(name = "symbol")
	
	private String symbol;
	
    @Basic
    @Column(name = "time")
//    @XmlAttribute(name = "time")
	private Calendar time;
	
	public StockQuoteDao() {

	}
	
	public StockQuoteDao(double p_value, String p_tickerSymbol) {
		this.price = p_value;
		this.symbol = p_tickerSymbol;
	}
		
	public StockQuoteDao(StockQuoteDao p_stockQuote) {
		this.price = p_stockQuote.price;
		this.symbol = p_stockQuote.symbol;
	}
	
	public StockQuoteDao(double p_value, String p_tickerSymbol, Calendar p_date) {
		this.price = p_value;
		this.symbol = p_tickerSymbol;
		this.time = p_date;
	}

	@XmlAttribute(name = "symbol")
	public String getTickerSymbol() { return symbol; }
	

    public int getId() {
        return id;
    }
	
    @XmlAttribute(name = "price")
	public double getValue() { return this.price; }
    
    @XmlAttribute(name = "time")
	public Calendar getDate() {
		return time;
	}

	
	public void setDate(GregorianCalendar date) {
		this.time = date;
	}
	

	
	
	@Override
    public String toString() {
        return "stock{" +
                "symbol='" + symbol + '\'' +
                ", price='" + price + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
	

}
