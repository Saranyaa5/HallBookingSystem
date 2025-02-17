package com.hallbooking;

import java.util.*;

class BookingDetails {
    private String hallId;
    private String date;
    private String bookingKey;
    private String customerId;
    private boolean amtPaid = false;

    public BookingDetails(String hallId, String date, String customerId, String bookingKey) {
        this.hallId = hallId;
        this.date = date;
        this.customerId = customerId;
        this.bookingKey = bookingKey;
    }

    public String getHallId() {
        return hallId;
    }

    public String getDate() {
        return date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getBookingKey() {
        return bookingKey;
    }
    
    public void setAmtPaidStatus(boolean paid) {
        this.amtPaid = paid;
    }

    public boolean getAmtPaidStatus() {
        return amtPaid;
    }
}
