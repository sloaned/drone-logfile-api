package com.dronelogfileapi.domain.flight;

import lombok.Getter;

@Getter
public class LogMessage {

    private FlightLogMessageFile file;
    private String message_type;
    private FlightLog flight_logging;
    private FlightData flight_data;
}
