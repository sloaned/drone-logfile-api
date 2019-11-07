package com.dronelogfileapi.repositories;

import com.dronelogfileapi.domain.FlightLogfile;
import com.dronelogfileapi.domain.flight.*;
import com.dronelogfileapi.domain.flight.flightdata.*;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

@Repository
public class LogfileRepositoryImpl implements LogfileRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightLogKeyRepositoryImpl.class);

    private JdbcTemplate jdbcTemplate;
    private FlightEventRepository eventRepository;
    private FlightLogItemRepository itemRepository;
    private FlightLogKeyRepository keyRepository;

    @Autowired
    public LogfileRepositoryImpl(DataSource dataSource, FlightEventRepository eventRepository,
                                 FlightLogItemRepository itemRepository, FlightLogKeyRepository keyRepository) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        this.eventRepository = eventRepository;
        this.itemRepository = itemRepository;
        this.keyRepository = keyRepository;
    }

    @Transactional
    @Override
    public Integer save(FlightLogfile file) {
        String sql = "INSERT INTO flight_logfile (exchange_type, exchanger, uploadingOrgUuid, uploadingPilotUuid, " +
                "flight_session_id, file_creation_dtg, file_logging_type, filename, altitude_system, logging_start_dtg, " +
                "camera_serial_number, camera_model, camera_firmware_version, gimbal_serial_number, gimbal_firmware_version, " +
                "remote_controller_serial_number, remote_controller_firmware_version, aircraft_manufacturer, " +
                "aircraft_serial_number, aircraft_name, aircraft_model, aircraft_firmware_version, home_location_lat, " +
                "home_location_lon, aircraft_smart_gohome_flight_return_time, aircraft_smart_gohome_landing_power, " +
                "aircraft_smart_gohome_return_power, aircraft_smart_gohome_landing_radius, aircraft_smart_gohome_landing_time, " +
                "gcs_manufacturer, gcs_model, gcs_version, flight_session_start, flight_session_end, flight_controller_serial_number, " +
                "flight_controller_firmware_version, battery_serial_number, battery_remaining_life, battery_discharges, " +
                "battery_full_charge_volume, battery_cell_number, battery_firmware_version, device_origin_user_interface_idiom, " +
                "device_origin_operating_system, device_origin_model, device_origin_device_ssid, message_type) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, file.getExchange_type());
            ps.setString(2, file.getExchanger());
            ps.setString(3, file.getUploadingOrgUuid());
            ps.setString(4, file.getUploadingPilotUuid());
            ps.setString(5, file.getFlight_session_id());
            ps.setString(6, file.getMessage().getFile().getCreation_dtg());
            ps.setString(7, file.getMessage().getFile().getLogging_type());
            ps.setString(8, file.getMessage().getFile().getFilename());
            ps.setString(9, file.getMessage().getFlight_logging().getAltitude_system());
            ps.setString(10, file.getMessage().getFlight_logging().getLogging_start_dtg());
            ps.setString(11, file.getMessage().getFlight_data().getPayload().getCamera().getSerial_number());
            ps.setString(12, file.getMessage().getFlight_data().getPayload().getCamera().getModel());
            ps.setString(13, file.getMessage().getFlight_data().getPayload().getCamera().getFirmware_version());
            ps.setString(14, file.getMessage().getFlight_data().getPayload().getGimbal().getSerial_number());
            ps.setString(15, file.getMessage().getFlight_data().getPayload().getGimbal().getFirmware_version());
            ps.setString(16, file.getMessage().getFlight_data().getRemote_controller().getSerial_number());
            ps.setString(17, file.getMessage().getFlight_data().getRemote_controller().getFirmware_version());
            ps.setString(18, file.getMessage().getFlight_data().getAircraft().getManufacturer());
            ps.setString(19, file.getMessage().getFlight_data().getAircraft().getSerial_number());
            ps.setString(20, file.getMessage().getFlight_data().getAircraft().getName());
            ps.setString(21, file.getMessage().getFlight_data().getAircraft().getModel());
            ps.setString(22, file.getMessage().getFlight_data().getAircraft().getFirmware_version());
            if (file.getMessage().getFlight_data().getSummary().getHome_location_lat() != null) {
                ps.setDouble(23, Double.valueOf(file.getMessage().getFlight_data().getSummary().getHome_location_lat()));
            } else {
                ps.setNull(23, Types.NUMERIC);
            }
            if (file.getMessage().getFlight_data().getSummary().getHome_location_lon() != null) {
                ps.setDouble(24, Double.valueOf(file.getMessage().getFlight_data().getSummary().getHome_location_lon()));
            } else {
                ps.setNull(24, Types.NUMERIC);
            }
            if (file.getMessage().getFlight_data().getSummary().getAircraft_smart_gohome().getFlight_return_time() != null) {
                ps.setDouble(25, Double.valueOf(file.getMessage().getFlight_data().getSummary().getAircraft_smart_gohome().getFlight_return_time()));
            } else {
                ps.setNull(25, Types.NUMERIC);
            }
            if (file.getMessage().getFlight_data().getSummary().getAircraft_smart_gohome().getLanding_power() != null) {
                ps.setInt(26, Integer.valueOf(file.getMessage().getFlight_data().getSummary().getAircraft_smart_gohome().getLanding_power()));
            } else {
                ps.setNull(26, Types.NUMERIC);
            }
            if (file.getMessage().getFlight_data().getSummary().getAircraft_smart_gohome().getReturn_power() != null) {
                ps.setInt(27, Integer.valueOf(file.getMessage().getFlight_data().getSummary().getAircraft_smart_gohome().getReturn_power()));
            } else {
                ps.setNull(27, Types.NUMERIC);
            }
            if (file.getMessage().getFlight_data().getSummary().getAircraft_smart_gohome().getLanding_radius() != null) {
                ps.setDouble(28, Double.valueOf(file.getMessage().getFlight_data().getSummary().getAircraft_smart_gohome().getLanding_radius()));
            } else {
                ps.setNull(28, Types.NUMERIC);
            }
            if (file.getMessage().getFlight_data().getSummary().getAircraft_smart_gohome().getLanding_time() != null) {
                ps.setDouble(29, Double.valueOf(file.getMessage().getFlight_data().getSummary().getAircraft_smart_gohome().getLanding_time()));
            } else {
                ps.setNull(29, Types.NUMERIC);
            }

            ps.setString(30, file.getMessage().getFlight_data().getGcs().getManufacturer());
            ps.setString(31, file.getMessage().getFlight_data().getGcs().getModel());
            ps.setString(32, file.getMessage().getFlight_data().getGcs().getVersion());
            ps.setString(33, file.getMessage().getFlight_data().getFlight_session_start());
            ps.setString(34, file.getMessage().getFlight_data().getFlight_session_end());
            ps.setString(35, file.getMessage().getFlight_data().getFlight_controller().getSerial_number());
            ps.setString(36, file.getMessage().getFlight_data().getFlight_controller().getFirmware_version());
            ps.setString(37, file.getMessage().getFlight_data().getBattery().getSerial_number());
            ps.setString(38, file.getMessage().getFlight_data().getBattery().getRemaining_life());
            ps.setString(39, file.getMessage().getFlight_data().getBattery().getDischarges());
            ps.setString(40, file.getMessage().getFlight_data().getBattery().getFull_charge_volume());
            ps.setString(41, file.getMessage().getFlight_data().getBattery().getCell_number());
            ps.setString(42, file.getMessage().getFlight_data().getBattery().getFirmware_version());
            ps.setString(43, file.getMessage().getFlight_data().getLogfile_device_origin().getUser_interface_idiom());
            ps.setString(44, file.getMessage().getFlight_data().getLogfile_device_origin().getOperating_system());
            ps.setString(45, file.getMessage().getFlight_data().getLogfile_device_origin().getModel());
            ps.setString(46, file.getMessage().getFlight_data().getLogfile_device_origin().getDevice_ssid());
            ps.setString(47, file.getMessage().getMessage_type());

            return ps;
        }, keyHolder);

        Integer logfileId = keyHolder.getKey().intValue();

        List<FlightLogEvent> events = file.getMessage().getFlight_logging().getEvent();
        events.forEach(event -> event.setLogfile_id(logfileId));
        eventRepository.batchSave(events);

        List<FlightLogKey> keys = file.getMessage().getFlight_logging().getFlightLogKeys();
        keys.forEach(key -> key.setLogfile_id(logfileId));
        keyRepository.batchSave(keys);

        List<FlightLogItem> items = file.getMessage().getFlight_logging().getFlightLogItems();
        items.forEach(item -> item.setLogfile_id(logfileId));
        itemRepository.batchSave(items);

        return logfileId;

    }

    @Override
    public FlightLogfile findById(Integer id) {
        FlightLogfile logfile = null;
        String sql = "SELECT * FROM flight_logfile WHERE logfile_id = ?";

        List<FlightLogfile> logfiles = jdbcTemplate.query(sql, logfileRowMapper, id);

        if (CollectionUtils.isNotEmpty(logfiles)) {
            logfile = logfiles.get(0);
            Integer logfileId = Integer.valueOf(logfile.getLogfile_id());

            List<FlightLogItem> items = itemRepository.findByLogfileId(logfileId);
            List<FlightLogKey> keys = keyRepository.findByLogfileId(logfileId);
            List<FlightLogEvent> events = eventRepository.findByLogfileId(logfileId);

            logfile.getMessage().getFlight_logging().setFlightLogItems(items);
            logfile.getMessage().getFlight_logging().setFlightLogKeys(keys);
            logfile.getMessage().getFlight_logging().setEvent(events);
        }

        return logfile;
    }


    private static final RowMapper<FlightLogfile> logfileRowMapper = (rs, rowNum) -> {

        Aircraft aircraft = new Aircraft();
        aircraft.setManufacturer(rs.getString("aircraft_manufacturer"));
        aircraft.setSerial_number(rs.getString("aircraft_serial_number"));
        aircraft.setName(rs.getString("aircraft_name"));
        aircraft.setModel(rs.getString("aircraft_model"));
        aircraft.setFirmware_version(rs.getString("aircraft_firmware_version"));

        Camera camera = new Camera();
        camera.setSerial_number(rs.getString("camera_serial_number"));
        camera.setFirmware_version(rs.getString("camera_firmware_version"));
        camera.setModel(rs.getString("camera_model"));

        Gimbal gimbal = new Gimbal();
        gimbal.setFirmware_version(rs.getString("gimbal_firmware_version"));
        gimbal.setSerial_number(rs.getString("gimbal_serial_number"));

        Payload payload = new Payload();
        payload.setCamera(camera);
        payload.setGimbal(gimbal);

        Gcs gcs = new Gcs();
        gcs.setManufacturer(rs.getString("gcs_manufacturer"));
        gcs.setModel(rs.getString("gcs_model"));
        gcs.setVersion(rs.getString("gcs_version"));

        AircraftSmartGohome gohome = new AircraftSmartGohome();
        if (rs.getString("aircraft_smart_gohome_flight_return_time") != null) {
            gohome.setFlight_return_time(String.valueOf(rs.getDouble("aircraft_smart_gohome_flight_return_time")));
        }
        if (rs.getString("aircraft_smart_gohome_landing_power") != null) {
            gohome.setLanding_power(String.valueOf(rs.getInt("aircraft_smart_gohome_landing_power")));
        }
        if (rs.getString("aircraft_smart_gohome_return_power") != null) {
            gohome.setReturn_power(String.valueOf(rs.getInt("aircraft_smart_gohome_return_power")));
        }
        if (rs.getString("aircraft_smart_gohome_landing_radius") != null) {
            gohome.setLanding_radius(String.valueOf(rs.getDouble("aircraft_smart_gohome_landing_radius")));
        }
        if (rs.getString("aircraft_smart_gohome_landing_time") != null) {
            gohome.setLanding_time(String.valueOf(rs.getDouble("aircraft_smart_gohome_landing_time")));
        }

        Battery battery = new Battery();
        battery.setSerial_number(rs.getString("battery_serial_number"));
        battery.setRemaining_life(rs.getString("battery_remaining_life"));
        battery.setDischarges(rs.getString("battery_discharges"));
        battery.setFull_charge_volume(rs.getString("battery_full_charge_volume"));
        battery.setCell_number(rs.getString("battery_cell_number"));
        battery.setFirmware_version(rs.getString("battery_firmware_version"));

        LogfileDeviceOrigin origin = new LogfileDeviceOrigin();
        origin.setDevice_ssid(rs.getString("device_origin_device_ssid"));
        origin.setModel(rs.getString("device_origin_model"));
        origin.setUser_interface_idiom(rs.getString("device_origin_user_interface_idiom"));
        origin.setOperating_system(rs.getString("device_origin_operating_system"));

        FlightController flightController = new FlightController();
        flightController.setFirmware_version(rs.getString("flight_controller_firmware_version"));
        flightController.setSerial_number(rs.getString("flight_controller_serial_number"));

        RemoteController remoteController = new RemoteController();
        remoteController.setFirmware_version(rs.getString("remote_controller_firmware_version"));
        remoteController.setSerial_number(rs.getString("remote_controller_serial_number"));

        FlightSummary summary = new FlightSummary();
        summary.setHome_location_lat(rs.getString("home_location_lat"));
        summary.setHome_location_lon(rs.getString("home_location_lon"));
        summary.setAircraft_smart_gohome(gohome);

        FlightData flightData = new FlightData();
        flightData.setPayload(payload);
        flightData.setRemote_controller(remoteController);
        flightData.setBattery(battery);
        flightData.setAircraft(aircraft);
        flightData.setSummary(summary);
        flightData.setGcs(gcs);
        flightData.setFlight_session_start(rs.getString("flight_session_start"));
        flightData.setFlight_session_end(rs.getString("flight_session_end"));
        flightData.setFlight_controller(flightController);
        flightData.setLogfile_device_origin(origin);

        FlightLog flightLog = new FlightLog();
        flightLog.setAltitude_system(rs.getString("altitude_system"));
        flightLog.setLogging_start_dtg(rs.getString("logging_start_dtg"));

        FlightLogMessageFile messageFile = new FlightLogMessageFile();
        messageFile.setCreation_dtg(rs.getString("file_creation_dtg"));
        messageFile.setLogging_type(rs.getString("file_logging_type"));
        messageFile.setFilename(rs.getString("filename"));

        LogMessage logMessage = new LogMessage();
        logMessage.setFile(messageFile);
        logMessage.setMessage_type(rs.getString("message_type"));
        logMessage.setFlight_logging(flightLog);
        logMessage.setFlight_data(flightData);

        FlightLogfile logfile = new FlightLogfile();
        logfile.setLogfile_id(String.valueOf(rs.getInt("logfile_id")));
        logfile.setExchange_type(rs.getString("exchange_type"));
        logfile.setExchanger(rs.getString("exchanger"));
        logfile.setUploadingOrgUuid(rs.getString("uploadingOrgUuid"));
        logfile.setUploadingPilotUuid(rs.getString("uploadingPilotUuid"));
        logfile.setFlight_session_id(rs.getString("flight_session_id"));
        logfile.setMessage(logMessage);

        return logfile;
    };

}
