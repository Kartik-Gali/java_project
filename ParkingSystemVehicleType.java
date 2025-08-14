import java.util.Scanner;

public class ParkingSystemVehicleType {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ParkingLot parkingLot = new ParkingLot(5); // capacity 5

        while (true) {
            System.out.println("\n===== Parking System Menu =====");
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
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}