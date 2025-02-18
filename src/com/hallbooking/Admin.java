package com.hallbooking;
import java.util.*;
public class Admin extends User {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    private static final Map<String, List<String>> reservations = new HashMap<>();

    public Admin(String name, String userId, String email, String password) {
        super(name, userId, email, password);
    }

    public void adminMenu(Scanner sc) {
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            System.out.println("Login Successful.");
            adminFunctionality(sc);
            
        } 
        else {
            System.out.println("Incorrect Username or Password.");
        }
    }
    
    private void adminFunctionality(Scanner sc) {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add a New Hall");
            System.out.println("2. Delete an Existing Hall");
            System.out.println("3. Reserve a Hall for a Customer");
            System.out.println("4. View Reports");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");

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
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void addNewHall(Scanner sc) {
        System.out.print("Enter Hall ID: ");
        String hallId = sc.nextLine();

        System.out.print("Enter Hall Name: ");
        String hallName = sc.nextLine();

        System.out.print("Enter Capacity: ");
        int capacity = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Amenities (comma-separated): ");
        String[] amenitiesArr = sc.nextLine().split(",");
        Set<String> amenities = new HashSet<>(Arrays.asList(amenitiesArr));

        System.out.print("Enter Location: ");
        String location = sc.nextLine();

        System.out.print("Enter Available Dates (comma-separated, YYYY-MM-DD): ");
        String[] datesArr = sc.nextLine().split(",");
        Set<String> availableDates = new HashSet<>(Arrays.asList(datesArr));

        Hall newHall = new Hall(hallId, hallName, capacity, amenities, location, availableDates);
        HallData.getHalls().add(newHall);

        System.out.println("Hall added successfully.");
    }

    private void deleteExistingHall(Scanner sc) {
        System.out.print("Enter Hall ID to delete: ");
        String hallId = sc.nextLine();

        List<Hall> halls = HallData.getHalls();
        Iterator<Hall> iterator = halls.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            Hall hall = iterator.next();
            if (hall.getHallId().equalsIgnoreCase(hallId)) {
                iterator.remove();
                System.out.println("Hall deleted successfully.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Hall not found.");
        }
    }

    private void reserveHall(Scanner sc) {
        System.out.print("Enter Hall ID to reserve: ");
        String hallId = sc.nextLine();

        Hall selectedHall = null;
        for (Hall hall : HallData.getHalls()) {
            if (hall.getHallId().equalsIgnoreCase(hallId)) {
                selectedHall = hall;
                break;
            }
        }

        if (selectedHall == null) {
            System.out.println("Hall ID not found.");
            return;
        }

        System.out.print("Enter Reservation Date (YYYY-MM-DD): ");
        String date = sc.nextLine();

        if (!selectedHall.isAvailableOn(date)) {
            System.out.println("Hall is not available on this date.");
            return;
        }

        System.out.print("Enter Customer Name: ");
        String customerName = sc.nextLine();

        selectedHall.getAvailableDates().remove(date);
        reservations.computeIfAbsent(hallId, k -> new ArrayList<>()).add("Reserved by " + customerName + " on " + date);

        System.out.println("Hall reserved successfully for " + customerName + " on " + date);
    }

    private void viewReports() {
        System.out.println("\nReport: All Halls & Reservations");
        List<Hall> halls = HallData.getHalls();

        if (halls.isEmpty()) {
            System.out.println("No halls available.");
            return;
        }

        for (Hall hall : halls) {
            System.out.println("\nHall ID: " + hall.getHallId());
            System.out.println("   Name: " + hall.getName());
            System.out.println("   Capacity: " + hall.getCapacity());
            System.out.println("   Amenities: " + hall.getAmenities());
            System.out.println("   Location: " + hall.getLocation());
            System.out.println("   Available Dates: " + hall.getAvailableDates());

            if (reservations.containsKey(hall.getHallId())) {
                System.out.println("   Reservations: " + reservations.get(hall.getHallId()));
            }
            else {
                System.out.println("   No reservations yet.");
            }
        }
    }
}