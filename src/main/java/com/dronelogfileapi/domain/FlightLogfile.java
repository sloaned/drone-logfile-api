package com.dronelogfileapi.domain;

import com.dronelogfileapi.domain.flight.LogMessage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class FlightLogfile {

    @JsonIgnore
    private String logfile_id;

    private String exchange_type;
    private String exchanger;
    private String uploadingOrgUuid;
    private String uploadingPilotUuid;
    private String flight_session_id;
    private LogMessage message;

    public LogMessage getMessage() {
        return message == null ? new LogMessage() : message;
    }
}
