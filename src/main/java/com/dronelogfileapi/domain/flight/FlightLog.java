package com.dronelogfileapi.domain.flight;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class FlightLog {

    private List<FlightLogEvent> event;
    private String altitude_system;
    private LocalDateTime logging_start_dtg;
    private List<List<String>> flight_logging_items;
    private List<String> flight_logging_keys;

    @JsonIgnore
    private List<FlightLogItem> flightLogItems;
    @JsonIgnore
    private List<FlightLogKey> flightLogKeys;
}
