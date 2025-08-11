import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ParkingLot parkingLot = new ParkingLot(5); // Example capacity

        while (true) {
            System.out.println("\n--- Parking System ---");
            System.out.println("1. Park Vehicle");
            System.out.println("2. Remove Vehicle");
            System.out.println("3. Show Parking Status");
            System.out.println("4. Search Vehicle");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Plate Number: ");
                    String plate = sc.nextLine();
                    System.out.print("Enter Vehicle Type (Car/Bike/Truck): ");
                    String type = sc.nextLine();
                    parkingLot.parkVehicle(new Vehicle(plate, type));
                    break;
                case 2:
                    System.out.print("Enter Plate Number: ");
                    plate = sc.nextLine();
                    parkingLot.removeVehicle(plate);
                    break;
                case 3:
                    parkingLot.showStatus();
                    break;
                case 4:
                    System.out.print("Enter Plate Number: ");
                    plate = sc.nextLine();
                    parkingLot.searchVehicle(plate);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
