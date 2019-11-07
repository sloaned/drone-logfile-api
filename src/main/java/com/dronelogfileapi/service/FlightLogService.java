package com.dronelogfileapi.service;

import com.dronelogfileapi.domain.FlightLogfile;
import com.dronelogfileapi.domain.FlightStartEndValueReport;

import java.io.FileNotFoundException;
import java.util.List;

public interface FlightLogService {

    Integer saveLogfile(FlightLogfile logfile);

    FlightLogfile getLogfile(Integer logfileId) throws FileNotFoundException;

    FlightStartEndValueReport getLogStartAndEndValues(Integer logfileId, List<String> fields) throws FileNotFoundException;
}
