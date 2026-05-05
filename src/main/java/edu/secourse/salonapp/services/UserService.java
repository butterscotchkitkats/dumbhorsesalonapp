package edu.secourse.salonapp.services;

import edu.secourse.salonapp.models.Customer;
import edu.secourse.salonapp.models.Stylist;
import edu.secourse.salonapp.models.User;

import java.util.ArrayList;
import java.util.Random;

/**
 * Provides users with the functionality to add, view, update, or delete their profile
 *
 * <p>This class handles the business logic for managing users in the system,
 * including creating Customers and Stylists, retrieving user information,
 * updating user details, and removing users from the system</p>
 *
 * @author Dylan DelConte
 * @version 1.0
 */
public class UserService {

    private ArrayList<User> users = new ArrayList<User>();

    /**
     * Creates a user object of the appropriate subtype based on role
     * and adds them to the list of users
     * @param userName this is the user's display name
     * @param password this is the user's password
     * @param name this is the user's full name
     * @param emailAddress this is the user's email address
     * @param role this is the user's role -- either Customer or Stylist
     */
    public void createUser(String userName, String password, String name, String emailAddress, String role) {
        int accNum = generateUserID();
        User user;

        if (role.equals("Customer")) {
            user = new Customer(accNum, userName, password, name, emailAddress);
        } else if (role.equals("Stylist")) {
            user = new Stylist(accNum, userName, password, name, emailAddress);
        } else {
            System.out.println("Invalid role: " + role + ". User not created.");
            return;
        }

        users.add(user);
    }

    /**
     * Generates a unique account number for a new user
     * @return id this is the unique number assigned to the user
     */
    private int generateUserID() {
        Random r = new Random();
        int id;
        boolean duplicate;
        do {
            id = 100000 + r.nextInt(900000);
            duplicate = false;
            for (User user : users) {
                if (user.getAccountNumber() == id) {
                    duplicate = true;
                    break;
                }
            }
        } while (duplicate);

        return id;
    }

    /**
     * Adds a user object directly to the list of users
     * @param u this is the User object to be added
     */
    public void addUser(User u) {
        users.add(u);
    }

    /**
     * Retrieves a user from the list by their account number
     * @param u this is the User object to look up
     * @return the matching User object, or null if not found
     */
    public User readUser(User u) {
        return findByAccountNumber(u.getAccountNumber());
    }

    /**
     * Finds a user in the list by their account number
     * @param accountNumber this is the unique identifier for the user
     * @return the matching User object, or null if not found
     */
    private User findByAccountNumber(int accountNumber) {
        for (User user : users) {
            if (user.getAccountNumber() == accountNumber) {
                return user;
            }
        }
        return null;
    }

    /**
     * Removes a user from the list of users
     * @param u this is the User object to be removed
     */
    public void deleteUser(User u) {
        User found = findByAccountNumber(u.getAccountNumber());
        if (found != null) {
            users.remove(found);
        } else {
            System.out.println("User not found.");
        }
    }

    /**
     * Retrieves the full list of users in the system
     * @return ArrayList of all User objects
     */
    public ArrayList<User> getAllUsers() {
        return users;
    }

    /**
     * Retrieves all users who have the role of Stylist
     * @return ArrayList of Stylist objects
     */
    public ArrayList<Stylist> getAllStylists() {
        ArrayList<Stylist> stylists = new ArrayList<>();
        for (User user : users) {
            if (user.getRole().equals("Stylist")) {
                stylists.add((Stylist) user);
            }
        }
        return stylists;
    }
}
