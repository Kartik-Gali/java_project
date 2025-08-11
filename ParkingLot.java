import java.util.*;

public class ParkingLot {
    private Map<String, Ticket> parkedVehicles = new HashMap<>();
    private int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        if (parkedVehicles.size() >= capacity) {
            System.out.println("Parking Lot is Full!");
            return false;
        }
        if (parkedVehicles.containsKey(vehicle.getPlateNumber())) {
            System.out.println("Vehicle already parked!");
            return false;
        }
        parkedVehicles.put(vehicle.getPlateNumber(), new Ticket(vehicle));
        System.out.println("Vehicle parked successfully.");
        return true;
    }

    public boolean removeVehicle(String plateNumber) {
        Ticket ticket = parkedVehicles.remove(plateNumber);
        if (ticket == null) {
            System.out.println("Vehicle not found!");
            return false;
        }
        long duration = java.time.Duration.between(ticket.getEntryTime(), java.time.LocalDateTime.now()).toMinutes();
        System.out.println("Vehicle removed. Duration: " + duration + " mins. Fee: $" + (duration * 0.5));
        return true;
    }

    public void showStatus() {
        if (parkedVehicles.isEmpty()) {
            System.out.println("Parking lot is empty.");
            return;
        }
        System.out.println("Parked Vehicles:");
        for (Ticket ticket : parkedVehicles.values()) {
            System.out.println(ticket);
        }
    }

    public void searchVehicle(String plateNumber) {
        Ticket ticket = parkedVehicles.get(plateNumber);
        if (ticket == null) {
            System.out.println("Vehicle not found.");
        } else {
            System.out.println("Vehicle found: " + ticket);
        }
    }
}
