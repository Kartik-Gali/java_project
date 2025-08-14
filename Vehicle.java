import java.text.SimpleDateFormat;
import java.util.Date;

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

    

    public String getNumberPlate() { return numberPlate; }
    public String getOwnerName() { return ownerName; }
    public String getVehicleType() { return vehicleType; }
    public long getTimeIn() { return timeIn; }
    public Long getTimeOut() { return timeOut; }
    public void setTimeOut(long timeOut) { this.timeOut = timeOut; }

    public double calculateCharges() {
        if (timeOut == null) return 0;
        long durationMillis = timeOut - timeIn;
        double hours = Math.ceil(durationMillis / (1000.0 * 60 * 60)); 
        double ratePerHour;

        switch (vehicleType.toLowerCase()) {
            case "bike": ratePerHour = 10; break;
            case "car": ratePerHour = 20; break;
            case "truck": ratePerHour = 50; break;
            default: ratePerHour = 20; // default rate
        }

        return hours * ratePerHour;
    }

    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String timeInStr = sdf.format(new Date(timeIn));
        String timeOutStr = (timeOut != null) ? sdf.format(new Date(timeOut)) : "Still Parked";
        String chargesStr = (timeOut != null) ? "Rs " + calculateCharges() : "Pending";
        return numberPlate + "," + ownerName + "," + vehicleType + "," + timeInStr + "," + timeOutStr + "," + chargesStr;
    }
}
