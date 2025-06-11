-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 10, 2020 at 04:18 PM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `super_market`
--

-- --------------------------------------------------------

--
-- Table structure for table `bills`
--

CREATE TABLE `bills` (
  `bil_id` int(11) NOT NULL,
  `factor_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `bil_reminder` double DEFAULT NULL,
  `customer_id` int(11) NOT NULL,
  `pay_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `catagory`
--

CREATE TABLE `catagory` (
  `cat_id` int(11) NOT NULL,
  `cat_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `cus_id` int(11) NOT NULL,
  `cus_name` varchar(30) NOT NULL,
  `cus_address` varchar(30) NOT NULL,
  `cus_number` varchar(14) DEFAULT NULL,
  `cus_number2` varchar(14) DEFAULT NULL,
  `cus_rag_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `factor`
--

CREATE TABLE `factor` (
  `fac_id` int(11) NOT NULL,
  `fk_cus_id` int(11) NOT NULL,
  `fk_pro_id` int(11) NOT NULL,
  `fac_total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `pro_id` int(11) NOT NULL,
  `pro_name` varchar(100) NOT NULL,
  `fk_catagory_id` int(11) NOT NULL,
  `pro_total` double NOT NULL,
  `fk_unit_id` int(11) NOT NULL,
  `pro_price` double NOT NULL,
  `pro_import_date` date NOT NULL,
  `pro_expire_date` date DEFAULT NULL,
  `fk_seller_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `seller`
--

CREATE TABLE `seller` (
  `sel_id` int(11) NOT NULL,
  `sel_name` varchar(30) NOT NULL,
  `sel_address` varchar(30) NOT NULL,
  `sel_number` varchar(14) DEFAULT NULL,
  `sel_number2` varchar(14) DEFAULT NULL,
  `sel_rag_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sellerreminder`
--

CREATE TABLE `sellerreminder` (
  `rem_id` int(11) NOT NULL,
  `reminder` double NOT NULL,
  `fk_seller_id` int(11) NOT NULL,
  `rem_pay_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `units`
--

CREATE TABLE `units` (
  `uni_id` int(11) NOT NULL,
  `uni_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `use_id` int(11) NOT NULL,
  `use_name` varchar(30) NOT NULL,
  `use_password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8_persian_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bills`
--
ALTER TABLE `bills`
  ADD PRIMARY KEY (`bil_id`),
  ADD KEY `factor_id` (`factor_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `catagory`
--
ALTER TABLE `catagory`
  ADD PRIMARY KEY (`cat_id`),
  ADD UNIQUE KEY `cat_name` (`cat_name`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`cus_id`),
  ADD UNIQUE KEY `cus_name` (`cus_name`);

--
-- Indexes for table `factor`
--
ALTER TABLE `factor`
  ADD PRIMARY KEY (`fac_id`),
  ADD KEY `fk_cus_id` (`fk_cus_id`),
  ADD KEY `fk_pro_id` (`fk_pro_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`pro_id`),
  ADD UNIQUE KEY `pro_name` (`pro_name`),
  ADD KEY `fk_catagory_id` (`fk_catagory_id`),
  ADD KEY `fk_unit_id` (`fk_unit_id`),
  ADD KEY `fk_seller_id` (`fk_seller_id`);

--
-- Indexes for table `seller`
--
ALTER TABLE `seller`
  ADD PRIMARY KEY (`sel_id`),
  ADD UNIQUE KEY `sel_name` (`sel_name`);

--
-- Indexes for table `sellerreminder`
--
ALTER TABLE `sellerreminder`
  ADD PRIMARY KEY (`rem_id`),
  ADD KEY `fk_seller_id` (`fk_seller_id`);

--
-- Indexes for table `units`
--
ALTER TABLE `units`
  ADD PRIMARY KEY (`uni_id`),
  ADD UNIQUE KEY `uni_name` (`uni_name`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`use_id`),
  ADD UNIQUE KEY `use_name` (`use_name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bills`
--
ALTER TABLE `bills`
  MODIFY `bil_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `catagory`
--
ALTER TABLE `catagory`
  MODIFY `cat_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `cus_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `factor`
--
ALTER TABLE `factor`
  MODIFY `fac_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `pro_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `seller`
--
ALTER TABLE `seller`
  MODIFY `sel_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sellerreminder`
--
ALTER TABLE `sellerreminder`
  MODIFY `rem_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `units`
--
ALTER TABLE `units`
  MODIFY `uni_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `use_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bills`
--
ALTER TABLE `bills`
  ADD CONSTRAINT `bills_ibfk_1` FOREIGN KEY (`factor_id`) REFERENCES `factor` (`fac_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bills_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`use_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bills_ibfk_3` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`cus_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `factor`
--
ALTER TABLE `factor`
  ADD CONSTRAINT `factor_ibfk_1` FOREIGN KEY (`fk_cus_id`) REFERENCES `customer` (`cus_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `factor_ibfk_2` FOREIGN KEY (`fk_pro_id`) REFERENCES `product` (`pro_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`fk_catagory_id`) REFERENCES `catagory` (`cat_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `product_ibfk_2` FOREIGN KEY (`fk_unit_id`) REFERENCES `units` (`uni_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `product_ibfk_3` FOREIGN KEY (`fk_seller_id`) REFERENCES `seller` (`sel_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sellerreminder`
--
ALTER TABLE `sellerreminder`
  ADD CONSTRAINT `sellerreminder_ibfk_1` FOREIGN KEY (`fk_seller_id`) REFERENCES `seller` (`sel_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
