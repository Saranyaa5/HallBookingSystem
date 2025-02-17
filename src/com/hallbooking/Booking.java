package com.hallbooking;

import java.util.*;

public class Booking {
    private List<Hall> halls;
    private static Map<String, BookingDetails> bookings = new HashMap<>();

    public Booking() {
        this.halls = HallData.getHalls();
    }

    public boolean bookHall(Scanner sc, Customer customer) {
        System.out.print("Enter the Hall ID to book: ");
        String hallId = sc.nextLine().trim();

        Hall selectedHall = null;
        for (Hall hall : halls) {
            if (hall.getHallId().equals(hallId)) {
                selectedHall = hall;
                break;
            }
        }

        if (selectedHall == null) {
            System.out.println("❌ Hall ID not found.");
            return false;
        }

        Set<String> availableDates = selectedHall.getAvailableDates();
        if (availableDates.isEmpty()) {
            System.out.println("❌ No available dates for this hall.");
            return false;
        }

        System.out.println("Available Dates: " + availableDates);
        System.out.print("Enter the date to book (YYYY-MM-DD): ");
        String bookingDate = sc.nextLine().trim();
        
        if (!availableDates.contains(bookingDate)) {
            System.out.println("❌ Invalid date. Please enter a valid date from the available dates.");
            return false;
        }
        
        String bookingKey = hallId + "-" + bookingDate;
        if (bookings.containsKey(bookingKey)) {
            System.out.println("❌ Hall is already booked on this date.");
            return false;
        }

        BookingDetails bookingDetails = new BookingDetails(hallId, bookingDate, customer.getUserId(), bookingKey);
        bookings.put(bookingKey, bookingDetails);

        System.out.println("✅ Booking confirmed for Hall ID " + hallId + " on " + bookingDate + " by " + customer.getUserName());
        return true;
    }

    public void cancelBooking(Scanner sc, Customer customer) {
    	
        System.out.print("Enter the Hall ID to cancel: ");
        String hallId = sc.nextLine().trim();
        System.out.print("Enter the Date (YYYY-MM-DD): ");
        String bookingDate = sc.nextLine().trim();

        String bookingKey = hallId + "-" + bookingDate;
        BookingDetails bookingDetails = bookings.get(bookingKey);

        if (bookingDetails != null && bookingDetails.getCustomerId().equals(customer.getUserId())) {
            bookings.remove(bookingKey);
            System.out.println("✅ Booking canceled successfully!");
        } else {
            System.out.println("❌ No matching booking found.");
        }
    }

    public static Map<String, BookingDetails> getBookings() {
        return bookings;
    }
}