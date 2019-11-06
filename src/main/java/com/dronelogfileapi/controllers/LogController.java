package com.dronelogfileapi.controllers;

import com.dronelogfileapi.domain.FlightLogfile;
import com.dronelogfileapi.domain.FlightStartEndValue;
import com.dronelogfileapi.service.FlightLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {

    private FlightLogService flightLogService;

    @Autowired
    public LogController(FlightLogService flightLogService) {
        this.flightLogService = flightLogService;
    }

    @PostMapping("")
    public Integer createLog(@RequestBody FlightLogfile logFile) {
        return flightLogService.saveLogfile(logFile);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public FlightLogfile getLog(@PathVariable(name = "id") Integer id, HttpServletResponse response) throws IOException {
        FlightLogfile file = null;
        try {
            file = flightLogService.getLogfile(id);
        } catch (Exception e) {
            response.sendError(404, "There is no logfile with that id");
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
            response.sendError(404, "There is no logfile with that id");
        }


        return values;
    }

}
