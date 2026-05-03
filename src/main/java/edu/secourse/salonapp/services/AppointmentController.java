package edu.secourse.salonapp.services;

import edu.secourse.salonapp.components.Appointment;
import edu.secourse.salonapp.models.Customer;
import edu.secourse.salonapp.models.Stylist;
import edu.secourse.salonapp.models.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class AppointmentController {
    // when user clicks create new appointment button, create a new appointment and add to list
    // when user clicks cancel appointment, delete appointment from list
    // get user-inputted information from fields
    // assign information to appropriate parameters

    private AppointmentService appointmentService;
    private UserService userService;
    private Scanner scanner;

    public AppointmentController(AppointmentService appointmentService, UserService userService) {
        this.appointmentService = appointmentService;
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Shows the appropriate appointment menu based on role
     * @param u this is the User
     */
    public void showMenu(User u)
    {
        if(u.getRole().equals("customer")){
            showCustomerMenu((Customer)u);
        }
        else if(u.getRole().equals("stylist")){
            showStylistMenu((Stylist)u);
        }
        else{
            System.out.println("Unrecognized role");
        }
    }

    /**
     * Displays Appointment menu from the view of the customer
     * @param customer the user is a customer
     */
    private void showCustomerMenu(Customer customer)
    {
        boolean running = true;
        while (running) {
            System.out.println("\n*---Appointment Menu--*\n");
            System.out.println("1. Create Appointment");
            System.out.println("2. View My Appointments");
            System.out.println("3. Update Appointment");
            System.out.println("4. Cancel Appointment");
            System.out.println("5. Back");
            System.out.println("Enter the number of the desired option: ");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    createAppointment(customer);
                    break;
                case "2":
                    viewCustomerAppointments(customer);
                    break;
                case "3":
                    updateAppointment(customer);
                    break;
                case "4":
                    cancelCustomerAppointment(customer);
                    break;
                case "5":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    /**
     * Displays Appointment menu from the view of a stylist
     * @param stylist the user is a stylist
     */
    private void showStylistMenu(Stylist stylist)
    {
        boolean running = true;
        while (running) {
            System.out.println("\n*---Appointment Menu--*\n");
            System.out.println("1. View Appointments");
            System.out.println("2. Cancel Appointment");
            System.out.println("3. Manage Work Days");
            System.out.println("4. Back");
            System.out.println("Enter the number of the desired option: ");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    viewStylistAppointments(stylist);
                    break;
                case "2":
                    cancelStylistAppointment(stylist);
                    break;
                case "3":
                    manageWorkDays(stylist);
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    /**
     * Create an appointment as a customer
     * @param customer - the user is a customer
     */
    private void createAppointment(Customer customer)
    {
        Stylist stylist = selectStylist();
        if(stylist == null) return;

        System.out.println("\nEnter appointment date and time in (yyyy-MM-dd HH:mm): ");
        LocalDateTime appointmentTime = LocalDateTime.parse(scanner.nextLine());

        appointmentService.createAppointment(customer, stylist, appointmentTime);
        System.out.println("Appointment Created");
    }

    /**
     * Displays all the appointments a customer has
     * @param customer - the user is a customer
     */
    private void viewCustomerAppointments(Customer customer)
    {
        ArrayList<Appointment> appointments = appointmentService.getCustomerAppointments(customer);
        if(appointments.isEmpty())
        {
            System.out.println("No appointments found");
        }
        printAppointments(appointments);
    }

    /**
     * Updates the selected appointment
     * @param customer - this is the user
     */
    private void updateAppointment(Customer customer)
    {
        Appointment selected = selectCustomerAppointment(customer);
        if(selected == null) return;

        Stylist stylist = selectStylist();
        if(stylist == null) return;

        System.out.println("Enter appointment date and time in (yyyy-MM-dd HH:mm): or press Enter to keep current date and time:");
        String input = scanner.nextLine();
        LocalDateTime newTime = null;
        if(!input.isBlank())
        {
            newTime = LocalDateTime.parse(input);
        }

        appointmentService.updateAppointment(selected.getAppointmentID(), stylist, newTime, null);
        System.out.println("Appointment Updated");
    }

    /**
     * Cancels the selected appointment for the customer
     * @param customer - this is the user
     */
    private void cancelCustomerAppointment(Customer customer)
    {
        Appointment selected = selectCustomerAppointment(customer);
        if(selected == null) return;

        appointmentService.updateAppointment(selected.getAppointmentID(), null, null, Appointment.Status.CANCELLED);
        System.out.println("Appointment Cancelled");
    }

    /**
     * Cancels the selected appointment for the stylist
     * @param stylist - this is the user
     */
    private void cancelStylistAppointment(Stylist stylist)
    {
        Appointment selected = selectStylistAppointment(stylist);
        if(selected == null) return;

        appointmentService.updateAppointment(selected.getAppointmentID(), null, null, Appointment.Status.CANCELLED);
        System.out.println("Appointment Cancelled");
    }

    /**
     * Displays all appointments a stylist has
     * @param stylist - this is the user
     */
    private void viewStylistAppointments(Stylist stylist)
    {
        ArrayList<Appointment> appointments = appointmentService.getStylistAppointments(stylist);
        if(appointments.isEmpty())
        {
            System.out.println("You have no appointments.");
            return;
        }
        printAppointments(appointments);
    }

    /**
     * Displays a menu for the stylist to manage what days they are working
     * @param stylist - this is the user
     */
    private void manageWorkDays(Stylist stylist)
    {
        boolean running = true;
        while (running) {
            System.out.println("\n*---Manage Work Days Menu--*\n");
            System.out.println("1. Add Work Day");
            System.out.println("2. Remove Work Day");
            System.out.println("3. Back");
            System.out.println("Enter the number of the desired option: ");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    System.out.println("Enter date to add in (yyyy-MM-dd): ");
                    try
                    {
                        stylist.addWorkDay(LocalDate.parse(scanner.nextLine()));
                    }catch(DateTimeParseException ex){
                        System.out.println("Invalid date format");
                    }
                    break;
                case "2":
                    System.out.println("Enter date to remove in (yyyy-MM-dd): ");
                    try{
                        stylist.removeWorkDay(LocalDate.parse(scanner.nextLine()));
                    }catch (DateTimeParseException ex){
                        System.out.println("Invalid date format");
                    }
                    break;
                case "3":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
                    break;

            }
        }
    }

    private void printAppointments(ArrayList<Appointment> appointments)
    {
        System.out.println("\n*--Appointments--*\n");
        for(Appointment appointment : appointments)
        {
            System.out.println(appointment);
        }
    }

    /**
     * Has the user select an appointment
     * @param appointments list of appointments to choose from
     * @return the selected appointment
     */
    private Appointment selectAppointment(ArrayList<Appointment> appointments)
    {
        if(appointments.isEmpty())
        {
            System.out.println("There are no appointments in the system");
            return null;
        }

        printAppointments(appointments);
        System.out.println("Select an appointment number: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if(index < 0 || index >= appointments.size())
            {
                System.out.println("Invalid selection.");
                return null;
            }
            return appointments.get(index);
        }
        catch(NumberFormatException e){
            System.out.println("Invalid input.");
            return null;
        }
    }

    /**
     * Retrieves the selected appointment from the customer's list of appointments
     * @param customer - the user is a customer
     * @return Appointment object of the selected appointment
     */
    private Appointment selectCustomerAppointment(Customer customer)
    {
        ArrayList<Appointment> appointments = appointmentService.getCustomerAppointments(customer);
        return selectAppointment(appointments);
    }

    /**
     * Retrieves the selected appointment from the stylist's list of appointments
     * @param stylist - the user is a stylist
     * @return Appointment object of the selected appointment
     */
    private Appointment selectStylistAppointment(Stylist stylist)
    {
        ArrayList<Appointment> appointments = appointmentService.getStylistAppointments(stylist);
        return selectAppointment(appointments);
    }

    /**
     * Chooses a stylist from a list of stylists
     * @return Stylist object of the chosen stylist
     */
    private Stylist selectStylist()
    {
        ArrayList<Stylist> stylists = userService.getAllStylists();
        if(stylists.isEmpty())
        {
            System.out.println("There are no stylists in the system");
            return null;
        }

        System.out.println("\n*--Available Stylists--*\n");
        for(int i = 0; i < stylists.size(); i++)
        {
            System.out.println((i+1) + ". " + stylists.get(i).getName());
        }
        System.out.println("Select a Stylist: ");
        try
        {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if(index < 0 || index >= stylists.size()){
                System.out.println("Invalid selection.");
                return null;
            }
            return stylists.get(index);
        }
        catch(NumberFormatException e){
            System.out.println("Invalid input.");
            return null;
        }
    }
}
