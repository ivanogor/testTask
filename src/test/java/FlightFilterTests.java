import com.gridnine.testing.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightFilterTests {
    private List<Flight> flights;
    private FlightFilterManager filterManager;

    @BeforeEach
    void setUp() {
        flights = FlightBuilder.createFlights();
        filterManager = new FlightFilterManager();
    }

    @Test
    public void DepartureBeforeNowFilterTest(){
        DepartureBeforeNowFilter filter = new DepartureBeforeNowFilter();
        List<Flight> result = filterManager.applyFilter(flights, List.of(filter));

        assertEquals(5, result.size());
    }

    @Test
    public void FlightsWithGroundTimeExceedingTwoHoursTest(){
        GroundTimeExceedingTwoHoursFilter filter = new GroundTimeExceedingTwoHoursFilter();
        List<Flight> result = filterManager.applyFilter(flights, List.of(filter));

        assertEquals(4, result.size());
    }

    @Test
    public void ArrivalBeforeDepartureFilterTest(){
        ArrivalBeforeDepartureFilter filter = new ArrivalBeforeDepartureFilter();
        List<Flight> result = filterManager.applyFilter(flights, List.of(filter));

        assertEquals(5, result.size());
    }

    @Test
    public void testCombinedFilters() {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilterManager filterManager = new FlightFilterManager();
        List<FlightFilter> filters = List.of(new ArrivalBeforeDepartureFilter(), new DepartureBeforeNowFilter(), new GroundTimeExceedingTwoHoursFilter());
        List<Flight> filteredFlights = filterManager.applyFilter(flights, filters);
        assertEquals(2, filteredFlights.size());
    }
}
