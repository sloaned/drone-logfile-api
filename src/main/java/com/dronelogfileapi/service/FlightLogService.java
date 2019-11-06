package com.dronelogfileapi.service;

import com.dronelogfileapi.domain.FlightLogfile;
import com.dronelogfileapi.domain.FlightStartEndValue;

import java.util.List;

public interface FlightLogService {

    Integer saveLogfile(FlightLogfile logfile);

    FlightLogfile getLogfile(Integer logfileId) throws Exception;

    List<FlightStartEndValue> getLogStartAndEndValues(Integer logfileId, List<String> fields) throws Exception;
}
