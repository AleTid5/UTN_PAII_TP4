CREATE TABLE IF NOT EXISTS `categoria` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`descripcion` varchar(25) NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `articulo` (
`id` int(11) NOT NULL,
`nombre` varchar(25) NOT NULL,
`stock` int(11) NOT NULL,
`idCategoria` int(11) NOT NULL,
`status` BOOL NOT NULL DEFAULT TRUE,
PRIMARY KEY (`id`),
FOREIGN KEY (idCategoria) REFERENCES categoria(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO categoria (descripcion) VALUES ('Almacén'), ('Bebidas'), ('Cosmética'), ('Desayuno'), ('Electrónica');

INSERT INTO articulo (id, nombre, stock, idCategoria)
VALUES (1, 'Yerba Playadito', 5, 1),
       (2, 'Aquarius Manzana', 10, 2),
       (3, 'Rimel Mac Rojo', 15, 3),
       (4, 'Tostadas integrales', 30, 4),
       (5, 'Auriculares Sony', 8, 5);