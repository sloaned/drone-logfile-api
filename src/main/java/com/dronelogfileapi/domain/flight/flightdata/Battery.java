package com.dronelogfileapi.domain.flight.flightdata;

import lombok.Getter;

@Getter
public class Battery {

    private String serial_number;
    private String remaining_life;
    private String discharges;
    private String full_charge_volume;
    private String cell_number;
    private String firmware_version;
}
