package com.hallbooking;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner sc;
        File file = new File(System.getProperty("user.dir") + "/flow1.txt");

        if (file.exists()) {
            try {
                sc = new Scanner(file);
            } catch (FileNotFoundException e) {
                System.out.println("File not found. Switching to manual input.");
                sc = new Scanner(System.in);
            }
        } else {
            System.out.println("flow1.txt not found. Using manual input.");
            sc = new Scanner(System.in);
        }

        try {
            while (true) {
                System.out.println("Enter the application as:\n1. Customer\n2. Admin\n3. Exit the application");

                int userType;
                try {
                    userType = sc.nextInt();
                    sc.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a valid number (1, 2, or 3).");
                    sc.nextLine();
                    continue;
                }

                switch (userType) {
                    case 1:
                        try {
                            Customer customer = new Customer("", "", "", "");
                            customer.customerMenu(sc);
                        } catch (Exception e) {
                            System.out.println("Error in Customer menu: " + e.getMessage());
                        }
                        break;
                    case 2:
                        try {
                            Admin admin = new Admin("", "", "", "");
                            admin.adminMenu(sc);
                        } catch (Exception e) {
                            System.out.println("Error in Admin menu: " + e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.println("Exiting system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice! Please enter 1, 2, or 3.");
                }

                if (userType == 3) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
