package data.entities;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;

import data.entities.Token;
import data.entities.User;

public class TokenTest {

    @Test
    public void testTokenUser() {
        User user = new User("u", "u@gmail.com", "p", Calendar.getInstance());
        Token token = new Token(user);
        assertTrue(token.getValue().length() > 20);
    }
    
    @Test
    public void testTokenIsValid()
    {
    	User user = new User("u", "u@gmail.com", "p", Calendar.getInstance());
        Token token = new Token(user);
        assertTrue(token.isValid());
        Calendar date = Calendar.getInstance();
        date.set(2016, Calendar.MARCH, 01);
        token.setCreatedDate(date);
        assertTrue(!token.isValid());
    }

}
