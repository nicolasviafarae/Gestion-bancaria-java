-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sistemabancario
-- ------------------------------------------------------
-- Server version	8.0.46

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
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `Cliente_ID` int NOT NULL AUTO_INCREMENT,
  `Cedula` int NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Apellido` varchar(50) NOT NULL,
  `Correo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Cliente_ID`),
  UNIQUE KEY `Cedula` (`Cedula`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,1001001001,'Marcos','Zurdo','Zurdo@gmail.com'),(2,1001001002,'Laura','Martinez','laura@correo.com'),(3,1001001003,'Carlos','Rodriguez','carlos@correo.com'),(7,10022000,'luz','Segura','luz@gmail.com'),(8,1193118342,'Andres','Ramirez','andres@gmail.com');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuentas`
--

DROP TABLE IF EXISTS `cuentas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuentas` (
  `Cuenta_ID` int NOT NULL AUTO_INCREMENT,
  `Cliente_ID` int DEFAULT NULL,
  `tipo_Cuenta` varchar(50) NOT NULL,
  `Saldo` double NOT NULL DEFAULT (0),
  `Sucursal` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Cuenta_ID`),
  KEY `Cliente_ID` (`Cliente_ID`),
  CONSTRAINT `cuentas_ibfk_1` FOREIGN KEY (`Cliente_ID`) REFERENCES `clientes` (`Cliente_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuentas`
--

LOCK TABLES `cuentas` WRITE;
/*!40000 ALTER TABLE `cuentas` DISABLE KEYS */;
INSERT INTO `cuentas` VALUES (2,1,'Corriente',3000000,'Norte'),(3,2,'Ahorros',300000,'Centro'),(4,3,'Ahorros',950000,'Sur'),(5,7,'Ahorros',5000000,'Norte'),(6,7,'Corriente',3400000,'Centro'),(7,8,'Ahorros',2000000,'Centro'),(8,8,'Ahorros',2000000,'Sur'),(9,1,'Ahorros',1500000,'Centro'),(10,1,'Corriente',800000,'Norte'),(11,2,'Ahorros',2300000,'Centro'),(12,3,'Ahorros',950000,'Sur');
/*!40000 ALTER TABLE `cuentas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimientos`
--

DROP TABLE IF EXISTS `movimientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimientos` (
  `movimiento_ID` int NOT NULL AUTO_INCREMENT,
  `cuenta_ID` int DEFAULT NULL,
  `transferencia_ID` int DEFAULT NULL,
  `Fecha` date DEFAULT (curdate()),
  `tipo_Movimiento` varchar(50) NOT NULL,
  `Monto` double NOT NULL,
  PRIMARY KEY (`movimiento_ID`),
  KEY `cuenta_ID` (`cuenta_ID`),
  KEY `transferencia_ID` (`transferencia_ID`),
  CONSTRAINT `movimientos_ibfk_1` FOREIGN KEY (`cuenta_ID`) REFERENCES `cuentas` (`Cuenta_ID`),
  CONSTRAINT `movimientos_ibfk_2` FOREIGN KEY (`transferencia_ID`) REFERENCES `transferencias` (`transferencia_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimientos`
--

LOCK TABLES `movimientos` WRITE;
/*!40000 ALTER TABLE `movimientos` DISABLE KEYS */;
INSERT INTO `movimientos` VALUES (2,6,NULL,'2026-08-28','Deposito',500000),(4,6,NULL,'2026-08-28','Retiro',500000),(5,2,NULL,'2026-08-28','Deposito',100000),(7,5,1,'2026-08-29','Retiro',100000),(8,6,1,'2026-08-29','Deposito',100000),(9,6,1,'2026-08-29','Retiro',100000),(10,5,1,'2026-08-29','Deposito',100000),(11,5,NULL,'2026-08-29','Deposito',100000),(12,5,1,'2026-08-29','Retiro',100000),(13,6,1,'2026-08-29','Deposito',100000),(14,5,NULL,'2026-08-30','Deposito',5000000),(15,7,NULL,'2026-08-30','Deposito',1000000),(16,6,NULL,'2026-08-30','Deposito',1000000),(17,2,NULL,'2026-08-30','Retiro',900000),(18,2,NULL,'2026-08-30','Deposito',3000000),(19,3,1,'2026-08-30','Retiro',3000000),(20,3,1,'2026-08-30','Deposito',3000000),(21,3,1,'2026-08-30','Retiro',1000000),(22,2,1,'2026-08-30','Deposito',1000000),(23,3,1,'2026-08-30','Retiro',1000000),(24,2,1,'2026-08-30','Deposito',1000000);
/*!40000 ALTER TABLE `movimientos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transferencias`
--

DROP TABLE IF EXISTS `transferencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transferencias` (
  `transferencia_ID` int NOT NULL AUTO_INCREMENT,
  `transaccion_Fecha` date DEFAULT (curdate()),
  `Tipo_transacción` varchar(50) NOT NULL,
  PRIMARY KEY (`transferencia_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transferencias`
--

LOCK TABLES `transferencias` WRITE;
/*!40000 ALTER TABLE `transferencias` DISABLE KEYS */;
INSERT INTO `transferencias` VALUES (1,'2026-08-29',''),(2,'2026-08-29','Deposito'),(3,'2026-08-30','Deposito'),(4,'2026-08-30','Deposito'),(5,'2026-08-30','Deposito');
/*!40000 ALTER TABLE `transferencias` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-08-30 16:02:05
