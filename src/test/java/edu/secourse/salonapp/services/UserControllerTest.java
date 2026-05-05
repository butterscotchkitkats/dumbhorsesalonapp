package edu.secourse.salonapp.services;
import edu.secourse.salonapp.models.Customer;
import edu.secourse.salonapp.models.Stylist;
import edu.secourse.salonapp.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for UserController
 *
 * Tests simulate console input using ByteArrayInputStream and capture
 * console output using ByteArrayOutputStream to verify correct behavior
 */
public class UserControllerTest {

    private UserService userService;

    /**
     * Initializes a fresh UserService before each test
     */
    @BeforeEach
    public void setUp() {
        userService = new UserService();
    }

    /**
     * Simulates console input and returns a UserController that will read from it
     * @param input this is the simulated user input as a single string with newlines
     * @return a UserController wired to read the simulated input
     */
    private UserController buildController(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        return new UserController(userService);
    }

    /**
     * Captures console output during a runnable block
     * @param action this is the code block to run while capturing output
     * @return the captured output as a String
     */
    private String captureOutput(Runnable action) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        action.run();
        System.setOut(System.out);
        return out.toString();
    }
    /**
     * Tests that selecting Create User with role Customer adds a Customer to the system
     */
    @Test
    public void testCreateCustomerThroughMenu() {
        // Selects: 1 (Create User), enters details, picks Customer, then 5 (Back)
        String input = "1\njdoe\npass123\nJohn Doe\njdoe@email.com\n1\n5\n";
        UserController controller = buildController(input);
        captureOutput(controller::showMenu);
        assertEquals(1, userService.getAllUsers().size());
        assertInstanceOf(Customer.class, userService.getAllUsers().get(0));
        assertEquals("John Doe", userService.getAllUsers().get(0).getName());
    }

    /**
     * Tests that selecting Create User with role Stylist adds a Stylist to the system
     */
    @Test
    public void testCreateStylistThroughMenu() {
        // Selects: 1 (Create User), enters details, picks Stylist, then 5 (Back)
        String input = "1\njsmith\npass456\nJane Smith\njsmith@email.com\n2\n5\n";
        UserController controller = buildController(input);
        captureOutput(controller::showMenu);
        assertEquals(1, userService.getAllUsers().size());
        assertInstanceOf(Stylist.class, userService.getAllUsers().get(0));
        assertEquals("Jane Smith", userService.getAllUsers().get(0).getName());
    }

    /**
     * Tests that entering an invalid role during Create User does not add a user
     */
    @Test
    public void testCreateUserInvalidRole() {
        // Selects: 1 (Create User), enters details, picks invalid role 9, then 5 (Back)
        String input = "1\nadmin1\npass789\nAdmin User\nadmin@email.com\n9\n5\n";
        UserController controller = buildController(input);
        captureOutput(controller::showMenu);
        assertEquals(0, userService.getAllUsers().size());
    }
    /**
     * Tests that viewing a user prints their details to the console
     */
    @Test
    public void testViewUserPrintsDetails() {
        userService.createUser("jdoe", "pass123", "John Doe", "jdoe@email.com", "Customer");
        String input = "2\n1\n5\n";
        UserController controller = buildController(input);
        String output = captureOutput(controller::showMenu);
        assertTrue(output.contains("John Doe"));
        assertTrue(output.contains("jdoe@email.com"));
        assertTrue(output.contains("Customer"));
    }
    /**
     * Tests that viewing a user when no users exist prints an appropriate message
     */
    @Test
    public void testViewUserNoUsers() {
        // Selects: 2 (View User), then 5 (Back)
        String input = "2\n5\n";
        UserController controller = buildController(input);
        String output = captureOutput(controller::showMenu);

        assertTrue(output.contains("No users found"));
    }
    /**
     * Tests that updating a user's name changes it in the system
     */
    @Test
    public void testUpdateUserName() {
        userService.createUser("jdoe", "pass123", "John Doe", "jdoe@email.com", "Customer");
        String input = "3\n1\n\n\nJohnathan Doe\n\n5\n";
        UserController controller = buildController(input);
        captureOutput(controller::showMenu);
        assertEquals("Johnathan Doe", userService.getAllUsers().get(0).getName());
    }

    /**
     * Tests that pressing Enter with no input keeps the current username
     */
    @Test
    public void testUpdateUserKeepsCurrentValues() {
        userService.createUser("jdoe", "pass123", "John Doe", "jdoe@email.com", "Customer");
        String input = "3\n1\n\n\n\n\n5\n";
        UserController controller = buildController(input);
        captureOutput(controller::showMenu);
        User user = userService.getAllUsers().get(0);
        assertEquals("jdoe", user.getUserName());
        assertEquals("John Doe", user.getName());
        assertEquals("jdoe@email.com", user.getEmailAddress());
    }

    /**
     * Tests that updating a user's email changes it in the system
     */
    @Test
    public void testUpdateUserEmail() {
        userService.createUser("jdoe", "pass123", "John Doe", "jdoe@email.com", "Customer");
        String input = "3\n1\n\n\n\nnewemail@email.com\n5\n";
        UserController controller = buildController(input);
        captureOutput(controller::showMenu);
        assertEquals("newemail@email.com", userService.getAllUsers().get(0).getEmailAddress());
    }

    /**
     * Tests that confirming deletion removes the user from the system
     */
    @Test
    public void testDeleteUserConfirmed() {
        userService.createUser("jdoe", "pass123", "John Doe", "jdoe@email.com", "Customer");
        String input = "4\n1\nyes\n5\n";
        UserController controller = buildController(input);
        captureOutput(controller::showMenu);
        assertEquals(0, userService.getAllUsers().size());
    }

    /**
     * Tests that cancelling deletion keeps the user in the system
     */
    @Test
    public void testDeleteUserCancelled() {
        userService.createUser("jdoe", "pass123", "John Doe", "jdoe@email.com", "Customer");
        String input = "4\n1\nno\n5\n";
        UserController controller = buildController(input);
        captureOutput(controller::showMenu);
        assertEquals(1, userService.getAllUsers().size());
    }

    /**
     * Tests that deleting when no users exist prints an appropriate message
     */
    @Test
    public void testDeleteUserNoUsers() {
        String input = "4\n5\n";
        UserController controller = buildController(input);
        String output = captureOutput(controller::showMenu);
        assertTrue(output.contains("No users found"));
    }
    /**
     * Tests that entering an invalid menu option prints an error message
     */
    @Test
    public void testInvalidMenuOption() {
        // Selects: 9 (invalid), then 5 (Back)
        String input = "9\n5\n";
        UserController controller = buildController(input);
        String output = captureOutput(controller::showMenu);
        assertTrue(output.contains("Invalid option"));
    }

    /**
     * Tests that selecting Back (5) exits the menu loop cleanly
     */
    @Test
    public void testBackExitsMenu() {
        String input = "5\n";
        UserController controller = buildController(input);
        assertDoesNotThrow(() -> captureOutput(controller::showMenu));
    }
}