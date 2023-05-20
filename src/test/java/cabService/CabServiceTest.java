package cabService;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CabServiceTest {
    CabServiceGen invoiceGenerator = new CabServiceGen();

    @Test
    public void testCalculateFare() {
        double fare1 = invoiceGenerator.calculateFare(10.0, 30.0);
        assertEquals(130.0, fare1);

        double fare2 = invoiceGenerator.calculateFare(5.0, 15.0);
        assertEquals(55.0, fare2);

        double fare3 = invoiceGenerator.calculateFare(2.0, 5.0);
        assertEquals(25.0, fare3);

        double fare4 = invoiceGenerator.calculateFare(0.0, 0.0);
        assertEquals(5.0, fare4);

        double fare5 = invoiceGenerator.calculateFare(15.0, 0.0);
        assertEquals(150.0, fare5);
    }

    @Test
    public void calculateTotalFareTest() {
        List<Ride> rides1 = new ArrayList<>();
        rides1.add(new Ride(10.0, 30.0));
        rides1.add(new Ride(5.0, 15.0));
        rides1.add(new Ride(2.0, 5.0));
        double totalFare1 = invoiceGenerator.calculateTotalFare(rides1);
        assertEquals(210.0, totalFare1);

        List<Ride> rides2 = new ArrayList<>();
        rides2.add(new Ride(15.0, 45.0));
        double totalFare2 = invoiceGenerator.calculateTotalFare(rides2);
        assertEquals(195.0, totalFare2);

        List<Ride> rides3 = new ArrayList<>();
        double totalFare3 = invoiceGenerator.calculateTotalFare(rides3);
        assertEquals(0.0, totalFare3);
    }

    @Test
    public void calculateInvoiceTest() {
        List<Ride> rides1 = new ArrayList<>();
        rides1.add(new Ride(10.0, 30.0));
        rides1.add(new Ride(5.0, 15.0));
        rides1.add(new Ride(2.0, 5.0));
        Invoice invoice1 = invoiceGenerator.calculateInvoice(rides1);
        assertEquals(3, invoice1.getTotalRides());
        assertEquals(210.0, invoice1.getTotalFare());
        assertEquals(70.0, invoice1.getAverageFarePerRide());

        List<Ride> rides2 = new ArrayList<>();
        rides2.add(new Ride(15.0, 45.0));
        Invoice invoice2 = invoiceGenerator.calculateInvoice(rides2);
        assertEquals(1, invoice2.getTotalRides());
        assertEquals(195.0, invoice2.getTotalFare());
        assertEquals(195.0, invoice2.getAverageFarePerRide());

        List<Ride> rides3 = new ArrayList<>();
        Invoice invoice3 = invoiceGenerator.calculateInvoice(rides3);
        assertEquals(0, invoice3.getTotalRides());
        assertEquals(0.0, invoice3.getTotalFare());
        assertEquals(0.0, invoice3.getAverageFarePerRide());
    }

    @Test
    public void GetInvoiceByUserIdTest() {
        RideRepository rideRepository = mock(RideRepository.class);
        when(rideRepository.getRidesByUserId("user1")).thenReturn(getSampleRides());
        InvoiceService invoiceService = new InvoiceService(invoiceGenerator, rideRepository);
        Invoice invoice = invoiceService.getInvoiceByUserId("user1");
        assertEquals(3, invoice.getTotalRides());
        assertEquals(210.0, invoice.getTotalFare());
        assertEquals(70.0, invoice.getAverageFarePerRide());
    }

    private List<Ride> getSampleRides() {
        List<Ride> rides = new ArrayList<>();
        rides.add(new Ride(10.0, 30.0));
        rides.add(new Ride(5.0, 15.0));
        rides.add(new Ride(2.0, 5.0));
        return rides;
    }
}