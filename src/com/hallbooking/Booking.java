package com.hallbooking;

import java.util.*;

public class Booking {
    private List<Hall> halls;
    private static Map<String, BookingDetails> bookings = new HashMap<>();
    static Map<String, Integer> bookingCount = new HashMap<>();

    public Booking() {
        this.halls = HallData.getHalls();
    }

    public boolean bookHall(Scanner sc, Customer customer) {
        try {
            System.out.print("Enter the Hall ID to book: ");
            String hallId = sc.nextLine().trim();

            if (hallId.isEmpty()) {
                throw new IllegalArgumentException("❌ Hall ID cannot be empty.");
            }

            Hall selectedHall = null;
            for (Hall hall : halls) {
                if (hall.getHallId().equals(hallId)) {
                    selectedHall = hall;
                    break;
                }
            }

            if (selectedHall == null) {
                throw new NoSuchElementException("❌ Hall ID not found.");
            }

            Set<String> availableDates = selectedHall.getAvailableDates();
            if (availableDates.isEmpty()) {
                throw new IllegalStateException("❌ No available dates for this hall.");
            }

            System.out.println("Available Dates: " + availableDates);
            System.out.print("Enter the date to book (YYYY-MM-DD): ");
            String bookingDate = sc.nextLine().trim();

            if (!availableDates.contains(bookingDate)) {
                throw new IllegalArgumentException("❌ Invalid date. Please enter a valid date from the available dates.");
            }

            String bookingKey = hallId + "-" + bookingDate;
            if (bookings.containsKey(bookingKey)) {
                throw new IllegalStateException("❌ Hall is already booked on this date.");
            }

            BookingDetails bookingDetails = new BookingDetails(hallId, bookingDate, customer.getUserId(), bookingKey);
            bookings.put(bookingKey, bookingDetails);

            bookingCount.put(hallId, bookingCount.getOrDefault(hallId, 0) + 1);

            System.out.println("✅ Booking confirmed for Hall ID " + hallId + " on " + bookingDate + " by " + customer.getUserName());
            return true;

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ An unexpected error occurred: " + e.getMessage());
        }
        return false;
    }

    public void cancelBooking(Scanner sc, Customer customer) {
        try {
            System.out.print("Enter the Hall ID to cancel: ");
            String hallId = sc.nextLine().trim();
            System.out.print("Enter the Date (YYYY-MM-DD): ");
            String bookingDate = sc.nextLine().trim();

            if (hallId.isEmpty() || bookingDate.isEmpty()) {
                throw new IllegalArgumentException("❌ Hall ID and Date cannot be empty.");
            }

            String bookingKey = hallId + "-" + bookingDate;
            BookingDetails bookingDetails = bookings.get(bookingKey);

            if (bookingDetails != null && bookingDetails.getCustomerId().equals(customer.getUserId())) {
                bookings.remove(bookingKey);

                bookingCount.put(hallId, Math.max(bookingCount.getOrDefault(hallId, 0) - 1, 0));

                System.out.println("✅ Booking canceled successfully!");
            } else {
                throw new NoSuchElementException("❌ No matching booking found.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ An unexpected error occurred: " + e.getMessage());
        }
    }

    public static Map<String, BookingDetails> getBookings() {
        return bookings;
    }
    public static Map<String, Integer> getbookingcount(){
    	return bookingCount;
    }
}
