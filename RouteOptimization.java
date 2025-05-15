import java.util.ArrayList;
import java.util.List;

public class RouteOptimization {

    public static class Location {
        private final String name;
        private final double latitude;
        private final double longitude;

        public Location(String name, double latitude, double longitude) {
            this.name = name;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getName() {
            return name;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        // Calculate Euclidean distance to another location
        public double distanceTo(Location other) {
            double latDiff = this.latitude - other.latitude;
            double lonDiff = this.longitude - other.longitude;
            return Math.sqrt(latDiff * latDiff + lonDiff * lonDiff);
        }

        @Override
        public String toString() {
            return name + "(" + latitude + "," + longitude + ")";
        }
    }

    /**
     * Optimizes route using Nearest Neighbor heuristic.
     * 
     * @param locations List of locations to visit
     * @return Optimized route as ordered list of locations
     */
    public List<Location> optimizeRoute(List<Location> locations) {
        if (locations == null || locations.isEmpty()) {
            return new ArrayList<>();
        }

        List<Location> route = new ArrayList<>();
        List<Location> unvisited = new ArrayList<>(locations);

        // Start from the first location
        Location current = unvisited.remove(0);
        route.add(current);

        while (!unvisited.isEmpty()) {
            Location nearest = null;
            double minDistance = Double.MAX_VALUE;

            for (Location loc : unvisited) {
                double dist = current.distanceTo(loc);
                if (dist < minDistance) {
                    minDistance = dist;
                    nearest = loc;
                }
            }

            route.add(nearest);
            unvisited.remove(nearest);
            current = nearest;
        }

        return route;
    }

    // Example usage
    public static void main(String[] args) {
        RouteOptimization optimizer = new RouteOptimization();

        List<Location> locations = List.of(
            new Location("A", 0, 0),
            new Location("B", 2, 3),
            new Location("C", 5, 4),
            new Location("D", 1, 1),
            new Location("E", 6, 1)
        );

        List<Location> optimizedRoute = optimizer.optimizeRoute(locations);

        System.out.println("Optimized route:");
        optimizedRoute.forEach(loc -> System.out.println(loc));
    }
}
