CREATE TABLE IF NOT EXISTS `flight_event` (
  `logfile_id` INTEGER NOT NULL,
  `event_info` VARCHAR(80),
  `event_timestamp` VARCHAR(40),
  `event_type` VARCHAR(80),
  FOREIGN KEY (`logfile_id`) REFERENCES `flight_logfile` (`logfile_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE
);