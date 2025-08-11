import java.time.LocalDateTime;

public class Ticket {
    private Vehicle vehicle;
    private LocalDateTime entryTime;

    public Ticket(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.entryTime = LocalDateTime.now();
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    @Override
    public String toString() {
        return vehicle + " | Entry Time: " + entryTime;
    }
}
