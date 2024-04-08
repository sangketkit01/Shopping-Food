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
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `shop` varchar(255) NOT NULL,
  `food_name` varchar(255) NOT NULL,
  `price` int NOT NULL DEFAULT '0',
  `url` text NOT NULL,
  `piece` int NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES ('Mario store','Beef wellington',1200,'FoodPic/beefwellington.png',2,'sangketkit03'),('Mario store','Hamburger',180,'FoodPic/hamburger.png',1,'sangketkit03'),('Mario store','Pizza Peperioni',300,'FoodPic/peperoni.jpg',1,'sangketkit03'),('Mario store','Foiegras',790,'FoodPic/foiegras.jpg',1,'sangketkit03'),('Mario store','Beef Wellington',1200,'FoodPic/Mario store_beefwellington.png',2,'kitty'),('Mario store','Foiegras',890,'FoodPic/Mario store_foiegras.jpg',2,'kitty'),('Mario store','Cottage Cheese Pasta',400,'FoodPic/Mario store_cottagecheese.jpg',2,'kitty'),('Mario store','Pizza Peperoni',490,'FoodPic/Mario store_peperoni.jpg',2,'kitty'),('Mario store','Spaghetti Carbonara',350,'FoodPic/Mario store_carbonara.jpg',2,'kitty'),('Mario store','Beef Tartare',1050,'FoodPic/Mario store_beeftartare.jpg',2,'kitty');
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
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
