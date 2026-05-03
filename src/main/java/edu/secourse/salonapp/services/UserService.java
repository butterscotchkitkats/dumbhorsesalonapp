package edu.secourse.salonapp.services;

import edu.secourse.salonapp.components.Appointment;
import edu.secourse.salonapp.models.Stylist;
import edu.secourse.salonapp.models.User;

import java.util.ArrayList;

import java.util.Random;

/**
 * Provides users with the functionality to add, view, update, or delete their profile
 *
 * <p>This class provides basic functionality and stores data on appointments created</p>
 *
 * @author Stephanie Parma
 * @version 1.0
 */

public class UserService {

    /**
     * Creates a user object
     * @param userName this is the user's display name
     * @param password this is the user's password
     * @param name this is the user's name
     * @param emailAddress this is the user's email address
     * @param role this is the user's role -- user
     */

    public void createUser(String userName, String password, String name, String emailAddress, String role){
        int accNum = generateUserID();
        User user = new User(accNum, userName, password, name, emailAddress, role);
        addUser(user);
    }

    private int generateUserID()
    {
        Random r = new Random();
        int id;
        boolean duplicate;
        do {
            id = 100000 + r.nextInt(900000);
            duplicate = false;
            for (User user : users)
            {
                if (user.getAccountNumber() == id) {
                    duplicate = true;
                    break;
                }
            }
        }while(duplicate);

        return id;
    }

    /**
     * Adds a new user to the list of users
     * @param u this is a User object
     */

    public void addUser(User u){
        users.add(u);
    }

    /**
     * Allows the user to view the attributes of a user object from the list of users
     * @param u this is a User object
     * @return User object
     */

    public User readUser(User u){
        return findByAccountNumber(u.getAccountNumber());
    }


    private User findByAccountNumber(int accountNumber) {
        for (User user : users) {
            if (user.getAccountNumber() == accountNumber) {
                return user;
            }
        }
        return null;
    }

    /**
     * Adds a new user object to the list of users
     * @param newUser this is the new User object
     * @param oldUser this is the old User object
     */

    public void updateUser(User newUser, User oldUser){
        users.remove(users.get(users.indexOf(oldUser)));
        users.add(newUser);
    }

    /**
     * Removes a user from the list of users
     * @param u this is a User object
     */

    public void deleteUser(User u){
        users.remove(users.get(users.indexOf(u)));
    }
    ArrayList<User> users = new ArrayList<User>();


    /**
     * Retrieves all users who are stylists
     * @return list of stylists
     */
    public ArrayList<Stylist> getAllStylists(){
        ArrayList<Stylist> stylists = new ArrayList<>();
        for(User user : users){
            if(user.getRole().equals("Stylist"))
            {
                stylists.add((Stylist) user);
            }
        }

        return stylists;
    }
}
