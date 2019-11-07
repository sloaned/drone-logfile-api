package com.dronelogfileapi.repositories;

import com.dronelogfileapi.domain.flight.FlightLogEvent;
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
public class FlightEventRepositoryImpl implements FlightEventRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightEventRepositoryImpl.class);

    private static final String BATCH_INSERT_SQL = "INSERT INTO flight_event (logfile_id, event_info, event_timestamp, " +
            "event_type) VALUES (?, ?, ?, ?)";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional
    @Override
    public void batchSave(List<FlightLogEvent> events) {
        if (CollectionUtils.isEmpty(events)) {
            LOGGER.info("Batch save request with no events, exiting");
            return;
        }

        LOGGER.info("Batch save request for {} events", events.size());


        BatchPreparedStatementSetter statement = new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                FlightLogEvent event = events.get(i);
                ps.setInt(1, event.getLogfile_id());
                ps.setString(2, event.getEvent_info());
                ps.setString(3, event.getEvent_timestamp());
                ps.setString(4, event.getEvent_type());
            }

            @Override
            public int getBatchSize() {
                return events.size();
            }
        };

        jdbcTemplate.batchUpdate(BATCH_INSERT_SQL, statement);
    }

    @Override
    public List<FlightLogEvent> findByLogfileId(Integer logfileId) {

        List<FlightLogEvent> events = jdbcTemplate.query(
                "SELECT * FROM flight_event WHERE logfile_id = ? ORDER BY event_timestamp ASC",
                flightEventRowMapper,
                logfileId
        );

        return events == null ? new ArrayList<>() : events;
    }

    private RowMapper<FlightLogEvent> flightEventRowMapper = (rs, rowNum) -> {
        FlightLogEvent event = new FlightLogEvent();

        event.setLogfile_id(rs.getInt("logfile_id"));
        event.setEvent_info(rs.getString("event_info"));
        event.setEvent_timestamp(rs.getString("event_timestamp"));

        event.setEvent_type(rs.getString("event_type"));

        return event;
    };
}
