-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: store
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `store_user`
--

DROP TABLE IF EXISTS `store_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store_user` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `store_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `bank` varchar(50) NOT NULL,
  `bank_account` varchar(50) NOT NULL,
  `category` varchar(50) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store_user`
--

LOCK TABLES `store_user` WRITE;
/*!40000 ALTER TABLE `store_user` DISABLE KEYS */;
INSERT INTO `store_user` VALUES ('jirchin01','15092548','Jirchin','jiruttikan','Kuy Jirchin','jiruttikan.c@kkumail.com','0123456788','Krungthai','112233445566','main_dish','ShopPic/Kuy Jirchin_goIcon.jpg'),('Mario','mario101','Mario','chanatrip','Mario Store','mario@gmail.com','0123456789','SCB','1122334455','main_dish','ShopPic/Mario Store_cIcon.jpg'),('Pat','qwerty','Thanapat','Supen','Teri','Teriri@gmail.net','0910212772','บางระจันทร์','0987612345','drink','ShopPic/Teri_javaIcon.jpg'),('sangketkit01','0627457454New','Sangketkit01','Sangketkit','Thiraphat shop','thiraphat120@hotmail.com','0619933073','KBank','0762800675','dessert','ShopPic/goIcon.jpg'),('sangketkit02','0627457454New','Sangketkit02','Sangketkit','A shop','thiraphat.sa@kkumail.com','0627457454New','KBank','0762800675','main_dish','ShopPic/jsIcon.png'),('sangketkit03','0627457454New','Sangketkit03','Sangketkit','B shop','thiraphat.sa1@kkumail.com','0616148569','KBank','0762800675','main_dish','ShopPic/pythonIcon.jpg'),('Sangketkit05','0627457454New','Haha','Sangketkit','OOP shop','thiraphat_120@hotmail.com','0627457454','บางระจันทร์','0762800675','dessert','ShopPic/OOP store_cIcon.jpg'),('saranasus','1','Thiraphat','Tramtaisong','Kaprao JingJung','saranasus@gmail.com','0698392103','กรุงเทพ','1238881271','main_dish','ShopPic/Kaprao JingJung_cIcon.jpg'),('thiraphat_120','271146','thiraphat.sa','Sangketkit','HCI HK','thiraphat1324@outlook.co.th','0611993102','SCB','4220518219','main_dish','ShopPic/goIcon.jpg'),('thiraphat1324','271146','thiraphat1324','Sangketkit','Computer Shop','thiraphat_27@hotmail.com','0632171111','SCB','4220518219','drink','ShopPic/javaIcon.jpg'),('uppower002','o789987789987','chanathip','silaphol','Oh','uppower002@gmil.com','0619308198','บางระจันทร์','1234567890','main_dish','ShopPic/Oh_Teri_javaIcon.jpg');
/*!40000 ALTER TABLE `store_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-02  6:03:29
