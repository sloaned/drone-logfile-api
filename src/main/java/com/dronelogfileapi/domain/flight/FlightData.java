package com.dronelogfileapi.domain.flight;

import com.dronelogfileapi.domain.flight.flightdata.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FlightData {

    private Payload payload;
    private RemoteController remote_controller;
    private Aircraft aircraft;
    private FlightSummary summary;
    private Gcs gcs;
    private LocalDateTime flight_session_start;
    private LocalDateTime flight_session_end;
    private FlightController flight_controller;
    private LogfileDeviceOrigin logfile_device_origin;
}
