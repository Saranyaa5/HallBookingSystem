package com.hallbooking;
import java.util.*;

public class Customer extends User {
    private static HashMap<String, Customer> customerData = new HashMap<>(); 
    public Customer(String name, String userId, String email, String password) {
        super(name, userId, email, password);
    }

    public void customerMenu(Scanner sc) {
        while (true) {
            try {
                System.out.println("1. Register\n2. Login\n3. Exit");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1 -> register(sc);
                    case 2 -> login(sc);
                    case 3 -> {
                        System.out.println("Exiting customer section...");
                        return;
                    }
                    default -> System.out.println("Invalid choice! Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine(); 
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private void register(Scanner sc) {
        try {
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
        } catch (Exception e) {
            System.out.println("Error occurred during registration: " + e.getMessage());
        }
    }

    private void login(Scanner sc) {
        try {
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
        } catch (Exception e) {
            System.out.println("An error occurred during login: " + e.getMessage());
        }
    }

    public void customerFunctionality(Scanner sc, Customer customer) {
        while (true) {
            try {
                System.out.println("\nChoose an option:");
                System.out.println("1. Search Hall\n2. Book Hall using Hall ID\n3. Cancel Booked Hall\n4. View pricing of halls\n5. Make Payment\n6. Logout from customer section");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1 -> search(sc);
                    case 2 -> booking(sc, customer);
                    case 3 -> cancelBooking(sc, customer);
                    case 4 -> viewHallPrice(sc);
                    case 5 -> makePayment(sc, customer);
                    case 6 -> {
                        System.out.println("Logging out...");
                        return;
                    }
                    default -> System.out.println("Invalid choice! Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine(); 
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
    private void makePayment(Scanner sc, Customer customer) {
        try {
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
        } catch (NullPointerException e) {
            System.out.println("Error: Invalid booking details. Please check your inputs.");
        } catch (Exception e) {
            System.out.println("An error occurred while processing the payment: " + e.getMessage());
        }
    }



    private void viewHallPrice(Scanner sc) {
    	try {
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
        catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a number.");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred during search: " + e.getMessage());
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
        try {
            HallSearch search = new HallSearch();
            while (true) {
                System.out.println("\nSearch Options:");
                System.out.println("1. Search by Date\n2. Search by Hall ID\n3. Search by Name\n4. Search by Capacity\n5. Search by Location\n6. Search by Amenities\n7. Show All Halls\n8. Exit search functionality");
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
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a number.");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred during search: " + e.getMessage());
        }
    }
    

	 
	
	

	
}
