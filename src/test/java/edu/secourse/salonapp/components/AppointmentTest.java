package edu.secourse.salonapp.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {

    private Appointment appointment;
    @BeforeEach
    void setUp(){
        appointment = new Appointment(123456, 234567, 345678,
                LocalDateTime.of(2026, Month.JULY, 12, 10, 30), Appointment.Status.ACTIVE);
    }

    @Test
    void getAppointmentID() {
        assertEquals(123456, appointment.getAppointmentID());
    }

    @Test
    void getCustomerID() {
        assertEquals(234567, appointment.getCustomerID());
    }

    @Test
    void getStylistID() {
        assertEquals(345678, appointment.getStylistID());
    }

    @Test
    void getStartTime() {
        assertEquals(LocalDateTime.of(2026, Month.JULY, 12, 10, 30), appointment.getStartTime());
    }

    @Test
    void changeCustomer() {
        appointment.changeCustomer(876543);
        assertEquals(876543, appointment.getCustomerID());
    }

    @Test
    void changeStylist() {
        appointment.changeStylist(999888);
        assertEquals(999888, appointment.getStylistID());
    }

    @Test
    void changeStartTime() {
        appointment.changeStartTime(LocalDateTime.of(2026, Month.AUGUST, 20, 11, 45));
        assertEquals(LocalDateTime.of(2026, Month.AUGUST, 20, 11, 45), appointment.getStartTime());
    }

    @Test
    void changeStatus() {
        appointment.changeStatus(Appointment.Status.CANCELLED);
        assertEquals(Appointment.Status.CANCELLED, appointment.getStatus());
    }
}