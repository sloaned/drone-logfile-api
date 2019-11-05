package com.dronelogfileapi.domain.flight.flightdata;

import lombok.Getter;

@Getter
public class Aircraft {

    private String manufacturer;
    private String serial_number;
    private String name;
    private String model;
    private String firmware_version;
}
