package cabService;

import java.util.List;

public class CabServiceGen {
    private static final double NORMAL_COST_PER_KILOMETER = 10.0;
    private static final double NORMAL_COST_PER_MINUTE = 1.0;
    private static final double NORMAL_MINIMUM_FARE = 5.0;
    private static final double PREMIUM_COST_PER_KILOMETER = 15.0;
    private static final double PREMIUM_COST_PER_MINUTE = 2.0;
    private static final double PREMIUM_MINIMUM_FARE = 20.0;

    public double calculateFare(double distance, double time, boolean isPremiumRide) {
        double costPerKilometer = isPremiumRide ? PREMIUM_COST_PER_KILOMETER : NORMAL_COST_PER_KILOMETER;
        double costPerMinute = isPremiumRide ? PREMIUM_COST_PER_MINUTE : NORMAL_COST_PER_MINUTE;
        double minimumFare = isPremiumRide ? PREMIUM_MINIMUM_FARE : NORMAL_MINIMUM_FARE;

        double fare = distance * costPerKilometer + time * costPerMinute;
        return Math.max(fare, minimumFare);
    }

    public double calculateTotalFare(List<Ride> rides, boolean isPremiumRide) {
        double totalFare = 0.0;
        for (Ride ride : rides) {
            totalFare += calculateFare(ride.getDistance(), ride.getTime(), isPremiumRide);
        }
        return totalFare;
    }

    public Invoice calculateInvoice(List<Ride> rides, boolean isPremiumRide) {
        double totalFare = calculateTotalFare(rides, isPremiumRide);
        int totalRides = rides.size();
        double averageFarePerRide = totalFare / totalRides;
        return new Invoice(totalRides, totalFare, averageFarePerRide);
    }
}
