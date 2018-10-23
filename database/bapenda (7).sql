-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 23, 2018 at 12:18 PM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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
-- Table structure for table `detail`
--

CREATE TABLE `detail` (
  `idDetail` int(11) NOT NULL,
  `idJurnal` int(11) NOT NULL,
  `obyek` int(11) NOT NULL,
  `kegiatan` varchar(255) NOT NULL,
  `keterangan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detail`
--

INSERT INTO `detail` (`idDetail`, `idJurnal`, `obyek`, `kegiatan`, `keterangan`) VALUES
(1, 2, 2, 'keijef', 3),
(2, 2, 3, 'djdcuhdufd', 2);

-- --------------------------------------------------------

--
-- Table structure for table `jabatan`
--

CREATE TABLE `jabatan` (
  `idJabatan` int(11) NOT NULL,
  `jabatan` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jabatan`
--

INSERT INTO `jabatan` (`idJabatan`, `jabatan`) VALUES
(1, 'Kabid Pendataan dan Penyuluhan'),
(2, 'Staf Pengelola Pendaftaran dan Pendataan Pajak/Ret');

-- --------------------------------------------------------

--
-- Table structure for table `jurnal`
--

CREATE TABLE `jurnal` (
  `idJurnal` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `pegawai` varchar(25) NOT NULL,
  `namaWajibPajak` varchar(50) NOT NULL,
  `alamat` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jurnal`
--

INSERT INTO `jurnal` (`idJurnal`, `tanggal`, `pegawai`, `namaWajibPajak`, `alamat`) VALUES
(1, '2018-10-23', '19611227 200701 1 001', 'lalalala', 'lilili'),
(2, '2018-10-23', '19611227 200701 1 001', 'kakaka', 'akakkaka');

-- --------------------------------------------------------

--
-- Table structure for table `keterangan`
--

CREATE TABLE `keterangan` (
  `idKeterangan` int(11) NOT NULL,
  `keterangan` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `keterangan`
--

INSERT INTO `keterangan` (`idKeterangan`, `keterangan`) VALUES
(1, 'selesai'),
(2, 'selesai di OC'),
(3, 'sudah bayar'),
(4, 'berkas belum lengkap');

-- --------------------------------------------------------

--
-- Table structure for table `obyekpajak`
--

CREATE TABLE `obyekpajak` (
  `idObyek` int(11) NOT NULL,
  `obyekPajak` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `obyekpajak`
--

INSERT INTO `obyekpajak` (`idObyek`, `obyekPajak`) VALUES
(1, 'Semua Obyek Pajak'),
(2, 'Restoran'),
(3, 'PBB'),
(4, 'Pajak Air Tanah'),
(5, 'Cafe'),
(6, 'Hotel'),
(7, 'Hiburan'),
(8, 'Parkir'),
(9, 'PPJ Non PLN');

-- --------------------------------------------------------

--
-- Table structure for table `pangkat`
--

CREATE TABLE `pangkat` (
  `pangkatGol` varchar(5) NOT NULL,
  `golRuang` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pangkat`
--

INSERT INTO `pangkat` (`pangkatGol`, `golRuang`) VALUES
('II/a', 'Pengatur Muda'),
('II/c', 'Pengatur'),
('II/d', 'Pengatur Tingkat I'),
('III/a', 'Penata Muda TK I'),
('III/c', 'Penata'),
('IV/a', 'Pembina');

-- --------------------------------------------------------

--
-- Table structure for table `pegawai`
--

CREATE TABLE `pegawai` (
  `NIP` varchar(21) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `jabatan` int(11) NOT NULL,
  `pangkat` varchar(11) NOT NULL,
  `level` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pegawai`
--

INSERT INTO `pegawai` (`NIP`, `nama`, `jabatan`, `pangkat`, `level`) VALUES
('19611227 200701 1 001', 'Ir. Bambang Subagio', 2, 'III/c', 1),
('19650802 199403 1 009', 'Drs. FX. Agus Sudarsono, MM.', 1, 'IV/a', 1),
('19710117 200701 1 012', 'Uud Junaidi', 2, 'II/c', 1),
('19751125 200801 1 011', 'Sulaeman', 2, 'II/a', 1),
('19760519 200801 1 011', 'Bambang Rusmiadi', 2, 'II/c', 1),
('19790616 201001 2 001', 'Elli Andriyani', 2, 'II/d', 1),
('19810514 201001 1 010', 'Dodon Dwi Sulis Bahalwan', 2, 'II/d', 1),
('19941102 201609 1 001', 'Mochamad Elfhad Labima', 2, 'III/a', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `detail`
--
ALTER TABLE `detail`
  ADD PRIMARY KEY (`idDetail`);

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
-- Indexes for table `keterangan`
--
ALTER TABLE `keterangan`
  ADD PRIMARY KEY (`idKeterangan`);

--
-- Indexes for table `obyekpajak`
--
ALTER TABLE `obyekpajak`
  ADD PRIMARY KEY (`idObyek`);

--
-- Indexes for table `pangkat`
--
ALTER TABLE `pangkat`
  ADD PRIMARY KEY (`pangkatGol`);

--
-- Indexes for table `pegawai`
--
ALTER TABLE `pegawai`
  ADD PRIMARY KEY (`NIP`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detail`
--
ALTER TABLE `detail`
  MODIFY `idDetail` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `jabatan`
--
ALTER TABLE `jabatan`
  MODIFY `idJabatan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `jurnal`
--
ALTER TABLE `jurnal`
  MODIFY `idJurnal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `keterangan`
--
ALTER TABLE `keterangan`
  MODIFY `idKeterangan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `obyekpajak`
--
ALTER TABLE `obyekpajak`
  MODIFY `idObyek` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
