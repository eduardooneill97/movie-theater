package com.jpmc.theater;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PricingCalculatorTest {

    @ParameterizedTest
    @MethodSource("ticketPriceShowingsArgs")
    public void testFinalTicketPrice(Showing showing, double ticketPrice){
        assertEquals(PricingCalculator.getFinalTicketPrice(showing), ticketPrice);
    }

    private static Stream<Arguments> ticketPriceShowingsArgs(){
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, MovieCategory.SPECIAl);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, MovieCategory.REGULAR);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, MovieCategory.REGULAR);

        return Stream.of(
                Arguments.of(new Showing(turningRed, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0))), 11-3),
                Arguments.of(new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0))), 12.50-3.125),
                Arguments.of(new Showing(theBatMan, 3, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 50))), 9-2.25),
                Arguments.of(new Showing(turningRed, 4, LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 30))), 11-2.75),
                Arguments.of(new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 10))), 12.50-3.125),
                Arguments.of(new Showing(theBatMan, 6, LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 50))), 9),
                Arguments.of(new Showing(turningRed, 7, LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30))), 11),
                Arguments.of(new Showing(spiderMan, 8, LocalDateTime.of(LocalDate.now(), LocalTime.of(21, 10))), 12.50-2.50),
                Arguments.of(new Showing(theBatMan, 9, LocalDateTime.of(LocalDate.of(2022, 5, 7), LocalTime.of(23, 0))), 9-1),
                Arguments.of(new Showing(turningRed, 9, LocalDateTime.of(LocalDate.of(2022, 5, 7), LocalTime.of(12, 0))), 11-2.75),
                Arguments.of(new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.of(2022, 5, 7), LocalTime.of(12, 0))), 12.50-3.125)
        );
    }
}
