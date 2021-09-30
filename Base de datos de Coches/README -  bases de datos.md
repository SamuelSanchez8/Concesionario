CREATE SCHEMA coches;
USE coches;

CREATE TABLE `coches` (
  `id` int(11) AUTO_INCREMENT,
  `matricula` varchar(45) DEFAULT NULL,
  `marca` varchar(45) DEFAULT NULL,
  `modelo` varchar(45) DEFAULT NULL,
  `color` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `pasajeros` (
  `id` int(11) AUTO_INCREMENT PRIMARY KEY ,
  `nombre` varchar(45),
  `edad` int(3) DEFAULT NULL,
  `peso` double DEFAULT NULL,
  `idcoche` int(11),
   FOREIGN KEY (idcoche) REFERENCES coches(id)
   
);


INSERT INTO coches VALUES  ('null', '8799TRD', 'AUDI', 'TT', 'AZUL');
INSERT INTO coches VALUES  ('null', '5969DFG', 'FIAT', 'BRAVA', 'BLANCO');
INSERT INTO coches VALUES  ('null', '6256HSK', 'SEAT', 'LEON', 'AMARILLO');
INSERT INTO coches VALUES  ('null', '4569DJY', 'DAEWO', 'LANOS', 'VERDE');
INSERT INTO coches VALUES  ('null','7965KSO', 'PORSCHE', '911', 'NEGRO');

INSERT INTO pasajeros VALUES  ('null','Frodos', 80, 50, null);
INSERT INTO pasajeros VALUES  ('null','Samsagut', 95, 55, null);
INSERT INTO pasajeros VALUES  ('null','Merryadu', 83, 44, null);
INSERT INTO pasajeros VALUES  ('null','Pippinesku', 82, 49, null);
INSERT INTO pasajeros VALUES  ('null','Gandolfis', 90, 1500, null);