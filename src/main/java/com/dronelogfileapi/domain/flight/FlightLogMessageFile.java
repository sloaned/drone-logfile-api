package com.dronelogfileapi.domain.flight;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FlightLogMessageFile {

    private LocalDateTime creation_dtg;
    private String logging_type;
    private String filename;
}
