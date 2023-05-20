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
        double fare1 = invoiceGenerator.calculateFare(10.0, 30.0, false);
        assertEquals(130.0, fare1);

        double fare2 = invoiceGenerator.calculateFare(5.0, 15.0, true);
        assertEquals(75.0, fare2);

        double fare3 = invoiceGenerator.calculateFare(2.0, 5.0, false);
        assertEquals(25.0, fare3);

        double fare4 = invoiceGenerator.calculateFare(0.0, 0.0, true);
        assertEquals(20.0, fare4);

        double fare5 = invoiceGenerator.calculateFare(15.0, 0.0, true);
        assertEquals(200.0, fare5);
    }

    @Test
    public void calculateTotalFareTest() {
        List<Ride> rides1 = new ArrayList<>();
        rides1.add(new Ride(10.0, 30.0));
        rides1.add(new Ride(5.0, 15.0));
        rides1.add(new Ride(2.0, 5.0));
        double totalFare1 = invoiceGenerator.calculateTotalFare(rides1, false);
        assertEquals(180.0, totalFare1);

        List<Ride> rides2 = new ArrayList<>();
        rides2.add(new Ride(15.0, 45.0));
        double totalFare2 = invoiceGenerator.calculateTotalFare(rides2, true);
        assertEquals(375.0, totalFare2);

        List<Ride> rides3 = new ArrayList<>();
        double totalFare3 = invoiceGenerator.calculateTotalFare(rides3, true);
        assertEquals(0.0, totalFare3);
    }

    @Test
    public void calculateInvoiceTest() {
        List<Ride> rides1 = new ArrayList<>();
        rides1.add(new Ride(10.0, 30.0));
        rides1.add(new Ride(5.0, 15.0));
        rides1.add(new Ride(2.0, 5.0));
        Invoice invoice1 = invoiceGenerator.calculateInvoice(rides1, true);
        assertEquals(3, invoice1.getTotalRides());
        assertEquals(180.0, invoice1.getTotalFare());
        assertEquals(60.0, invoice1.getAverageFarePerRide());

        List<Ride> rides2 = new ArrayList<>();
        rides2.add(new Ride(15.0, 45.0));
        Invoice invoice2 = invoiceGenerator.calculateInvoice(rides2, true);
        assertEquals(1, invoice2.getTotalRides());
        assertEquals(375.0, invoice2.getTotalFare());
        assertEquals(375.0, invoice2.getAverageFarePerRide());

        List<Ride> rides3 = new ArrayList<>();
        Invoice invoice3 = invoiceGenerator.calculateInvoice(rides3, true);
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
        assertEquals(180.0, invoice.getTotalFare());
        assertEquals(60.0, invoice.getAverageFarePerRide());
    }

    private List<Ride> getSampleRides() {
        List<Ride> rides = new ArrayList<>();
        rides.add(new Ride(10.0, 30.0));
        rides.add(new Ride(5.0, 15.0));
        rides.add(new Ride(2.0, 5.0));
        return rides;
    }

    @Test
    public void testCalculateTotalFare() {
        List<Ride> normalRides = new ArrayList<>();
        normalRides.add(new Ride(10.0, 30.0));
        normalRides.add(new Ride(5.0, 15.0));
        normalRides.add(new Ride(2.0, 5.0));
        double normalTotalFare = invoiceGenerator.calculateTotalFare(normalRides, false);
        assertEquals(130.0, normalTotalFare);

        List<Ride> premiumRides = new ArrayList<>();
        premiumRides.add(new Ride(10.0, 30.0));
        premiumRides.add(new Ride(5.0, 15.0));
        premiumRides.add(new Ride(2.0, 5.0));
        double premiumTotalFare = invoiceGenerator.calculateTotalFare(premiumRides, true);
        assertEquals(205.0, premiumTotalFare);

        List<Ride> mixedRides = new ArrayList<>();
        mixedRides.add(new Ride(10.0, 30.0));
        mixedRides.add(new Ride(5.0, 15.0));
        mixedRides.add(new Ride(2.0, 5.0));
        mixedRides.add(new Ride(8.0, 20.0));
        mixedRides.add(new Ride(3.0, 10.0));
        double mixedTotalFare = invoiceGenerator.calculateTotalFare(mixedRides, false);
        assertEquals(215.0, mixedTotalFare);
    }
}