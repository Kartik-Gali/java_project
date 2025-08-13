import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

class Vehicle {
    private String numberPlate;
    private String ownerName;
    private String vehicleType;
    private long timeIn;
    private Long timeOut;

    public Vehicle(String numberPlate, String ownerName, String vehicleType) {
        this.numberPlate = numberPlate;
        this.ownerName = ownerName;
        this.vehicleType = vehicleType;
        this.timeIn = System.currentTimeMillis();
        this.timeOut = null;
    }

    public Vehicle(String numberPlate, String ownerName, String vehicleType, long timeIn, Long timeOut) {
        this.numberPlate = numberPlate;
        this.ownerName = ownerName;
        this.vehicleType = vehicleType;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    public String getNumberPlate() { return numberPlate; }
    public String getOwnerName() { return ownerName; }
    public String getVehicleType() { return vehicleType; }
    public long getTimeIn() { return timeIn; }
    public Long getTimeOut() { return timeOut; }
    public void setTimeOut(long timeOut) { this.timeOut = timeOut; }

    public double calculateCharges() {
        if (timeOut == null) return 0;
        long durationMillis = timeOut - timeIn;
        double hours = Math.ceil(durationMillis / (1000.0 * 60 * 60)); // Round up hours
        double ratePerHour;

        switch (vehicleType.toLowerCase()) {
            case "bike": ratePerHour = 10; break;
            case "car": ratePerHour = 20; break;
            case "truck": ratePerHour = 50; break;
            default: ratePerHour = 20; // default rate
        }

        return hours * ratePerHour;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String timeInStr = sdf.format(new Date(timeIn));
        String timeOutStr = (timeOut != null) ? sdf.format(new Date(timeOut)) : "Still Parked";
        String chargesStr = (timeOut != null) ? "₹" + calculateCharges() : "Pending";
        return numberPlate + "," + ownerName + "," + vehicleType + "," + timeInStr + "," + timeOutStr + "," + chargesStr;
    }
}

class ParkingLot {
    private int capacity;
    private ArrayList<Vehicle> vehicles;
    private final String FILE_NAME = "parking_records.txt";

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.vehicles = new ArrayList<>();
        loadData();
    }

    public boolean parkVehicle(Vehicle vehicle) {
        if (getCurrentParkedCount() < capacity) {
            vehicles.add(vehicle);
            saveData();
            System.out.println("✅ Vehicle parked successfully.");
            return true;
        } else {
            System.out.println("❌ Parking lot is full!");
            return false;
        }
    }

    public boolean removeVehicle(String numberPlate) {
        for (Vehicle v : vehicles) {
            if (v.getNumberPlate().equalsIgnoreCase(numberPlate) && v.getTimeOut() == null) {
                v.setTimeOut(System.currentTimeMillis());
                saveData();
                System.out.println("✅ Vehicle removed successfully. Charges: ₹" + v.calculateCharges());
                return true;
            }
        }
        System.out.println("❌ Vehicle not found or already removed!");
        return false;
    }

    public void displayVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("📭 No vehicle records found.");
        } else {
            System.out.println("🚗 All Vehicle Records:");
            for (Vehicle v : vehicles) {
                System.out.println(v);
            }
        }
    }

    public void searchVehicle(String numberPlate) {
        boolean found = false;
        for (Vehicle v : vehicles) {
            if (v.getNumberPlate().equalsIgnoreCase(numberPlate)) {
                System.out.println("🔍 Found: " + v);
                found = true;
            }
        }
        if (!found) System.out.println("❌ Vehicle not found.");
    }

    public void showEarnings() {
        double total = 0;
        for (Vehicle v : vehicles) {
            if (v.getTimeOut() != null) {
                total += v.calculateCharges();
            }
        }
        System.out.println("💰 Total Earnings: ₹" + total);
    }

    public void showAvailableSlots() {
        int available = capacity - getCurrentParkedCount();
        System.out.println("📦 Available Slots: " + available + "/" + capacity);
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
            System.out.println("❌ Error saving data: " + e.getMessage());
        }
    }

    private void loadData() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    long timeIn = sdf.parse(parts[3]).getTime();
                    Long timeOut = parts[4].equals("Still Parked") ? null : sdf.parse(parts[4]).getTime();
                    Vehicle v = new Vehicle(parts[0], parts[1], parts[2], timeIn, timeOut);
                    vehicles.add(v);
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error loading data: " + e.getMessage());
        }
    }
}

public class ParkingSystemVehicleType {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ParkingLot parkingLot = new ParkingLot(5); // capacity 5

        while (true) {
            System.out.println("\n===== 🚗 Parking System Menu =====");
            System.out.println("1. Park Vehicle");
            System.out.println("2. Remove Vehicle");
            System.out.println("3. Display All Vehicles");
            System.out.println("4. Search Vehicle");
            System.out.println("5. Show Total Earnings");
            System.out.println("6. Show Available Slots");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter vehicle number plate: ");
                    String numberPlate = sc.nextLine();
                    System.out.print("Enter owner name: ");
                    String ownerName = sc.nextLine();
                    System.out.print("Enter vehicle type (Bike/Car/Truck): ");
                    String type = sc.nextLine();
                    parkingLot.parkVehicle(new Vehicle(numberPlate, ownerName, type));
                    break;
                case 2:
                    System.out.print("Enter vehicle number plate to remove: ");
                    String removePlate = sc.nextLine();
                    parkingLot.removeVehicle(removePlate);
                    break;
                case 3:
                    parkingLot.displayVehicles();
                    break;
                case 4:
                    System.out.print("Enter vehicle number plate to search: ");
                    String searchPlate = sc.nextLine();
                    parkingLot.searchVehicle(searchPlate);
                    break;
                case 5:
                    parkingLot.showEarnings();
                    break;
                case 6:
                    parkingLot.showAvailableSlots();
                    break;
                case 7:
                    System.out.println("👋 Exiting... Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }
}
