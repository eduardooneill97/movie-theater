package com.jpmc.theater;

import java.util.Arrays;
import java.util.Collections;

public class PricingCalculator {

    public static double getFinalTicketPrice(Showing showing){
        double specialDiscount = 0;
        Movie movie = showing.getMovie();
        if (movie.getMovieCategory() == MovieCategory.SPECIAl) {
            specialDiscount = movie.getTicketPrice() * 0.2;  // 20% discount for special movie
        }

        double sequenceDiscount = 0;
        if (showing.getSequenceOfTheDay() == 1) {
            sequenceDiscount = 3; // $3 discount for 1st show
        } else if (showing.getSequenceOfTheDay() == 2) {
            sequenceDiscount = 2; // $2 discount for 2nd show
        }

        double timeWindowDiscount = 0;
        if(showing.getStartTime().getHour() >= 11 && showing.getStartTime().getHour() <= 16){
            timeWindowDiscount = movie.getTicketPrice() * 0.25;
        }

        double dayDiscount = 0;
        if(showing.getStartTime().getDayOfMonth() == 7){
            dayDiscount = 1;
        }

        double finalDiscount = Collections.max(Arrays.asList(specialDiscount, sequenceDiscount, timeWindowDiscount, dayDiscount));
        return movie.getTicketPrice() - finalDiscount;
    }

}
