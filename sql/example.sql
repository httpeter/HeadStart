-- MySQL dump 10.13  Distrib 8.0.19, for macos10.15 (x86_64)
--
-- Host: localhost    Database: example
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `PERSON`
--

DROP TABLE IF EXISTS `PERSON`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PERSON` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `AGE` varchar(255) DEFAULT NULL,
  `FIRSTNAME` varchar(255) DEFAULT NULL,
  `GENDER` varchar(255) DEFAULT NULL,
  `LASTNAME` varchar(255) DEFAULT NULL,
  `NOTES` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PERSON`
--

LOCK TABLES `PERSON` WRITE;
/*!40000 ALTER TABLE `PERSON` DISABLE KEYS */;
INSERT INTO `PERSON` VALUES (3,'64','Pieter','m','Post','test'),(4,'58','sdfdf','f','dddd',''),(5,'44','DIngetje','','Dee','ssss'),(6,'33','Dinges','f','Doe','asdfasdf'),(8,'22','Mina','f','Naeimi','');
/*!40000 ALTER TABLE `PERSON` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PLACE`
--

DROP TABLE IF EXISTS `PLACE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PLACE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL,
  `RATING` int(11) DEFAULT NULL,
  `DESCRIPTION` varchar(2500) DEFAULT NULL,
  `IMGURLS` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PLACE`
--

LOCK TABLES `PLACE` WRITE;
/*!40000 ALTER TABLE `PLACE` DISABLE KEYS */;
INSERT INTO `PLACE` VALUES (1,'Alpenblick',3,'Located and founded within the Alps, our company has been growing rapidly over the last 8 years.\n\nWe were awarded Gold in 2018 and Silver in 2017 in the winter ski chalet category.\n\nWe have over 130 beautiful chalets, luxurious, ultra luxurious for all kinds of budget, just contact us and we will find you the best option within your budget.\n\nWe charge 0% booking commission + all bookings will receive a free airport transfer up to 10 people.\n\nBook one of our chalets and let us take care of you - it\'s your holiday - just sit back & relax.','https://i.ytimg.com/vi/_X1nU-qpGkI/maxresdefault.jpg'),(2,'Marmottengau',5,'Don\'t have time to browse all the 100+ chalets? Fill out our contact form with the following details - your preferred destination, bedrooms, budget per night, the dates and we will get back to you shortly with the best available options.','https://s-ec.bstatic.com/images/hotel/max1024x768/933/93300258.jpg'),(3,'Marmottengau',5,'Don\'t have time to browse all the 100+ chalets? Fill out our contact form with the following details - your preferred destination, bedrooms, budget per night, the dates and we will get back to you shortly with the best available options.','https://s-ec.bstatic.com/images/hotel/max1024x768/933/93300258.jpg'),(4,'Marmottengau',5,'Don\'t have time to browse all the 100+ chalets? Fill out our contact form with the following details - your preferred destination, bedrooms, budget per night, the dates and we will get back to you shortly with the best available options.','https://s-ec.bstatic.com/images/hotel/max1024x768/933/93300258.jpg');
/*!40000 ALTER TABLE `PLACE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER`
--

DROP TABLE IF EXISTS `USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `USER` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(45) DEFAULT NULL,
  `PWDHASH` varchar(45) DEFAULT NULL,
  `ROLE` varchar(45) DEFAULT NULL,
  `FIRSTNAME` varchar(45) DEFAULT NULL,
  `LASTNAME` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER`
--

LOCK TABLES `USER` WRITE;
/*!40000 ALTER TABLE `USER` DISABLE KEYS */;
INSERT INTO `USER` VALUES (4,'httpeter@gmail.com','r8tjDugvcpxIcP8U4zjUdw==','admin','Peters','Hendriks'),(13,'test@test.com','r8tjDugvcpxIcP8U4zjUdw==','tester','test','user'),(16,'vhofstede@servoy.com','e4tpQxCcce9s6dOKt3TBIQ==','admin','Victor','Hofstede'),(17,'phendriks@servoy.com','r8tjDugvcpxIcP8U4zjUdw==','admin','Peter','Hendriks');
/*!40000 ALTER TABLE `USER` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-29  0:33:18
