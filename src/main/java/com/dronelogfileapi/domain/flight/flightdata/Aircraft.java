package com.dronelogfileapi.domain.flight.flightdata;

import lombok.Data;

@Data
public class Aircraft {

    private String manufacturer;
    private String serial_number;
    private String name;
    private String model;
    private String firmware_version;
}
