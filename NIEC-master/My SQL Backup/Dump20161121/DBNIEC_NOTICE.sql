CREATE DATABASE  IF NOT EXISTS `DBNIEC` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `DBNIEC`;
-- MySQL dump 10.13  Distrib 5.5.53, for debian-linux-gnu (i686)
--
-- Host: 127.0.0.1    Database: DBNIEC
-- ------------------------------------------------------
-- Server version	5.5.53-0ubuntu0.14.04.1

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
-- Table structure for table `NOTICE`
--

DROP TABLE IF EXISTS `NOTICE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NOTICE` (
  `ID` int(5) NOT NULL AUTO_INCREMENT,
  `CONTENT` varchar(500) DEFAULT NULL,
  `SENDER` varchar(50) DEFAULT NULL,
  `RECEIVER` varchar(50) DEFAULT NULL,
  `APPROVE` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NOTICE`
--

LOCK TABLES `NOTICE` WRITE;
/*!40000 ALTER TABLE `NOTICE` DISABLE KEYS */;
INSERT INTO `NOTICE` VALUES (28,'Loveleen Aggarwal has sent you a Friend Request','Loveleen Aggarwal','00315602713','1'),(29,'Loveleen Aggarwal has sent you a Friend Request','Loveleen Aggarwal','00415602713','1'),(30,'Loveleen Aggarwal has sent you a Friend Request','Loveleen Aggarwal','00515602713','1'),(31,'Loveleen Aggarwal has sent you a Friend Request','Loveleen Aggarwal','00615602713','1'),(32,'Loveleen Aggarwal has sent you a Friend Request','Loveleen Aggarwal','00715602713','1'),(33,'Loveleen Aggarwal has sent you a Friend Request','Loveleen Aggarwal','00815602713','1'),(34,'Loveleen Aggarwal has sent you a Friend Request','Loveleen Aggarwal','00915602713','1'),(35,'Loveleen Aggarwal has sent you a Friend Request','Loveleen Aggarwal','01015602713','1'),(36,'Loveleen Aggarwal has sent you a Friend Request','Loveleen Aggarwal','|101','1'),(37,'Loveleen Aggarwal has sent you a Friend Request','Loveleen Aggarwal','|102','1'),(38,'Loveleen Aggarwal has sent you a Friend Request','Loveleen Aggarwal','|103','1'),(39,'Gaurav Kumar has Accepted your Friend Request','Gaurav Kumar','01915602713','1'),(40,'Amit sinha has Accepted your Friend Request','Amit sinha','01915602713','1'),(41,'navneet kumar has sent you a Friend Request','navneet kumar','01915602713','1'),(42,'mayank kumar has sent you a Friend Request','mayank kumar','01915602713','1'),(43,'sandeep kumar has sent you a Friend Request','sandeep kumar','01915602713','1'),(46,'','Asha Kalra','|102','1'),(47,'','Asha Kalra','|103','1'),(48,'','Asha Kalra','|104','1'),(49,'','Asha Kalra','|105','1'),(50,'','Asha Kalra','|106','1'),(51,'','Asha Kalra','|108','1'),(52,'','Asha Kalra','00115602713','1'),(53,'','Asha Kalra','00215602713','1'),(54,'','Asha Kalra','00315602713','1'),(55,'','Asha Kalra','00415602713','1'),(56,'Sahil Singh has Accepted your Friend Request','Sahil Singh','|101','1'),(57,'Amit sinha has Accepted your Friend Request','Amit sinha','|101','1'),(58,'','Kartik singh','|101','1'),(59,'ajit gupta has sent you a Friend Request','ajit gupta','|101','1'),(60,'abhishek singh has sent you a Friend Request','abhishek singh','|101','1');
/*!40000 ALTER TABLE `NOTICE` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-21 21:07:55
