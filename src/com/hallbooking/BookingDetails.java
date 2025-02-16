package com.hallbooking;

class BookingDetails {
    private String hallId;
    private String date;
    private Customer customer;

    public BookingDetails(String hallId, String date, Customer customer) {
        this.hallId = hallId;
        this.date = date;
        this.customer = customer;
    }

    public String getHallId() {
        return hallId;
    }

    public String getDate() {
        return date;
    }

    public Customer getCustomer() {
        return customer;
    }
}
