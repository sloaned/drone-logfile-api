package com.dronelogfileapi.service;

import com.dronelogfileapi.domain.FlightLogfile;
import com.dronelogfileapi.domain.FlightStartEndValue;
import com.dronelogfileapi.domain.flight.FlightLog;
import com.dronelogfileapi.domain.flight.FlightLogItem;
import com.dronelogfileapi.domain.flight.FlightLogKey;
import com.dronelogfileapi.repositories.LogfileRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class FlightLogServiceImpl implements FlightLogService {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    private LogfileRepository logfileRepository;

    @Autowired
    public FlightLogServiceImpl(LogfileRepository logfileRepository) {
        this.logfileRepository = logfileRepository;
    }

    @Override
    public Integer saveLogfile(FlightLogfile logfile) {
        FlightLog log = logfile.getMessage().getFlight_logging();

        logfile.getMessage().setFlight_logging(FlightLogConverter.convertForIntake(log));

        return logfileRepository.save(logfile);
    }

    @Override
    public FlightLogfile getLogfile(Integer logfileId) throws Exception {
        FlightLogfile file = logfileRepository.findById(logfileId);

        if (file != null) {
            FlightLog log = file.getMessage().getFlight_logging();

            file.getMessage().setFlight_logging(FlightLogConverter.convertForExport(log));
        } else {
            throw new Exception("No logfile found with id " + logfileId);
        }

        return file;
    }

    @Override
    public List<FlightStartEndValue> getLogStartAndEndValues(Integer logfileId, List<String> fields) throws Exception {
        List<FlightStartEndValue> startEndValues = new ArrayList<>();

        FlightLogfile file = getLogfile(logfileId);

        FlightLog log = file.getMessage().getFlight_logging();

        List<List<Object>> itemsLists = log.getFlight_logging_items();

        if (CollectionUtils.isNotEmpty(itemsLists)) {
            List<Object> firstList = itemsLists.get(0);
            List<Object> lastList = itemsLists.get(itemsLists.size() - 1);

            for (String field : fields) {
                int index = log.getFlight_logging_keys().indexOf(field);

                if (index >= 0) {
                    FlightStartEndValue flightStartEndValue = new FlightStartEndValue();
                    flightStartEndValue.setFieldName(field);
                    flightStartEndValue.setStartValue(firstList.get(index));
                    flightStartEndValue.setEndValue(lastList.get(index));

                    startEndValues.add(flightStartEndValue);
                }
            }
        }

        return startEndValues;
    }

    /*
    Integer saveLogfile(FlightLogfile logfile);

    FlightLogfile getLogfile(Integer logfileId);

    List<FlightStartEndValue> getLogStartAndEndValues(Integer logfileId, List<String> fields);
     */


    private static class FlightLogConverter {

        public static FlightLog convertForIntake(FlightLog flightLog) {

            List<FlightLogItem> flightLogItems = new ArrayList<>();
            List<FlightLogKey> flightLogKeys = new ArrayList<>();

            List<List<Object>> loggingItemsLists = flightLog.getFlight_logging_items();

            for (List<Object> itemsList : loggingItemsLists) {
                flightLogItems.add(new FlightLogItem());
            }

            List<String> itemKeys = flightLog.getFlight_logging_keys();

            for (int i = 0; i < itemKeys.size(); i++) {
                String field = itemKeys.get(i);

                FlightLogKey key = new FlightLogKey();
                key.setFieldName(field);
                key.setOrder(i + 1);
                flightLogKeys.add(key);

                for (int j = 0; j < loggingItemsLists.size(); j++) {
                    List<Object> itemsList = loggingItemsLists.get(j);
                    Object value = itemsList.get(i);

                    FlightLogItem item = flightLogItems.get(j);

                    switch(field) {
                        case "timestamp":
                            item.setTimestamp(value.toString());
                            break;
                        case "aircraft_lon":
                            item.setAircraft_lon(((Number) value).doubleValue());
                            break;
                        case "aircraft_lat":
                            item.setAircraft_lat(((Number) value).doubleValue());
                            break;
                        case "aircraft_altitude":
                            item.setAircraft_altitude(((Number) value).doubleValue());
                            break;
                        case "aircraft_speed":
                            item.setAircraft_speed(((Number) value).doubleValue());
                            break;
                        case "aircraft_heading":
                            item.setAircraft_heading(((Number) value).doubleValue());
                            break;
                        case "aircraft_vel_x":
                            item.setAircraft_vel_x(((Number) value).doubleValue());
                            break;
                        case "aircraft_vel_y":
                            item.setAircraft_vel_y(((Number) value).doubleValue());
                            break;
                        case "aircraft_vel_z":
                            item.setAircraft_vel_z(((Number) value).doubleValue());
                            break;
                        case "aircraft_pitch":
                            item.setAircraft_pitch(((Number) value).doubleValue());
                            break;
                        case "aircraft_roll":
                            item.setAircraft_roll(((Number) value).doubleValue());
                            break;
                        case "aircraft_yaw":
                            item.setAircraft_yaw(((Number) value).doubleValue());
                            break;
                        case "aircraft_satellites":
                            item.setAircraft_satellites((Integer) value);
                            break;
                        case "aircraft_motors_on":
                            item.setAircraft_motors_on(((Integer) value) != 0);
                            break;
                        case "aircraft_flying":
                            item.setAircraft_flying(((Integer) value) != 0);
                            break;
                        case "aircraft_flight_mode":
                            item.setAircraft_flight_mode((String) value);
                            break;
                        case "aircraft_flight_mode_value":
                            item.setAircraft_flight_mode_value((Integer) value);
                            break;
                        case "aircraft_imu_preheating":
                            item.setAircraft_imu_preheating(((Integer) value) != 0);
                            break;
                        case "aircraft_ultrasonic_on":
                            item.setAircraft_ultrasonic_on(((Integer) value) != 0);
                            break;
                        case "aircraft_ultrasonic_altitude":
                            item.setAircraft_ultrasonic_altitude(((Number) value).doubleValue());
                            break;
                        case "aircraft_vision_on":
                            item.setAircraft_vision_on(((Integer) value) != 0);
                            break;
                        case "aircraft_gps_signal":
                            item.setAircraft_gps_signal(((Integer) value) != 0);
                            break;
                        case "aircraft_gps_signal_value":
                            item.setAircraft_gps_signal_value(((Number) value).doubleValue());
                            break;
                        case "aircraft_smart_gohome_flight_time_remaining":
                            item.setAircraft_smart_gohome_flight_time_remaining((Integer) value);
                            break;
                        case "aircraft_smart_gohome_requesting":
                            item.setAircraft_smart_gohome_requesting(((Integer) value) != 0);
                            break;
                        case "aircraft_last_updated":
                            item.setAircraft_last_updated(value.toString());
                            break;
                        case "battery_power":
                            item.setBattery_power((Integer) value);
                            break;
                        case "battery_current":
                            item.setBattery_current((Integer) value);
                            break;
                        case "battery_voltage":
                            item.setBattery_voltage((Integer) value);
                            break;
                        case "battery_temperature":
                            item.setBattery_temperature(((Number) value).doubleValue());
                            break;
                        case "battery_charge":
                            item.setBattery_charge((Integer)value);
                            break;
                        case "battery_last_updated":
                            item.setBattery_last_updated(value.toString());
                            break;
                        case "gcs_lat":
                            item.setGcs_lat(((Number) value).doubleValue());
                            break;
                        case "gcs_lon":
                            item.setGcs_lon(((Number) value).doubleValue());
                            break;
                        case "gcs_location_last_updated":
                            item.setGcs_location_last_updated(value.toString());
                            break;
                        case "gimbal_pitch":
                            item.setGimbal_pitch(((Number) value).doubleValue());
                            break;
                        case "gimbal_roll":
                            item.setGimbal_roll(((Number) value).doubleValue());
                            break;
                        case "gimbal_yaw":
                            item.setGimbal_yaw(((Number) value).doubleValue());
                            break;
                        case "gimbal_mode":
                            item.setGimbal_mode(((Number) value).doubleValue());
                            break;
                        default:
                            break;

                    }
                }
            }

            flightLog.setFlightLogItems(flightLogItems);
            flightLog.setFlightLogKeys(flightLogKeys);

            return flightLog;
        }

        public static FlightLog convertForExport(FlightLog flightLog) {

            List<FlightLogKey> keys = flightLog.getFlightLogKeys();
            List<FlightLogItem> items = flightLog.getFlightLogItems();

            List<List<Object>> itemsLists = new ArrayList<>();
            List<String> keysList = new ArrayList<>();

            keys.sort(Comparator.comparing(FlightLogKey::getOrder));
            items.sort(Comparator.comparing(FlightLogItem::getTimestamp));

            for (int i = 0; i < items.size(); i++) {
                itemsLists.add(new ArrayList<>());
            }

            for (int i = 0; i < keys.size(); i++) {
                String field = keys.get(i).getFieldName();
                keysList.add(field);

                for (int j = 0; j < itemsLists.size(); j++) {
                    FlightLogItem item = items.get(j);
                    List<Object> itemsList = itemsLists.get(j);

                    switch(field) {
                        case "timestamp":
                            itemsList.add(item.getTimestamp().toString());
                            break;
                        case "aircraft_lon":
                            itemsList.add(item.getAircraft_lon());
                            break;
                        case "aircraft_lat":
                            itemsList.add(item.getAircraft_lat());
                            break;
                        case "aircraft_altitude":
                            itemsList.add(item.getAircraft_altitude());
                            break;
                        case "aircraft_speed":
                            itemsList.add(item.getAircraft_speed());
                            break;
                        case "aircraft_heading":
                            itemsList.add(item.getAircraft_heading());
                            break;
                        case "aircraft_vel_x":
                            itemsList.add(item.getAircraft_vel_x());
                            break;
                        case "aircraft_vel_y":
                            itemsList.add(item.getAircraft_vel_y());
                            break;
                        case "aircraft_vel_z":
                            itemsList.add(item.getAircraft_vel_z());
                            break;
                        case "aircraft_pitch":
                            itemsList.add(item.getAircraft_pitch());
                            break;
                        case "aircraft_roll":
                            itemsList.add(item.getAircraft_roll());
                            break;
                        case "aircraft_yaw":
                            itemsList.add(item.getAircraft_yaw());
                            break;
                        case "aircraft_satellites":
                            itemsList.add(item.getAircraft_satellites());
                            break;
                        case "aircraft_motors_on":
                            itemsList.add(item.getAircraft_motors_on() ? 1 : 0);
                            break;
                        case "aircraft_flying":
                            itemsList.add(item.getAircraft_flying() ? 1 : 0);
                            break;
                        case "aircraft_flight_mode":
                            itemsList.add(item.getAircraft_flight_mode());
                            break;
                        case "aircraft_flight_mode_value":
                            itemsList.add(item.getAircraft_flight_mode_value());
                            break;
                        case "aircraft_imu_preheating":
                            itemsList.add(item.getAircraft_imu_preheating() ? 1 : 0);
                            break;
                        case "aircraft_ultrasonic_on":
                            itemsList.add(item.getAircraft_ultrasonic_on() ? 1 : 0);
                            break;
                        case "aircraft_ultrasonic_altitude":
                            itemsList.add(item.getAircraft_ultrasonic_altitude());
                            break;
                        case "aircraft_vision_on":
                            itemsList.add(item.getAircraft_vision_on() ? 1 : 0);
                            break;
                        case "aircraft_gps_signal":
                            itemsList.add(item.getAircraft_gps_signal() ? 1 : 0);
                            break;
                        case "aircraft_gps_signal_value":
                            itemsList.add(item.getAircraft_gps_signal_value());
                            break;
                        case "aircraft_smart_gohome_flight_time_remaining":
                            itemsList.add(item.getAircraft_smart_gohome_flight_time_remaining());
                            break;
                        case "aircraft_smart_gohome_requesting":
                            itemsList.add(item.getAircraft_smart_gohome_requesting() ? 1 : 0);
                            break;
                        case "aircraft_last_updated":
                            itemsList.add(item.getAircraft_last_updated().toString());
                            break;
                        case "battery_power":
                            itemsList.add(item.getBattery_power());
                            break;
                        case "battery_current":
                            itemsList.add(item.getBattery_current());
                            break;
                        case "battery_voltage":
                            itemsList.add(item.getBattery_voltage());
                            break;
                        case "battery_temperature":
                            itemsList.add(item.getBattery_temperature());
                            break;
                        case "battery_charge":
                            itemsList.add(item.getBattery_charge());
                            break;
                        case "battery_last_updated":
                            itemsList.add(item.getBattery_last_updated().toString());
                            break;
                        case "gcs_lat":
                            itemsList.add(item.getGcs_lat());
                            break;
                        case "gcs_lon":
                            itemsList.add(item.getGcs_lon());
                            break;
                        case "gcs_location_last_updated":
                            itemsList.add(item.getGcs_location_last_updated().toString());
                            break;
                        case "gimbal_pitch":
                            itemsList.add(item.getGimbal_pitch());
                            break;
                        case "gimbal_roll":
                            itemsList.add(item.getGimbal_roll());
                            break;
                        case "gimbal_yaw":
                            itemsList.add(item.getGimbal_yaw());
                            break;
                        case "gimbal_mode":
                            itemsList.add(item.getGimbal_mode());
                            break;
                        default:
                            break;

                    }
                }
            }

            flightLog.setFlight_logging_keys(keysList);
            flightLog.setFlight_logging_items(itemsLists);

            return flightLog;
        }

        private static LocalDateTime getLocalDateTime(String timeString) {
            System.out.println("timeString = " + timeString);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
            return LocalDateTime.parse(timeString, formatter);
        }
    }
}
