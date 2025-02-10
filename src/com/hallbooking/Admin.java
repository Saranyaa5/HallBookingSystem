package com.hallbooking;
import java.util.Scanner;

public class Admin extends User {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

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
        } else {
            System.out.println("Incorrect Username or Password.");
        }
    }
}
