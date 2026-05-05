package edu.secourse.salonapp.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {

    private Appointment appointment;
    private LocalDateTime startTime;
    @BeforeEach
    void setUp(){
        startTime=LocalDateTime.of(2027,Month.APRIL,19,15,0);
        appointment = new Appointment(123456, 234567, 345678,
                startTime, Appointment.Status.ACTIVE);
    }

    @Test
    @DisplayName("getAppointmentID(): retrieves appointment ID")
    void getAppointmentID() {
        assertEquals(123456, appointment.getAppointmentID());
    }

    @Test
    @DisplayName("getCustomerID(): retrieves customer ID")
    void getCustomerID() {
        assertEquals(234567, appointment.getCustomerID());
    }

    @Test
    @DisplayName("getStylistID(): retrieves stylist ID")
    void getStylistID() {
        assertEquals(345678, appointment.getStylistID());
    }

    @Test
    @DisplayName("getStartTime(): retrieves appointment start time")
    void getStartTime() {
        assertEquals(startTime, appointment.getStartTime());
    }

    @Test
    @DisplayName("getStatus(): retrieves the appointment status")
    void getStatus() {
        assertEquals(Appointment.Status.ACTIVE, appointment.getStatus());
    }

    @Test
    @DisplayName("changeCustomer(): updates the customer associated with the appointment")
    void changeCustomer() {
        appointment.changeCustomer(876543);
        assertEquals(876543, appointment.getCustomerID());
    }

    @Test
    @DisplayName("changeStylist(): updates the customer associated with the appointment")
    void changeStylist() {
        appointment.changeStylist(999888);
        assertEquals(999888, appointment.getStylistID());
    }

    @Test
    @DisplayName("changeStartTime(): changes the start time of an appointment")
    void changeStartTime() {
        LocalDateTime newTime = LocalDateTime.of(2026, Month.AUGUST, 20, 11, 45);
        appointment.changeStartTime(newTime);
        assertEquals(LocalDateTime.of(2026, Month.AUGUST, 20, 11, 45), appointment.getStartTime());
    }

    @Test
    @DisplayName("changeStatus(): updates the status of the appointment (ACTIVE or CANCELLED)")
    void changeStatus() {
        appointment.changeStatus(Appointment.Status.CANCELLED);
        assertEquals(Appointment.Status.CANCELLED, appointment.getStatus());
    }
}