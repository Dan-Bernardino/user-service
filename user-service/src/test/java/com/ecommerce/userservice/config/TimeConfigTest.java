package com.ecommerce.userservice.config;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TimeConfigTest {

    @Test
    void clockDevuelveUnRelojEnUtc() {
        Clock clock = new TimeConfig().clock();

        assertNotNull(clock);
        assertEquals(ZoneOffset.UTC, clock.getZone());
    }
}
