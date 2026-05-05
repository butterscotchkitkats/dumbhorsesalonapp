package edu.secourse.salonapp.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest{

    private Customer customer;
    @BeforeEach
    void setUp(){
        customer = new Customer(123456, "vcor1", "passcode11", "Ven Corwell",
                "vc98@hotmail.com");
    }

    @Test
    @DisplayName("Customer role is automatically set to Customer")
    void customerRoleIsCustomer()
    {
        assertEquals("Customer", customer.getRole());
    }

    @Test
    @DisplayName("getLoyaltyPoints() should be zero when the customer is created")
    void getLoyaltyPointsAtCustomerCreation() {
        assertEquals(0, customer.getLoyaltyPoints());
    }

    @Test
    @DisplayName("addLoyaltyPoints(): increases customer's loyalty points")
    void addLoyaltyPoints() {
        customer.addLoyaltyPoints(1000);
        assertEquals(1000, customer.getLoyaltyPoints());
    }

    @Test
    @DisplayName("addLoyaltyPoints(): can add points from multiple calls")
    void addLoyaltyPointsFromMultipleCalls() {
        customer.addLoyaltyPoints(1000);
        customer.addLoyaltyPoints(500);
        customer.addLoyaltyPoints(1500);
        assertEquals(3000, customer.getLoyaltyPoints());
    }


    @Test
    @DisplayName("removeLoyaltyPoints(): decreases points by a set amount")
    void removeLoyaltyPoints() {
        customer.addLoyaltyPoints(2000);
        customer.removeLoyaltyPoints(1000);
        assertEquals(1000, customer.getLoyaltyPoints());
    }

    @Test
    @DisplayName("removeLoyaltyPoints(): when removing all points the balance should be zero")
    void removeLoyaltyPointsWhenRemovingAllPoints() {
        customer.addLoyaltyPoints(1000);
        customer.removeLoyaltyPoints(1000);
        assertEquals(0, customer.getLoyaltyPoints());
    }
}