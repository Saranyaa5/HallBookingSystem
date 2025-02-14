package com.hallbooking;
import java.util.Scanner;

public class Admin extends User {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";
    private String[] halls = new String[10];
    private int hallCount = 0;

    public Admin(String name, String userId, String email, String password) {
        super(name, userId, email, password);
    }

    public void adminMenu(Scanner sc) {
        System.out.println("Enter Username: ");
        String username = sc.nextLine();
        System.out.println("Enter Password: ");
        String password = sc.nextLine();

        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            System.out.println("Login Successful! Welcome, Admin.");
            adminFunctionality(sc);
        } else {
            System.out.println("Incorrect Username or Password.");
        }
    }

    private void adminFunctionality(Scanner sc) {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1 Add a New Hall");
            System.out.println("2️ Delete an Existing Hall");
            System.out.println("3️ Reserve a Hall for a Customer");
            System.out.println("4️ View Reports");
            System.out.println("5️ Logout");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addNewHall(sc);
                    break;
                case 2:
                    deleteExistingHall(sc);
                    break;
                case 3:
                    reserveHall(sc);
                    break;
                case 4:
                    viewReports();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private void addNewHall(Scanner sc) {
        if (hallCount >= halls.length) {
            System.out.println("Cannot add more halls.Limit reached.");
            return;
        }
        System.out.println("Enter hall name:");
        String hallName = sc.nextLine();
        halls[hallCount++] = hallName;
        System.out.println("Hall added successfully!");
    }

    private void deleteExistingHall(Scanner sc) {
        if (hallCount == 0) {
            System.out.println("No halls available to delete.");
            return;
        }
        System.out.println("Enter hall name to delete:");
        String hallName = sc.nextLine();
        boolean found = false;

        for (int i = 0; i < hallCount; i++) {
            if (halls[i].equalsIgnoreCase(hallName)) {
                halls[i] = halls[hallCount - 1];
                halls[hallCount - 1] = null;
                hallCount--;
                found = true;
                System.out.println("Hall deleted successfully!");
                break;
            }
        }
        if (!found) {
            System.out.println("Hall not found!");
        }
    }

    private void reserveHall(Scanner sc) {
        if (hallCount == 0) {
            System.out.println("No halls available to reserve.");
            return;
        }
        System.out.println("Available Halls:");
        for (int i = 0; i < hallCount; i++) {
            System.out.println((i + 1) + ". " + halls[i]);
        }
        System.out.println("Enter hall number to reserve:");
        int hallIndex = sc.nextInt();
        sc.nextLine();

        if (hallIndex < 1 || hallIndex > hallCount) {
            System.out.println("Invalid hall number!");
            return;
        }
        System.out.println("Hall " + halls[hallIndex - 1] + " reserved successfully!");
    }

    private void viewReports() {
        if (hallCount == 0) {
            System.out.println("No halls available.");
            return;
        }
        System.out.println("Halls List:");
        for (int i = 0; i < hallCount; i++) {
            System.out.println((i + 1) + ". " + halls[i]);
        }
    }
}
