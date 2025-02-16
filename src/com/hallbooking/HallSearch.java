package com.hallbooking;

import java.util.*;

public class HallSearch {
    private List<Hall> halls;

    public HallSearch() {
        this.halls = HallData.getHalls();
    }

    public void searchByDate(Scanner sc) {
        System.out.println("Enter date (YYYY-MM-DD): ");
        String date = sc.nextLine();
        List<Hall> availableHalls = new ArrayList<>();

        for (Hall hall : halls) {
            if (hall.isAvailableOn(date)) {
                availableHalls.add(hall);
            }
        }

        displayResults(availableHalls);
    }

    public void searchById(Scanner sc) {
        System.out.println("Enter Hall ID: ");
        String hallId = sc.nextLine();
        for (Hall hall : halls) {
            if (hall.getHallId().equalsIgnoreCase(hallId)) {
                System.out.println(hall);
                return;
            }
        }
        System.out.println("Hall ID not found.");
    }

    public void searchByName(Scanner sc) {
        System.out.println("Enter Hall Name: ");
        String name = sc.nextLine();
        List<Hall> matchingHalls = new ArrayList<>();

        for (Hall hall : halls) {
            if (hall.getName().equalsIgnoreCase(name)) {
                matchingHalls.add(hall);
            }
        }

        displayResults(matchingHalls);
    }

    public void searchByCapacity(Scanner sc) {
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
    }

    public void searchByLocation(Scanner sc) {
        System.out.println("Enter location: ");
        String location = sc.nextLine();
        List<Hall> hallsInLocation = new ArrayList<>();

        for (Hall hall : halls) {
            if (hall.getLocation().equalsIgnoreCase(location)) {
                hallsInLocation.add(hall);
            }
        }

        displayResults(hallsInLocation);
    }

    public void searchByAmenities(Scanner sc) {
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
    }


    public void showAllHalls() {
        displayResults(halls);
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
