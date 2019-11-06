package com.dronelogfileapi.domain.flight;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class FlightLog {

    private List<FlightLogEvent> event;
    private String altitude_system;
    private String logging_start_dtg;
    private List<List<Object>> flight_logging_items;
    private List<String> flight_logging_keys;

    @JsonIgnore
    private List<FlightLogItem> flightLogItems = new ArrayList<>();
    @JsonIgnore
    private List<FlightLogKey> flightLogKeys = new ArrayList<>();
}
