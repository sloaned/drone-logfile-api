package com.dronelogfileapi.domain;

import com.dronelogfileapi.domain.flight.LogMessage;
import lombok.Getter;

@Getter
public class FlightLogfile {

    private String logfile_id;
    private String exchange_type;
    private String exchanger;
    private String uploadingOrgUuid;
    private String uploadingPilotUuid;
    private String flight_session_id;
    private LogMessage message;
}
