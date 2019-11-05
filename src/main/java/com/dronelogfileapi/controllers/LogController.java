package com.dronelogfileapi.controllers;

import com.dronelogfileapi.domain.flight.FlightLogMessageFile;
import org.springframework.web.bind.annotation.*;

@RestController
public class LogController {

    @PostMapping("/log")
    public void createLog(@RequestBody FlightLogMessageFile logFile) {

    }

    @GetMapping("/log/{id}")
    @ResponseBody
    public String getLog(@PathVariable String id) {
        return "Your id = " + id;
    }
}
