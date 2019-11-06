package com.dronelogfileapi.repositories;

import com.dronelogfileapi.domain.flight.FlightLogKey;
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
public class FlightLogKeyRepositoryImpl implements FlightLogKeyRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightLogKeyRepositoryImpl.class);

    private static final String BATCH_INSERT_SQL = "INSERT INTO flight_log_key (logfile_id, field_name, order) " +
            "VALUES (?, ?, ?)";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional
    @Override
    public void batchSave(List<FlightLogKey> keys) {
        if (CollectionUtils.isEmpty(keys)) {
            LOGGER.info("Batch save request with no keys, exiting");
            return;
        }

        LOGGER.info("Batch save request for {} keys", keys.size());


        BatchPreparedStatementSetter statement = new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                FlightLogKey key = keys.get(i);
                ps.setInt(1, key.getLogfile_id());
                ps.setString(2, key.getFieldName());
                ps.setInt(3, key.getOrder());
            }

            @Override
            public int getBatchSize() {
                return keys.size();
            }
        };

        jdbcTemplate.batchUpdate(BATCH_INSERT_SQL, statement);
    }

    @Override
    public List<FlightLogKey> findByLogfileId(Integer logfileId) {
        List<FlightLogKey> keys = jdbcTemplate.query(
                "SELECT * FROM flight_log_key WHERE logfile_id = ? ORDER BY order ASC",
                logKeyRowMapper,
                logfileId
        );

        return keys == null ? new ArrayList<>() : keys;
    }
    
    private RowMapper<FlightLogKey> logKeyRowMapper = (rs, rowNum) -> {
        FlightLogKey key = new FlightLogKey();

        key.setLogfile_id(rs.getInt("logfile_id"));
        key.setFieldName(rs.getString("field_name"));
        key.setOrder(rs.getInt("order"));

        return key;
    };
}
