CREATE DATABASE  IF NOT EXISTS `locadora` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `locadora`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: locadora
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `carro`
--

DROP TABLE IF EXISTS `carro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carro` (
  `id` int NOT NULL AUTO_INCREMENT,
  `modelo` varchar(45) DEFAULT NULL,
  `placa` varchar(10) DEFAULT NULL,
  `cor` varchar(10) DEFAULT NULL,
  `ano` int DEFAULT NULL,
  `dataAquisicao` date DEFAULT NULL,
  `Categoria_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Carro_Categoria_idx` (`Categoria_id`),
  CONSTRAINT `fk_Carro_Categoria` FOREIGN KEY (`Categoria_id`) REFERENCES `categoria` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carro`
--

LOCK TABLES `carro` WRITE;
/*!40000 ALTER TABLE `carro` DISABLE KEYS */;
INSERT INTO `carro` VALUES (1,'Fiat Uno 1.0','NEN-6925','VERMELHA',2020,'2020-01-30',1),(2,'Fiat Uno 1.0','NEU-4521','BRANCA',2021,'2021-06-20',2),(3,'Fiat 500e Elétrico','NEV-8047','VERMELHA',2022,'2022-03-15',3),(4,'Ford Ka Hatch 1.0','NEX-5158','PRETA',2020,'2020-01-25',4),(5,'Renault Duster 1.6','NEU-7319','BRANCA',2022,'2022-06-10',6),(6,'Fiat Uno 1.0','NEL-7448','CINZA',2021,'2021-05-12',1),(7,'Fiat Mobi 1.0','NEW-6215','BRANCA',2021,'2021-02-28',2),(8,'Renault Kwid 1.0','NEK-5322','CINZA',2022,'2022-04-18',2),(9,'Peugeot e-208 GT Elétrico','NEU-5494','PRETA',2022,'2022-01-25',3),(10,'Toyota Corolla 2.0','NET-4338','CINZA',2022,'2022-01-20',7),(11,'Hyundai HB20S 1.0','JYE-5433','CINZA',2020,'2020-05-29',4),(12,'Hyundai HB20S 1.0','HNM-3994','BRANCA',2022,'2022-01-29',4),(13,'Hyundai HB20S 1.0','IAE-3667','VERMELHA',2021,'2021-07-20',4),(14,'Audi A4 2.0 Turbo','NAC-8339','BRANCA',2022,'2022-09-01',8),(16,'Hyundai HB20S 1.0','AMQ-5596','PRETA',2020,'2020-10-15',11),(17,'VW Virtus 1.0 Turbo FAST','MUS-7945','BRANCA',2019,'2019-05-01',12);
/*!40000 ALTER TABLE `carro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descricao` varchar(45) DEFAULT NULL,
  `precoDiaria` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Compacto',90),(2,'Compacto com ar',100),(3,'Descrição: Compacto elétrico',120),(4,'Econômico com ar',80),(5,'Econômico',60),(6,'SUV',130),(7,'Executivo',110),(8,'Executivo Bindado',250),(11,'Econômico Sedan c/ ar',85),(12,'Intermediário Sedan',115);
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `cpf` varchar(11) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Ononurci','15963429005','Ononurci@gmail.com'),(2,'Wogel','24035321001','Wogel@gmail.com'),(3,'Nekol','90224046098','Nekol@gmail.com'),(4,'Mayki','55662703019','Mayki@gmail.com'),(5,'Zitio','90428770061','Zitio@gmail.com'),(6,'Tiadu','03352445052','Tiadu@gmail.com'),(7,'Claci','32052974075','Claci@gmail.com'),(8,'Voeun','43779421070','Voeun@gmail.com'),(9,'Renoer','33561225098','Renoer@gmail.com'),(10,'Fedil','19377378028','Fedil@gmail.com'),(11,'Gramseza','51835217001','Gramseza@gmail.com'),(12,'Fallreola','54476913032','Fallreola@gmail.com'),(13,'Kaergo Rodrigues','41262978041','kaergo@gmail.com'),(14,'João da Mata','89226872074','joao@hotmail.com');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locacao`
--

DROP TABLE IF EXISTS `locacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `locacao` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dataRetirada` datetime DEFAULT NULL,
  `dataDevolucao` datetime DEFAULT NULL,
  `diasPrevistoDevolucao` int DEFAULT NULL,
  `porcentagemDesconto` double DEFAULT NULL,
  `Cliente_id` int NOT NULL,
  `Carro_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Locacao_Cliente1_idx` (`Cliente_id`),
  KEY `fk_Locacao_Carro1_idx` (`Carro_id`),
  CONSTRAINT `fk_Locacao_Carro1` FOREIGN KEY (`Carro_id`) REFERENCES `carro` (`id`),
  CONSTRAINT `fk_Locacao_Cliente1` FOREIGN KEY (`Cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locacao`
--

LOCK TABLES `locacao` WRITE;
/*!40000 ALTER TABLE `locacao` DISABLE KEYS */;
INSERT INTO `locacao` VALUES (5,'2022-09-05 11:00:00','2022-11-10 12:01:00',5,0.1,13,6),(7,'2022-09-05 11:00:00','2022-09-10 12:01:00',5,NULL,13,14),(8,'2022-09-05 11:00:00','2022-09-10 12:01:00',5,NULL,13,14),(9,'2022-09-05 11:00:00','2022-09-10 12:01:00',5,NULL,13,14),(10,'2022-09-05 11:00:00','2022-09-06 12:01:00',2,NULL,13,14),(11,'2022-09-05 11:00:00','2022-12-06 12:01:00',NULL,0.05,13,14),(12,'2022-09-05 11:00:00','2022-11-10 12:01:00',NULL,0.12,8,6),(13,'2022-09-05 11:00:00','2022-12-06 12:01:00',NULL,0.08,1,1),(14,'2022-09-05 11:00:00','2022-12-06 12:01:00',NULL,0.07,8,4),(15,'2022-09-05 11:00:00','2022-09-08 12:01:00',3,NULL,13,14),(16,'2022-09-05 11:00:00','2022-10-06 12:01:00',NULL,0.15,13,14),(17,'2022-09-07 00:35:00','2022-09-10 01:00:00',3,NULL,5,3),(18,'2022-09-07 00:36:00','2022-12-30 12:00:00',NULL,0.2,10,8),(19,'2022-09-08 23:00:00','2022-09-11 10:00:00',3,NULL,14,5),(20,'2022-09-08 23:00:00','2022-10-07 23:00:00',NULL,0.05,14,6);
/*!40000 ALTER TABLE `locacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefone`
--

DROP TABLE IF EXISTS `telefone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `telefone` (
  `id` int NOT NULL AUTO_INCREMENT,
  `numero` varchar(11) DEFAULT NULL,
  `Cliente_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Telefone_Cliente1_idx` (`Cliente_id`),
  CONSTRAINT `fk_Telefone_Cliente1` FOREIGN KEY (`Cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefone`
--

LOCK TABLES `telefone` WRITE;
/*!40000 ALTER TABLE `telefone` DISABLE KEYS */;
INSERT INTO `telefone` VALUES (1,'92526550049',1),(2,'92623714376',1),(3,'92777071216',2),(4,'92469044037',3),(5,'92811563764',4),(6,'92355113943',5),(7,'92395898536',6),(8,'92544407704',7),(9,'92308718622',8),(10,'92842193171',9),(11,'92365606177',10),(12,'92413254092',11),(13,'92783457414',12),(14,'92995563670',13),(15,'92984816685',13),(16,'92995432503',13),(17,'92991212208',14),(18,'92991357311',14);
/*!40000 ALTER TABLE `telefone` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-09  3:06:47
