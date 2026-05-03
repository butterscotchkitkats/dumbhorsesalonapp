package edu.secourse.salonapp.models;

import org.junit.jupiter.api.BeforeEach;
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
    void getLoyaltyPoints() {
        customer.addLoyaltyPoints(1234);
        assertEquals(1234, customer.getLoyaltyPoints());
    }

    @Test
    void addLoyaltyPoints() {
        assertEquals(0, customer.getLoyaltyPoints());
    }

    @Test
    void removeLoyaltyPoints() {
        customer.addLoyaltyPoints(2000);
        customer.removeLoyaltyPoints(1000);
        assertEquals(1000, customer.getLoyaltyPoints());
    }
}