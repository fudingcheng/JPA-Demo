CREATE DATABASE;

USE `jpa`;

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `custId` bigint(20) NOT NULL,
  `custAddress` varchar(255) DEFAULT NULL,
  `custIndustry` varchar(255) DEFAULT NULL,
  `custLevel` varchar(255) DEFAULT NULL,
  `custName` varchar(255) DEFAULT NULL,
  `custPhone` varchar(255) DEFAULT NULL,
  `custSource` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`custId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


