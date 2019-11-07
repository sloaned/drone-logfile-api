package com.dronelogfileapi.repositories;

import com.dronelogfileapi.domain.flight.FlightLogItem;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightLogItemRepositoryImpl implements FlightLogItemRepository {


    private static final Logger LOGGER = LoggerFactory.getLogger(FlightLogKeyRepositoryImpl.class);

    private static final String BATCH_INSERT_SQL = "INSERT INTO flight_log_item (logfile_id, timestamp, aircraft_lon, " +
            "aircraft_lat, aircraft_altitude, aircraft_speed, aircraft_heading, aircraft_vel_x, aircraft_vel_y, aircraft_vel_z, " +
            "aircraft_pitch, aircraft_roll, aircraft_yaw, aircraft_satellites, aircraft_motors_on, aircraft_flying, " +
            "aircraft_flight_mode, aircraft_flight_mode_value, aircraft_imu_preheating, aircraft_ultrasonic_on, " +
            "aircraft_ultrasonic_altitude, aircraft_vision_on, aircraft_gps_signal, aircraft_gps_signal_value, " +
            "aircraft_smart_gohome_flight_time_remaining, aircraft_smart_gohome_requesting, aircraft_last_updated, " +
            "battery_power, battery_current, battery_voltage, battery_temperature, battery_charge, battery_last_updated, " +
            "battery_cell_one, battery_cell_two, battery_cell_three, battery_cell_four, battery_cell_five, battery_cell_six, " +
            "battery_cell_last_updated, gcs_lat, gcs_lon, gcs_location_last_updated, gimbal_pitch, gimbal_roll, gimbal_yaw, " +
            "gimbal_mode) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional
    @Override
    public void batchSave(List<FlightLogItem> items) {
        if (CollectionUtils.isEmpty(items)) {
            LOGGER.info("Batch save request with no items, exiting");
            return;
        }

        LOGGER.info("Batch save request for {} items", items.size());


        BatchPreparedStatementSetter statement = new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                FlightLogItem item = items.get(i);
                ps.setInt(1, item.getLogfile_id());
                ps.setString(2, String.valueOf(item.getTimestamp()));
                ps.setBigDecimal(3, item.getAircraft_lon());
                ps.setBigDecimal(4, item.getAircraft_lat());
                ps.setBigDecimal(5, item.getAircraft_altitude());
                ps.setBigDecimal(6, item.getAircraft_speed());
                ps.setBigDecimal(7, item.getAircraft_heading());
                ps.setBigDecimal(8, item.getAircraft_vel_x());
                ps.setBigDecimal(9, item.getAircraft_vel_y());
                ps.setBigDecimal(10, item.getAircraft_vel_z());
                ps.setBigDecimal(11, item.getAircraft_pitch());
                ps.setBigDecimal(12, item.getAircraft_roll());
                ps.setBigDecimal(13, item.getAircraft_yaw());
                ps.setInt(14, item.getAircraft_satellites());
                ps.setBoolean(15, item.getAircraft_motors_on());
                ps.setBoolean(16, item.getAircraft_flying());
                ps.setString(17, item.getAircraft_flight_mode());
                ps.setInt(18, item.getAircraft_flight_mode_value());
                ps.setBoolean(19, item.getAircraft_imu_preheating());
                ps.setBoolean(20, item.getAircraft_ultrasonic_on());
                ps.setBigDecimal(21, item.getAircraft_ultrasonic_altitude());
                ps.setBoolean(22, item.getAircraft_vision_on());
                ps.setBoolean(23, item.getAircraft_gps_signal());
                ps.setBigDecimal(24, item.getAircraft_gps_signal_value());
                ps.setInt(25, item.getAircraft_smart_gohome_flight_time_remaining());
                ps.setBoolean(26, item.getAircraft_smart_gohome_requesting());
                ps.setString(27, String.valueOf(item.getAircraft_last_updated()));
                ps.setInt(28, item.getBattery_power());
                ps.setInt(29, item.getBattery_current());
                ps.setInt(30, item.getBattery_voltage());
                ps.setBigDecimal(31, item.getBattery_temperature());
                ps.setInt(32, item.getBattery_charge());
                ps.setString(33, String.valueOf(item.getBattery_last_updated()));
                ps.setInt(34, item.getBattery_cell_one());
                ps.setInt(35, item.getBattery_cell_two());
                ps.setInt(36, item.getBattery_cell_three());
                ps.setInt(37, item.getBattery_cell_four());
                ps.setInt(38, item.getBattery_cell_five());
                ps.setInt(39, item.getBattery_cell_six());
                ps.setString(40, String.valueOf(item.getBattery_cell_last_updated()));
                ps.setBigDecimal(41, item.getGcs_lat());
                ps.setBigDecimal(42, item.getGcs_lon());
                ps.setString(43, String.valueOf(item.getGcs_location_last_updated()));
                ps.setBigDecimal(44, item.getGimbal_pitch());
                ps.setBigDecimal(45, item.getGimbal_roll());
                ps.setBigDecimal(46, item.getGimbal_yaw());
                ps.setBigDecimal(47, item.getGimbal_mode());
            }

            @Override
            public int getBatchSize() {
                return items.size();
            }
        };

        jdbcTemplate.batchUpdate(BATCH_INSERT_SQL, statement);
    }

    @Override
    public List<FlightLogItem> findByLogfileId(Integer logfileId) {


        List<FlightLogItem> items = jdbcTemplate.query(
                "SELECT * FROM flight_log_item WHERE logfile_id = ?",
                logItemRowMapper,
                logfileId
        );

        return items == null ? new ArrayList<>() : items;
    }

    private RowMapper<FlightLogItem> logItemRowMapper = (rs, rowNum) -> {
        FlightLogItem item = new FlightLogItem();

        item.setLogfile_id(rs.getInt("logfile_id"));
        item.setTimestamp(rs.getString("timestamp"));
        item.setAircraft_lon(rs.getBigDecimal("aircraft_lon"));
        item.setAircraft_lat(rs.getBigDecimal("aircraft_lat"));
        item.setAircraft_altitude(rs.getBigDecimal("aircraft_altitude"));
        item.setAircraft_speed(rs.getBigDecimal("aircraft_speed"));
        item.setAircraft_heading(rs.getBigDecimal("aircraft_heading"));
        item.setAircraft_vel_x(rs.getBigDecimal("aircraft_vel_x"));
        item.setAircraft_vel_y(rs.getBigDecimal("aircraft_vel_y"));
        item.setAircraft_vel_z(rs.getBigDecimal("aircraft_vel_z"));
        item.setAircraft_pitch(rs.getBigDecimal("aircraft_pitch"));
        item.setAircraft_roll(rs.getBigDecimal("aircraft_roll"));
        item.setAircraft_yaw(rs.getBigDecimal("aircraft_yaw"));
        item.setAircraft_satellites(rs.getInt("aircraft_satellites"));
        item.setAircraft_motors_on(rs.getBoolean("aircraft_motors_on"));
        item.setAircraft_flying(rs.getBoolean("aircraft_flying"));
        item.setAircraft_flight_mode(rs.getString("aircraft_flight_mode"));
        item.setAircraft_flight_mode_value(rs.getInt("aircraft_flight_mode_value"));
        item.setAircraft_imu_preheating(rs.getBoolean("aircraft_imu_preheating"));
        item.setAircraft_ultrasonic_on(rs.getBoolean("aircraft_ultrasonic_on"));
        item.setAircraft_ultrasonic_altitude(rs.getBigDecimal("aircraft_ultrasonic_altitude"));
        item.setAircraft_vision_on(rs.getBoolean("aircraft_vision_on"));
        item.setAircraft_gps_signal(rs.getBoolean("aircraft_gps_signal"));
        item.setAircraft_gps_signal_value(rs.getBigDecimal("aircraft_gps_signal_value"));
        item.setAircraft_smart_gohome_flight_time_remaining(rs.getInt("aircraft_smart_gohome_flight_time_remaining"));
        item.setAircraft_smart_gohome_requesting(rs.getBoolean("aircraft_smart_gohome_requesting"));
        item.setAircraft_last_updated(rs.getString("aircraft_last_updated"));
        item.setBattery_power(rs.getInt("battery_power"));
        item.setBattery_current(rs.getInt("battery_current"));
        item.setBattery_voltage(rs.getInt("battery_voltage"));
        item.setBattery_temperature(rs.getBigDecimal("battery_temperature"));
        item.setBattery_charge(rs.getInt("battery_charge"));
        item.setBattery_last_updated(rs.getString("battery_last_updated"));
        item.setBattery_cell_one(rs.getInt("battery_cell_one"));
        item.setBattery_cell_two(rs.getInt("battery_cell_two"));
        item.setBattery_cell_three(rs.getInt("battery_cell_three"));
        item.setBattery_cell_four(rs.getInt("battery_cell_four"));
        item.setBattery_cell_five(rs.getInt("battery_cell_five"));
        item.setBattery_cell_six(rs.getInt("battery_cell_six"));
        item.setBattery_cell_last_updated(rs.getString("battery_cell_last_updated"));
        item.setGcs_lat(rs.getBigDecimal("gcs_lat"));
        item.setGcs_lon(rs.getBigDecimal("gcs_lon"));
        item.setGcs_location_last_updated(rs.getString("gcs_location_last_updated"));
        item.setGimbal_pitch(rs.getBigDecimal("gimbal_pitch"));
        item.setGimbal_roll(rs.getBigDecimal("gimbal_roll"));
        item.setGimbal_yaw(rs.getBigDecimal("gimbal_yaw"));
        item.setGimbal_mode(rs.getBigDecimal("gimbal_mode"));

        return item;
    };
}
