package com.dronelogfileapi.domain.flight;

import lombok.Data;

@Data
public class LogMessage {

    private FlightLogMessageFile file;
    private String message_type;
    private FlightLog flight_logging;
    private FlightData flight_data;
}
