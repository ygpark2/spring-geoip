-- MySQL dump 10.13  Distrib 5.1.68, for apple-darwin11.4.2 (i386)
--
-- Host: 192.168.0.203    Database: Test2
-- ------------------------------------------------------
-- Server version	5.5.27-MariaDB-mariadb1~precise-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `GeoLiteCityBlock`
--

DROP TABLE IF EXISTS `GeoLiteCityBlock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GeoLiteCityBlock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `endIpNum` bigint(20) NOT NULL,
  `startIpNum` bigint(20) NOT NULL,
  `geoLiteCityLoc_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKF573C923F9BC2F31` (`geoLiteCityLoc_id`),
  CONSTRAINT `FKF573C923F9BC2F31` FOREIGN KEY (`geoLiteCityLoc_id`) REFERENCES `GeoLiteCityLocation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2293726 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `GeoLiteCityLocation`
--

DROP TABLE IF EXISTS `GeoLiteCityLocation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GeoLiteCityLocation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `areaCode` int(11) DEFAULT NULL,
  `city` varchar(250) DEFAULT NULL,
  `country` varchar(2) NOT NULL,
  `latitude` float NOT NULL,
  `longitude` float NOT NULL,
  `metroCode` int(11) DEFAULT NULL,
  `postalCode` varchar(50) NOT NULL,
  `region` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=403754 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `GeoLiteCityLocation_GeoLiteCityBlock`
--

DROP TABLE IF EXISTS `GeoLiteCityLocation_GeoLiteCityBlock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GeoLiteCityLocation_GeoLiteCityBlock` (
  `GeoLiteCityLocation_id` bigint(20) NOT NULL,
  `geoLiteCityBlk_id` bigint(20) NOT NULL,
  UNIQUE KEY `geoLiteCityBlk_id` (`geoLiteCityBlk_id`),
  KEY `FK20F62A03D0661108` (`GeoLiteCityLocation_id`),
  KEY `FK20F62A031915B318` (`geoLiteCityBlk_id`),
  CONSTRAINT `FK20F62A031915B318` FOREIGN KEY (`geoLiteCityBlk_id`) REFERENCES `GeoLiteCityBlock` (`id`),
  CONSTRAINT `FK20F62A03D0661108` FOREIGN KEY (`GeoLiteCityLocation_id`) REFERENCES `GeoLiteCityLocation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-03-22 15:25:47
