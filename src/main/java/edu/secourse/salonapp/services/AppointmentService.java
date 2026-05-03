package edu.secourse.salonapp.services;

import edu.secourse.salonapp.components.Appointment;
import edu.secourse.salonapp.models.Customer;
import edu.secourse.salonapp.models.Stylist;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class AppointmentService
{

    ArrayList<Appointment> appointments = new ArrayList<Appointment>();

    /**
     * Creates an appointment and stores it in the list of appointments
     * @param customer this is the customer scheduling the appointment
     * @param stylist this is the stylist who the customer wants to cut their hair
     * @param appointmentTime this is the appointment date and time that the customer has chosen
     */
    public void createAppointment(Customer customer, Stylist stylist, LocalDateTime appointmentTime)
    {
        if(!isStylistAvailable(stylist, appointmentTime))
        {
            System.out.println("Appointment not available");
            return;
        }
        int appointmentID = generateAppointmentID();
        Appointment appointment = new Appointment(appointmentID, customer.getAccountNumber(), stylist.getAccountNumber(),
                appointmentTime, Appointment.Status.ACTIVE);
        appointments.add(appointment);
    }

    /**
     * Generates a unique number used to the appointment id
     * @return id this is the unique number assigned to the appointment
     */
    private int generateAppointmentID()
    {
        Random r = new Random();
        int id;
        boolean duplicate;
        do {
            id = 100000 + r.nextInt(900000);
            duplicate = false;
            for (Appointment appointment : appointments)
            {
                if (appointment.getAppointmentID() == id) {
                    duplicate = true;
                    break;
                }
            }
        }while(duplicate);

        return id;
    }

    /**
     * Allows the user to see the attributes of the appointment from the list of appointments
     * @param a this is the appointment that we want to see the details for
     * @return Appointment object of the appointment we are looking for
     */
    public Appointment readAppointment(Appointment a)
    {
        return findByID(a.getAppointmentID());
    }

    /**
     * Update the selected appointment attributes
     * @param stylist this is the new or current stylist
     * @param appointmentID this is the appointment we want to update
     * @param appointmentTime this is the new time of the appointment. Set null if not being changed
     * @param status this is the new status of the appointment. Set null if not being changed
     */
    public void updateAppointment(int appointmentID, Stylist stylist, LocalDateTime appointmentTime, Appointment.Status status)
    {
        Appointment a = findByID(appointmentID);
        if(a == null) return;

        // If this is not the same stylist, update to the new one
        if(a.getStylistID() != stylist.getAccountNumber()) {
            a.changeStylist(stylist.getAccountNumber());
        }

        if(appointmentTime != null) {
            if(isStylistAvailable(stylist, appointmentTime)){
                a.changeStartTime(appointmentTime);
            }
            else {
                System.out.println("Appointment time not available");
            }
        }
        if(status != null) a.changeStatus(status);
    }

    /**
     * Deletes a selected appointment
     * @param a this is the appointment to be removed
     */
    public void deleteAppointment(Appointment a)
    {
        Appointment found = findByID(a.getAppointmentID());
        if (found != null) {
            appointments.remove(found);
        }
    }

    /**
     * List of a customer's appointments
     * @param customer this is the customer whose list will be displayed
     * @return ArrayList of object Appointment
     */
    public ArrayList<Appointment> getCustomerAppointments(Customer customer)
    {
        ArrayList<Appointment> customerAppointments = new ArrayList<>();
        for(Appointment appointment : appointments)
        {
            if(appointment.getCustomerID() == customer.getAccountNumber()) {
                customerAppointments.add(appointment);
            }
        }
        return customerAppointments;
    }

    /**
     * List of a stylist's appointments
     * @param stylist this is the stylist whose list will be displayed
     * @return ArrayList of object Appointment
     */
    public ArrayList<Appointment> getStylistAppointments(Stylist stylist)
    {
        ArrayList<Appointment> stylistAppointments = new ArrayList<>();
        for(Appointment appointment : appointments)
        {
            if(appointment.getStylistID() == stylist.getAccountNumber()) {
                stylistAppointments.add(appointment);
            }
        }
        return stylistAppointments;
    }

    /**
     * Finds an appointment from the stored list
     * @param id appointmentID number
     * @return appointment object if found, null otherwise
     */
    private Appointment findByID(int id) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID() == id) {
                return appointment;
            }
        }
        return null;
    }

    /**
     * Lets the user know if the stylist is available at the requested time
     * @param stylist the stylist we want to know is available
     * @param requestedTime the time a customer is requesting
     * @return true if available, false if not
     */
    private boolean isStylistAvailable(Stylist stylist, LocalDateTime requestedTime) {
        boolean isStylistWorking = stylist.isWorkDay(requestedTime.toLocalDate());
        boolean slotIsFree = appointments.stream()
                .filter(a -> a.getStylistID() == stylist.getAccountNumber())
                .noneMatch(a -> a.getStartTime().equals(requestedTime));
        return isStylistWorking && slotIsFree;
    }
}
