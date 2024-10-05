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
-- Table structure for table `STUDENT_CSE`
--

DROP TABLE IF EXISTS `STUDENT_CSE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `STUDENT_CSE` (
  `S_ROLL` varchar(15) NOT NULL DEFAULT '',
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
  `CHATID` varchar(50) DEFAULT '',
  PRIMARY KEY (`S_ROLL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `STUDENT_CSE`
--

LOCK TABLES `STUDENT_CSE` WRITE;
/*!40000 ALTER TABLE `STUDENT_CSE` DISABLE KEYS */;
INSERT INTO `STUDENT_CSE` VALUES ('00115602713','0110','Sahil Singh','z','CSE','A1','1','New Delhi','0123456789','sahil@gmail.com','male','','|101','','','Good Student','','38#'),('00215602713','0210','Rahul Singh','z','CSE','A1','1','New Delhi','0123456789','rahul@gmail.com','male','','','|101','','Good Tutor','',''),('00315602713','0310','Gaurav Kumar','z','CSE','S1','1','Mumbai','9876544433','Gaurumar@gmail.com','male','1310','','|101','','A very good student and a good programmer with some technical skills','',''),('00415602713','0410','Amit sinha','z','CSE','S1','1','pune','9876544433','amit@gmail.com','male','1310','|101','','','A very good student and a good programmer with some technical skills','','36#'),('00515602713','0510','parth shukla','z','CSE','S1','1','delhi','98734443334','parth@gmail.com','male','','','1310','','A very good musician','','38#'),('00615602713','0610','karan ahuja','z','CSE','S1','1','kerela','865344433','parth@gmail.com','male','','','1310','','A very good dancer','',''),('00715602713','0710','shailendra singh','z','CSE','S1','1','orissa','865344433','shailendra@gmail.com','male','','','1310','','A very good dancer and singer','',''),('00815602713','0810','amit kumar','z','CSE','S1','1','punjab','862344433','kumara@gmail.com','male','','','1310','','programmer','',''),('00915602713','0910','umang jain','z','CSE','S1','1','banglore','8623244433','umang@gmail.com','male','','','','','programmer and coder','',''),('01015602713','0A10','parth kaushik','z','CSE','S1','1','banglore','8623244433','parth@gmail.com','male','','','','','programmer and coder and dancer','',''),('01115602713','0B10','navneet kumar','z','CSE','S1','1','haryana','9123244433','navnnet@gmail.com','male','','','','1310','singer','',''),('01215602713','0C10','mayank kumar','z','CSE','T1','1','haryana','9123244433','mayak@gmail.com','male','','','','1310','performer','',''),('01315602713','0D10','sandeep kumar','z','CSE','T1','1','karnataka','8983244433','sandeep@gmail.com','male','','','','1310','performer and dancer','',''),('01415602713','0E10','pushkar nagar','z','CSE','T1','1','goa','7983244433','push@gmail.com','male','','','','','entertainer','',''),('01515602713','0F10','ajit gupta','z','CSE','T1','1','jammu','9883244433','guptaam@gmail.com','male','','','','|101','entertainer and programmer','',''),('01615602713','1010','amrita singh','z','CSE','T2','1','kashmir','7883244433','amrita@gmail.com','male','','','','','programmer','',''),('01715602713','1110','abhishek singh','z','CSE','T2','1','pune','7883244433','abhi@gmail.com','male','','','','|101','programmer and dancer','',''),('01815602713','1210','harshit bhatia','z','CSE','T2','1','goa','7883244433','harshit@gmail.com','male','','','','','entertainer','',''),('01915602713','1310','Loveleen Aggarwal','z','CSE','A1','2','New Delhi','7883244433','loveleen@gmail.com','male','03100410','|101|102','0B100C100D10|105|106','0510061007100810|103','calm and composed','','36#37#38#'),('02015602713','1410','Shivam Aggarwal','z','CSE','T2','1','New Delhi','8783244433','shivam@gmail.com','male','','','','','curious','','');
/*!40000 ALTER TABLE `STUDENT_CSE` ENABLE KEYS */;
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
