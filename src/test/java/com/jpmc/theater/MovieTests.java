package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MovieTests {

    @Test
    public void testEqualsReturnsTrue(){
        Movie m1 = new Movie("Bullet Train", Duration.ofMinutes(150), 9.75, MovieCategory.REGULAR);
        Movie m2 = new Movie("Bullet Train", Duration.ofMinutes(150), 9.75, MovieCategory.REGULAR);

        assertEquals(m1, m2);
    }

    @Test
    public void testEqualsReturnsFalse(){
        Movie m1 = new Movie("Avatar 2", Duration.ofMinutes(190), 14.75, MovieCategory.SPECIAl);
        Movie m2 = new Movie("Bullet Train", Duration.ofMinutes(150), 9.75, MovieCategory.REGULAR);
        Movie m3 = new Movie("Avatar 2", Duration.ofMinutes(190), 14.75, MovieCategory.REGULAR);

        assertNotEquals(m1, m2);
        assertNotEquals(m1, m3);
    }

    @Test
    public void testEqualsDifferentClassReturnsFalse(){
        Movie m1 = new Movie("Avatar 2", Duration.ofMinutes(190), 14.75, MovieCategory.SPECIAl);
        Object m2 = new Object();

        assertNotEquals(m1, m2);
    }
}