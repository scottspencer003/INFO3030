package com.scottspencer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Models a table the combines person with their stocks.
 */
@Entity
@Table(name = "UserStocks", catalog = "stocks")
public class PersonQuote {

	private int id;
    private Person person;
    private StockQuoteDao quote;


    public PersonQuote() {
        // this empty constructor is required by hibernate framework

    }


    public PersonQuote(Person person, StockQuoteDao quote) {
        setQuote(quote);
        setPerson(person);
    }


    @Id
    @Column(name = "ID", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "ID", nullable = false)
    public Person getPerson() {
        return person;
    }


    public void setPerson(Person person) {
        this.person = person;
    }


    @ManyToOne
    @JoinColumn(name = "quotes_id", referencedColumnName = "ID", nullable = false)
    public StockQuoteDao getQuote() {
        return quote;
    }


    public void setQuote(StockQuoteDao quote) {
        this.quote = quote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonQuote that = (PersonQuote) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + person.hashCode();
        result = 31 * result + quote.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PersonQuote{" +
                "id=" + id +
                ", person=" + person +
                ", hobby=" + quote +
                '}';
    }
}
