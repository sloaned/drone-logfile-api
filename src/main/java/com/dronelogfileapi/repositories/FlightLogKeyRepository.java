package com.dronelogfileapi.repositories;

import com.dronelogfileapi.domain.flight.FlightLogKey;

import java.util.List;

public interface FlightLogKeyRepository {

    void batchSave(List<FlightLogKey> flightLogKeys);

    List<FlightLogKey> findByLogfileId(Integer logfileId);
}
