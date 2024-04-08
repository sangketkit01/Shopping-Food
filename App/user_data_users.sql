-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: user_data
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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('dqwdwq','dqwdq@dqwddwq.com','1','1231232141',NULL,'dqwdwq'),('jayjay','jayjay@gmail.com','1','0945197693','profile\\jayjay.jpg','jayjay'),('jirchin','jirchin11@hotmail.com','1','0666666666','profile\\jirchin.jpg','jirchin'),('kitty','kitty@gmail.com','1','0981234567','profile\\kitty.png','kiitty@!#$%'),('kong','johnny@gmail.com','1','0983123215','profile\\kong.png','kong'),('kuyjirchin','kuyjirchin@gmail.com','1','0632183792','profile\\kuyjirchin.jpg','kuyjirchin'),('pat1','qwddwq@wqddq.com','11','1123124214',NULL,'pat1'),('sangketkit01','thiraphat_120@hotmail.com','1','0627457454','profile\\sangketkit01.jpg','sangketkit01'),('sangketkit02','thiraphat_121@hotmail.com','1','0627457453','profile\\sangketkit02.jpg','Thiraphat.sa'),('sangketkit03','thiraphat_122@hotmail.com','1','0831293812',NULL,'sangketkit03'),('Sei','Seisai@gmail.net','wasd','0899996969',NULL,NULL),('tanny','tanny@gmail.com','1','0623124712','profile\\tanny.jpg','tanny'),('test1','test1@gmail.com','1','8321739012',NULL,'test1'),('test2','test2@gmail.com','1','1241292183',NULL,'test2'),('uppower','uppower@gmail.com','1','0615308198','profile\\uppower.jpg','uppower'),('uppower002','uppower002@gmail.com','o789987789987','1234567890','profile\\uppower002.jpg','uppower002');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
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
