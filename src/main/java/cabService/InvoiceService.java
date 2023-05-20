package cabService;

import java.util.List;

public class InvoiceService {
    private CabServiceGen invoiceGenerator;
    private RideRepository rideRepository;

    public InvoiceService(CabServiceGen invoiceGenerator, RideRepository rideRepository) {
        this.invoiceGenerator = invoiceGenerator;
        this.rideRepository = rideRepository;
    }

    public Invoice getInvoiceByUserId(String userId) {
        List<Ride> rides = rideRepository.getRidesByUserId(userId);
        boolean isPremiumRide = true;
        return invoiceGenerator.calculateInvoice(rides, isPremiumRide);
    }
}

class RideRepository {
    public List<Ride> getRidesByUserId(String userId) {
        return null;
    }
}
