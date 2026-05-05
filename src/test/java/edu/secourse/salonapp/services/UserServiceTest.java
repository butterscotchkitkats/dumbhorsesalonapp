package edu.secourse.salonapp.services;

import edu.secourse.salonapp.models.Customer;
import edu.secourse.salonapp.models.Stylist;
import edu.secourse.salonapp.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for UserService
 * Tests cover creating, reading, deleting, and retrieving users
 * from the in-memory user list
 */
public class UserServiceTest {

    private UserService userService;

    /**
     * Initializes a fresh UserService before each test
     */
    @BeforeEach
    public void setUp() {
        userService = new UserService();
    }
    /**
     * Tests that creating a Customer adds a Customer to the user list
     */
    @Test
    public void testCreateCustomer() {
        userService.createUser("jdoe", "pass123", "John Doe", "jdoe@email.com", "Customer");
        ArrayList<User> users = userService.getAllUsers();
        assertEquals(1, users.size());
        assertInstanceOf(Customer.class, users.get(0));
        assertEquals("John Doe", users.get(0).getName());
        assertEquals("Customer", users.get(0).getRole());
    }

    /**
     * Tests that creating a Stylist adds a Stylist to the user list
     */
    @Test
    public void testCreateStylist() {
        userService.createUser("jsmith", "pass456", "Jane Smith", "jsmith@email.com", "Stylist");
        ArrayList<User> users = userService.getAllUsers();
        assertEquals(1, users.size());
        assertInstanceOf(Stylist.class, users.get(0));
        assertEquals("Jane Smith", users.get(0).getName());
        assertEquals("Stylist", users.get(0).getRole());
    }

    /**
     * Tests that creating a user with an invalid role does not add anyone to the list
     */
    @Test
    public void testCreateUserInvalidRole() {
        userService.createUser("admin1", "pass789", "Admin User", "admin@email.com", "Admin");

        ArrayList<User> users = userService.getAllUsers();
        assertEquals(0, users.size());
    }

    /**
     * Tests that multiple users can be created and all are stored
     */
    @Test
    public void testCreateMultipleUsers() {
        userService.createUser("user1", "pass1", "Alice", "alice@email.com", "Customer");
        userService.createUser("user2", "pass2", "Bob", "bob@email.com", "Stylist");
        userService.createUser("user3", "pass3", "Carol", "carol@email.com", "Customer");

        assertEquals(3, userService.getAllUsers().size());
    }

    /**
     * Tests that each created user is assigned a unique account number
     */
    @Test
    public void testUniqueAccountNumbers() {
        userService.createUser("user1", "pass1", "Alice", "alice@email.com", "Customer");
        userService.createUser("user2", "pass2", "Bob", "bob@email.com", "Customer");
        ArrayList<User> users = userService.getAllUsers();
        assertNotEquals(users.get(0).getAccountNumber(), users.get(1).getAccountNumber());
    }
    /**
     * Tests that readUser returns the correct user by account number
     */
    @Test
    public void testReadUser() {
        userService.createUser("jdoe", "pass123", "John Doe", "jdoe@email.com", "Customer");
        User created = userService.getAllUsers().get(0);
        User found = userService.readUser(created);
        assertNotNull(found);
        assertEquals(created.getAccountNumber(), found.getAccountNumber());
    }
    /**
     * Tests that readUser returns null when the user does not exist
     */
    @Test
    public void testReadUserNotFound() {
        User ghost = new Customer(999999, "ghost", "pass", "Ghost User", "ghost@email.com");
        User found = userService.readUser(ghost);
        assertNull(found);
    }
    /**
     * Tests that addUser directly adds a pre-built User object to the list
     */
    @Test
    public void testAddUser() {
        Customer customer = new Customer(123456, "jdoe", "pass", "John Doe", "jdoe@email.com");
        userService.addUser(customer);
        assertEquals(1, userService.getAllUsers().size());
        assertEquals("John Doe", userService.getAllUsers().get(0).getName());
    }
    /**
     * Tests that deleteUser removes the correct user from the list
     */
    @Test
    public void testDeleteUser() {
        userService.createUser("jdoe", "pass123", "John Doe", "jdoe@email.com", "Customer");
        User created = userService.getAllUsers().get(0);

        userService.deleteUser(created);
        assertEquals(0, userService.getAllUsers().size());
    }

    /**
     * Tests that deleteUser does not throw when the user is not found
     */
    @Test
    public void testDeleteUserNotFound() {
        User ghost = new Customer(999999, "ghost", "pass", "Ghost User", "ghost@email.com");
        assertDoesNotThrow(() -> userService.deleteUser(ghost));
    }

    /**
     * Tests that only the targeted user is removed when multiple users exist
     */
    @Test
    public void testDeleteOneOfMultipleUsers() {
        userService.createUser("user1", "pass1", "Alice", "alice@email.com", "Customer");
        userService.createUser("user2", "pass2", "Bob", "bob@email.com", "Customer");

        User alice = userService.getAllUsers().get(0);
        userService.deleteUser(alice);

        ArrayList<User> remaining = userService.getAllUsers();
        assertEquals(1, remaining.size());
        assertEquals("Bob", remaining.get(0).getName());
    }
    /**
     * Tests that getAllUsers returns an empty list when no users exist
     */
    @Test
    public void testGetAllUsersEmpty() {
        assertTrue(userService.getAllUsers().isEmpty());
    }

    /**
     * Tests that getAllUsers returns all users in the system
     */
    @Test
    public void testGetAllUsersReturnsAll() {
        userService.createUser("user1", "pass1", "Alice", "alice@email.com", "Customer");
        userService.createUser("user2", "pass2", "Bob", "bob@email.com", "Stylist");

        assertEquals(2, userService.getAllUsers().size());
    }
    /**
     * Tests that getAllStylists returns only Stylist users
     */
    @Test
    public void testGetAllStylistsOnlyReturnStylists() {
        userService.createUser("user1", "pass1", "Alice", "alice@email.com", "Customer");
        userService.createUser("user2", "pass2", "Bob", "bob@email.com", "Stylist");
        userService.createUser("user3", "pass3", "Carol", "carol@email.com", "Stylist");
        ArrayList<Stylist> stylists = userService.getAllStylists();
        assertEquals(2, stylists.size());
        for (Stylist s : stylists) {
            assertEquals("Stylist", s.getRole());
        }
    }
    /**
     * Tests that getAllStylists returns an empty list when no stylists exist
     */
    @Test
    public void testGetAllStylistsEmpty() {
        userService.createUser("user1", "pass1", "Alice", "alice@email.com", "Customer");

        assertTrue(userService.getAllStylists().isEmpty());
    }
}