package com.dronelogfileapi.repositories;

import com.dronelogfileapi.domain.FlightLogfile;

public interface LogfileRepository {

    Integer save(FlightLogfile logfile);

    FlightLogfile findById(Integer id);
}
