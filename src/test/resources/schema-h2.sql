--
-- Table structure for table cab_trip_data
--

DROP TABLE IF EXISTS cab_trip_data;
CREATE TABLE cab_trip_data (
  medallion varchar2(50),
  hack_license varchar2(50),
  vendor_id varchar2(20),
  rate_code BIGINT DEFAULT NULL,
  store_and_fwd_flag varchar2(10),
  pickup_datetime datetime DEFAULT NULL,
  dropoff_datetime datetime DEFAULT NULL,
  passenger_count BIGINT DEFAULT NULL,
  trip_time_in_secs BIGINT DEFAULT NULL,
  trip_distance double DEFAULT NULL,
  pickup_longitude double DEFAULT NULL,
  pickup_latitude double DEFAULT NULL,
  dropoff_longitude double DEFAULT NULL,
  dropoff_latitude double DEFAULT NULL
)