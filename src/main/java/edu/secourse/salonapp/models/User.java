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
     * @param userName
     * @param password
     * @param name
     * @param emailAddress
     * @param role
     */
    User(String userName, String password, String name, String emailAddress, String role)
    {
        // Generate account number here?
        accountNumber = (int) Math.ceil(Math.random());

        this.userName = userName;
        this.password = password;
        this.name = name;
        this.emailAddress = emailAddress;
        this.role = role;
    }

    /**
     * Updates the userName field
     * @param userName
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    /**
     * Needs to be updated to do the required hashing of the password
     * Updates the password field
     * @param password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Updates the name field
     * @param name
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
     * @param emailAddress
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
     * @param role
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

