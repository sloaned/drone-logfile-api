package com.dronelogfileapi.domain.flight;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightLogMessageFile {

    private String creation_dtg;
    private String logging_type;
    private String filename;
}
