package edu.secourse.salonapp.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(123456, "vcor2", "password123", "Ven Corwell", "vc98@gmail.com", "Customer");
    }

    @Test
    @DisplayName("getAccountNumber(): retrieves user account number")
    void getAccountNumber() {
        assertEquals(123456, user.getAccountNumber());
    }

    @Test
    @DisplayName("getUserName(): retrieves the User's username")
    void getUserName() {
        assertEquals("vcor2", user.getUserName());
    }

    @Test
    @DisplayName("setUserName(): establish a User's username")
    void setUserName() {
        user.setUserName("Ven1998");
        assertEquals("Ven1998", user.getUserName());
    }

    @Test
    @DisplayName("setPassword(): establish a User's password")
    void setPassword() {
        assertDoesNotThrow(() -> user.setPassword("totallyvalidpassword456"));
    }

    @Test
    @DisplayName("setName(): establish a User's full name")
    void setName() {
        user.setName("Matthew Rada");
        assertEquals("Matthew Rada", user.getName());
    }

    @Test
    @DisplayName("getName(): retrieve a User's full name")
    void getName() {
        assertEquals("Ven Corwell", user.getName());
    }

    @Test
    @DisplayName("setEmailAddress(): establish a User's email address")
    void setEmailAddress() {
        user.setEmailAddress("ilovecats@msn.com");
        assertEquals("ilovecats@msn.com", user.getEmailAddress());
    }

    @Test
    @DisplayName("getEmailAddress(): retrieve a User's email address")
    void getEmailAddress() {
        assertEquals("vc98@gmail.com", user.getEmailAddress());
    }

    @Test
    @DisplayName("setRole(): establish a User's role")
    void setRole() {
        user.setRole("Stylist");
        assertEquals("Stylist", user.getRole());
    }

    @Test
    @DisplayName("getRole(): retrieve a User's role")
    void getRole() {
        assertEquals("Customer", user.getRole());
    }
}