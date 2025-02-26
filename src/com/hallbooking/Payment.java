package com.hallbooking;

import java.util.Scanner;

public class Payment {

    public static boolean processPayment(Scanner sc, BookingDetails bookingDetails) {
        try {
            System.out.println("\n--- Payment Processing ---");
            System.out.println("Booking Details:");
            System.out.println("Hall ID: " + bookingDetails.getHallId());
            System.out.println("Date: " + bookingDetails.getDate());

            Hall selectedHall = getHallById(bookingDetails.getHallId());
            if (selectedHall == null) {
                System.out.println("❌ Error: Hall not found. Payment aborted.");
                return false;
            }

            double price = selectedHall.calculatePrice();
            System.out.println("Total Amount: ₹" + price);

            System.out.print("Enter payment method (1. Credit Card, 2. Debit Card, 3. UPI): ");
            int paymentMethod;

            try {
                paymentMethod = sc.nextInt();
                if (paymentMethod < 1 || paymentMethod > 3) {
                    System.out.println("Invalid payment method selected. Please try again.");
                    return false;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number (1-3) for payment method.");
                sc.nextLine(); 
                return false;
            }

            System.out.print("Enter amount to pay: ₹");
            double amountPaid;

            try {
                amountPaid = sc.nextDouble();
            } catch (Exception e) {
                System.out.println("Invalid amount entered. Please enter a valid number.");
                sc.nextLine(); 
                return false;
            }

            if (amountPaid > price) {
                System.out.println("Payment of ₹" + price + " successful!");
                System.out.println("Sent balance ₹" + (amountPaid - price) + " to " + bookingDetails.getCustomerId());
                return true;
            } else if (amountPaid == price) {
                System.out.println("Payment of ₹" + amountPaid + " successful!");
                return true;
            } else {
                System.out.println("Insufficient amount. Payment failed.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred during payment: " + e.getMessage());
            return false;
        }
    }

    private static Hall getHallById(String hallId) {
        try {
            for (Hall hall : HallData.getHalls()) {
                if (hall.getHallId().equals(hallId)) {
                    return hall;
                }
            }
        } catch (Exception e) {
            System.out.println("Error retrieving hall details: " + e.getMessage());
        }
        return null;
    }
}
