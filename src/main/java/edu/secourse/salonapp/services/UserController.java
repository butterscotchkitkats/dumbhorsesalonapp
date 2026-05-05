package edu.secourse.salonapp.services;

import edu.secourse.salonapp.models.User;

import java.util.Scanner;

/**
 * Handles user input involving user information
 *
 * <p>This class provides console-based interaction for creating, updating,
 * viewing, and deleting users in the system</p>
 *
 * @author Dylan DelConte
 * @version 1.0
 */
public class UserController {

    private UserService userService;
    private Scanner scanner;

    /**
     * Creates a UserController with the provided UserService
     * @param userService this is the service that handles user business logic
     */
    public UserController(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the main user management menu and routes to the appropriate action
     * Loops until the user selects the Back option
     */
    public void showMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n*---User Menu---*\n");
            System.out.println("1. Create User");
            System.out.println("2. View User");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Back");
            System.out.print("Enter the number of the desired option: ");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    createUser();
                    break;
                case "2":
                    viewUser();
                    break;
                case "3":
                    updateUser();
                    break;
                case "4":
                    deleteUser();
                    break;
                case "5":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    /**
     * Prompts the admin to enter details for a new user and creates them
     * based on the selected role (Customer or Stylist)
     */
    private void createUser() {
        System.out.println("\n*---Create User---*\n");

        System.out.print("Enter username: ");
        String userName = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter full name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email address: ");
        String emailAddress = scanner.nextLine();

        System.out.println("Select role:");
        System.out.println("1. Customer");
        System.out.println("2. Stylist");
        System.out.print("Enter the number of the desired option: ");
        String roleOption = scanner.nextLine();

        switch (roleOption) {
            case "1":
                userService.createUser(userName, password, name, emailAddress, "Customer");
                System.out.println("Customer created successfully.");
                break;
            case "2":
                userService.createUser(userName, password, name, emailAddress, "Stylist");
                System.out.println("Stylist created successfully.");
                break;
            default:
                System.out.println("Invalid role selection. User not created.");
        }
    }

    /**
     * Prompts the admin to select a user and displays their details
     * Prints account number, username, full name, email, and role to the console
     */
    private void viewUser() {
        User user = selectUser();
        if (user == null) return;

        System.out.println("\n*---User Details---*\n");
        System.out.println("Account Number : " + user.getAccountNumber());
        System.out.println("Username       : " + user.getUserName());
        System.out.println("Full Name      : " + user.getName());
        System.out.println("Email          : " + user.getEmailAddress());
        System.out.println("Role           : " + user.getRole());
    }

    /**
     * Prompts the admin to select a user and update their details
     * Fields left blank will retain their current values
     */
    private void updateUser() {
        User user = selectUser();
        if (user == null) return;

        System.out.println("\n*---Update User---*\n");
        System.out.println("Press Enter to keep the current value.\n");

        System.out.print("New username (current: " + user.getUserName() + "): ");
        String userName = scanner.nextLine();
        if (!userName.isBlank()) user.setUserName(userName);

        System.out.print("New password: ");
        String password = scanner.nextLine();
        if (!password.isBlank()) user.setPassword(password);

        System.out.print("New full name (current: " + user.getName() + "): ");
        String name = scanner.nextLine();
        if (!name.isBlank()) user.setName(name);

        System.out.print("New email address (current: " + user.getEmailAddress() + "): ");
        String email = scanner.nextLine();
        if (!email.isBlank()) user.setEmailAddress(email);

        System.out.println("User updated successfully.");
    }

    /**
     * Prompts the admin to select a user and delete them
     * Requires confirmation before permanently removing the user
     */
    private void deleteUser() {
        User user = selectUser();
        if (user == null) return;

        System.out.print("Are you sure you want to delete " + user.getName() + "? (yes/no): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            userService.deleteUser(user);
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    /**
     * Displays a list of all users and prompts the admin to select one
     * @return the selected User, or null if selection is invalid
     */
    private User selectUser() {
        var users = userService.getAllUsers();

        if (users.isEmpty()) {
            System.out.println("No users found in the system.");
            return null;
        }

        System.out.println("\n*---Select a User---*\n");
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            System.out.println((i + 1) + ". " + u.getName() + " (" + u.getRole() + ")");
        }
        System.out.print("Enter user number: ");

        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index < 0 || index >= users.size()) {
                System.out.println("Invalid selection.");
                return null;
            }
            return users.get(index);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return null;
        }
    }
}
