package cabService;

public class Invoice {
    private int totalRides;
    private double totalFare;
    private double averageFarePerRide;

    public Invoice(int totalRides, double totalFare, double averageFarePerRide) {
        this.totalRides = totalRides;
        this.totalFare = totalFare;
        this.averageFarePerRide = averageFarePerRide;
    }

    public int getTotalRides() {
        return totalRides;
    }

    public double getTotalFare() {
        return totalFare;
    }

    public double getAverageFarePerRide() {
        return averageFarePerRide;
    }
}
