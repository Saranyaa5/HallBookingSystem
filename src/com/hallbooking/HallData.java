package com.hallbooking;
import java.util.*;

public class HallData {
    private static List<Hall> halls = new ArrayList<>();

    static {
        halls.add(new Hall("H101", "Grand Hall", 500, Set.of("AC", "Parking"), "Chennai", Set.of("2025-02-20", "2025-02-25","2025-03-11")));
        halls.add(new Hall("H102", "Royal Palace", 300, Set.of("Non-AC", "Parking"), "Bangalore", Set.of("2025-02-18", "2025-02-22")));
        halls.add(new Hall("H103", "Sunset Banquet", 400, Set.of("AC", "WiFi"), "Hyderabad", Set.of("2025-02-21", "2025-02-24")));
        halls.add(new Hall("H104", "Ocean View", 250, Set.of("AC", "Stage"), "Mumbai", Set.of("2025-02-19", "2025-02-26")));
        halls.add(new Hall("H105", "Garden Plaza", 350, Set.of("Non-AC", "Parking", "Garden"), "Delhi", Set.of("2025-02-17", "2025-02-23")));
        halls.add(new Hall("H106", "Majestic Hall", 450, Set.of("AC", "Valet"), "Kolkata", Set.of("2025-02-22", "2025-02-27")));
        halls.add(new Hall("H107", "Crystal Ballroom", 600, Set.of("AC", "WiFi", "Parking"), "Pune", Set.of("2025-02-20", "2025-02-28")));
        halls.add(new Hall("H108", "Heritage Hall", 275, Set.of("Non-AC", "Stage"), "Jaipur", Set.of("2025-02-21", "2025-02-29")));
        halls.add(new Hall("H109", "Skyline Venue", 320, Set.of("AC", "Terrace"), "Ahmedabad", Set.of("2025-02-19", "2025-02-25")));
        halls.add(new Hall("H110", "Sunshine Hall", 150, Set.of("Non-AC", "WiFi"), "Lucknow", Set.of("2025-02-18", "2025-02-26")));
    }

    public static List<Hall> getHalls() {
        return halls;
    }

    
}
