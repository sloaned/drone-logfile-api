package com.dronelogfileapi.domain.flight.flightdata;

import com.dronelogfileapi.domain.flight.flightdata.AircraftSmartGohome;
import lombok.Getter;

@Getter
public class FlightSummary {

    private float home_location_lat;
    private float home_location_lon;
    private AircraftSmartGohome aircraft_smart_gohome;
}
