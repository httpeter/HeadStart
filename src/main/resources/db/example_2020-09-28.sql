# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: case1.nl (MySQL 5.7.31-0ubuntu0.18.04.1)
# Database: example
# Generation Time: 2020-09-28 09:34:14 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table PLACE
# ------------------------------------------------------------

DROP TABLE IF EXISTS `PLACE`;

CREATE TABLE `PLACE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TRIPID` int(11) NOT NULL,
  `NAME` varchar(45) NOT NULL,
  `PAYED` bit(1) NOT NULL DEFAULT b'0',
  `BOOKED` bit(1) NOT NULL DEFAULT b'0',
  `RATING` int(11) DEFAULT NULL,
  `DESCRIPTION` varchar(500) DEFAULT NULL,
  `IMGURLS` varchar(500) DEFAULT NULL,
  `DEPARTUREDATE` datetime DEFAULT NULL,
  `ARRIVALDATE` datetime DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `LAT` varchar(45) DEFAULT NULL,
  `LNG` varchar(45) DEFAULT NULL,
  `COUNTRY` varchar(45) DEFAULT NULL,
  `FREECANCELLATIONDATE` datetime DEFAULT NULL,
  `OPTIONAL` bit(1) NOT NULL DEFAULT b'0',
  `PAYEDBYUSERID` varchar(45) DEFAULT NULL,
  `URLS` varchar(500) DEFAULT NULL,
  `PRICE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `PLACE` WRITE;
/*!40000 ALTER TABLE `PLACE` DISABLE KEYS */;

INSERT INTO `PLACE` (`ID`, `TRIPID`, `NAME`, `PAYED`, `BOOKED`, `RATING`, `DESCRIPTION`, `IMGURLS`, `DEPARTUREDATE`, `ARRIVALDATE`, `ADDRESS`, `LAT`, `LNG`, `COUNTRY`, `FREECANCELLATIONDATE`, `OPTIONAL`, `PAYEDBYUSERID`, `URLS`, `PRICE`)
VALUES
	(3,1,'Brunet resort',b'1',b'1',5,'really chill','https://www.brunethotels.it/fileadmin/_processed_/c/9/csm_brunet-offerta-vacanza-breve-4-3_35721c7119.jpg','2020-10-06 00:00:00','2020-10-02 00:00:00','a','46.1787661','11.8303635','Italy',NULL,b'1',NULL,'','670'),
	(12,1,'Borgo Tre Rose',b'0',b'1',5,'Very nice agriturismo in Tuscany','https://lh3.googleusercontent.com/proxy/GfJHza4vn1cEvCrxgFStvWGOZb8SMHTdNmO9zHWP5QyxQya-Z9rEPTdmdaxw6GIShw9JSxg2YSoojnyyVqbL6JygGyrrRRCA27C3SkMspScdiUSCD6Zh9_GnrfSQEbRMcPVAt0YW1ugyaHmnHHqlQL4au7UYBQ=w408-h264-k-no','2020-10-13 00:00:00','2020-10-06 00:00:00','','43.155975','11.9237223','Italy',NULL,b'0',NULL,'','210'),
	(13,2,'Angers',b'0',b'1',2,'Met de orionen','','2020-08-31 00:00:00','2020-08-19 00:00:00','','47.380039858868756','2.4660877249999924','France',NULL,b'1',NULL,'','100'),
	(15,1,'Masseria Rosa, Truly Resort',b'0',b'1',NULL,'','https://lh4.googleusercontent.com/proxy/ihgDyj4iGzbOXts_CMcW-hLn0i-6y-A3DPf-VZe1exVsLmvfZjNzNQWJUYK2eux6nmBKinVROw7QaHSGDYwOMsWevpHmEbUQ87-XLBzFWH27K6uCIVVATt3ldChsD0ubfO8IMxhT1c0PxugTEN8Gz0RIjEQQEag=w408-h272-k-no','2020-10-20 00:00:00','2020-10-13 00:00:00','','40.7736358','17.2638788','Italy',NULL,b'0',NULL,'','1'),
	(16,1,'Agriturismo Tenuta La Campana',b'0',b'1',NULL,'Torre (Appartement D)\n\nTel: +39 0577 718103 Mobile: +39 348 3703970','https://lh5.googleusercontent.com/p/AF1QipMLmDhwidrZt46pd4aQFMf1ZubpO4WMCRAadhyl=w408-h272-k-no','2020-10-26 00:00:00','2020-10-20 00:00:00','Loc. Campana, 53041 Asciano (SI), Italia','43.2369212','11.4782276','Italy',NULL,b'0',NULL,'','365');

/*!40000 ALTER TABLE `PLACE` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
