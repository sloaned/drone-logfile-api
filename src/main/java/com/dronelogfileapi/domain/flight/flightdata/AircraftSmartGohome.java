package com.dronelogfileapi.domain.flight.flightdata;

import lombok.Data;

@Data
public class AircraftSmartGohome {

    private String flight_return_time;
    private String landing_power;
    private String return_power;
    private String landing_radius;
    private String landing_time;
}
