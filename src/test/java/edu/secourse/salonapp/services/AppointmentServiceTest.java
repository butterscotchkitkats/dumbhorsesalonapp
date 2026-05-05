package edu.secourse.salonapp.services;

import edu.secourse.salonapp.components.Appointment;
import edu.secourse.salonapp.models.Customer;
import edu.secourse.salonapp.models.Stylist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentServiceTest {

    private AppointmentService appointmentService;
    private Customer customer;
    private Stylist stylist;
    LocalDateTime appointmentTime;



    @BeforeEach
    void setUp() {
        appointmentService = new AppointmentService();
        customer = new Customer(567890, "vac2", "SamIsTheBest1", "Ven Corwell", "vencorwell@email.net");
        stylist = new Stylist(334455, "stylist3", "storecode", "Emily Jones", "emily_jones@dumbhorsesalon.com");
        appointmentTime = LocalDateTime.of(2028, 5, 12, 12, 30);
        stylist.addWorkDay(LocalDate.of(2028, 5, 12));
    }

    @Test
    @DisplayName("createAppointment(): does not create an appointment before store open at 9am")
    void createAppointmentBeforeStoreOpens() {
        LocalDateTime beforeStoreOpen = LocalDateTime.of(2028, 5, 12, 8, 30);
        appointmentService.createAppointment(customer, stylist, beforeStoreOpen);
        assertEquals(0, appointmentService.getCustomerAppointments(customer).size());
    }

    @Test
    @DisplayName("createAppointment(): does not create an appointment after 15 min before store has closed at 9pm (21:00)")
    void createAppointmentAfterStoreHasClosed(){
        LocalDateTime afterStoreClosed = LocalDateTime.of(2028, 5, 12, 21, 0);
        appointmentService.createAppointment(customer, stylist, afterStoreClosed);
        assertEquals(0, appointmentService.getCustomerAppointments(customer).size());
    }

    @Test
    @DisplayName("createAppointment(): creates an appointment")
    void createAppointment(){
        appointmentService.createAppointment(customer, stylist, appointmentTime);
        assertEquals(1, appointmentService.getCustomerAppointments(customer).size());
    }



    @Test
    @DisplayName("readAppointment(): shows the appointment if found")
    void readAppointment() {
        appointmentService.createAppointment(customer, stylist, appointmentTime);
        Appointment created = appointmentService.getCustomerAppointments(customer).get(0);
        Appointment found = appointmentService.readAppointment(created);
        assertEquals(created.getAppointmentID(), found.getAppointmentID());
    }

    @Test
    @DisplayName("readAppointment(): returns null when appointment does not exist")
    void readAppointmentNotFound() {
        Appointment notInList = new Appointment(999999, 111222, 333444, appointmentTime, Appointment.Status.ACTIVE);
        assertNull(appointmentService.readAppointment(notInList));
    }

    @Test
    @DisplayName("updateAppointment(): does not let you update to a time before store open")
    void updateAppointmentBeforeStoreOpen() {
        appointmentService.createAppointment(customer, stylist, appointmentTime);
        Appointment createdAppointment = appointmentService.getCustomerAppointments(customer).get(0);
        LocalDateTime beforeStoreOpen = LocalDateTime.of(2028, 5, 12, 8, 30);
        appointmentService.updateAppointment(createdAppointment.getAppointmentID(), stylist, beforeStoreOpen, Appointment.Status.ACTIVE);
        assertEquals(appointmentTime, appointmentService.readAppointment(createdAppointment).getStartTime());
    }

    @Test
    @DisplayName("updateAppointment(): does not let you update to a time after 15 min before store close")
    void updateAppointmentAfterStoreClosed() {
        appointmentService.createAppointment(customer, stylist, appointmentTime);
        Appointment createdAppointment = appointmentService.getCustomerAppointments(customer).get(0);
        LocalDateTime afterStoreClosed = LocalDateTime.of(2028, 5, 12, 20, 50);
        appointmentService.updateAppointment(createdAppointment.getAppointmentID(), stylist, afterStoreClosed, Appointment.Status.ACTIVE);
        assertEquals(appointmentTime, appointmentService.readAppointment(createdAppointment).getStartTime());
    }

    @Test
    @DisplayName("updateAppointment(): updates appointment time ")
    void updateAppointmentTimeOnly() {
        appointmentService.createAppointment(customer, stylist, appointmentTime);
        Appointment createdAppointment = appointmentService.getCustomerAppointments(customer).get(0);
        LocalDateTime newAppointmentTime = LocalDateTime.of(2028, 5, 12, 10, 45);
        appointmentService.updateAppointment(createdAppointment.getAppointmentID(), stylist, newAppointmentTime, Appointment.Status.ACTIVE);
        assertEquals(newAppointmentTime, appointmentService.readAppointment(createdAppointment).getStartTime());
    }

    @Test
    @DisplayName("updateAppointment(): updates appointment stylist ")
    void updateAppointmentStylistOnly() {
        appointmentService.createAppointment(customer, stylist, appointmentTime);
        Appointment createdAppointment = appointmentService.getCustomerAppointments(customer).get(0);
        Stylist stylist2 = new Stylist(3333333, "qwerty", "asdf", "Quincy Adams", "qa@dumbhorsesalon.com");
        appointmentService.updateAppointment(createdAppointment.getAppointmentID(), stylist2, appointmentTime, Appointment.Status.ACTIVE);
        assertEquals(stylist2.getAccountNumber(), appointmentService.readAppointment(createdAppointment).getStylistID());
    }

    @Test
    @DisplayName("updateAppointment(): updates appointment status ")
    void updateAppointmentStatusOnly() {
        appointmentService.createAppointment(customer, stylist, appointmentTime);
        Appointment createdAppointment = appointmentService.getCustomerAppointments(customer).get(0);
        appointmentService.updateAppointment(createdAppointment.getAppointmentID(), stylist, appointmentTime, Appointment.Status.CANCELLED);
        assertEquals(Appointment.Status.CANCELLED, appointmentService.getCustomerAppointments(customer).get(0).getStatus());
    }

    @Test
    @DisplayName("deleteAppointment(): removes an appointment from the system")
    void deleteAppointment() {
        appointmentService.createAppointment(customer, stylist, appointmentTime);
        appointmentService.createAppointment(customer, stylist, LocalDateTime.of(2028, 5, 12, 11, 45));
        appointmentService.createAppointment(customer, stylist, LocalDateTime.of(2028, 5, 12, 14, 00));
        Appointment selectedAppointment = appointmentService.getCustomerAppointments(customer).get(0);
        appointmentService.deleteAppointment(selectedAppointment);
        assertEquals(2, appointmentService.getCustomerAppointments(customer).size());
    }

    @Test
    @DisplayName("deleteAppointment(): does nothing when appointment does not exist")
    void deleteAppointmentNotFound() {
        Appointment notInList = new Appointment(999999, 111222, 333444, appointmentTime, Appointment.Status.ACTIVE);
        assertDoesNotThrow(() -> appointmentService.deleteAppointment(notInList));
    }

    @Test
    @DisplayName("getCustomerAppointments(): returns only the customer's appointments")
    void getCustomerAppointments() {
        Customer customer2 = new Customer(999888, "Batman", "JokerArchnemesis001!", "Bruce Wayne", "batman@gmail.com");
        LocalDateTime otherTime = LocalDateTime.of(2026, 5, 12, 11, 0);

        appointmentService.createAppointment(customer, stylist, appointmentTime);
        appointmentService.createAppointment(customer2, stylist, otherTime);

        ArrayList<Appointment> result = appointmentService.getCustomerAppointments(customer);
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("getCustomerAppointments(): returns empty list when customer has no appointments")
    void getCustomerAppointmentsEmpty() {
        ArrayList<Appointment> result = appointmentService.getCustomerAppointments(customer);
        assertEquals(0, result.size());
    }

    @Test
    @DisplayName("getStylistAppointments() returns only the stylist's appointments")
    void getStylistAppointments() {
        Stylist stylist2 = new Stylist(987654, "bestbiscuit", "Sourdough18!", "Dough Boy", "Pillsbury@yahoo.com");
        stylist2.addWorkDay(LocalDate.of(2028, 5, 12));
        LocalDateTime otherTime = LocalDateTime.of(2026, 5, 12, 17, 45);

        appointmentService.createAppointment(customer, stylist, appointmentTime);
        appointmentService.createAppointment(customer, stylist2, otherTime);

        ArrayList<Appointment> result = appointmentService.getCustomerAppointments(customer);
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("getStylistAppointments(): returns empty list when a stylist has no appointments")
    void getStylistAppointmentsEmpty() {
        ArrayList<Appointment> result = appointmentService.getStylistAppointments(stylist);
        assertEquals(0, result.size());
    }
}