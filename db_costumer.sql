-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 28 Mar 2021 pada 12.11
-- Versi server: 10.4.14-MariaDB
-- Versi PHP: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_costumer`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_costumer`
--

CREATE TABLE `tbl_costumer` (
  `id_cos` int(10) NOT NULL,
  `name_cos` varchar(100) NOT NULL,
  `alamat_cos` varchar(100) NOT NULL,
  `birthdate_cos` date NOT NULL,
  `nohp_cos` varchar(20) NOT NULL,
  `kelamin_cos` varchar(10) NOT NULL,
  `cekin_cos` date NOT NULL,
  `cekout_cos` date NOT NULL,
  `type_cos` varchar(20) NOT NULL,
  `kamar_cos` int(100) NOT NULL,
  `price_cos` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tbl_costumer`
--

INSERT INTO `tbl_costumer` (`id_cos`, `name_cos`, `alamat_cos`, `birthdate_cos`, `nohp_cos`, `kelamin_cos`, `cekin_cos`, `cekout_cos`, `type_cos`, `kamar_cos`, `price_cos`) VALUES
(1, 'Erni', 'Bandung', '2021-03-12', '03837382038', 'Perempuan', '2021-03-11', '2021-03-12', 'Single', 5, '200000'),
(2, 'Erni', 'Medan', '1970-01-01', '0837382728', 'Perempuan', '1970-01-01', '1970-01-01', 'Family', 7, '300000'),
(3, 'erni', 'Salatiga', '2021-03-12', '08292728', 'Perempuan', '2021-03-11', '2021-03-12', 'Single', 5, '230000'),
(7, 'getse', 'kemiri', '2021-03-04', '0973849', 'Perempuan', '2021-03-03', '2021-03-04', 'Single', 2, '638900'),
(33, 'getsemani', 'werwerw', '2021-01-18', '324324', 'Perempuan', '2021-09-21', '2021-09-28', 'Single', 5, '2300000');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_user`
--

CREATE TABLE `tbl_user` (
  `id_user` int(10) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  `level` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tbl_user`
--

INSERT INTO `tbl_user` (`id_user`, `nama`, `password`, `level`) VALUES
(1, 'erni', '1234', 'client'),
(2, 'erni', '1234', 'client');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tbl_costumer`
--
ALTER TABLE `tbl_costumer`
  ADD PRIMARY KEY (`id_cos`);

--
-- Indeks untuk tabel `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
