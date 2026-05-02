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


    public void updateAppointment(int appointmentID, Integer stylistID, LocalDateTime appointmentTime, Appointment.Status status)
    {
        Appointment a = findByID(appointmentID);
        if(a == null) return;

        if(stylistID != null) a.changeStylist(stylistID);
        if(appointmentTime != null) a.changeStartTime(appointmentTime);
        if(status != null) a.changeStatus(status);
    }

    public void deleteAppointment(Appointment a)
    {
        Appointment found = findByID(a.getAppointmentID());
        if (found != null) {
            appointments.remove(found);
        }
    }

    private Appointment findByID(int id) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID() == id) {
                return appointment;
            }
        }
        return null;
    }

    // AppointmentService becomes the single source of truth
    private boolean isStylistAvailable(Stylist stylist, LocalDateTime requestedTime) {
        boolean isStylistWorking = stylist.isWorkDay(requestedTime.toLocalDate());
        boolean slotIsFree = appointments.stream()
                .filter(a -> a.getStylistID() == stylist.getAccountNumber())
                .noneMatch(a -> a.getStartTime().equals(requestedTime));
        return isStylistWorking && slotIsFree;
    }
}
