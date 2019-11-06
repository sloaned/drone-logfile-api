package com.dronelogfileapi.domain;

import lombok.Data;

@Data
public class FlightStartEndValue {

    private String fieldName;
    private Object startValue;
    private Object endValue;
}
