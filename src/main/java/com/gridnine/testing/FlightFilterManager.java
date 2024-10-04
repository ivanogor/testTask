package com.gridnine.testing;

import java.util.List;

public class FlightFilterManager {
    public List<Flight> applyFilter(List<Flight> flights, List<FlightFilter> filters) {
        for (FlightFilter flightFilter : filters) {
            flights = flightFilter.filter(flights);
        }
        return flights;
    }
}
