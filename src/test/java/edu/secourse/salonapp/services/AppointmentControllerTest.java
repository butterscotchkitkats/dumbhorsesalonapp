package edu.secourse.salonapp.services;

import edu.secourse.salonapp.components.Appointment;
import edu.secourse.salonapp.models.Customer;
import edu.secourse.salonapp.models.Stylist;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentControllerTest {

    private FakeAppointmentService appointmentService;
    private FakeUserService userService;
    private AppointmentController controller;
    private Customer testCustomer;
    private Stylist testStylist;
    private InputStream originalIn;

    @BeforeEach
    void setUp() {
        originalIn = System.in;
        testCustomer = new Customer(135790, "talktomanager", "imthebest1", "Karen Best", "perfect1@aol.com");
        testStylist  = new Stylist(246800,"stylist1","password246800", "Kevin Giovanni", "kevin_g@dumbhorsesalon.com");

        appointmentService = new FakeAppointmentService();
        userService = new FakeUserService();
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
    }

    /**
     * Helper to feed text into the Scanner inside AppointmentController
     */
    private void setInputAndInitController(String... lines) {
        String input = String.join("\n", lines) + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        controller = new AppointmentController(appointmentService, userService);
    }

    @Test
    void createAppointment_SuccessfulFlow() {
        userService.stylistsToReturn.add(testStylist);

        setInputAndInitController("1", "1", "2026-06-01T10:00", "5");

        controller.showMenu(testCustomer);

        assertEquals(1, appointmentService.createCallCount);
        assertEquals(testCustomer, appointmentService.lastCustomer);
        assertEquals(testStylist, appointmentService.lastStylist);
        assertEquals(LocalDateTime.of(2026, 6, 1, 10, 0), appointmentService.lastTime);
    }

    @Test
    void cancelCustomerAppointment_CallsUpdateWithCancelledStatus() {
        Appointment appt = new Appointment(12345, testCustomer.getAccountNumber(),
                testStylist.getAccountNumber(), LocalDateTime.now(),
                Appointment.Status.ACTIVE);
        appointmentService.customerAppointments.add(appt);

        setInputAndInitController("4", "1", "5");

        controller.showMenu(testCustomer);

        assertEquals(12345, appointmentService.lastUpdateId);
        assertEquals(Appointment.Status.CANCELLED, appointmentService.lastStatus);
        assertNull(appointmentService.lastUpdateStylist); // cancel doesn't change stylist
    }

    @Test
    void updateAppointment_ChangesTimeAndStylist() {
        Appointment appt = new Appointment(999, testCustomer.getAccountNumber(),
                testStylist.getAccountNumber(), LocalDateTime.now(),
                Appointment.Status.ACTIVE);
        appointmentService.customerAppointments.add(appt);
        userService.stylistsToReturn.add(testStylist);

        setInputAndInitController("3", "1", "1", "2026-12-25T14:00", "5");

        controller.showMenu(testCustomer);

        assertEquals(999, appointmentService.lastUpdateId);
        assertEquals(LocalDateTime.of(2026, 12, 25, 14, 0), appointmentService.lastUpdateTime);
    }

    @Test
    void manageWorkDays_AddsDateToStylistObject() {
        setInputAndInitController("3", "1", "2026-05-10", "3", "4");

        controller.showMenu(testStylist);

        assertTrue(testStylist.isWorkDay(LocalDate.of(2026, 5, 10)));
    }

    private static class FakeAppointmentService extends AppointmentService {
        int createCallCount = 0;
        Customer lastCustomer;
        Stylist lastStylist;
        LocalDateTime lastTime;

        int lastUpdateId;
        Stylist lastUpdateStylist;
        LocalDateTime lastUpdateTime;
        Appointment.Status lastStatus;

        ArrayList<Appointment> customerAppointments = new ArrayList<>();

        @Override
        public void createAppointment(Customer c, Stylist s, LocalDateTime time) {
            this.createCallCount++;
            this.lastCustomer = c;
            this.lastStylist = s;
            this.lastTime = time;
        }

        @Override
        public ArrayList<Appointment> getCustomerAppointments(Customer c) {
            return customerAppointments;
        }

        @Override
        public ArrayList<Appointment> getStylistAppointments(Stylist s) {
            return new ArrayList<>();
        }

        @Override
        public void updateAppointment(int appointmentID, Stylist stylist, LocalDateTime appointmentTime, Appointment.Status status) {
            this.lastUpdateId = appointmentID;
            this.lastUpdateStylist = stylist;
            this.lastUpdateTime = appointmentTime;
            this.lastStatus = status;
        }
    }

    private static class FakeUserService extends UserService {
        ArrayList<Stylist> stylistsToReturn = new ArrayList<>();

        @Override
        public ArrayList<Stylist> getAllStylists() {
            return stylistsToReturn;
        }
    }
}