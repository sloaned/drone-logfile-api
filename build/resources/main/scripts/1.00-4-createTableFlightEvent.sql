CREATE TABLE IF NOT EXISTS `flight_event` (
  `logfile_id` INTEGER NOT NULL,
  `event_info` VARCHAR(80),
  `event_timestamp` TIMESTAMP,
  `event_type` VARCHAR(80)
);