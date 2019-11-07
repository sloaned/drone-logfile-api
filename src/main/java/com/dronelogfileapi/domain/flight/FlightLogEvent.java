package com.dronelogfileapi.domain.flight;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class FlightLogEvent {

    @JsonIgnore
    private int logfile_id;

    private String event_info;
    private String event_timestamp;
    private String event_type;
}
