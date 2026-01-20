package com.example.vehiclerepairtracker;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PasswordUnitTest {
    //Test if password is correct
    @Test
    public void testPasswordIsCorrect() {

        String actualPassword = "password123";

        String expectedPassword = "password123";
        assertEquals(expectedPassword, actualPassword);
    }
    //Test if password is not empty
    @Test
    public void testPasswordNotEmpty() {
        String password = "password123";
        assertFalse(password.isEmpty());
    }
    //Test if password length is 8
    @Test
    public void testPasswordLength() {
        String password = "password123";
        assertTrue(password.length() >= 8);
    }
}