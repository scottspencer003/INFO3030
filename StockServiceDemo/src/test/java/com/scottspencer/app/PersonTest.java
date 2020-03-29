package com.scottspencer.app;


import org.junit.Test;
import com.scottspencer.model.Person;
import static org.junit.Assert.assertEquals;



public class PersonTest {
	
	public static final String firstName = "Kennedy";
    public  static final String lastName = "Spencer";

	@Test
	public static Person createPerson() {
		Person person = new Person();
		person.setFirstName(firstName);
        person.setLastName(lastName);
        return person;
	}
	
    @Test
    public void testPersonGettersAndSetters() {
        Person person = createPerson();
        int id = 10;
        person.setId(id);
        assertEquals("first name matches", firstName, person.getFirstName());
        assertEquals("last name matches", lastName, person.getLastName());
        assertEquals("id matches", id, person.getId());

    }

}
