package com.dronelogfileapi.domain.flight;

import lombok.Data;

@Data
public class LogMessage {

    private FlightLogMessageFile file;
    private String message_type;
    private FlightLog flight_logging;
    private FlightData flight_data;

    public FlightLogMessageFile getFile() {
        return file == null ? new FlightLogMessageFile() : file;
    }

    public FlightLog getFlight_logging() {
        return flight_logging == null ? new FlightLog() : flight_logging;
    }

    public FlightData getFlight_data() {
        return flight_data == null ? new FlightData() : flight_data;
    }
}
