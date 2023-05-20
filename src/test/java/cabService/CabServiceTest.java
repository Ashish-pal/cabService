package cabService;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void CalculateTotalFareTest() {
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
}
