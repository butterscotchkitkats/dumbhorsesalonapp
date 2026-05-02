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

    public boolean isWorkDay(LocalDate date)
    {
        return workDays.contains(date);
    }
}
