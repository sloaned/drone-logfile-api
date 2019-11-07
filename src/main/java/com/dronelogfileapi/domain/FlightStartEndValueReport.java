package com.dronelogfileapi.domain;

import lombok.Value;

import java.util.List;

@Value
public class FlightStartEndValueReport {

    private Integer logfileId;
    private List<FlightStartEndValue> values;

    @Value
    public static class FlightStartEndValue {
        private String fieldName;
        private Object startValue;
        private Object endValue;
    }
}
