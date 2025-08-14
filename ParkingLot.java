import java.io.*;
import java.util.ArrayList;

class ParkingLot {
    private int capacity;
    private ArrayList<Vehicle> vehicles;
    private final String FILE_NAME = "parking_records.txt";

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.vehicles = new ArrayList<>();
        
    }

    public void parkVehicle(Vehicle vehicle) {
        if (getCurrentParkedCount() < capacity) {
            vehicles.add(vehicle);
            saveData();
            System.out.println("Vehicle parked successfully.");
            
        } else {
            System.out.println("Parking lot is full!");
            
        }
    }

    public void removeVehicle(String numberPlate) {
        for (Vehicle v : vehicles) {
            if (v.getNumberPlate().equalsIgnoreCase(numberPlate) && v.getTimeOut() == null) {
                v.setTimeOut(System.currentTimeMillis());
                saveData();
                System.out.println("Vehicle removed successfully. Charges: Rs " + v.calculateCharges());
                return;
            }
        }
        System.out.println("Vehicle not found or already removed!");
        
    }

    public void displayVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("vehicle records found.");
        } else {
            System.out.println("All Vehicle Records:");
            for (Vehicle v : vehicles) {
                System.out.println(v);
            }
        }
    }

    public void searchVehicle(String numberPlate) {
        boolean found = false;
        for (Vehicle v : vehicles) {
            if (v.getNumberPlate().equalsIgnoreCase(numberPlate)) {
                System.out.println("Found: " + v);
                found = true;
            }
        }
        if (!found) System.out.println("Vehicle not found.");
    }

    public void showEarnings() {
        double total = 0;
        for (Vehicle v : vehicles) {
            if (v.getTimeOut() != null) {
                total += v.calculateCharges();
            }
        }
        System.out.println("Total Earnings: Rs " + total);
    }

    public void showAvailableSlots() {
        int available = capacity - getCurrentParkedCount();
        System.out.println("Available Slots: " + available + "/" + capacity);
    }

    private int getCurrentParkedCount() {
        int count = 0;
        for (Vehicle v : vehicles) {
            if (v.getTimeOut() == null) count++;
        }
        return count;
    }

    private void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Vehicle v : vehicles) {
                writer.write(v.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}
