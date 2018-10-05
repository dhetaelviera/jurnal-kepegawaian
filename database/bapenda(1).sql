-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 05, 2018 at 05:49 AM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 5.6.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bapenda`
--

-- --------------------------------------------------------

--
-- Table structure for table `jabatan`
--

CREATE TABLE `jabatan` (
  `idJabatan` int(11) NOT NULL,
  `jabatan` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jabatan`
--

INSERT INTO `jabatan` (`idJabatan`, `jabatan`) VALUES
(1, 'pegawai');

-- --------------------------------------------------------

--
-- Table structure for table `jurnal`
--

CREATE TABLE `jurnal` (
  `idJurnal` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `pegawai` int(11) NOT NULL,
  `kegiatan` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jurnal`
--

INSERT INTO `jurnal` (`idJurnal`, `tanggal`, `pegawai`, `kegiatan`) VALUES
(1, '2018-10-03', 123, 'jfhwf'),
(2, '2018-10-03', 123, 'sudah bisa masukin jurnal baru.'),
(3, '2018-10-04', 123, 'sekarang nambahin apa ya enaknya'),
(4, '2018-10-05', 123, 'lalalala');

-- --------------------------------------------------------

--
-- Table structure for table `kecamatan`
--

CREATE TABLE `kecamatan` (
  `idKecamatan` int(11) NOT NULL,
  `kecamatan` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kecamatan`
--

INSERT INTO `kecamatan` (`idKecamatan`, `kecamatan`) VALUES
(1, 'jember');

-- --------------------------------------------------------

--
-- Table structure for table `kelurahan`
--

CREATE TABLE `kelurahan` (
  `idKelurahan` int(11) NOT NULL,
  `kelurahan` varchar(30) NOT NULL,
  `kecamatan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kelurahan`
--

INSERT INTO `kelurahan` (`idKelurahan`, `kelurahan`, `kecamatan`) VALUES
(1, 'jember', 1);

-- --------------------------------------------------------

--
-- Table structure for table `pegawai`
--

CREATE TABLE `pegawai` (
  `NIP` varchar(10) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `jabatan` int(11) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `kelurahan` int(11) NOT NULL,
  `kecamatan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pegawai`
--

INSERT INTO `pegawai` (`NIP`, `nama`, `jabatan`, `alamat`, `kelurahan`, `kecamatan`) VALUES
('123', 'dheta', 1, 'pondok gede', 1, 1),
('234', 'umi', 1, 'jalan jawa', 1, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `jabatan`
--
ALTER TABLE `jabatan`
  ADD PRIMARY KEY (`idJabatan`);

--
-- Indexes for table `jurnal`
--
ALTER TABLE `jurnal`
  ADD PRIMARY KEY (`idJurnal`);

--
-- Indexes for table `kecamatan`
--
ALTER TABLE `kecamatan`
  ADD PRIMARY KEY (`idKecamatan`);

--
-- Indexes for table `kelurahan`
--
ALTER TABLE `kelurahan`
  ADD PRIMARY KEY (`idKelurahan`);

--
-- Indexes for table `pegawai`
--
ALTER TABLE `pegawai`
  ADD PRIMARY KEY (`NIP`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `jabatan`
--
ALTER TABLE `jabatan`
  MODIFY `idJabatan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `jurnal`
--
ALTER TABLE `jurnal`
  MODIFY `idJurnal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `kecamatan`
--
ALTER TABLE `kecamatan`
  MODIFY `idKecamatan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `kelurahan`
--
ALTER TABLE `kelurahan`
  MODIFY `idKelurahan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
