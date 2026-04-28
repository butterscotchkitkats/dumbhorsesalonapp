package edu.secourse.salonapp.services;

import edu.secourse.salonapp.components.Appointment;
import edu.secourse.salonapp.models.Customer;
import edu.secourse.salonapp.models.Stylist;

import java.time.LocalDateTime;
import java.util.HashMap;

public class AppointmentService
{

    HashMap<Integer, Appointment> appointments;

    /**
     * Creates an appointment and stores it in the list of appointments
     * @param customer this is the customer scheduling the appointment
     * @param stylist this is the stylist who the customer wants to cut their hair
     * @param appointmentTime this is the appointment date and time that the customer has chosen
     */
    public void createAppointent(Customer customer, Stylist stylist, LocalDateTime appointmentTime)
    {
        int appointmentID = generateAppointmentID();
        Appointment appointment = new Appointment(appointmentID, customer.getAccountNumber(), stylist.getAccountNumber(),
                appointmentTime, Appointment.Status.ACTIVE);
        appointments.put(appointmentID, appointment);
    }

    private int generateAppointmentID()
    {
        int appointmentID = (int) Math.ceil(Math.random());
        while(appointments.containsKey(appointmentID))
        {
            appointmentID = (int) Math.ceil(Math.random());
        }

        return appointmentID;
    }

    public void updateAppointent()
    {
        // Break this into update appointment time and update appointment status?
    }

    public void deleteAppointent(Appointment appointment)
    {
        appointments.remove(appointment.getAppointmentID());
    }
}
