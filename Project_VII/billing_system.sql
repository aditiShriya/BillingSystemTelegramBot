-- MySQL dump 10.13  Distrib 5.7.33, for Linux (x86_64)
--
-- Host: localhost    Database: billing_system
-- ------------------------------------------------------
-- Server version	5.7.33-0ubuntu0.16.04.1

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
-- Table structure for table `Consumer_Information`
--

DROP TABLE IF EXISTS `Consumer_Information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Consumer_Information` (
  `Area` varchar(20) DEFAULT NULL,
  `Circle` varchar(20) DEFAULT NULL,
  `Division` varchar(20) DEFAULT NULL,
  `SubDivision` varchar(20) DEFAULT NULL,
  `cust_id` varchar(20) NOT NULL,
  `Consumer_No` varchar(20) DEFAULT NULL,
  `Consumer_Name` varchar(20) DEFAULT NULL,
  `Mobile_No` varchar(20) DEFAULT NULL,
  `Email_ID` varchar(20) DEFAULT NULL,
  `Address` varchar(20) DEFAULT NULL,
  `meter_type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Consumer_Information`
--

LOCK TABLES `Consumer_Information` WRITE;
/*!40000 ALTER TABLE `Consumer_Information` DISABLE KEYS */;
INSERT INTO `Consumer_Information` VALUES ('Jharkhand','E.Singhbhum','Jamshedpur','Golmuri','LMNO1400','UN01','Aditi Shriya','9570121221','aaditi@gmail.com','109/2/2','personal'),('Jharkhand','E.Singhbhum','Jamshedpur','Golmuri','LMNO1410','UN02','Puja Sowmondal','0123456789','pujas@gmail.com','110/2/2','commercial'),('Jharkhand','E.Singhbhum','Jamshedpur','Golmuri','LMNO1420','UN03','Ausmita','9876543210','aushmita@gmail.com','111/2/2','personal'),('Jharkhand','E.Singhbhum','Jamshedpur','Golmuri','LMNO1430','UN04','Priya Sharma','9657822221','priya@gmail.com','112/2/2','commercial'),('Jharkhand','E.Singhbhum','Jamshedpur','Golmuri','LMNO1440','UN05','Vikrant kumar','9570121221','vk@gmail.com','113/2/2','commercial');
/*!40000 ALTER TABLE `Consumer_Information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `current_active_bill`
--

DROP TABLE IF EXISTS `current_active_bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `current_active_bill` (
  `user_id` varchar(20) DEFAULT NULL,
  `bill_month` varchar(20) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `meter_reading_end` int(11) DEFAULT NULL,
  `meter_reading_start` int(11) DEFAULT NULL,
  `meter_type` varchar(15) DEFAULT NULL,
  `timestamp` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `current_active_bill`
--

LOCK TABLES `current_active_bill` WRITE;
/*!40000 ALTER TABLE `current_active_bill` DISABLE KEYS */;
INSERT INTO `current_active_bill` VALUES ('user001','January',2021,4678,4563,'personal','2021-02-05'),('user001','December',2020,4563,4489,'personal','2021-01-08'),('user001','November',2020,4489,4421,'personal','2020-12-03'),('user002','January',2021,10653,10432,'commercial','2021-02-04'),('user003','January',2021,1653,1611,'personal','2021-02-05'),('user003','December',2020,1611,1598,'personal','2021-01-12'),('user004','January',2021,5253,5132,'commercial','2021-02-09');
/*!40000 ALTER TABLE `current_active_bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `last_payment_details`
--

DROP TABLE IF EXISTS `last_payment_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `last_payment_details` (
  `user_id` varchar(15) DEFAULT NULL,
  `transaction_id` varchar(45) DEFAULT NULL,
  `opening_unit` int(11) DEFAULT NULL,
  `closing_unit` int(11) DEFAULT NULL,
  `bill_month` varchar(20) DEFAULT NULL,
  `timestamp` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `last_payment_details`
--

LOCK TABLES `last_payment_details` WRITE;
/*!40000 ALTER TABLE `last_payment_details` DISABLE KEYS */;
INSERT INTO `last_payment_details` VALUES ('user001','BPQ001',4299,4421,'October','2020-10-18'),('user002','BPQ011',10267,10432,'December','2020-12-15'),('user003','BPQ053',1513,1598,'November','2020-11-08'),('user004','BPQ099',5098,5132,'December','2020-12-24'),('user001','BPQ002',4361,4421,'September','2020-09-30');
/*!40000 ALTER TABLE `last_payment_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_details`
--

DROP TABLE IF EXISTS `user_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_details` (
  `user_id` varchar(7) DEFAULT NULL,
  `pwd` varchar(20) DEFAULT NULL,
  `cust_name` varchar(50) DEFAULT NULL,
  `meter_no` int(11) DEFAULT NULL,
  `cust_id` varchar(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_details`
--

LOCK TABLES `user_details` WRITE;
/*!40000 ALTER TABLE `user_details` DISABLE KEYS */;
INSERT INTO `user_details` VALUES ('user001','abc001','Aditi Shriya',123456,'LMNO1400'),('user002','def002','Puja Sowmondal',123457,'LMNO1410'),('user003','ghi003','Ausmita',123458,'LMNO1420'),('user004','jkl004','Priya Sharma',123459,'LMNO1430'),('user005','mno005','Vikrant Kumar',123460,'LMNO1440');
/*!40000 ALTER TABLE `user_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-25 19:34:02
