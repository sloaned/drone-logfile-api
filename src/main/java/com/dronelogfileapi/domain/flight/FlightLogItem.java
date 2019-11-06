package com.dronelogfileapi.domain.flight;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class FlightLogItem {
    
    @JsonIgnore
    private int logfile_id;
    
    private String timestamp;
    private double aircraft_lon;
    private double aircraft_lat;
    private double aircraft_altitude;
    private double aircraft_speed;
    private double aircraft_heading;
    private double aircraft_vel_x;
    private double aircraft_vel_y;
    private double aircraft_vel_z;
    private double aircraft_pitch;
    private double aircraft_roll;
    private double aircraft_yaw;
    private int aircraft_satellites;
    private Boolean aircraft_motors_on;
    private Boolean aircraft_flying;
    private String aircraft_flight_mode;
    private Integer aircraft_flight_mode_value;
    private Boolean aircraft_imu_preheating;
    private Boolean aircraft_ultrasonic_on;
    private double aircraft_ultrasonic_altitude;
    private Boolean aircraft_vision_on;
    private Boolean aircraft_gps_signal;
    private double aircraft_gps_signal_value;
    private int aircraft_smart_gohome_flight_time_remaining;
    private Boolean aircraft_smart_gohome_requesting;
    private String aircraft_last_updated;
    private int battery_power;
    private int battery_current;
    private int battery_voltage;
    private double battery_temperature;
    private int battery_charge;
    private String battery_last_updated;
    private int battery_cell_one;
    private int battery_cell_two;
    private int battery_cell_three;
    private int battery_cell_four;
    private int battery_cell_five;
    private int battery_cell_six;
    private String battery_cell_last_updated;
    private double gcs_lat;
    private double gcs_lon;
    private String gcs_location_last_updated;
    private double gimbal_pitch;
    private double gimbal_roll;
    private double gimbal_yaw;
    private double gimbal_mode;

}
