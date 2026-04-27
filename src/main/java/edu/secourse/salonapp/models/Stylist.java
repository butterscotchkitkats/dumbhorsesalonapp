package edu.secourse.salonapp.models;

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
}
