package com.dronelogfileapi.domain.flight.flightdata;

import lombok.Data;

@Data
public class FlightSummary {

    private String home_location_lat;
    private String home_location_lon;
    private AircraftSmartGohome aircraft_smart_gohome;
}
