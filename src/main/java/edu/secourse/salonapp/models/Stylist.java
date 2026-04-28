package edu.secourse.salonapp.models;

import edu.secourse.salonapp.components.Appointment;

import java.time.*;
import java.util.ArrayList;

/**
 * Represents a user who is of type stylist
 *
 * <p>This class is a child of the User class. It represents and provides specific functionality for Users with the role
 * of Stylist</p>
 *
 * @author Ven Corwell
 * @version 1.0
 */
public class Stylist extends User{

    private ArrayList<Appointment> scheduledAppointments;
    private ArrayList<LocalDate> workDays;
    /**
     * Creates Stylist Object
     * @param accountNumber this is the unique key that identifies the user in the list of users
     * @param username this is a unique name the user can be identified as in the system
     * @param password this is the user created log-in code
     * @param name this is the customer's full name
     * @param emailAddress this is the email address that a user wants to be contacted by
     */
    public Stylist(int accountNumber, String username, String password, String name, String emailAddress)
    {
        super(accountNumber, username, password, name, emailAddress, "Stylist");
    }

    /**
     * Adds a date that the stylist is working
     * @param workDay this is the year-month-day that the stylist will be working
     */
    public void addWorkDay(LocalDate workDay)
    {
        this.workDays.add(workDay);
    }

    /**
     * Removes a date that the stylist available to work
     * @param workDay this is the year-month-day that the stylist will not be available
     */
    public void removeWorkDay(LocalDate workDay)
    {
        this.workDays.remove(workDay);
    }

    /**
     * Checks if the stylist does not have any appointments scheduled at the selected time
     * @param date this is when the customer is requesting an appointment
     * @return true if the requested date and time is not in the schedule
     */
    private boolean isTimeAvailable(LocalDateTime date)
    {
        boolean slotIsFree = scheduledAppointments.stream().noneMatch(a -> a.getStartTime().equals(date));
        boolean isStylistWorking = workDays.contains(date.toLocalDate());
        return slotIsFree && isStylistWorking;
    }

    /**
     * Add an appointment to the stylist's schedule
     * @param appointment this is the date and time that the customer is scheduling the appointment
     */
    public void addAppointment(Appointment appointment)
    {
        LocalDateTime appointmentTime = appointment.getStartTime();
        // Check if the appointment date/time is available
        if(isTimeAvailable(appointmentTime))
        {
            // If the selected date/time is available add it to the stylist's list of appointments
            scheduledAppointments.add(appointment);
        }
        else {
            System.out.println("Appointment time is unavailable");
        }
    }

    /**
     * Remove an appointment from the stylist's schedule
     * @param appointment the date and time of the canceled appointment
     */
    public void removeAppointment(Appointment appointment)
    {
        scheduledAppointments.remove(appointment);
    }
}
