package cabService;

import java.util.List;

public class CabServiceGen {
    private static final double COST_PER_KILOMETER = 10.0;
    private static final double COST_PER_MINUTE = 1.0;
    private static final double MINIMUM_FARE = 5.0;

    public double calculateFare(double distance, double time) {
        double fare = distance * COST_PER_KILOMETER + time * COST_PER_MINUTE;
        return Math.max(fare, MINIMUM_FARE);
    }

    public double calculateTotalFare(List<Ride> rides) {
        double totalFare = 0.0;
        for (Ride ride : rides) {
            totalFare += calculateFare(ride.getDistance(), ride.getTime());
        }
        return totalFare;
    }

    public Invoice calculateInvoice(List<Ride> rides) {
        double totalFare = 0.0;
        int totalRides = rides.size();
        for (Ride ride : rides) {
            totalFare += calculateFare(ride.getDistance(), ride.getTime());
        }
        double averageFarePerRide = totalFare / totalRides;
        return new Invoice(totalRides, totalFare, averageFarePerRide);
    }
}
