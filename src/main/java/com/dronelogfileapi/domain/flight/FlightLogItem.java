package com.dronelogfileapi.domain.flight;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FlightLogItem {
    
    @JsonIgnore
    private int logfile_id;
    
    private String timestamp;
    private BigDecimal aircraft_lon;
    private BigDecimal aircraft_lat;
    private BigDecimal aircraft_altitude;
    private BigDecimal aircraft_speed;
    private BigDecimal aircraft_heading;
    private BigDecimal aircraft_vel_x;
    private BigDecimal aircraft_vel_y;
    private BigDecimal aircraft_vel_z;
    private BigDecimal aircraft_pitch;
    private BigDecimal aircraft_roll;
    private BigDecimal aircraft_yaw;
    private int aircraft_satellites;
    private Boolean aircraft_motors_on;
    private Boolean aircraft_flying;
    private String aircraft_flight_mode;
    private Integer aircraft_flight_mode_value;
    private Boolean aircraft_imu_preheating;
    private Boolean aircraft_ultrasonic_on;
    private BigDecimal aircraft_ultrasonic_altitude;
    private Boolean aircraft_vision_on;
    private Boolean aircraft_gps_signal;
    private BigDecimal aircraft_gps_signal_value;
    private int aircraft_smart_gohome_flight_time_remaining;
    private Boolean aircraft_smart_gohome_requesting;
    private String aircraft_last_updated;
    private int battery_power;
    private int battery_current;
    private int battery_voltage;
    private BigDecimal battery_temperature;
    private int battery_charge;
    private String battery_last_updated;
    private int battery_cell_one;
    private int battery_cell_two;
    private int battery_cell_three;
    private int battery_cell_four;
    private int battery_cell_five;
    private int battery_cell_six;
    private String battery_cell_last_updated;
    private BigDecimal gcs_lat;
    private BigDecimal gcs_lon;
    private String gcs_location_last_updated;
    private BigDecimal gimbal_pitch;
    private BigDecimal gimbal_roll;
    private BigDecimal gimbal_yaw;
    private BigDecimal gimbal_mode;

}
