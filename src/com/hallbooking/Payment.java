package com.hallbooking;

import java.util.Scanner;

public class Payment {

    public static boolean processPayment(Scanner sc, BookingDetails bookingDetails) {
        System.out.println("\n--- Payment Processing ---");
        System.out.println("Booking Details:");
        System.out.println("Hall ID: " + bookingDetails.getHallId());
        System.out.println("Date: " + bookingDetails.getDate());

        Hall selectedHall = getHallById(bookingDetails.getHallId());
        double price = selectedHall.calculatePrice();
        System.out.println("Total Amount: ₹" + price);

        System.out.print("Enter payment method (1. Credit Card, 2. Debit Card, 3. UPI): ");
        int paymentMethod = sc.nextInt();
        sc.nextLine(); 
        System.out.print("Enter amount to pay: ₹");
        double amountPaid = sc.nextDouble();

        if (amountPaid > price) {
            System.out.println("✅ Payment of ₹" + price + " successful!");
            System.out.println("sent balance " + (amountPaid -price) + " to "+bookingDetails.getCustomerId() );
            return true;
        }
        else if(amountPaid==price) {
        	 System.out.println("✅ Payment of ₹" + amountPaid + " successful!");
             return true;
        }
        else {
            System.out.println("❌ Insufficient amount. Payment failed.");
            return false;
        }
    }

    private static Hall getHallById(String hallId) {
        for (Hall hall : HallData.getHalls()) {
            if (hall.getHallId().equals(hallId)) {
                return hall;
            }
        }
        return null;
    }
}
