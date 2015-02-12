-- phpMyAdmin SQL Dump
-- version 4.1.4
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 12 Février 2015 à 12:02
-- Version du serveur :  5.6.15-log
-- Version de PHP :  5.5.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `unikorntubesql`
--
CREATE DATABASE IF NOT EXISTS `unikorntubesql` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `unikorntubesql`;

-- --------------------------------------------------------

--
-- Structure de la table `comments`
--

CREATE TABLE IF NOT EXISTS `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment` text NOT NULL,
  `id_video` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_response` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=41 ;

--
-- Contenu de la table `comments`
--

INSERT INTO `comments` (`id`, `comment`, `id_video`, `id_user`, `id_response`) VALUES
(1, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer', 7, 19, NULL),
(2, 'Lorem ipsum dolor sit amet,', 3, 4, NULL),
(3, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', 8, 11, NULL),
(4, 'Lorem', 8, 15, NULL),
(5, 'Lorem ipsum dolor sit amet, consectetuer adipiscing', 3, 3, NULL),
(6, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu', 3, 12, NULL),
(7, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing', 8, 16, NULL),
(8, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu', 6, 4, NULL),
(9, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut', 9, 10, NULL),
(10, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et', 2, 3, NULL),
(11, 'Lorem ipsum dolor sit', 6, 18, NULL),
(12, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing', 6, 1, NULL),
(13, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur', 7, 8, NULL),
(14, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et', 9, 18, NULL),
(15, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', 1, 16, NULL),
(16, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam', 5, 6, NULL),
(17, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus.', 2, 12, NULL),
(18, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna', 1, 19, NULL),
(19, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing', 1, 7, NULL),
(20, 'Lorem', 2, 13, NULL),
(21, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer', 5, 9, 5),
(22, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna', 1, 5, 6),
(23, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et', 4, 16, 7),
(24, 'Lorem ipsum', 7, 15, 18),
(25, 'Lorem ipsum dolor sit amet, consectetuer', 4, 2, 2),
(26, 'Lorem ipsum', 3, 12, 1),
(27, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing', 6, 9, 2),
(28, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut', 3, 19, 8),
(29, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', 6, 7, 1),
(30, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus.', 8, 1, 7),
(31, 'Lorem ipsum dolor sit amet,', 5, 18, 4),
(32, 'Lorem', 7, 14, 19),
(33, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam', 5, 10, 6),
(34, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor.', 1, 5, 12),
(35, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et', 10, 13, 2),
(36, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor.', 1, 17, 16),
(37, 'Lorem ipsum dolor sit', 10, 18, 14),
(38, 'Lorem', 7, 4, 8),
(39, 'Lorem', 8, 10, 16),
(40, 'Lorem ipsum', 9, 9, 5);

-- --------------------------------------------------------

--
-- Structure de la table `likes`
--

CREATE TABLE IF NOT EXISTS `likes` (
  `viewedtime` datetime NOT NULL,
  `value` smallint(6) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_video` int(11) NOT NULL,
  PRIMARY KEY (`id_user`,`id_video`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `likes`
--

INSERT INTO `likes` (`viewedtime`, `value`, `id_user`, `id_video`) VALUES
('2015-08-13 04:20:41', 0, 2, 10),
('2014-12-26 10:37:51', 2, 2, 11),
('2015-11-21 08:05:10', 1, 2, 17),
('2015-11-12 17:56:49', 2, 3, 6),
('2014-02-14 07:44:11', 2, 3, 7),
('2015-10-13 15:17:22', 0, 3, 8),
('2014-11-23 06:32:05', 2, 3, 20),
('2014-12-08 21:04:38', 2, 4, 20),
('2014-08-27 15:54:20', 0, 6, 7),
('2015-08-30 01:00:59', 2, 6, 9),
('2015-11-18 10:19:59', 0, 6, 10),
('2015-05-08 17:13:54', 2, 6, 15),
('2015-08-16 20:36:23', 1, 7, 4),
('2015-04-20 19:17:55', 0, 7, 6),
('2015-01-22 02:08:53', 0, 7, 16),
('2014-06-17 19:19:33', 2, 7, 19),
('2015-07-11 19:59:56', 1, 8, 9),
('2015-02-12 12:42:44', 0, 9, 15),
('2015-04-20 03:16:14', 1, 10, 5),
('2014-11-12 11:43:28', 1, 10, 10);

-- --------------------------------------------------------

--
-- Structure de la table `playlists`
--

CREATE TABLE IF NOT EXISTS `playlists` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`,`id_user`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Contenu de la table `playlists`
--

INSERT INTO `playlists` (`id`, `id_user`, `title`) VALUES
(1, 5, 'dolor.'),
(2, 5, 'sapien, gravida non,'),
(3, 12, 'nisi. Cum sociis natoque'),
(4, 5, 'ante blandit viverra.'),
(5, 10, 'Sed dictum. Proin'),
(6, 17, 'scelerisque'),
(7, 17, 'semper auctor. Mauris vel'),
(8, 8, 'Mauris eu'),
(9, 3, 'et'),
(10, 3, 'imperdiet ullamcorper. Duis'),
(11, 14, 'tortor nibh sit amet'),
(12, 13, 'quam. Pellentesque habitant morbi'),
(13, 4, 'ipsum'),
(14, 13, 'semper egestas, urna justo'),
(15, 3, 'bibendum. Donec felis orci, adipiscing'),
(16, 20, 'tellus id nunc'),
(17, 7, 'justo eu arcu. Morbi'),
(18, 3, 'tellus,'),
(19, 4, 'in felis. Nulla tempor augue'),
(20, 13, 'Donec felis orci, adipiscing');

-- --------------------------------------------------------

--
-- Structure de la table `playlist_video`
--

CREATE TABLE IF NOT EXISTS `playlist_video` (
  `id_playlist` int(11) NOT NULL,
  `id_video` int(11) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `playlist_video`
--

INSERT INTO `playlist_video` (`id_playlist`, `id_video`, `id_user`) VALUES
(15, 6, 6),
(17, 20, 2),
(11, 1, 19),
(4, 15, 17),
(4, 18, 16),
(11, 2, 10),
(19, 8, 8),
(16, 19, 15),
(3, 9, 6),
(13, 9, 6),
(17, 20, 20),
(2, 19, 14),
(19, 20, 17),
(15, 2, 10),
(5, 4, 13),
(6, 2, 1),
(16, 12, 4),
(12, 18, 1),
(15, 17, 6),
(11, 14, 3);

-- --------------------------------------------------------

--
-- Structure de la table `tags`
--

CREATE TABLE IF NOT EXISTS `tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Contenu de la table `tags`
--

INSERT INTO `tags` (`id`, `tag`) VALUES
(1, 'nulla'),
(2, 'urna'),
(3, 'odio'),
(4, 'In'),
(5, 'euismod'),
(6, 'pede.'),
(7, 'ac,'),
(8, 'Phasellus'),
(9, 'nibh'),
(10, 'erat'),
(11, 'auctor.'),
(12, 'ac'),
(13, 'Sed'),
(14, 'fringilla.'),
(15, 'augue'),
(16, 'Morbi'),
(17, 'ante'),
(18, 'aliquam,'),
(19, 'fringilla'),
(20, 'In');

-- --------------------------------------------------------

--
-- Structure de la table `tag_video`
--

CREATE TABLE IF NOT EXISTS `tag_video` (
  `id_video` int(11) NOT NULL,
  `id_tag` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `tag_video`
--

INSERT INTO `tag_video` (`id_video`, `id_tag`) VALUES
(12, 5),
(7, 8),
(3, 9),
(5, 14),
(6, 5),
(4, 13),
(7, 9),
(10, 9),
(14, 17),
(12, 4),
(7, 9),
(2, 2),
(10, 7),
(8, 4),
(18, 12),
(11, 8),
(15, 11),
(9, 20),
(11, 18),
(14, 1);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `channel_name` varchar(255) NOT NULL,
  `connected` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `email`, `channel_name`, `connected`) VALUES
(1, 'Léo', 'varius', 'leo.donny2@gmail.com', '', 0),
(2, 'Guillaume', 'mollis.', 'guillaume.lecocq@gmx.fr', '', 0),
(3, 'Idriss', 'libero', 'aarsenyx@gmail.com', '', 0),
(4, 'Sandra', 'adipiscing', 'sandra.ladu@gmail.com', '', 0),
(5, 'Damien', 'egestas.', 'damien.serin@gmail.com', '', 0),
(6, 'Mickael', 'sem', 'mickael.goualard@gmail.com', '', 0),
(7, 'Chandler', 'placerat,', 'molestie.sodales@acfeugiat.ca', '', 0),
(8, 'Brynne', 'ut', 'Nunc.mauris.Morbi@lacusQuisquepurus.ca', '', 0),
(9, 'Ignacia', 'Integer', 'varius@nequesedsem.org', '', 0),
(10, 'Kermit', 'libero', 'purus.accumsan@nequeet.net', '', 0),
(11, 'Ariel', 'ligula.', 'facilisis.Suspendisse.commodo@commodoauctor.net', '', 0),
(12, 'Gareth', 'luctus', 'a.tortor.Nunc@dui.co.uk', '', 0),
(13, 'Demetria', 'a', 'sodales@rutrum.com', '', 0),
(14, 'Hayden', 'tempus', 'Phasellus@ipsumdolorsit.edu', '', 0),
(15, 'Aiko', 'diam', 'ligula.Nullam@dapibusgravida.edu', '', 0),
(16, 'Bevis', 'magna.', 'pretium@acfacilisis.co.uk', '', 0),
(17, 'Jane', 'eros.', 'lorem@vitae.ca', '', 0),
(18, 'Quentin', 'Nunc', 'penatibus@eu.edu', '', 0),
(19, 'Nayda', 'ultricies', 'ullamcorper.Duis@lectusquismassa.edu', '', 0),
(20, 'Lawrence', 'cubilia', 'eget.odio@ac.org', '', 0);

-- --------------------------------------------------------

--
-- Structure de la table `videos`
--

CREATE TABLE IF NOT EXISTS `videos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `link` varchar(255) NOT NULL,
  `nb_like` int(11) NOT NULL,
  `nb_dislike` int(11) NOT NULL,
  `nb_view` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Contenu de la table `videos`
--

INSERT INTO `videos` (`id`, `title`, `link`, `nb_like`, `nb_dislike`, `nb_view`, `id_user`) VALUES
(1, 'purus sapien, gravida non,', '', 0, 0, 0, 7),
(2, 'augue,', '', 0, 0, 0, 7),
(3, 'ante blandit viverra. Donec tempus,', '', 0, 0, 0, 16),
(4, 'turpis', '', 0, 0, 0, 7),
(5, 'Fusce aliquam, enim', '', 0, 0, 0, 7),
(6, 'magna. Suspendisse', '', 0, 0, 0, 18),
(7, 'Class', '', 0, 0, 0, 15),
(8, 'mauris.', '', 0, 0, 0, 20),
(9, 'ullamcorper eu, euismod ac,', '', 0, 0, 0, 12),
(10, 'dolor elit, pellentesque a,', '', 0, 0, 0, 13),
(11, 'ipsum', '', 0, 0, 0, 3),
(12, 'molestie tellus. Aenean', '', 0, 0, 0, 8),
(13, 'ornare, elit', '', 0, 0, 0, 14),
(14, 'vel', '', 0, 0, 0, 4),
(15, 'per conubia nostra, per', '', 0, 0, 0, 20),
(16, 'vel, convallis in,', '', 0, 0, 0, 17),
(17, 'Fusce', '', 0, 0, 0, 2),
(18, 'Donec egestas.', '', 0, 0, 0, 3),
(19, 'Morbi non', '', 0, 0, 0, 2),
(20, 'iaculis nec, eleifend', '', 0, 0, 0, 7);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
