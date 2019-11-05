package com.dronelogfileapi.domain.flight;

import lombok.Data;

@Data
public class FlightLogKey {

    private int logfile_id;

    private String fieldName;
    private int order;
}
