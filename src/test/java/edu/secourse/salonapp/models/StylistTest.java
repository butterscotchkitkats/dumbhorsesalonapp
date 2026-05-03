package edu.secourse.salonapp.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StylistTest {

    private Stylist stylist;
    private LocalDate workDay;

    @BeforeEach
    void setUp() {
        stylist = new Stylist(111222, "stylist1", "icuthair", "Marie Smith",
                "thecutter@gmail.com");
        workDay = LocalDate.of(2026, 5, 10);
    }

    @Test
    @DisplayName("Stylist role is automatically set to Stylist")
    void stylistRoleIsSetCorrectly() {
        assertEquals("Stylist", stylist.getRole());
    }

    @Test
    @DisplayName("addWorkDay(): adds a work day to the stylist's schedule")
    void addWorkDay() {
        stylist.addWorkDay(workDay);
        assertTrue(stylist.isWorkDay(workDay));
    }

    @Test
    @DisplayName("removeWorkDay(): removes a work day from the stylist's schedule")
    void removeWorkDay() {
        stylist.addWorkDay(workDay);
        stylist.removeWorkDay(workDay);
        assertFalse(stylist.isWorkDay(workDay));
    }

    @Test
    @DisplayName("isWorkDay(): returns true when date is a scheduled work day")
    void isWorkDayTrue() {
        stylist.addWorkDay(workDay);
        assertTrue(stylist.isWorkDay(workDay));
    }

    @Test
    @DisplayName("isWorkDay(): returns false when date is not a scheduled work day")
    void isWorkDayFalse() {
        assertFalse(stylist.isWorkDay(workDay));
    }
}