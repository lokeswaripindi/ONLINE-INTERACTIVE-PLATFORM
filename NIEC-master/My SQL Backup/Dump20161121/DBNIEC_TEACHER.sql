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
-- Table structure for table `TEACHER`
--

DROP TABLE IF EXISTS `TEACHER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TEACHER` (
  `T_ID` varchar(8) DEFAULT NULL,
  `T_NAME` varchar(30) DEFAULT NULL,
  `T_PASSWORD` varchar(30) DEFAULT NULL,
  `T_BRANCH` varchar(5) DEFAULT NULL,
  `T_PHONE` varchar(15) DEFAULT NULL,
  `T_CLASSES` varchar(200) DEFAULT NULL,
  `T_EMAIL` varchar(30) DEFAULT NULL,
  `T_GENDER` varchar(10) DEFAULT NULL,
  `T_STUDENTLIST` text,
  `T_TEACHERLIST` text,
  `T_PENDINGLIST` varchar(400) DEFAULT NULL,
  `T_SENTLIST` varchar(400) DEFAULT NULL,
  `T_ABOUTME` varchar(400) DEFAULT NULL,
  `SNO` int(5) NOT NULL AUTO_INCREMENT,
  `CHATID` varchar(50) DEFAULT '',
  PRIMARY KEY (`SNO`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TEACHER`
--

LOCK TABLES `TEACHER` WRITE;
/*!40000 ALTER TABLE `TEACHER` DISABLE KEYS */;
INSERT INTO `TEACHER` VALUES ('|101','Asha Kalra','q','CSE','8989898989','T2, T3, S1','asha@gmail.com','male','131001100410','|102|103|104','|1090F101110','|105|10602100310','expert in data structures',1,'37#38#'),('|102','Kumar Amit','q','CSE','8989753989','T1, T3, S1','kumaram@gmail.com','male','1310','|101','','','expert in data ',2,'38#'),('|103','Neha gupta','q','CSE','9889753989','T1, T3','neha@gmail.com','female','','|101','1310','','expert in networking',3,''),('|104','Krishna Kumar','q','CSE','7889753989','T1, T3','krishna@gmail.com','female','','|101','','','expert in OS',4,''),('|105','Sanjay Gupta','q','CSE','9989753989','S3, T3','sanjay@gmail.com','female','','','|101','1310','Data mining',5,''),('|106','Sandeep Jain','q','CSE','9912753989','F1','sandeep@gmail.com','female','','','|101','1310','C',6,''),('|107','Kehav Gupta','q','CSE','8812753989','F1','keshav@gmail.com','female','','','','','C++ expert',7,''),('|108','Anita sharma','q','CSE','9812753989','F1','anita@gmail.com','female','','','','','Java expert',8,''),('|109','Kartik singh','q','CSE','9912753989','F1','kartik@gmail.com','male','','','','|101','compiler design',9,''),('|010','Ayush jain','q','CSE','9812753912','T2','ayush@gmail.com','male','','','','','i like meeting new people',10,''),('|011','Somesh Gupta','q','CSE','9835753912','T2','guupta@gmail.com','male','','','','','i like meeting new people',11,''),('|012','Renu Aggarwal','q','CSE','9857753912','T1','Renu@gmail.com','male','','','','','I like programming',12,'');
/*!40000 ALTER TABLE `TEACHER` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-21 21:07:56
