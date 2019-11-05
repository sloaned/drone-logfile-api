package com.dronelogfileapi.domain.flight;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightLogEvent {

    @JsonIgnore
    private int logfile_id;

    private String event_info;
    private LocalDateTime event_timestamp;
    private String event_type;
}
