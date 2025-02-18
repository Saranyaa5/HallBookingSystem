package com.hallbooking;

import java.util.*;

public class HallSearch {
    private List<Hall> halls;

    public HallSearch() {
        this.halls = HallData.getHalls();
    }

    public void searchByDate(Scanner sc) {
        try {
            System.out.println("Enter date (YYYY-MM-DD): ");
            String date = sc.nextLine();
            List<Hall> availableHalls = new ArrayList<>();

            for (Hall hall : halls) {
                if (hall.isAvailableOn(date)) {
                    availableHalls.add(hall);
                }
            }

            displayResults(availableHalls);
        } catch (Exception e) {
            System.out.println("An error occurred while searching by date: " + e.getMessage());
        }
    }

    public void searchById(Scanner sc) {
        try {
            System.out.println("Enter Hall ID: ");
            String hallId = sc.nextLine();
            boolean found = false;
            for (Hall hall : halls) {
                if (hall.getHallId().equalsIgnoreCase(hallId)) {
                    System.out.println(hall);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Hall ID not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while searching by ID: " + e.getMessage());
        }
    }

    public void searchByName(Scanner sc) {
        try {
            System.out.println("Enter Hall Name: ");
            String name = sc.nextLine();
            List<Hall> matchingHalls = new ArrayList<>();

            for (Hall hall : halls) {
                if (hall.getName().equalsIgnoreCase(name)) {
                    matchingHalls.add(hall);
                }
            }

            displayResults(matchingHalls);
        } catch (Exception e) {
            System.out.println("An error occurred while searching by name: " + e.getMessage());
        }
    }

    public void searchByCapacity(Scanner sc) {
        try {
            System.out.println("Enter maximum capacity required: ");
            int capacity = sc.nextInt();
            sc.nextLine(); 

            List<Hall> suitableHalls = new ArrayList<>();
            for (Hall hall : halls) {
                if (hall.getCapacity() <= capacity) {
                    suitableHalls.add(hall);
                }
            }

            displayResults(suitableHalls);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input for capacity. Please enter a valid integer.");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("An error occurred while searching by capacity: " + e.getMessage());
        }
    }

    public void searchByLocation(Scanner sc) {
        try {
            System.out.println("Enter location: ");
            String location = sc.nextLine();
            List<Hall> hallsInLocation = new ArrayList<>();

            for (Hall hall : halls) {
                if (hall.getLocation().equalsIgnoreCase(location)) {
                    hallsInLocation.add(hall);
                }
            }

            displayResults(hallsInLocation);
        } catch (Exception e) {
            System.out.println("An error occurred while searching by location: " + e.getMessage());
        }
    }

    public void searchByAmenities(Scanner sc) {
        try {
            System.out.println("Enter amenities (comma separated): AC, Parking, Wifi, Stage, Terrace: ");
            String[] amenitiesInput = sc.nextLine().toLowerCase().split(",");

            Set<String> requiredAmenities = new HashSet<>();
            for (String amenity : amenitiesInput) {
                requiredAmenities.add(amenity.trim());
            }

            List<Hall> matchingHalls = new ArrayList<>();
            for (Hall hall : halls) {
                Set<String> hallAmenities = new HashSet<>();
                for (String amenity : hall.getAmenities()) {
                    hallAmenities.add(amenity.toLowerCase());
                }
                if (hallAmenities.containsAll(requiredAmenities)) {
                    matchingHalls.add(hall);
                }
            }

            displayResults(matchingHalls);
        } catch (Exception e) {
            System.out.println("An error occurred while searching by amenities: " + e.getMessage());
        }
    }

    public void showAllHalls() {
        try {
            displayResults(halls);
        } catch (Exception e) {
            System.out.println("An error occurred while displaying all halls: " + e.getMessage());
        }
    }

    private void displayResults(List<Hall> result) {
        if (result.isEmpty()) {
            System.out.println("No halls found.");
        } else {
            for (Hall hall : result) {
                System.out.println(hall);
            }
        }
    }
}
