CREATE DATABASE IF NOT EXISTS ActividadIMC;

use ActividadIMC;

DROP USER IF EXISTS 'tofol'@'localhost';
DROP USER IF EXISTS 'tofol'@'%';

drop procedure if exists crear_usuario;

#Procedure que intenta bajar el nivel de seguridad por si acaso y crea el usuario
DELIMITER $$
CREATE PROCEDURE crear_usuario()
BEGIN
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
		SET GLOBAL validate_password_policy = 0;
	CREATE USER IF NOT EXISTS 'tofol'@'%' IDENTIFIED BY 'password';
END$$
DELIMITER ;
SET GLOBAL validate_password_policy = 0;
CALL crear_usuario();

GRANT ALL PRIVILEGES ON ActividadIMC.* TO 'tofol'@'%' WITH GRANT OPTION;

CREATE TABLE IF NOT EXISTS usuario (
	id INT(11) auto_increment PRIMARY KEY,
	correo VARCHAR(100) NOT NULL UNIQUE,
	nombre VARCHAR(100) NOT NULL,
	password VARCHAR(100) NOT NULL,
	foto VARCHAR(255),
    validado BOOLEAN NOT NULL,
    fechaRegistro DATE NOT NULL,
    modoNocturno BOOLEAN NOT NULL
)  ENGINE=INNODB charset=utf8;

CREATE TABLE IF NOT EXISTS altas_bajas(
	id INT(11) auto_increment primary key,
    correo VARCHAR(100) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    tipoAccion CHAR(1) NOT NULL,
    fecha DATETIME NOT NULL
)  ENGINE=INNODB charset=utf8;

CREATE TABLE IF NOT EXISTS validacion (
	codigo VARCHAR(100) NOT NULL PRIMARY KEY,
    idUsuario INT(11) NOT NULL,
    foreign key (idUsuario)
   	 references usuario (id)
    	on delete cascade
    	on update cascade
)  ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS calculo (
	id INT(11) AUTO_INCREMENT PRIMARY KEY,
    estatura float NOT NULL,
    peso float NOT NULL,
    fecha DATETIME NOT NULL,
    idUsuario INT(11) NOT NULL,
    foreign key (idUsuario)
   	 references usuario (id)
    	on delete cascade
    	on update cascade
)  ENGINE=INNODB;

#Trigger para insertar altas de usuarios en altas_bajas
DELIMITER $$
CREATE TRIGGER altas AFTER INSERT ON usuario
FOR EACH ROW
begin
	INSERT INTO altas_bajas (correo,nombre,tipoAccion,fecha)
    VALUES (new.correo, new.nombre, 'A', NOW());
end$$
DELIMITER ;

#Trigger para insertar bajas de usuarios en altas_bajas
DELIMITER $$
CREATE TRIGGER bajas BEFORE DELETE ON usuario
FOR EACH ROW
begin
	INSERT INTO altas_bajas (correo,nombre,tipoAccion,fecha)
    VALUES (old.correo, old.nombre, 'B', NOW());
end$$
DELIMITER ;

INSERT INTO usuario (correo,nombre,password,foto,validado,fechaRegistro,modoNocturno)
values ('tofol@gmail.com','tofol','passwordtofol','default.png',false,'2019-12-06',true);

INSERT INTO usuario (correo,nombre,password,foto,validado,fechaRegistro,modoNocturno)
values ('pep@gmail.com','pep','passwordpep','default.png',true,'2019-12-07',false);

INSERT INTO usuario (correo,nombre,password,foto,validado,fechaRegistro,modoNocturno)
values ('pip@gmail.com','pip','passwordpip','default.png',true,'2019-12-08',true);

INSERT INTO usuario (correo,nombre,password,foto,validado,fechaRegistro,modoNocturno)
values ('pop@gmail.com','pop','passwordpop','default.png',false,'2019-12-09',false);

INSERT INTO usuario (correo,nombre,password,foto,validado,fechaRegistro,modoNocturno)
values ('pup@gmail.com','pup','passwordpup','default.png',true,'2019-12-10',false);

INSERT INTO validacion (codigo,idUsuario)
values ('algkkjadkfajfajsdnrri',1);

INSERT INTO validacion (codigo,idUsuario)
values ('pydfhrybcnweuqysaixjh',4);

INSERT INTO calculo (estatura,peso,fecha,idUsuario)
values (1.90,80.7,'2018-12-06 18:14:00',1);

INSERT INTO calculo (estatura,peso,fecha,idUsuario)
values (1.90,81,'2018-12-07 18:16:00',1);

INSERT INTO calculo (estatura,peso,fecha,idUsuario)
values (1.90,86,'2018-12-10 20:00:00',1);

INSERT INTO calculo (estatura,peso,fecha,idUsuario)
values (1.90,100.4,'2019-01-01 00:00:14',1);

INSERT INTO calculo (estatura,peso,fecha,idUsuario)
values (1.90,101,'2018-12-06 18:14:00',2);

INSERT INTO calculo (estatura,peso,fecha,idUsuario)
values (1.90,100,'2018-12-06 20:00:00',2);

INSERT INTO calculo (estatura,peso,fecha,idUsuario)
values (1.90,98,'2018-12-07 04:30:00',2);

INSERT INTO calculo (estatura,peso,fecha,idUsuario)
values (1.79,68,'2019-01-01 00:00:07',2);