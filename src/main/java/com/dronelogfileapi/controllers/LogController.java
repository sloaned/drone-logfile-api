package com.dronelogfileapi.controllers;

import com.dronelogfileapi.domain.FlightLogfile;
import com.dronelogfileapi.domain.FlightStartEndValue;
import com.dronelogfileapi.service.FlightLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {
    private final static Logger LOGGER = LoggerFactory.getLogger(LogController.class);

    private FlightLogService flightLogService;

    @Autowired
    public LogController(FlightLogService flightLogService) {
        this.flightLogService = flightLogService;
    }

    @PostMapping
    public Integer createLog(@RequestBody FlightLogfile logFile) {
        return flightLogService.saveLogfile(logFile);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public FlightLogfile getLog(@PathVariable(name = "id") String id, HttpServletResponse response) throws IOException {
        FlightLogfile file = null;
        try {
            Integer logfileId = Integer.valueOf(id);
            file = flightLogService.getLogfile(logfileId);
        } catch (NumberFormatException | FileNotFoundException e) {
            LOGGER.error("Error occurred retrieving logfile with id {}", id, e);
            response.sendError(404, String.format("There is no logfile for id %s", id));
        }

        return file;
    }

    @GetMapping("/{id}/values/{fields}")
    @ResponseBody
    public List<FlightStartEndValue> getLogStartAndEndValues(
            @PathVariable(name = "id") Integer id,
            @PathVariable(name = "fields") List<String> fields,
            HttpServletResponse response) throws IOException {
        List<FlightStartEndValue> values = null;
        try {
            values = flightLogService.getLogStartAndEndValues(id, fields);
        } catch (Exception e) {
            LOGGER.error("Error occurred retrieving start and end values for logfile with id {}", id, e);
            response.sendError(404, String.format("There is no logfile for id %s", id));
        }


        return values;
    }

}
