package com.dronelogfileapi.domain.flight.flightdata;

import lombok.Data;

@Data
public class Camera {

    private String serial_number;
    private String model;
    private String firmware_version;
}
