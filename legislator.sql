-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 27, 2022 at 08:40 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `javatest`
--

-- --------------------------------------------------------

--
-- Table structure for table `legislator`
--

CREATE TABLE `legislator` (
  `cid` varchar(255) NOT NULL,
  `firstlast` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `party` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `firstelectoff` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `votesmart_id` varchar(255) NOT NULL,
  `feccandid` varchar(255) NOT NULL,
  `birthdate` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `legislator`
--
ALTER TABLE `legislator`
  ADD PRIMARY KEY (`cid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
