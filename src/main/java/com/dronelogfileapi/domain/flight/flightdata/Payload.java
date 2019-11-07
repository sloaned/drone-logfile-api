package com.dronelogfileapi.domain.flight.flightdata;

import lombok.Data;

@Data
public class Payload {

    private Camera camera;
    private Gimbal gimbal;

    public Camera getCamera() {
        return camera == null ? new Camera() : camera;
    }

    public Gimbal getGimbal() {
        return gimbal == null ? new Gimbal() : gimbal;
    }
}
