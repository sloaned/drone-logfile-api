package com.dronelogfileapi.domain.flight;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightLogItem {
    
    @JsonIgnore
    private int logfile_id;
    
    private LocalDateTime timestamp;
    private float aircraft_lon;
    private float aircraft_lat;
    private float aircraft_altitude;
    private float aircraft_speed;
    private float aircraft_heading;
    private float aircraft_vel_x;
    private float aircraft_vel_y;
    private float aircraft_vel_z;
    private float aircraft_pitch;
    private float aircraft_roll;
    private float aircraft_yaw;
    private int aircraft_satellites;
    private Boolean aircraft_motors_on;
    private Boolean aircraft_flying;
    private String aircraft_flight_mode;
    private Integer aircraft_flight_mode_value;
    private Boolean aircraft_imu_preheating;
    private Boolean aircraft_ultrasonic_on;
    private float aircraft_ultrasonic_altitude;
    private Boolean aircraft_vision_on;
    private Boolean aircraft_gps_signal;
    private float aircraft_gps_signal_value;
    private int aircraft_smart_gohome_flight_time_remaining;
    private Boolean aircraft_smart_gohome_requesting;
    private LocalDateTime aircraft_last_updated;
    private int battery_power;
    private int battery_current;
    private int battery_voltage;
    private float battery_temperature;
    private int battery_charge;
    private LocalDateTime battery_last_updated;
    private int battery_cell_one;
    private int battery_cell_two;
    private int battery_cell_three;
    private int battery_cell_four;
    private int battery_cell_five;
    private int battery_cell_six;
    private LocalDateTime battery_cell_last_updated;
    private float gcs_lat;
    private float gcs_lon;
    private LocalDateTime gcs_location_last_updated;
    private float gimbal_pitch;
    private float gimbal_roll;
    private float gimbal_yaw;
    private float gimbal_mode;

}
