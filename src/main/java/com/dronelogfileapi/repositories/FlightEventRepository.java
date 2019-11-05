package com.dronelogfileapi.repositories;

import com.dronelogfileapi.domain.flight.FlightLogEvent;

import java.util.List;

public interface FlightEventRepository {

    void batchSave(List<FlightLogEvent> events);
    List<FlightLogEvent> findByLogfileId(Integer logfileId);
}
