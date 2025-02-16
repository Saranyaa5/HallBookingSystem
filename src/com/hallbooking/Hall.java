package com.hallbooking;

import java.util.ArrayList;
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
    
    public Set<String> getAvailableDates(){
    	return availableDates;
    }
 


    public void addAvailableDate(String date) {
        availableDates.add(date);
    }
    @Override
    public String toString() {
        return "Hall ID: " + hallId + ", Name: " + name + ", Capacity: " + capacity + ", Location: " + location + ", Amenities: " + amenities;
    }

}
