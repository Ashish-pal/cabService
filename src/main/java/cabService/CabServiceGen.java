package cabService;

public class CabServiceGen {
    private static final double COST_PER_KILOMETER = 10.0;
    private static final double COST_PER_MINUTE = 1.0;
    private static final double MINIMUM_FARE = 5.0;

    public double calculateFare(double distance, double time) {
        double fare = distance * COST_PER_KILOMETER + time * COST_PER_MINUTE;
        return Math.max(fare, MINIMUM_FARE);
    }
}
