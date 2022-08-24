package com.jpmc.theater;

import java.time.Duration;
import java.util.Objects;

public class Movie {
    private static int MOVIE_CODE_SPECIAL = 1;

    private String title;
    private String description;
    private Duration runningTime;
    private double ticketPrice;
    private MovieCategory movieCategory;

    public Movie(String title,
                 Duration runningTime,
                 double ticketPrice,
                 MovieCategory category) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.movieCategory = category;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public MovieCategory getMovieCategory() {
        return movieCategory;
    }

    public void setMovieCategory(MovieCategory movieCategory) {
        this.movieCategory = movieCategory;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return Double.compare(
                movie.ticketPrice, ticketPrice) == 0 &&
                Objects.equals(title, movie.title)
                && Objects.equals(description, movie.description)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(movieCategory, movie.movieCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime,
                ticketPrice,
                movieCategory);
    }
}