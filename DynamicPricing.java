public class DynamicPricing {

    /**
     * Calculate dynamic price based on demand and time to event.
     *
     * @param basePrice      The base price of the ticket/booking.
     * @param currentBookings Number of tickets/slots already booked.
     * @param totalCapacity   Total available tickets/slots.
     * @param hoursToEvent    Hours left until the event starts.
     * @return The dynamically adjusted price.
     */
    public double calculatePrice(double basePrice, int currentBookings, int totalCapacity, int hoursToEvent) {
        if (totalCapacity <= 0) {
            throw new IllegalArgumentException("Total capacity must be greater than zero");
        }

        double demandRatio = (double) currentBookings / totalCapacity;
        double priceMultiplier;

        if (demandRatio > 0.8) {
            priceMultiplier = 1.5;  // High demand
        } else if (demandRatio > 0.5) {
            priceMultiplier = 1.2;  // Medium demand
        } else {
            priceMultiplier = 1.0;  // Low demand
        }

        if (hoursToEvent < 24) {
            priceMultiplier += 0.2;  // Increase price if event is near
        }

        return basePrice * priceMultiplier;
    }

    // Example usage
    public static void main(String[] args) {
        DynamicPricing pricing = new DynamicPricing();

        double basePrice = 100.0;
        int currentBookings = 85;
        int totalCapacity = 100;
        int hoursToEvent = 12;

        double finalPrice = pricing.calculatePrice(basePrice, currentBookings, totalCapacity, hoursToEvent);

        System.out.println("Dynamic Price: " + finalPrice);
    }
}
