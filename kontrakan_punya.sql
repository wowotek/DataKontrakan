-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost:81
-- Generation Time: Apr 14, 2018 at 07:57 PM
-- Server version: 5.7.21-0ubuntu0.16.04.1
-- PHP Version: 7.0.28-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kontrakan_punya`
--

-- --------------------------------------------------------

--
-- Table structure for table `DaftarHarga`
--

CREATE TABLE `DaftarHarga` (
  `ID` int(100) NOT NULL,
  `Nama` varchar(50) NOT NULL,
  `Harga` int(8) NOT NULL,
  `Prioritas` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Pemasukan`
--

CREATE TABLE `Pemasukan` (
  `ID_Pengeluaran` int(100) NOT NULL,
  `Transaksi` varchar(35) NOT NULL,
  `Sumber` varchar(35) NOT NULL,
  `Jumlah` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Pemasukan`
--

INSERT INTO `Pemasukan` (`ID_Pengeluaran`, `Transaksi`, `Sumber`, `Jumlah`) VALUES
(1, 'Bayar Uang Galon', 'Anggi', 15000);

-- --------------------------------------------------------

--
-- Table structure for table `Pengeluaran`
--

CREATE TABLE `Pengeluaran` (
  `ID_Pemasukan` int(100) NOT NULL,
  `Transaksi` varchar(50) NOT NULL,
  `Jumlah` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `DaftarHarga`
--
ALTER TABLE `DaftarHarga`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `Pemasukan`
--
ALTER TABLE `Pemasukan`
  ADD PRIMARY KEY (`ID_Pengeluaran`);

--
-- Indexes for table `Pengeluaran`
--
ALTER TABLE `Pengeluaran`
  ADD PRIMARY KEY (`ID_Pemasukan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `DaftarHarga`
--
ALTER TABLE `DaftarHarga`
  MODIFY `ID` int(100) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Pemasukan`
--
ALTER TABLE `Pemasukan`
  MODIFY `ID_Pengeluaran` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `Pengeluaran`
--
ALTER TABLE `Pengeluaran`
  MODIFY `ID_Pemasukan` int(100) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
