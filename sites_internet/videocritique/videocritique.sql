-- phpMyAdmin SQL Dump
-- version 5.1.2
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le : mer. 15 mai 2024 à 06:02
-- Version du serveur : 5.7.24
-- Version de PHP : 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `videocritique`
--

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

CREATE TABLE `article` (
  `titreArticle` varchar(255) NOT NULL,
  `contenu` text NOT NULL,
  `noteArticle` int(2) NOT NULL,
  `dateArticle` date NOT NULL,
  `dateModif` datetime NOT NULL,
  `nomjeu` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `article`
--

INSERT INTO `article` (`titreArticle`, `contenu`, `noteArticle`, `dateArticle`, `dateModif`, `nomjeu`, `login`) VALUES
('A en couper le souffle', 'Que dire sur ce jeux, c\'est toute ma vie !\nJe joue à ARK depuis sa sortie anticipée et malgré de nombreux bugs je n\'en ai jamais été dessus. Un jeux aussi beau graphiquement que complet dans son gameplay. Le but est simple : survivre, et pourtant, ce n\'est pas si simple. entre les dinosaures et les différentes créatures vous n\'avez pas fini de courir et c\'est ça qui me plait. Devoir être toujours sur ses gardes au risque d\'y laisser sa peau. Ce que j\'aime aussi c\'est le fait de pouvoir apprivoiser ses fameux dinosaures qui veulent notre peau ou encore construire des forteresses imprenables capables de résister à des dragons et autre monstres.\nMerci pour votre lecture, n\'hésitez pas à laisser un avis à coté !', 9, '2024-05-07', '2024-05-07 19:45:50', 'ARK : Survival Ascended', 'IonixTV'),
('Excellent', 'Salut tout le monde, c\'est Siphano ! Aujourd\'hui, je vais vous parler de Minecraft, un jeu qui a marqué des générations entières et qui continue de fasciner les joueurs du monde entier.\n\nMinecraft, c\'est bien plus qu\'un simple jeu, c\'est un véritable univers infini où votre seule limite est votre imagination. Que vous aimiez construire de majestueuses structures, explorer des mondes générés aléatoirement, affronter des monstres redoutables ou simplement vivre des aventures avec vos amis, Minecraft offre une expérience unique à chacun.\n\nCe qui rend Minecraft si spécial, c\'est sa liberté totale. Vous pouvez littéralement faire ce que vous voulez, quand vous le voulez. Vous pouvez construire un château, creuser des mines à la recherche de ressources précieuses, élever du bétail, cultiver des champs, ou même créer des mécanismes redstone complexes. C\'est cette liberté qui permet à chacun de trouver son propre style de jeu et de vivre des expériences uniques.\n\nMais au-delà de son aspect créatif, Minecraft offre également une dimension sociale incroyable. Que ce soit en jouant en multijoueur sur des serveurs publics ou en hébergeant votre propre serveur privé pour jouer avec vos amis, Minecraft favorise les rencontres, les échanges et la coopération entre joueurs du monde entier.\n\nEnfin, Minecraft bénéficie d\'une communauté extrêmement active et créative. Des millions de joueurs partagent régulièrement leurs créations, que ce soit des maps, des mods, des skins ou des vidéos sur YouTube, ce qui garantit une expérience toujours renouvelée et enrichissante.\n\nEn résumé, Minecraft est bien plus qu\'un simple jeu, c\'est un véritable phénomène culturel qui continue de captiver les joueurs de tous âges et de toutes origines. Si vous ne l\'avez pas encore essayé, je vous encourage vivement à plonger dans cet univers fascinant et à laisser libre cours à votre imagination !', 10, '2024-05-07', '2024-05-07 08:20:07', 'Minecraft', 'Siphano'),
('Génial', 'Salut, moi c\'est LaChasseuse une joueuse passionnée de Monster hunter, c\'est pourquoi je commente ce magnifique jeux qu\'est Dauntless. Malgré sa communauté faiblissante et le manque d\'activité des développer le jeux reste une masterclass. Entre ses graphismes très agréables, sa fluidité de jeux et son accessibilité à tous, Dauntless est sans nul doute mon jeux favori. \n\nIl n\'est peut être pas le meilleur jeux en therme d\'intrigue mais le fait qu\'il soit gratuit pour tous en accentue la valeur.\n\nMon arme classement des armes dans dauntless est : la lance, les chaines lames, la hache, l\'épée, le marteau et enfin les répéteurs. donnez moi votre classement en avis !\n\nPourquoi la lance ?\nc\'est simple, c\'est l\'arme avec les meilleurs DPS du jeux ! Sa capacité à interrompre les béhémothes d\'un simple clic au bon moment est aussi une super capacité qui est très utile en combat. Qu\'elle plaisir de martyriser un jeune embermane heroïque en interrompant sa moindre charge! \n\nBref tout ça pour vous conseiller ce super jeux gratuit qu\'est Dauntless, n\'hésitez pas à me dire en avis ce que vous pensez du jeux en avis juste en dessous ! Bisous !', 8, '2024-05-07', '2024-05-07 08:52:30', 'Dauntless', 'LaChasseuse');

-- --------------------------------------------------------

--
-- Structure de la table `avis`
--

CREATE TABLE `avis` (
  `titreAvis` varchar(255) NOT NULL,
  `texte` text NOT NULL,
  `noteAvis` int(2) NOT NULL,
  `dateAvis` date NOT NULL,
  `idAvis` int(255) NOT NULL,
  `titreArticle` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `avis`
--

INSERT INTO `avis` (`titreAvis`, `texte`, `noteAvis`, `dateAvis`, `idAvis`, `titreArticle`, `login`) VALUES
('Super intéressant', 'Un jeux génial sur lequel j\'ai passé des heures ! Je recommande à fond ! c\'est aussi sans doute pourquoi j\'ai adoré votre article. Merci pour votre travail !\r\npremierMembre.', 10, '2024-05-29', 1, 'Génial', 'premierMembre');

-- --------------------------------------------------------

--
-- Structure de la table `categories`
--

CREATE TABLE `categories` (
  `idCat` int(255) NOT NULL,
  `libelleCat` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `categories`
--

INSERT INTO `categories` (`idCat`, `libelleCat`) VALUES
(1, 'Aventure'),
(2, 'RPG'),
(3, 'FPS'),
(4, 'MOBA'),
(5, 'course'),
(6, 'football'),
(7, 'survie');

-- --------------------------------------------------------

--
-- Structure de la table `estcat`
--

CREATE TABLE `estcat` (
  `idCat` int(255) NOT NULL,
  `nomjeu` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `estcat`
--

INSERT INTO `estcat` (`idCat`, `nomjeu`) VALUES
(1, 'ARK : survival ascended'),
(1, 'Minecraft'),
(2, 'Dauntless'),
(3, 'Valorant'),
(4, 'Brawl Star'),
(4, 'League Of Legend'),
(5, 'Rocket League'),
(6, 'Rocket League'),
(7, 'ARK : survival ascended'),
(7, 'Fortnite'),
(7, 'Minecraft');

-- --------------------------------------------------------

--
-- Structure de la table `estsupport`
--

CREATE TABLE `estsupport` (
  `idSupports` int(255) NOT NULL,
  `nomjeu` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `estsupport`
--

INSERT INTO `estsupport` (`idSupports`, `nomjeu`) VALUES
(1, 'ARK : survival ascended'),
(1, 'Dauntless'),
(1, 'Fortnite'),
(1, 'Minecraft'),
(1, 'Rocket League'),
(2, 'ARK : survival ascended'),
(2, 'Dauntless'),
(2, 'Fortnite'),
(2, 'Minecraft'),
(2, 'Rocket League'),
(3, 'ARK : survival ascended'),
(3, 'Dauntless'),
(3, 'Fortnite'),
(3, 'League Of Legend'),
(3, 'Minecraft'),
(3, 'Rocket League'),
(3, 'Valorant'),
(4, 'ARK : survival ascended'),
(4, 'Dauntless'),
(4, 'Fortnite'),
(4, 'Minecraft'),
(4, 'Rocket League'),
(5, 'ARK : survival ascended'),
(5, 'Brawl Star'),
(5, 'Fortnite'),
(5, 'League Of Legend'),
(5, 'Mincraft');

-- --------------------------------------------------------

--
-- Structure de la table `images`
--

CREATE TABLE `images` (
  `idImage` int(255) NOT NULL,
  `chemin` varchar(255) NOT NULL,
  `nomjeu` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `images`
--

INSERT INTO `images` (`idImage`, `chemin`, `nomjeu`) VALUES
(1, 'images/dauntless/1.png', 'Dauntless'),
(2, 'images/dauntless/2.png', 'Dauntless'),
(3, 'images/dauntless/3.png', 'Dauntless'),
(4, 'images/ark/1.png', 'ARK : survival ascended'),
(5, 'images/ark/2.png', 'ARK : survival ascended'),
(6, 'images/ark/3.png', 'ARK : survival ascended'),
(7, 'images/brawl_star/1.png', 'Brawl Star'),
(8, 'images/brawl_star/2.png', 'Brawl Star'),
(9, 'images/brawl_star/3.png', 'Brawl Star'),
(10, 'images/fortnite/1.png', 'Fortnite'),
(11, 'images/fortnite/2.png', 'Fortnite'),
(12, 'images/fortnite/3.png', 'Fortnite'),
(13, 'images/lol/1.png', 'League Of Legend'),
(14, 'images/lol/2.png', 'League Of Legend'),
(15, 'images/lol/3.png', 'League Of Legend'),
(16, 'images/minecraft/1.png', 'Minecraft'),
(17, 'images/minecraft/2.png', 'Minecraft'),
(18, 'images/minecraft/3.png', 'Minecraft'),
(19, 'images/rl/1.png', 'Rocket League'),
(20, 'images/rl/2.png', 'Rocket League'),
(21, 'images/rl/3.png', 'Rocket League'),
(22, 'images/valorant/1.png', 'Valorant'),
(23, 'images/valorant/2.png', 'Valorant'),
(24, 'images/valorant/3.png', 'Valorant');

-- --------------------------------------------------------

--
-- Structure de la table `jeu`
--

CREATE TABLE `jeu` (
  `prix` int(6) NOT NULL,
  `nomjeu` varchar(255) NOT NULL,
  `datejeu` date NOT NULL,
  `synopsis` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `jeu`
--

INSERT INTO `jeu` (`prix`, `nomjeu`, `datejeu`, `synopsis`) VALUES
(45, 'ARK : survival ascended', '2017-08-29', 'Comme un homme ou une femme échoué, nu, glacial et affamé sur les rives impitoyables d\'une île mystérieuse appelée ARCHE, utilisez votre habileté et votre ruse pour tuer ou apprivoiser et chevaucher la pléthore de dinosaures léviathan et autres primitifs créatures errant la terre. Chasse, récolte des ressources, artisanat articles, cultiver des cultures, technologies de recherche, et construire des abris résister à la éléments et stocker des objets de valeur, tout en faire équipe avec (ou s\'attaquant à) des centaines d\'autres joueurs pour survivre, dominer.. et s\'échapper!'),
(0, 'brawl star', '2018-12-12', 'Brawl Stars est un jeu mobile de combat multijoueur créé par Supercell, le même développeur derrière Clash of Clans et Clash Royale. Le jeu propose des combats rapides et dynamiques en équipe, où vous pouvez choisir parmi des dizaines de personnages uniques, appelés Brawlers, chacun avec ses compétences et pouvoirs spécifiques.'),
(0, 'Dauntless', '2019-05-21', 'Dauntless est un RPG d\'action massivement multijoueur où vous faites équipe avec d\'autres Slayers pour chasser de puissantes créatures appelées Behemoths. Explorez les régions les plus éloignées des Îles ravagées pendant que vous réalisez des quêtes, fabriquez des équipements ou terrassez des proies de plus en plus dangereuses, et tout cela en forgeant votre légende de Slayer de Ramsgate.'),
(0, 'Fortnite', '2017-07-25', 'Fortnite est un Tower-Defense orienté sandbox. Les joueurs se réunissent en équipe et doivent crafter armes et pièges pour ensuite construire une forteresse et la défendre contre les nombreux monstres qui viendront l’assaillir.\r\nUne version \"battle royal\" à vue le jour 26 septembre 2017. Elle représente le succès majoritaire de ce jeux crée par epic games.'),
(0, 'League Of Legends', '2009-10-27', '\"League of Legends\" (LoL) est un jeu en ligne multijoueur développé par Riot Games. Les joueurs incarnent des champions dotés de pouvoirs uniques et s\'affrontent dans des batailles stratégiques. Ils choisissent leur champion parmi une grande variété de personnages et tentent de détruire la base ennemie tout en protégeant la leur. Le jeu propose différents modes de jeu, des modes compétitifs aux modes plus décontractés. Avec une scène esportive dynamique et une communauté mondiale passionnée, \"League of Legends\" reste l\'un des jeux les plus populaires au monde.\r\n\r\n\r\n\r\n\r\n\r\n\r\n'),
(20, 'Minecraft', '2011-11-18', '\"Minecraft\" est un jeu de type bac à sable en monde ouvert où les joueurs peuvent explorer, construire et survivre dans un monde pixelisé et composé de blocs. Le jeu offre différents modes, notamment le Mode Survie, où les joueurs collectent des ressources, fabriquent des outils et des armes, et repoussent les ennemis tout en gérant leur faim et leur santé. Le Mode Créatif permet aux joueurs d\'avoir des ressources illimitées pour construire des structures élaborées et des créations sans la menace des ennemis.\r\nLes joueurs peuvent explorer divers environnements tels que des forêts, des montagnes, des océans et des grottes, chacun rempli de ressources et de créatures uniques. Le système de construction basé sur des blocs emblématique du jeu permet aux joueurs de construire tout ce qu\'ils peuvent imaginer, des maisons simples aux villes complexes, voire même des machines fonctionnelles grâce à la redstone.\r\n\"Minecraft\" propose une forte composante multijoueur, permettant aux joueurs de rejoindre des serveurs pour collaborer sur des projets, participer à des mini-jeux ou s\'affronter en joueur contre joueur. Le jeu dispose également d\'une communauté de modding active, avec d\'innombrables modifications créées par les utilisateurs qui ajoutent de nouveaux contenus, fonctionnalités et mécaniques de jeu.\r\nLes mécaniques simples mais polyvalentes du jeu, associées à ses possibilités infinies de créativité et d\'exploration, en ont fait l\'un des jeux vidéo les plus vendus de tous les temps, captivant des joueurs de tous âges à travers le monde.'),
(0, 'Rocket league', '2015-07-07', '\r\n\"Rocket League\" est un jeu vidéo développé et publié par Psyonix.\r\nLe jeu combine des éléments de football et de course automobile, dans lequel les joueurs contrôlent des voitures propulsées par des fusées et s\'affrontent dans des matchs compétitifs. L\'objectif principal est de marquer des buts en frappant un ballon géant dans le but adverse, tout en utilisant diverses techniques et acrobaties pour surprendre les adversaires.\r\nLes joueurs peuvent personnaliser leurs voitures avec une grande variété de décalcomanies, chapeaux, antennes et autres accessoires, ce qui ajoute une touche de personnalisation et de style au jeu. \"Rocket League\" propose plusieurs modes de jeu, y compris le mode en ligne compétitif, le mode coopératif et des modes de jeu hors ligne.\r\nAvec ses graphismes vibrants, son gameplay addictif et sa communauté active, \"Rocket League\" est rapidement devenu un succès mondial, attirant des millions de joueurs et de spectateurs à travers le monde.'),
(0, 'Valorant', '2020-06-02', 'Deux équipes de cinq joueurs s\'affrontent et les agents utilisent des crédits pour acheter des compétences uniques, des armes et des boucliers. Une équipe est en attaque et une est en défense : l\'équipe attaquante dispose d\'une bombe qu\'elle doit poser sur un site. Si elle parvient à être posée et qu\'elle explose au bout d\'un chronomètre, ou qu\'ils tuent tous les défenseurs, les attaquants gagnent un point. En revanche, si l\'équipe en défense réussit à désamorcer la bombe, à tuer tous les attaquants avant qu\'ils ne posent la bombe ou à empêcher les attaquants de planter la bombe, ce sont eux qui gagnent un point6. Au bout de douze rounds, les deux équipes échangent les rôles. Les attaquants deviennent défenseurs et inversement. Tandis que les crédits accumulés au cours des 12 premiers rounds sont réinitialisés pour repartir de zéro. La première équipe qui obtient treize points gagne la partie.');

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `idrole` int(255) NOT NULL,
  `libelleRole` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`idrole`, `libelleRole`) VALUES
(1, 'Visiteur'),
(2, 'Membre'),
(3, 'Redacteur'),
(4, 'Administrateur');

-- --------------------------------------------------------

--
-- Structure de la table `supports`
--

CREATE TABLE `supports` (
  `idSupports` int(255) NOT NULL,
  `libelleSup` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `supports`
--

INSERT INTO `supports` (`idSupports`, `libelleSup`) VALUES
(1, 'PS4'),
(2, 'XBOX'),
(3, 'PC'),
(4, 'Switch'),
(5, 'Mobile');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `nomUser` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `naissance` date NOT NULL,
  `idrole` int(255) NOT NULL,
  `creation` date NOT NULL,
  `derniereCo` datetime NOT NULL,
  `pp` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `mdp` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`nomUser`, `prenom`, `naissance`, `idrole`, `creation`, `derniereCo`, `pp`, `login`, `mdp`) VALUES
('Bapt', 'Gat', '1950-05-01', 2, '2024-05-01', '2024-05-13 10:26:40', 'images/user/premierMembre', 'premierMembre', 'prems'),
('jsaipafrere', 'jean-michel', '1998-06-02', 3, '2021-11-17', '2024-05-07 09:12:53', 'images/user/ionixTV.png', 'ionixTV', 'viveArk'),
('Lara', 'Daunt', '2000-01-01', 3, '2024-05-07', '2024-05-07 08:45:20', 'images/user/LaChasseuse.png', 'LaChasseuse', 'JeChasse'),
('Morana', 'Julien', '1992-07-30', 3, '2024-05-07', '2024-05-07 08:16:47', 'images/user/Siphano.png', 'Siphano', 'mineetcraft');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`titreArticle`,`nomjeu`,`login`);

--
-- Index pour la table `avis`
--
ALTER TABLE `avis`
  ADD PRIMARY KEY (`idAvis`,`titreArticle`,`login`);

--
-- Index pour la table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`idCat`);

--
-- Index pour la table `estcat`
--
ALTER TABLE `estcat`
  ADD PRIMARY KEY (`idCat`,`nomjeu`);

--
-- Index pour la table `estsupport`
--
ALTER TABLE `estsupport`
  ADD PRIMARY KEY (`idSupports`,`nomjeu`);

--
-- Index pour la table `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`idImage`,`nomjeu`);

--
-- Index pour la table `jeu`
--
ALTER TABLE `jeu`
  ADD PRIMARY KEY (`nomjeu`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`idrole`);

--
-- Index pour la table `supports`
--
ALTER TABLE `supports`
  ADD PRIMARY KEY (`idSupports`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`idrole`,`login`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `avis`
--
ALTER TABLE `avis`
  MODIFY `idAvis` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `categories`
--
ALTER TABLE `categories`
  MODIFY `idCat` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `images`
--
ALTER TABLE `images`
  MODIFY `idImage` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `idrole` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `supports`
--
ALTER TABLE `supports`
  MODIFY `idSupports` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
