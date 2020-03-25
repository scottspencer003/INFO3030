package com.scottspencer.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Models a table the combines person with their hobbies.
 */
@Entity
@Table(name = "UserStocks", catalog = "stocks")
public class PersonQuote {

	private int id;
    private Person person;
    private StockQuote quote;

    /**
     * Create a PersonHobby that needs to be initialized
     */
    public PersonQuote() {
        // this empty constructor is required by hibernate framework

    }

    /**
     * Create a valid PersonHobby
     *
     * @param person the person to assign the hobby to
     * @param hobby  the hobby to associate the person with
     */
    public PersonQuote(Person person, StockQuote quote) {
        setQuote(quote);
        setPerson(person);
    }

    /**
     * Primary Key - Unique ID for a particular row in the person_hobby table.
     *
     * @return an integer value
     */
    @Id
    @Column(name = "ID", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    /**
     * Set the unique ID for a particular row in the person_hobby table.
     * This method should not be called by client code. The value is managed in internally.
     *
     * @param id a unique value.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return get the Person associated with this hobby
     */
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "ID", nullable = false)
    public Person getPerson() {
        return person;
    }

    /**
     * Specify the Person associated with the hobby.
     *
     * @param person a person instance
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     *
     * @return get the Hobby  associated with this Person
     */
    @ManyToOne
    @JoinColumn(name = "quotes_id", referencedColumnName = "ID", nullable = false)
    public StockQuote getQuote() {
        return quote;
    }

    /**
     * Specify the Hobby associated with the Person.
     *
     * @param hobby a person instance
     */
    public void setQuote(StockQuote quote) {
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
