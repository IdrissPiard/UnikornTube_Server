

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

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

-- --------------------------------------------------------

--
-- Structure de la table `playlists`
--

CREATE TABLE IF NOT EXISTS `playlists` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`,`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `playlist_video`
--

CREATE TABLE IF NOT EXISTS `playlist_video` (
  `id_playlist` int(11) NOT NULL,
  `id_video` int(11) NOT NULL
  -- `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tags`
--

CREATE TABLE IF NOT EXISTS `tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tag_video`
--

CREATE TABLE IF NOT EXISTS `tag_video` (
  `id_video` int(11) NOT NULL,
  `id_tag` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `profil_img_url` varchar(255) NOT NULL,
  -- `connected` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `videos`
--

CREATE TABLE IF NOT EXISTS `videos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `nb_like` int(11) NOT NULL,
  `nb_dislike` int(11) NOT NULL,
  `nb_view` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `uploaded` DATETIME NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `subscriptions`
--

CREATE TABLE IF NOT EXISTS `subscriptions` (
  `id_user` int(11) NOT NULL,
  `id_sub` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
