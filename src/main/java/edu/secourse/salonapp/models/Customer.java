package edu.secourse.salonapp.models;

/**
 * Represents a user who is of type customer
 *
 * <p>This class is a child of the User class. It represents and provides specific functionality for Users with the role
 * of Customer</p>
 *
 * @author Ven Corwell
 * @version 1.0
 */
public class Customer extends User {
    private int loyaltyPoints;

    /**
     * Creates Customer Object
     * @param userName this is a unique name the user can be identified as in the system
     * @param password this is the user created log-in code
     * @param name this is the customer's full name
     * @param emailAddress this is the email address that a user wants to be contacted by
     */
    public Customer(String userName, String password, String name, String emailAddress)
    {
        super(userName, password, name, emailAddress, "Customer");
        this.loyaltyPoints = 0; // A new customer should start out with no points
    }

    /**
     * Retrieves a customer's loyalty points
     * @return loyaltyPoints
     */
    public int getLoyaltyPoints()
    {
        return loyaltyPoints;
    }

    /**
     * Adds additional points to the customer's total points
     * @param loyaltyPoints this is how many new points a customer has earned
     */
    public void addLoyaltyPoints(int loyaltyPoints)
    {
        this.loyaltyPoints += loyaltyPoints;
    }

    /**
     * Subtracts used points from the customer's total points
     * @param loyaltyPoints this is how many points the customer has used
     */
    public void removeLoyaltyPoints(int loyaltyPoints)
    {
        this.loyaltyPoints -= loyaltyPoints;
    }
}
