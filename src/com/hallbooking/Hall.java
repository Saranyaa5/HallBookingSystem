package com.hallbooking;

import java.util.Set;

public class Hall {
    private String hallId;
    private String name;
    private int capacity;
    private Set<String> amenities;
    private String location;
    private Set<String> availableDates;

    public Hall(String hallId, String name, int capacity, Set<String> amenities, String location, Set<String> availableDates) {
        this.hallId = hallId;
        this.name = name;
        this.capacity = capacity;
        this.amenities = amenities;
        this.location = location;
        this.availableDates = availableDates;
    }

    public String getHallId() {
        return hallId;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public Set<String> getAmenities() {
        return amenities;
    }

    public String getLocation() {
        return location;
    }

    public boolean isAvailableOn(String date) {
        return availableDates.contains(date);
    }

    public Set<String> getAvailableDates() {
        return availableDates;
    }

    public void addAvailableDate(String date) {
        availableDates.add(date);
    }

    // Calculate price based on capacity and amenities
    public double calculatePrice() {
        double basePricePerSeat = 50; // Base price per person
        double totalPrice = capacity * basePricePerSeat;

        // Additional charges for amenities
        if (amenities.contains("AC")) {
            totalPrice += 5000;
        }
        if (amenities.contains("WiFi")) {
            totalPrice += 2000;
        }
        if (amenities.contains("Parking")) {
            totalPrice += 3000;
        }
        if (amenities.contains("Stage")) {
            totalPrice += 4000;
        }
        if (amenities.contains("Valet")) {
            totalPrice += 6000;
        }
        if (amenities.contains("Garden")) {
            totalPrice += 7000;
        }
        if (amenities.contains("Terrace")) {
            totalPrice += 8000;
        }

        return totalPrice;
    }

    @Override
    public String toString() {
        return "Hall ID: " + hallId + ", Name: " + name + ", Capacity: " + capacity +
                ", Location: " + location + ", Amenities: " + amenities + ", Price: ₹" + calculatePrice();
    }
}
