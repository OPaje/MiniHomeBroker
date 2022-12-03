-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: bd
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `ativo`
--

DROP TABLE IF EXISTS `ativo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ativo` (
  `idativo` int NOT NULL AUTO_INCREMENT,
  `total_ativos` int NOT NULL,
  `preco_inicial` double NOT NULL,
  `nome_empresa` varchar(45) NOT NULL,
  `ticker` varchar(45) NOT NULL,
  `data_criacao_ativo` date NOT NULL,
  `data_modificacao_ativo` date NOT NULL,
  PRIMARY KEY (`idativo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ativo`
--

LOCK TABLES `ativo` WRITE;
/*!40000 ALTER TABLE `ativo` DISABLE KEYS */;
/*!40000 ALTER TABLE `ativo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ccorrente`
--

DROP TABLE IF EXISTS `ccorrente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ccorrente` (
  `idccorrente` int NOT NULL AUTO_INCREMENT,
  `saldo_ccorrente` double NOT NULL,
  `data_criacao_ccorrente` date NOT NULL,
  `data_modificacao_ccorrente` date NOT NULL,
  `ccorrente_cliente` int NOT NULL,
  PRIMARY KEY (`idccorrente`),
  KEY `fk_ccorrente_cli` (`ccorrente_cliente`),
  CONSTRAINT `fk_ccorrente_cli` FOREIGN KEY (`ccorrente_cliente`) REFERENCES `cliente` (`idcliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ccorrente`
--

LOCK TABLES `ccorrente` WRITE;
/*!40000 ALTER TABLE `ccorrente` DISABLE KEYS */;
/*!40000 ALTER TABLE `ccorrente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `idcliente` int NOT NULL AUTO_INCREMENT,
  `tipo_usuario` int NOT NULL,
  `nome_cliente` varchar(45) NOT NULL,
  `cpf` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `endereco` varchar(45) NOT NULL,
  `telefone` varchar(45) NOT NULL,
  `data_criacao_cliente` date NOT NULL,
  `data_modificacao_cliente` date NOT NULL,
  PRIMARY KEY (`idcliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meus_ativos`
--

DROP TABLE IF EXISTS `meus_ativos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `meus_ativos` (
  `idativo` int NOT NULL AUTO_INCREMENT,
  `qtde_ativo` int NOT NULL,
  `valor_pago_ativo` double NOT NULL,
  `cotacao_ativo` double NOT NULL,
  `total_din_ativo` double NOT NULL,
  `atv_meus_atv` int NOT NULL,
  `ccorrente_meus_atv` int NOT NULL,
  PRIMARY KEY (`idativo`),
  KEY `fk_atvs_meus_atvs` (`atv_meus_atv`),
  CONSTRAINT `fk_atvs_meus_atvs` FOREIGN KEY (`atv_meus_atv`) REFERENCES `ativo` (`idativo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meus_ativos`
--

LOCK TABLES `meus_ativos` WRITE;
/*!40000 ALTER TABLE `meus_ativos` DISABLE KEYS */;
/*!40000 ALTER TABLE `meus_ativos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mov_conta`
--

DROP TABLE IF EXISTS `mov_conta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mov_conta` (
  `idmovimento` int NOT NULL AUTO_INCREMENT,
  `tipo_mov` varchar(45) NOT NULL,
  `desc_mov` varchar(45) NOT NULL,
  `valor_mov` double NOT NULL,
  `data_criacao_mov` date NOT NULL,
  `data_modificacao_mov` date NOT NULL,
  `mov_conta_ccorrente` int NOT NULL,
  PRIMARY KEY (`idmovimento`),
  KEY `fk_mov_conta_ccorrente` (`mov_conta_ccorrente`),
  CONSTRAINT `fk_mov_conta_ccorrente` FOREIGN KEY (`mov_conta_ccorrente`) REFERENCES `ccorrente` (`idccorrente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mov_conta`
--

LOCK TABLES `mov_conta` WRITE;
/*!40000 ALTER TABLE `mov_conta` DISABLE KEYS */;
/*!40000 ALTER TABLE `mov_conta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordem`
--

DROP TABLE IF EXISTS `ordem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordem` (
  `idordem` int NOT NULL AUTO_INCREMENT,
  `qtde_ordem` int NOT NULL,
  `valor_ordem` double NOT NULL,
  `valor_total_ordem` double NOT NULL,
  `tipo_ordem` varchar(45) NOT NULL,
  `estado_ordem` varchar(45) NOT NULL,
  `data_criacao_ordem` date NOT NULL,
  `data_modificacao_ordem` date NOT NULL,
  `ordem_ccorrente` int NOT NULL,
  `ordem_ativo` int NOT NULL,
  PRIMARY KEY (`idordem`),
  KEY `fk_ordem_ccorrente` (`ordem_ccorrente`),
  KEY `fk_ordem_ativo` (`ordem_ativo`),
  CONSTRAINT `fk_ordem_ativo` FOREIGN KEY (`ordem_ativo`) REFERENCES `ativo` (`idativo`),
  CONSTRAINT `fk_ordem_ccorrente` FOREIGN KEY (`ordem_ccorrente`) REFERENCES `ccorrente` (`idccorrente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordem`
--

LOCK TABLES `ordem` WRITE;
/*!40000 ALTER TABLE `ordem` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordem_exec`
--

DROP TABLE IF EXISTS `ordem_exec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordem_exec` (
  `idordem_exec` int NOT NULL AUTO_INCREMENT,
  `qtde_ordem_exec` int NOT NULL,
  `data_criacao_ordem_exec` date NOT NULL,
  `data_mod_ordem_exec` date NOT NULL,
  `ordem_exec_ordemc` int NOT NULL,
  `ordem_exec_ordemv` int NOT NULL,
  `ordem_exec_ccorrentec` int NOT NULL,
  `ordem_exec_ccorrentev` int NOT NULL,
  PRIMARY KEY (`idordem_exec`),
  KEY `fk_ordem_exec_ordemc` (`ordem_exec_ordemc`),
  KEY `fk_ordem_exec_ordemv` (`ordem_exec_ordemv`),
  KEY `fk_ordem_exec_ccorrentec` (`ordem_exec_ccorrentec`),
  KEY `fk_ordem_exec_ccorrentev` (`ordem_exec_ccorrentev`),
  CONSTRAINT `fk_ordem_exec_ccorrentec` FOREIGN KEY (`ordem_exec_ccorrentec`) REFERENCES `ccorrente` (`idccorrente`),
  CONSTRAINT `fk_ordem_exec_ccorrentev` FOREIGN KEY (`ordem_exec_ccorrentev`) REFERENCES `ccorrente` (`idccorrente`),
  CONSTRAINT `fk_ordem_exec_ordemc` FOREIGN KEY (`ordem_exec_ordemc`) REFERENCES `ordem` (`idordem`),
  CONSTRAINT `fk_ordem_exec_ordemv` FOREIGN KEY (`ordem_exec_ordemv`) REFERENCES `ordem` (`idordem`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordem_exec`
--

LOCK TABLES `ordem_exec` WRITE;
/*!40000 ALTER TABLE `ordem_exec` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordem_exec` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-03 17:40:28
