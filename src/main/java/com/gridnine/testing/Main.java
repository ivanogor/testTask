package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilterManager filterManager = new FlightFilterManager();

        List<Flight> flightsWithSegmentsWithInvalidArrivalDate = filterManager.applyFilter(flights, List.of(new DepartureBeforeNowFilter()));
        System.out.println(flightsWithSegmentsWithInvalidArrivalDate);

        List<Flight> flightsDepartureBeforeNowFilter = filterManager.applyFilter(flights, List.of(new GroundTimeExceedingTwoHoursFilter()));
        System.out.println(flightsDepartureBeforeNowFilter);

        List<Flight> flightsBeforeCurrentTime = filterManager.applyFilter(flights, List.of(new DepartureBeforeNowFilter()));
        System.out.println(flightsBeforeCurrentTime);
    }
}