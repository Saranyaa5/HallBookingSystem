package com.hallbooking;
import java.util.*;

public class Customer extends User {
    private static HashMap<String, Customer> customerData = new HashMap<>();

    public Customer(String name, String userId, String email, String password) {
        super(name, userId, email, password);
    }

    public void customerMenu(Scanner sc) {
        while (true) {
            System.out.println("1. Register\n2. Login\n3. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    register(sc);
                    break;
                case 2:
                    login(sc);
                    break;
                case 3:
                    System.out.println("Exiting customer section...");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private void register(Scanner sc) {
        System.out.println("Enter Name: ");
        String name = sc.nextLine();
        System.out.println("Enter User ID: ");
        String userId = sc.nextLine();
        System.out.println("Enter Email: ");
        String email = sc.nextLine();

        if (customerData.containsKey(email)) {
            System.out.println("Email already registered. Please login.");
            login(sc);
            return; 
        }

        System.out.println("Enter Password: ");
        String password = sc.nextLine();

        Customer newCustomer = new Customer(name, userId, email, password);
        customerData.put(email, newCustomer);
        System.out.println("Registration successful! Please login.");
        login(sc);
    }

    private void login(Scanner sc) {
        while (true) {
            System.out.println("Enter Email: ");
            String email = sc.nextLine();
            System.out.println("Enter Password: ");
            String password = sc.nextLine();

            Customer existingCustomer = customerData.get(email);
            if (existingCustomer != null && existingCustomer.authenticate(password)) {
                System.out.println("Login Successful! Welcome, " + existingCustomer.name);
                customerFunctionality(sc);
                return; 
            } else {
                System.out.println("Incorrect credentials. Try again.");
                System.out.println("1. Register\n2. Try Login Again");
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    register(sc);
                    return; 
                }
            }
        }
    }

    public void customerFunctionality(Scanner sc) {
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Search Hall");
            System.out.println("2. Book Hall using Hall ID");
            System.out.println("3. Cancel Booked Hall");
            System.out.println("4. Make Payment");
            System.out.println("5. Logout");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Searching halls...");
                    search(sc);
                    break;
                case 2:
                    System.out.println("Booking a hall...");
                    
                    break;
                case 3:
                    System.out.println("Cancelling a booking...");
                    
                    break;
                case 4:
                    System.out.println("Processing payment...");
                  
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private void search(Scanner sc) {
        HallSearch search = new HallSearch();

        while (true) {
            System.out.println("\nSearch Options:");
            System.out.println("1. Search by Date");
            System.out.println("2. Search by Hall ID");
            System.out.println("3. Search by Name");
            System.out.println("4. Search by Capacity");
            System.out.println("5. Search by Location");
            System.out.println("6. Search by Amenities");
            System.out.println("7. Show All Halls");
            System.out.println("8. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> search.searchByDate(sc);
                case 2 -> search.searchById(sc);
                case 3 -> search.searchByName(sc);
                case 4 -> search.searchByCapacity(sc);
                case 5 -> search.searchByLocation(sc);
                case 6 -> search.searchByAmenities(sc);
                case 7 -> search.showAllHalls();
                case 8 -> { return; }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }
	
}
