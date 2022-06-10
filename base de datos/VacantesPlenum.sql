
Create database vacantesPlenum;
use vacantesPlenum;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Categorias`
--



CREATE TABLE `Categorias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text,
  PRIMARY KEY (`id`)
) ;




CREATE TABLE `Perfiles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `perfil` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
);




CREATE TABLE `Solicitudes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `archivo` varchar(250) NOT NULL,
  `comentarios` text,
  `idVacante` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Vacante_Usuario_UNIQUE` (`idVacante`,`idUsuario`),
  KEY `fk_Solicitudes_Vacantes1_idx` (`idVacante`),
  KEY `fk_Solicitudes_Usuarios1_idx` (`idUsuario`),
  CONSTRAINT `fk_Solicitudes_Usuarios1` FOREIGN KEY (`idUsuario`) REFERENCES `Usuarios` (`id`),
  CONSTRAINT `fk_Solicitudes_Vacantes1` FOREIGN KEY (`idVacante`) REFERENCES `Vacantes` (`id`)
) ;


CREATE TABLE `UsuarioPerfil` (
  `idUsuario` int(11) NOT NULL,
  `idPerfil` int(11) NOT NULL,
  UNIQUE KEY `Usuario_Perfil_UNIQUE` (`idUsuario`,`idPerfil`),
  KEY `fk_Usuarios1_idx` (`idUsuario`),
  KEY `fk_Perfiles1_idx` (`idPerfil`),
  CONSTRAINT `fk_Perfiles1` FOREIGN KEY (`idPerfil`) REFERENCES `Perfiles` (`id`),
  CONSTRAINT `fk_Usuarios1` FOREIGN KEY (`idUsuario`) REFERENCES `Usuarios` (`id`)
) ;


CREATE TABLE `Usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `email` varchar(100) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `estatus` int(11) NOT NULL DEFAULT '1',
  `fechaRegistro` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ;

CREATE TABLE `Vacantes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) NOT NULL,
  `descripcion` text NOT NULL,
  `fecha` date NOT NULL,
  `salario` double NOT NULL,
  `estatus` enum('Creada','Aprobada','Eliminada') NOT NULL,
  `destacado` int(11) NOT NULL,
  `imagen` varchar(250) NOT NULL,
  `detalles` text,
  `idCategoria` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_vacantes_categorias1_idx` (`idCategoria`),
  CONSTRAINT `fk_vacantes_categorias1` FOREIGN KEY (`idCategoria`) REFERENCES `Categorias` (`id`)
) ;



/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


