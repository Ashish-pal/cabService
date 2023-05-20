package cabService;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CabServiceTest {
    @Test
    public void testCalculateFare() {
        CabServiceGen invoiceGenerator = new CabServiceGen();

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
}
