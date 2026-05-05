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
    private int customerID;
    private int stylistID;
    private LocalDateTime startTime;
    private Status status;
    public enum  Status {
        ACTIVE, CANCELLED
    }


    /**
     * Creates an appointment
     * @param appointmentID this is a unique number that references the created appointment
     * @param customerID this is the customer account number
     * @param stylistID this is the stylist account number
     * @param startTime this is the date and time when the appointment is scheduled
     * @param status this signifies whether an appointment is active or canceled
     */
    public Appointment(int appointmentID, int customerID, int stylistID, LocalDateTime startTime, Status status) {
        this.appointmentID = appointmentID;
        this.customerID = customerID;
        this.stylistID = stylistID;
        this.startTime = startTime;
        this.status = status;
    }

    /**
     * Retrieves appointmentID
     * @return appointmentID
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * Retrieves the customer id number
     * @return customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Retrieves the stylist id number
     * @return stylistID
     */
    public int getStylistID() {
        return stylistID;
    }

    /**
     * Retrieve's the scheduled appointment time
     * @return startTime
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Retrieves the appointment status
     * @return appointment status - this is either ACTIVE or CANCELLED
     */
    public Status getStatus()
    {
        return status;
    }

    /**
     * Changes the customer on the scheduled appointment
     * @param customerID this is the account number of the new customer
     */
    public void changeCustomer(int customerID)
    {
        this.customerID = customerID;
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
     * Changes the appointment time
     * @param startTime this is the new start time of the appointment
     */
    public void changeStartTime(LocalDateTime startTime)
    {
        this.startTime = startTime;
    }

    /**
     * Changes the status of the appointment
     * @param status this is either active or canceled
     */
    public void changeStatus(Status status)
    {
        this.status = status;
    }

}
