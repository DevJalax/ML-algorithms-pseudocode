import java.util.List;

public class DriverDispatchService {

    // Your assignDriver method as-is
    public Driver assignDriver(HttpRequest request, List<Driver> availableDrivers) {
        Driver bestDriver = null;
        double minScore = Double.MAX_VALUE;

        for (Driver driver : availableDrivers) {
            if (!driver.isAvailable()) {
                continue;
            }

            double distance = calculateDistance(request.getPickupLocation(), driver.getLocation());
            double driverScore = distance + driver.getCurrentLoad() * 2 - driver.getRating() * 5;

            if (driverScore < minScore) {
                minScore = driverScore;
                bestDriver = driver;
            }
        }

        if (bestDriver != null) {
            bestDriver.assignTo(request);
            bestDriver.setAvailable(false);
            return bestDriver;
        } else {
            System.out.println("No drivers available");
            return null;
        }
    }

    // Dummy distance calculator - you can replace with real logic
    private double calculateDistance(Location loc1, Location loc2) {
        double latDiff = loc1.getLatitude() - loc2.getLatitude();
        double lonDiff = loc1.getLongitude() - loc2.getLongitude();
        return Math.sqrt(latDiff * latDiff + lonDiff * lonDiff);
    }

    // Supporting classes (simplified)

    public static class HttpRequest {
        private Location pickupLocation;

        public HttpRequest(Location pickupLocation) {
            this.pickupLocation = pickupLocation;
        }

        public Location getPickupLocation() {
            return pickupLocation;
        }
    }

    public static class Driver {
        private boolean isAvailable;
        private Location location;
        private int currentLoad;
        private double rating;

        public Driver(boolean isAvailable, Location location, int currentLoad, double rating) {
            this.isAvailable = isAvailable;
            this.location = location;
            this.currentLoad = currentLoad;
            this.rating = rating;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public void setAvailable(boolean available) {
            this.isAvailable = available;
        }

        public Location getLocation() {
            return location;
        }

        public int getCurrentLoad() {
            return currentLoad;
        }

        public double getRating() {
            return rating;
        }

        public void assignTo(HttpRequest request) {
            // Implement your assignment logic here
            System.out.println("Assigned driver to request with pickup at: " 
                + request.getPickupLocation().getLatitude() + ", " 
                + request.getPickupLocation().getLongitude());
        }
    }

    public static class Location {
        private double latitude;
        private double longitude;

        public Location(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }
    }
}
