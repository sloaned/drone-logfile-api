package com.dronelogfileapi.domain.flight;

import com.dronelogfileapi.domain.flight.flightdata.*;
import lombok.Data;

@Data
public class FlightData {

    private Payload payload;
    private RemoteController remote_controller;
    private Aircraft aircraft;
    private FlightSummary summary;
    private Gcs gcs;
    private String flight_session_start;
    private String flight_session_end;
    private FlightController flight_controller;
    private Battery battery;
    private LogfileDeviceOrigin logfile_device_origin;

    public Payload getPayload() {
        return payload == null ? new Payload() : payload;
    }

    public RemoteController getRemote_controller() {
        return remote_controller == null ? new RemoteController() : remote_controller;
    }

    public Battery getBattery() {
        return battery == null ? new Battery() : battery;
    }

    public Aircraft getAircraft() {
        return aircraft == null ? new Aircraft() : aircraft;
    }

    public FlightSummary getSummary() {
        return summary == null ? new FlightSummary() : summary;
    }

    public Gcs getGcs() {
        return gcs == null ? new Gcs() : gcs;
    }

    public FlightController getFlight_controller() {
        return flight_controller == null ? new FlightController() : flight_controller;
    }

    public LogfileDeviceOrigin getLogfile_device_origin() {
        return logfile_device_origin == null ? new LogfileDeviceOrigin() : logfile_device_origin;
    }
}
