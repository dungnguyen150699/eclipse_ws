-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: demo_teach_hung
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `doi_tac`
--

DROP TABLE IF EXISTS `doi_tac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doi_tac` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dia_chi` varchar(255) NOT NULL,
  `ma_so_thue` varchar(255) NOT NULL,
  `tencty` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doi_tac`
--

LOCK TABLES `doi_tac` WRITE;
/*!40000 ALTER TABLE `doi_tac` DISABLE KEYS */;
/*!40000 ALTER TABLE `doi_tac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dsmat_hang`
--

DROP TABLE IF EXISTS `dsmat_hang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dsmat_hang` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `count` int NOT NULL,
  `tbl_hop_dong_tra_gopmahd` bigint DEFAULT NULL,
  `tbl_mat_hangid` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK313mifqkx8cvodea0l5v6u2lp` (`tbl_hop_dong_tra_gopmahd`),
  KEY `FKds234rtwuaqb4yu7uhlg9ivxv` (`tbl_mat_hangid`),
  CONSTRAINT `FK313mifqkx8cvodea0l5v6u2lp` FOREIGN KEY (`tbl_hop_dong_tra_gopmahd`) REFERENCES `hop_dong_tra_gop` (`id`),
  CONSTRAINT `FKds234rtwuaqb4yu7uhlg9ivxv` FOREIGN KEY (`tbl_mat_hangid`) REFERENCES `mat_hang` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dsmat_hang`
--

LOCK TABLES `dsmat_hang` WRITE;
/*!40000 ALTER TABLE `dsmat_hang` DISABLE KEYS */;
/*!40000 ALTER TABLE `dsmat_hang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoa_don`
--

DROP TABLE IF EXISTS `hoa_don`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoa_don` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ds_thoi_diemttcon_lai` datetime(6) NOT NULL,
  `tbl_hop_dong_tra_gopmahd` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgr9t9xkwkod2eobwdo7lr8vf1` (`tbl_hop_dong_tra_gopmahd`),
  CONSTRAINT `FKgr9t9xkwkod2eobwdo7lr8vf1` FOREIGN KEY (`tbl_hop_dong_tra_gopmahd`) REFERENCES `hop_dong_tra_gop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoa_don`
--

LOCK TABLES `hoa_don` WRITE;
/*!40000 ALTER TABLE `hoa_don` DISABLE KEYS */;
/*!40000 ALTER TABLE `hoa_don` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hop_dong_tra_gop`
--

DROP TABLE IF EXISTS `hop_dong_tra_gop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hop_dong_tra_gop` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ds_thoi_diemtt` datetime(6) NOT NULL,
  `lai_xuat` float NOT NULL,
  `thoi_han_vay` datetime(6) NOT NULL,
  `doitacid` bigint DEFAULT NULL,
  `khach_hangid` bigint DEFAULT NULL,
  `nhanvienid` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhrqj7u52aykykiy0j7qe6uu82` (`doitacid`),
  KEY `FKs13cm25oha52alf42xlclayop` (`khach_hangid`),
  KEY `FK88bxl7ya420yk0l2s9r2t06f5` (`nhanvienid`),
  CONSTRAINT `FK88bxl7ya420yk0l2s9r2t06f5` FOREIGN KEY (`nhanvienid`) REFERENCES `nhan_vien` (`id`),
  CONSTRAINT `FKhrqj7u52aykykiy0j7qe6uu82` FOREIGN KEY (`doitacid`) REFERENCES `doi_tac` (`id`),
  CONSTRAINT `FKs13cm25oha52alf42xlclayop` FOREIGN KEY (`khach_hangid`) REFERENCES `khach_hang` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hop_dong_tra_gop`
--

LOCK TABLES `hop_dong_tra_gop` WRITE;
/*!40000 ALTER TABLE `hop_dong_tra_gop` DISABLE KEYS */;
/*!40000 ALTER TABLE `hop_dong_tra_gop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khach_hang`
--

DROP TABLE IF EXISTS `khach_hang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khach_hang` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dia_chi` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `ngay_sinh` datetime(6) DEFAULT NULL,
  `sdt` varchar(255) NOT NULL,
  `so_cmt` varchar(255) NOT NULL,
  `ten` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khach_hang`
--

LOCK TABLES `khach_hang` WRITE;
/*!40000 ALTER TABLE `khach_hang` DISABLE KEYS */;
/*!40000 ALTER TABLE `khach_hang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mat_hang`
--

DROP TABLE IF EXISTS `mat_hang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mat_hang` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `chung_loai` int NOT NULL,
  `chiet_khau` varchar(255) NOT NULL,
  `don_vi_tinh` varchar(10) NOT NULL,
  `gia_niem_yet` varchar(255) NOT NULL,
  `ten` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mat_hang`
--

LOCK TABLES `mat_hang` WRITE;
/*!40000 ALTER TABLE `mat_hang` DISABLE KEYS */;
/*!40000 ALTER TABLE `mat_hang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhan_vien`
--

DROP TABLE IF EXISTS `nhan_vien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhan_vien` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dia_chi` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `ngay_sinh` datetime(6) NOT NULL,
  `password` varchar(255) NOT NULL,
  `ten` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `vi_tri` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhan_vien`
--

LOCK TABLES `nhan_vien` WRITE;
/*!40000 ALTER TABLE `nhan_vien` DISABLE KEYS */;
/*!40000 ALTER TABLE `nhan_vien` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-11 23:37:45
