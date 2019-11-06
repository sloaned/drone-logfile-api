package com.dronelogfileapi.domain.flight.flightdata;

import lombok.Data;

@Data
public class LogfileDeviceOrigin {

    private String user_interface_idiom;
    private String operating_system;
    private String model;
    private String device_ssid;
}
