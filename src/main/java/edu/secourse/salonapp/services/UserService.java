package edu.secourse.salonapp.services;

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
        Random r = new Random();
        int accNum = 100000 + r.nextInt(900000) ;
        User user = new User(accNum, userName, password, name, emailAddress, role);
        addUser(user);
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

    public Object readUser(User u){
        return(users.get(users.indexOf(u.getAccountNumber())));
    }

    /**
     * Adds a new user object to the list of users
     * @param newUser this is the new User object
     * @param oldUser this is the old User object
     */

    public void updateUser(User newUser, User oldUser){
        users.remove(users.get(users.indexOf(oldUser.getAccountNumber())));
        users.add(newUser);
    }

    /**
     * Removes a user from the list of users
     * @param u this is a User object
     */

    public void deleteUser(User u){
        users.remove(users.get(users.indexOf(u.getAccountNumber())));
    }
    ArrayList<Object> users = new ArrayList<Object>();
    // Should we use a hashmap instead and the key value can be the user account number?
    // Probably but that's a lot of work that I don't want to do :(( -Steph
}
