package com.jpmc.theater;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Theater {

    LocalDateProvider provider;
    private List<Showing> schedule;

    public Theater(LocalDateProvider provider, List<Showing> schedule) {
        if(provider == null)
            throw new IllegalArgumentException("provider must not be null");
        if(schedule == null)
            throw new IllegalArgumentException("schedule must not be null");
        this.provider = provider;
        this.schedule = schedule;
    }

    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        Showing showing;
        try {
            showing = schedule.get(sequence - 1);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, showing, howManyTickets);
    }

    public void printSchedule() {
        System.out.println(provider.currentDate());
        System.out.println("===================================================");
        schedule.forEach(s ->
                System.out.println(s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " " + humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovieFee())
        );
        System.out.println("===================================================");
    }

    public String getScheduleJson(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schedule);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    // (s) postfix should be added to handle plural correctly
    private String handlePlural(long value) {
        if (value == 1) {
            return "";
        }
        else {
            return "s";
        }
    }
}