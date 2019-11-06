CREATE TABLE IF NOT EXISTS `flight_log_key` (
  `logfile_id` INTEGER NOT NULL,
  `field_name` VARCHAR(80) NOT NULL,
  `order` INTEGER NOT NULL,
  FOREIGN KEY (`logfile_id`) REFERENCES `logfile` (`logfile_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE
);