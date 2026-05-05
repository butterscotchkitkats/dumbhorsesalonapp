package edu.secourse.salonapp.models;

/**
 * Represents a person interacting with the system
 *
 * <p>This class provides basic functionality and stores data on users for the system</p>
 *
 * @author Ven Corwell
 * @version 1.0
 */

public class User {
    private int accountNumber; // Need to have account number be unique
    private String userName;
    private String password; // Password needs to be a hash String
    private String name;
    private String emailAddress;
    private String role;

    /**
     * Creates User Object
     * @param accountNumber this is the unique key that identifies the user in the database
     * @param userName this is a unique name the user can be identified as in the system
     * @param password this is the user created log-in code
     * @param name  this is a user's full name
     * @param emailAddress  this is the email address that a user wants to be contacted by
     * @param role  this identifies what access the user has with the system
     */
    public User(int accountNumber, String userName, String password, String name, String emailAddress, String role)
    {
        this.accountNumber = accountNumber;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.emailAddress = emailAddress;
        this.role = role;
    }

    /**
     * Retrieves a user's account number
     * @return accountNumber
     */
    public int getAccountNumber()
    {
        return accountNumber;
    }

    public String  getUserName()
    {
        return this.userName;
    }

    /**
     * Updates the userName field
     * @param userName this is a unique name the user can be identified as in the system
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    /**
     * Needs to be updated to do the required hashing of the password
     * Updates the password field
     * @param password this is the user created log-in code
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Updates the name field
     * @param name this is a user's full name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Retrieves the name field
     * @return name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Updates email address field
     * @param emailAddress this is the email address that a user wants to be contacted by
     */
    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    /**
     * Retrieves email address field
     * @return emailAddress
     */
    public String getEmailAddress()
    {
        return this.emailAddress;
    }

    /**
     * Update role field
     * @param role this identifies what access the user has with the system
     */
    public void setRole(String role)
    {
        this.role = role;
    }

    /**
     * Retrieve role field
     * @return role
     */
    public String getRole()
    {
        return this.role;
    }
}

