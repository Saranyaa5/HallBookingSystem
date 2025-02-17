package com.hallbooking;
import java.util.*;

public class Customer extends User {
    private static HashMap<String, Customer> customerData = new HashMap<>(); 
    public Customer(String name, String userId, String email, String password) {
        super(name, userId, email, password);
    }

    public void customerMenu(Scanner sc) {
        while (true) {
            System.out.println("1. Register\n2. Login\n3. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    register(sc);
                    break;
                case 2:
                    login(sc);
                    break;
                case 3:
                    System.out.println("Exiting customer section...");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private void register(Scanner sc) {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter User ID: ");
        String userId = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        if (customerData.containsKey(userId)) {
            System.out.println("User ID already taken. Try another one.");
            return;
        }

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        Customer newCustomer = new Customer(name, userId, email, password);
        customerData.put(userId, newCustomer);
        System.out.println("✅ Registration successful! Please login.");
    }

    private void login(Scanner sc) {
        while (true) {
            System.out.print("Enter User ID: ");
            String userId = sc.nextLine();
            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            Customer existingCustomer = customerData.get(userId);
            if (existingCustomer != null && existingCustomer.authenticate(password)) {
                System.out.println("✅ Login Successful! Welcome, " + existingCustomer.name);
                customerFunctionality(sc, existingCustomer);
                return;
            } else {
                System.out.println("❌ Incorrect credentials. Try again.");
            }
        }
    }

    public void customerFunctionality(Scanner sc, Customer customer) {
    	
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Search Hall");
            System.out.println("2. Book Hall using Hall ID");
            System.out.println("3. Cancel Booked Hall");
            System.out.println("4. View pricing of halls");
            System.out.println("5. Make Payment");
            System.out.println("6. Logout");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> search(sc);
                case 2 -> booking(sc, customer);
                case 3 -> cancelBooking(sc, customer);
                case 4 -> viewHallPrice(sc);
                case 5 -> makePayment(sc,customer);
                case 6 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }
    private void makePayment(Scanner sc, Customer customer) {
        System.out.print("Enter Hall ID to make payment: ");
        String hallId = sc.nextLine().trim();
        System.out.print("Enter the date of booking (YYYY-MM-DD): ");
        String bookingDate = sc.nextLine().trim();

        String bookingKey = hallId + "-" + bookingDate;
        BookingDetails bookingDetails = Booking.getBookings().get(bookingKey);

        if (bookingDetails != null && bookingDetails.getCustomerId().equals(customer.getUserId())) {
            if (bookingDetails.getAmtPaidStatus()) {
                System.out.println("❌ Payment already made for this booking.");
            } else {
                
                boolean paymentStatus = Payment.processPayment(sc, bookingDetails);

                if (paymentStatus) {
                    bookingDetails.setAmtPaidStatus(true);
                    System.out.println("✅ Payment successful! Booking confirmed.");
                } else {
                    System.out.println("❌ Payment failed. Please try again.");
                }
            }
        } else {
            System.out.println("❌ No booking found with this Hall ID and Date.");
        }
    }



    private void viewHallPrice(Scanner sc) {
        while (true) {
            System.out.println("1. See pricing for a specific hall");
            System.out.println("2. Show all hall details");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine(); 
            
            switch (choice) {
                case 1:
                    System.out.print("Enter Hall ID: ");
                    String hallId = sc.nextLine().trim(); 
                    
                    boolean hallFound = false;
                    for (Hall hall : HallData.getHalls()) {
                        if (hall.getHallId().equalsIgnoreCase(hallId)) {
                            System.out.println("\n--- Hall Pricing Details ---");
                            System.out.println("Hall ID: " + hall.getHallId());
                            System.out.println("Name: " + hall.getName());
                            System.out.println("Capacity: " + hall.getCapacity());
                            System.out.println("Location: " + hall.getLocation());
                            System.out.println("Amenities: " + hall.getAmenities());
                            System.out.println("Price: ₹" + hall.calculatePrice());
                            hallFound = true;
                            break;
                        }
                    }
                    if (!hallFound) {
                        System.out.println("Hall ID not found. Please enter a valid Hall ID.");
                    }
                    break;
                
                case 2:
                    System.out.println("\n--- All Hall Pricing Details ---");
                    for (Hall hall : HallData.getHalls()) {
                        System.out.println("Hall ID: " + hall.getHallId());
                        System.out.println("Name: " + hall.getName());
                        System.out.println("Capacity: " + hall.getCapacity());
                        System.out.println("Location: " + hall.getLocation());
                        System.out.println("Amenities: " + hall.getAmenities());
                        System.out.println("Price: ₹" + hall.calculatePrice());
                        System.out.println("------------------------------");
                    }
                    break;
                
                case 3:
                    System.out.println("Exiting Hall Pricing Menu 👋");
                    return;
                
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        }
    }

	private void booking(Scanner sc, Customer customer) {
        Booking book = new Booking();
        boolean bookingSuccess = book.bookHall(sc, customer);

        if (bookingSuccess) {
            System.out.println("✅ Booking confirmed!");
        } else {
            System.out.println("❌ Booking failed. The hall might already be booked.");
        }
    }

    private void cancelBooking(Scanner sc, Customer customer) {
        Booking booking = new Booking();
        booking.cancelBooking(sc, customer);
    }

	private void search(Scanner sc) {
        HallSearch search = new HallSearch();

        while (true) {
            System.out.println("\nSearch Options:");
            System.out.println("1. Search by Date");
            System.out.println("2. Search by Hall ID");
            System.out.println("3. Search by Name");
            System.out.println("4. Search by Capacity");
            System.out.println("5. Search by Location");
            System.out.println("6. Search by Amenities");
            System.out.println("7. Show All Halls");
            System.out.println("8. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> search.searchByDate(sc);
                case 2 -> search.searchById(sc);
                case 3 -> search.searchByName(sc);
                case 4 -> search.searchByCapacity(sc);
                case 5 -> search.searchByLocation(sc);
                case 6 -> search.searchByAmenities(sc);
                case 7 -> search.showAllHalls();
                case 8 -> { return; }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

	 
	
	

	
}
