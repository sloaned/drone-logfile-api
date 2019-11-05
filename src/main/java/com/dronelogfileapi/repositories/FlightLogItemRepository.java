package com.dronelogfileapi.repositories;

import com.dronelogfileapi.domain.flight.FlightLogItem;

import java.util.List;

public interface FlightLogItemRepository {

    void batchSave(List<FlightLogItem> items);

    List<FlightLogItem> findByLogfileId(Integer logfileId);
}
