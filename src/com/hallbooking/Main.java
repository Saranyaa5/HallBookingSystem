package com.hallbooking;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
            System.out.println("Enter the application as:\n1. Customer\n2. Admin\n3. Exit");
            int userType = sc.nextInt();
            sc.nextLine(); 

            switch (userType) {
                case 1:
                    Customer customer = new Customer("", "", "", "");
                    customer.customerMenu(sc);
                    break;
                case 2:
                    Admin admin = new Admin("", "", "", "");
                    admin.adminMenu(sc);
                    break;
                case 3:
                    System.out.println("Exiting system. Goodbye!");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please enter 1, 2, or 3.");
            }
    }
}
