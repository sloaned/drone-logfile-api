CREATE TABLE IF NOT EXISTS `flight_log_key` (
  `logfile_id` INTEGER NOT NULL,
  `field_name` VARCHAR(80) NOT NULL,
  `key_order` INTEGER NOT NULL,
  FOREIGN KEY (`logfile_id`) REFERENCES `flight_logfile` (`logfile_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE
);