package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CustomerTest {

    @Test
    public void testEqualsReturnsTrue(){
        Customer c1 = new Customer("Ed", "abcd");
        Customer c2 = new Customer("Ed", "abcd");

        assertEquals(c1, c2);
    }

    @Test
    public void testEqualsReturnsFalse(){
        Customer c1 = new Customer("Ed", "abcd");
        Customer c2 = new Customer("Anna", "xyz");

        assertNotEquals(c1, c2);
    }

    @Test
    public void testEqualsDifferentIdsReturnsFalse(){
        Customer c1 = new Customer("Ed", "efgh");
        Customer c2 = new Customer("Ed", "abcd");

        assertNotEquals(c1, c2);
    }

    @Test
    public void testEqualsDifferentClassReturnsFalse(){
        Customer c1 = new Customer("Ed", "efgh");
        Object c2 = new Object();

        assertNotEquals(c1, c2);
    }
}
