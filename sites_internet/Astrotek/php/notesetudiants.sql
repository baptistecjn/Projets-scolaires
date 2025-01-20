-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le : lun. 23 jan. 2023 à 15:59
-- Version du serveur :  5.7.24
-- Version de PHP : 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `notesetudiants`
--

-- --------------------------------------------------------

--
-- Structure de la table `adresse`
--

CREATE TABLE `adresse` (
  `id_adresse` int(11) NOT NULL,
  `numero` int(11) DEFAULT NULL,
  `bisTer` varchar(10) DEFAULT NULL,
  `nomRue` varchar(150) DEFAULT NULL,
  `codePostal` varchar(20) DEFAULT NULL,
  `ville` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `adresse`
--

INSERT INTO `adresse` (`id_adresse`, `numero`, `bisTer`, `nomRue`, `codePostal`, `ville`) VALUES
(1, 42, NULL, 'Rue Rémi Fasol', '35400', 'St Malo'),
(2, 12, NULL, 'Avenue Otto Matique', '35000', 'Rennes'),
(3, 3, 'Bis', 'Allée Edgar Gouille', '35000', 'Rennes');

-- --------------------------------------------------------

--
-- Structure de la table `enseignant`
--

CREATE TABLE `enseignant` (
  `id_enseignant` int(11) NOT NULL,
  `prenomEnseignant` varchar(50) DEFAULT NULL,
  `nomEnseignant` varchar(50) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `ville` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `enseignant`
--

INSERT INTO `enseignant` (`id_enseignant`, `prenomEnseignant`, `nomEnseignant`, `age`, `ville`) VALUES
(1, 'Alain', 'Verse', 33, 'St Malo'),
(2, 'Phil', 'Moilsel', 33, 'Rennes'),
(3, 'Astrid', 'Icule', 27, 'Rennes'),
(4, 'Jacques', 'Célaire', 42, 'St Malo');

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE `etudiant` (
  `id_etudiant` int(11) NOT NULL,
  `prenomEtudiant` varchar(50) DEFAULT NULL,
  `nomEtudiant` varchar(50) DEFAULT NULL,
  `id_adresse` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`id_etudiant`, `prenomEtudiant`, `nomEtudiant`, `id_adresse`) VALUES
(1001, 'Emile', 'Sabord', 2),
(1002, 'Isabelle', 'Lesyeuble', 3),
(1003, 'Pat', 'Ibulaire', 1);

-- --------------------------------------------------------

--
-- Structure de la table `etudiantmatiere`
--

CREATE TABLE `etudiantmatiere` (
  `id_etudiant` int(11) NOT NULL,
  `codeMatiere` varchar(50) NOT NULL,
  `note` decimal(4,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `etudiantmatiere`
--

INSERT INTO `etudiantmatiere` (`id_etudiant`, `codeMatiere`, `note`) VALUES
(1001, 'Algo', '17.00'),
(1001, 'BDD', '5.00'),
(1002, 'Algo', '20.00'),
(1002, 'BDD', '12.00'),
(1003, 'Algo', '2.00'),
(1003, 'BDD', '15.00'),
(1003, 'IHM', '11.00');

-- --------------------------------------------------------

--
-- Structure de la table `matiere`
--

CREATE TABLE `matiere` (
  `codeMatiere` varchar(50) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `id_enseignant` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `matiere`
--

INSERT INTO `matiere` (`codeMatiere`, `libelle`, `id_enseignant`) VALUES
('Algo', 'Algorithme', 4),
('BDD', 'Base de données', 3),
('IHM', 'Interface', 1),
('PROJ', 'Projet Programmation', 2);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `adresse`
--
ALTER TABLE `adresse`
  ADD PRIMARY KEY (`id_adresse`);

--
-- Index pour la table `enseignant`
--
ALTER TABLE `enseignant`
  ADD PRIMARY KEY (`id_enseignant`);

--
-- Index pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`id_etudiant`),
  ADD KEY `id_adresse` (`id_adresse`);

--
-- Index pour la table `etudiantmatiere`
--
ALTER TABLE `etudiantmatiere`
  ADD PRIMARY KEY (`id_etudiant`,`codeMatiere`),
  ADD KEY `codeMatiere` (`codeMatiere`);

--
-- Index pour la table `matiere`
--
ALTER TABLE `matiere`
  ADD PRIMARY KEY (`codeMatiere`),
  ADD KEY `id_enseignant` (`id_enseignant`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD CONSTRAINT `etudiant_ibfk_1` FOREIGN KEY (`id_adresse`) REFERENCES `adresse` (`id_adresse`);

--
-- Contraintes pour la table `etudiantmatiere`
--
ALTER TABLE `etudiantmatiere`
  ADD CONSTRAINT `etudiantmatiere_ibfk_1` FOREIGN KEY (`id_etudiant`) REFERENCES `etudiant` (`id_etudiant`),
  ADD CONSTRAINT `etudiantmatiere_ibfk_2` FOREIGN KEY (`codeMatiere`) REFERENCES `matiere` (`codeMatiere`);

--
-- Contraintes pour la table `matiere`
--
ALTER TABLE `matiere`
  ADD CONSTRAINT `matiere_ibfk_1` FOREIGN KEY (`id_enseignant`) REFERENCES `enseignant` (`id_enseignant`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
