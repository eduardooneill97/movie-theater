package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class LocalDateProviderTests {
    @Test
    void makeSureCurrentTime() {
        assertEquals(LocalDateProvider.singleton().currentDate(), LocalDate.now());
    }

    @Test
    public void testIsSingleton(){
        LocalDateProvider localDateProvider1 = LocalDateProvider.singleton();
        LocalDateProvider localDateProvider2 = LocalDateProvider.singleton();

        assertSame(localDateProvider1, localDateProvider2);
    }
}