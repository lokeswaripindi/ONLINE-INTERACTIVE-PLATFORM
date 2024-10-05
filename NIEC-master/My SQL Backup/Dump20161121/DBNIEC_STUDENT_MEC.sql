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
-- Table structure for table `STUDENT_MEC`
--

DROP TABLE IF EXISTS `STUDENT_MEC`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `STUDENT_MEC` (
  `S_ROLL` varchar(15) DEFAULT NULL,
  `S_HEXCODE` varchar(8) DEFAULT NULL,
  `S_NAME` varchar(30) DEFAULT NULL,
  `S_PASSWORD` varchar(20) DEFAULT NULL,
  `S_BRANCH` varchar(5) DEFAULT NULL,
  `S_SECTION` varchar(5) DEFAULT NULL,
  `S_SEMESTER` varchar(5) DEFAULT NULL,
  `S_ADDRESS` varchar(80) DEFAULT NULL,
  `S_PHONE` varchar(15) DEFAULT NULL,
  `S_EMAIL` varchar(20) DEFAULT NULL,
  `S_GENDER` varchar(10) DEFAULT NULL,
  `S_OTHERFRIENDLIST` varchar(400) DEFAULT NULL,
  `S_TEACHERLIST` varchar(400) DEFAULT NULL,
  `S_PENDINGLIST` varchar(400) DEFAULT NULL,
  `S_SENTLIST` varchar(400) DEFAULT NULL,
  `S_ABOUTME` varchar(500) DEFAULT NULL,
  `S_SOCIETY` varchar(400) DEFAULT NULL,
  `CHATID` varchar(50) DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `STUDENT_MEC`
--

LOCK TABLES `STUDENT_MEC` WRITE;
/*!40000 ALTER TABLE `STUDENT_MEC` DISABLE KEYS */;
/*!40000 ALTER TABLE `STUDENT_MEC` ENABLE KEYS */;
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
