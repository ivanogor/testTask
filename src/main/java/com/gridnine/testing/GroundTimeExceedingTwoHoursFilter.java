package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GroundTimeExceedingTwoHoursFilter implements FlightFilter {

    @Override
    public List<Flight> filter(List<Flight> flights) {
        List<Flight> result = new ArrayList<>();

        for (Flight flight : flights) {
            List<Segment> segments = flight.getSegments();
            long totalGroundTime = 0;

            for (int i = 0; i < segments.size() - 1; i++) {
                LocalDateTime arrivalDate = segments.get(i).getArrivalDate();
                LocalDateTime departureDate = segments.get(i + 1).getDepartureDate();
                totalGroundTime += Duration.between(arrivalDate, departureDate).toMinutes();
            }

            if (totalGroundTime <= 120) {
                result.add(flight);
            }
        }
        return result;
    }
}
