-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.20-log - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for mobile
CREATE DATABASE IF NOT EXISTS `mobile` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mobile`;

-- Dumping structure for table mobile.phones
CREATE TABLE IF NOT EXISTS `phones` (
  `phone_id` int(11) NOT NULL AUTO_INCREMENT,
  `name1` varchar(50) NOT NULL,
  `quantity1` int(11) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`phone_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Dumping data for table mobile.phones: ~5 rows (approximately)
/*!40000 ALTER TABLE `phones` DISABLE KEYS */;
INSERT INTO `phones` (`phone_id`, `name1`, `quantity1`, `price`) VALUES
	(1, 'Iphone 8', 22, 899),
	(2, 'LG G6', 20, 499),
	(3, 'Samsung S8', 55, 599),
	(4, 'Huawei P10', 18, 499),
	(5, 'Sony XZ Premium', 41, 549);
/*!40000 ALTER TABLE `phones` ENABLE KEYS */;

-- Dumping structure for table mobile.purchases
CREATE TABLE IF NOT EXISTS `purchases` (
  `purchase_id` int(20) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `prezime` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  PRIMARY KEY (`purchase_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- Dumping data for table mobile.purchases: ~42 rows (approximately)
/*!40000 ALTER TABLE `purchases` DISABLE KEYS */;
INSERT INTO `purchases` (`purchase_id`, `quantity`, `name`, `prezime`, `email`, `phone`) VALUES
	(1, 5, '4', '4', 'hhdr', 'hh'),
	(2, 5, '4', '4', 'hhdr', 'hh'),
	(3, 5, '4', '4', 'hhdr', 'hh'),
	(4, 1, 'h', 'h', 'hhdr', 'h'),
	(5, 1, 'h', 'h', 'hhdr', 'h'),
	(6, 1, 'h', 'h', 'hhdr', 'h'),
	(7, 1, 'h', 'h', 'hhdr', 'h'),
	(8, 1, 'h', 'h', 'hhdr', 'h'),
	(9, 1, 'h', 'h', 'hhdr', 'h'),
	(10, 1, 'h', 'h', 'hhdr', 'h'),
	(11, 1, 'h', 'h', 'hhdr', 'h'),
	(12, 1, 'Mata', 'Mikulandra', 'mikulandra.matea@gmail.com', '066'),
	(13, 1, 'Mata', 'Mikulandra', 'mikulandra.matea@gmail.com', '066'),
	(14, 1, 'Mata', 'Mikulandra', 'mikulandra.matea@gmail.com', '066'),
	(15, 1, 'Mata', 'Mikulandra', 'mikulandra.matea@gmail.com', '066'),
	(16, 1, 'Mata', 'Mikulandra', 'mikulandra.matea@gmail.com', '066'),
	(17, 1, 'Mata', 'Mikulandra', 'mikulandra.matea@gmail.com', '066'),
	(18, 1, 'Mata', 'Mikulandra', 'mikulandra.matea@gmail.com', '066'),
	(19, 1, 'Mata', 'Mikulandra', 'mikulandra.matea@gmail.com', '066'),
	(20, 1, 'Mata', 'Mikulandra', 'mikulandra.matea@gmail.com', '066'),
	(21, 1, 'Mata', 'Mikulandra', 'mikulandra.matea@gmail.com', '066'),
	(22, 1, 'Mata', 'Mikulandra', 'mikulandra.matea@gmail.com', '066'),
	(23, 1, '', '', '', ''),
	(24, 1, '', '', '', ''),
	(25, 3, 'Mata', 'Mikulandra', 'mikulandra.matea@gmail.com', '066'),
	(26, 1, '', '', '', ''),
	(27, 1, '', '', '', ''),
	(28, 1, '', '', '', ''),
	(29, 1, '', '', '', ''),
	(30, 1, '', '', '', ''),
	(31, 1, '', '', '', ''),
	(32, 1, '', '', '', ''),
	(33, 1, '', '', '', ''),
	(34, 1, '', '', '', ''),
	(35, 1, '', '', '', ''),
	(36, 1, '', '', '', ''),
	(37, 1, '', '', '', ''),
	(38, 1, '', '', '', ''),
	(39, 1, '', '', '', ''),
	(40, 1, '', '', '', ''),
	(41, 1, '', '', '', ''),
	(42, 1, '', '', '', '');
/*!40000 ALTER TABLE `purchases` ENABLE KEYS */;

-- Dumping structure for table mobile.users
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table mobile.users: ~3 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`user_id`, `username`, `password`) VALUES
	(1, 'admin', 'admin'),
	(2, 'admin', 'admin'),
	(3, 'mata', 'mata');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
