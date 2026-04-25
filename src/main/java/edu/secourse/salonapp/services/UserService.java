package edu.secourse.salonapp.services;

import edu.secourse.salonapp.models.User;

import java.util.HashMap;
import java.util.Map;

public class UserService
{
     // CRUD (Create Read Update Delete)

    /*
        Steph I made a hashmap to store the Users, that way the key can be the unique account number.
        Feel free to change it if you feel it doesn't make sense, and we should stick to an array.
        - Ven
     */

    private Map<Integer, User> users = new HashMap<Integer, User>();
    private int userId = 0;


}
