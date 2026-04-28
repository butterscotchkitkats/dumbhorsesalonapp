package edu.secourse.salonapp.components;

import java.time.LocalDateTime;

/**
 * Represents an appointment a customer has with a hair stylist
 *
 * <p>This class provides basic functionality and stores data on appointments created</p>
 *
 * @author Ven Corwell
 * @version 1.0
 */
public class Appointment {
    private int appointmentID;
    private int customerID; // Do we want this and stylistID as the user account number or as a separate number?
    private int stylistID;
    private LocalDateTime startTime;
    private boolean status; // 1 is active, 0 is canceled


    /**
     * Creates an appointment
     * @param appointmentID this is a unique number that references the created appointment
     * @param customerID this is the customer account number
     * @param stylistID this is the stylist account number
     * @param startTime this is the date and time when the appointment is scheduled
     * @param status this signifies whether an appointment is active or canceled
     */
    public Appointment(int appointmentID, int customerID, int stylistID, LocalDateTime startTime, boolean status) {
        this.appointmentID = appointmentID;
        this.customerID = customerID;
        this.stylistID = stylistID;
        this.startTime = startTime;
        this.status = status;
    }

    /**
     * Retrieve's the scheduled appointment time
     * @return startTime
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Changes which stylist will handle the appointment
     * @param stylistID this is the account number of the new stylist
     */
    public void changeStylist(int stylistID)
    {
        this.stylistID = stylistID;
    }

    /**
     * Likely won't need this function but left it just in case
     * Changes the customer on the scheduled appointment
     * @param customerID this is the account number of the new customer
     */
    /*public void changeCustomer(int customerID)
    {
        this.customerID = customerID;
    }
    */


    /**
     * Changes the status of the appointment
     * @param status this is either active or canceled
     */
    public void changeStatus(boolean status)
    {
        this.status = status;
    }
}
