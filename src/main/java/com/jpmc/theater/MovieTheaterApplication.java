package com.jpmc.theater;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class MovieTheaterApplication {

    public static void main(String[] args){

        LocalDateProvider provider = LocalDateProvider.singleton();

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

        Theater theater = new Theater(provider, schedule);
        theater.printSchedule();
        String scheduleJson = theater.getScheduleJson();
        System.out.println(scheduleJson);
    }
}
