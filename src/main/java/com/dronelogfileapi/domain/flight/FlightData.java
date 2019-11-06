package com.dronelogfileapi.domain.flight;

import com.dronelogfileapi.domain.flight.flightdata.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightData {

    private Payload payload;
    private RemoteController remote_controller;
    private Battery battery;
    private Aircraft aircraft;
    private FlightSummary summary;
    private Gcs gcs;
    private String flight_session_start;
    private String flight_session_end;
    private FlightController flight_controller;
    private LogfileDeviceOrigin logfile_device_origin;
}
