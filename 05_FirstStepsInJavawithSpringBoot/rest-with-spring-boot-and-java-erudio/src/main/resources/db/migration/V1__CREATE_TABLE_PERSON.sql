CREATE TABLE IF NOT EXISTS PERSON (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(50) NOT NULL,
  `LAST_NAME` varchar(50) NOT NULL,
  `ADDRESS` varchar(80) NOT NULL,
  `GENDER` varchar(6) NOT NULL,
  PRIMARY KEY (`ID`)
);

