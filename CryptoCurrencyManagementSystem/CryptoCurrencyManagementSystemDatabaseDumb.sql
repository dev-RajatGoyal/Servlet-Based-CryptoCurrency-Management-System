-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: cryptocurrencymanagementsystem
-- ------------------------------------------------------
-- Server version	5.5.49

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `adminId` int(11) NOT NULL AUTO_INCREMENT,
  `adminName` varchar(45) DEFAULT NULL,
  `adminEmail` varchar(45) DEFAULT NULL,
  `adminPassword` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (101,'admin','admin@gmail.com','admin@123');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `clientId` int(11) NOT NULL,
  `clientName` varchar(45) DEFAULT NULL,
  `clientEmail` varchar(45) DEFAULT NULL,
  `clientPassword` varchar(45) DEFAULT NULL,
  `clientPhone` varchar(45) DEFAULT NULL,
  `clientAddress` varchar(45) DEFAULT NULL,
  `clientBalance` int(11) DEFAULT NULL,
  `clientInvestment` int(11) DEFAULT NULL,
  PRIMARY KEY (`clientId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (102,'Rohini','rohini@gmail.com','rohini@123','456789','Delhi',25200,14800),(103,'Naveen Sir','naveen.kumar@realcoderz.in','naveen@123','1234561','Noida',55000,10000),(104,'Rajat','rajat@gmail.com','rajat123','456789','Bombay',80000,0),(105,'abc','abc@gmail.com','abc','457958','bombay',52000,0),(107,'Vishesh','vishesh100goyal@gmail.com','vishu123','789456','Dehradun',42500,15000),(110,'Aman','aman@gmail.com','aman123','8979745622','Kanpur',80000,0),(222,'xyz','xyz@gmail.com','xyz','111111','us',80000,20000);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cryptocurrency`
--

DROP TABLE IF EXISTS `cryptocurrency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cryptocurrency` (
  `cryptoId` int(11) NOT NULL AUTO_INCREMENT,
  `cryptoName` varchar(45) DEFAULT NULL,
  `cryptoPrice` int(11) DEFAULT NULL,
  PRIMARY KEY (`cryptoId`),
  UNIQUE KEY `cryptoName_UNIQUE` (`cryptoName`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cryptocurrency`
--

LOCK TABLES `cryptocurrency` WRITE;
/*!40000 ALTER TABLE `cryptocurrency` DISABLE KEYS */;
INSERT INTO `cryptocurrency` VALUES (101,'BitCoin',500),(102,'Ethereum',400),(103,'BinanceCoin',300),(104,'Tether',350),(105,'Nifty50',250),(106,'Share',250);
/*!40000 ALTER TABLE `cryptocurrency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `investmentdetails`
--

DROP TABLE IF EXISTS `investmentdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `investmentdetails` (
  `investmentId` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `cryptoId` int(11) DEFAULT NULL,
  `clientId` int(11) DEFAULT NULL,
  `investment` int(11) DEFAULT NULL,
  PRIMARY KEY (`investmentId`),
  KEY `cryptoId_idx` (`cryptoId`),
  KEY `clientId_idx` (`clientId`),
  CONSTRAINT `clientId` FOREIGN KEY (`clientId`) REFERENCES `client` (`clientId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `cryptoId` FOREIGN KEY (`cryptoId`) REFERENCES `cryptocurrency` (`cryptoId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `investmentdetails`
--

LOCK TABLES `investmentdetails` WRITE;
/*!40000 ALTER TABLE `investmentdetails` DISABLE KEYS */;
INSERT INTO `investmentdetails` VALUES (9,'2021-05-10',101,102,5500),(11,'2021-05-04',103,102,4500),(12,'2021-04-30',101,107,17500),(14,'2021-05-04',102,102,4800),(15,'2021-05-11',101,103,15000),(16,'2021-05-11',102,103,10000);
/*!40000 ALTER TABLE `investmentdetails` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-16  4:56:02
