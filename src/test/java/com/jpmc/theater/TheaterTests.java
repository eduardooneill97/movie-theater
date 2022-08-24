package com.jpmc.theater;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class TheaterTests {

    static Theater theater;
    static LocalDateProvider provider;

    @BeforeAll
    static void init(){
        provider = mock(LocalDateProvider.class);
        when(provider.currentDate()).thenReturn(LocalDate.now());

        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, MovieCategory.SPECIAl);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, MovieCategory.REGULAR);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, MovieCategory.REGULAR);
        List<Showing> schedule = List.of(
                new Showing(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0))),
                new Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0))),
                new Showing(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50))),
                new Showing(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30))),
                new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10))),
                new Showing(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50))),
                new Showing(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30))),
                new Showing(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10))),
                new Showing(theBatMan, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0)))
        );
        theater = new Theater(provider, schedule);
    }

    @Test
    void totalFeeForCustomer() {
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 2, 4);
        assertEquals(reservation.totalFee(), 50);
    }

    @Test
    void testReservationFailedForInvalidSequence() {
        try {
            Customer john = new Customer("John Doe", "id-12345");
            Reservation reservation = theater.reserve(john, 10, 4);
        } catch (Exception e){
            assertTrue(e instanceof IllegalStateException);
        }
    }

    @Test
    void printMovieSchedule() {
        theater.printSchedule();
    }

    @Test
    void testConstructorArgumentsMustNotBeNull(){
        try{
            Theater theater1 = new Theater(null, new ArrayList<>());
            fail();
        } catch (Exception e){
            assertTrue(e instanceof IllegalArgumentException);
        }

        try{
            Theater theater1 = new Theater(provider, null);
            fail();
        } catch (Exception e){
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @ParameterizedTest
    @MethodSource("theaterArgs")
    void testScheduleStringIsJson(Theater theater){
        String schedule = theater.getScheduleJson();
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(schedule);
        }catch (JsonProcessingException e){
            fail("schedule string not a valid json");
        }
    }

    private static Stream<Arguments> theaterArgs(){
        return Stream.of(
                Arguments.of(theater),
                Arguments.of(new Theater(provider, new ArrayList<>()))
        );
    }
}