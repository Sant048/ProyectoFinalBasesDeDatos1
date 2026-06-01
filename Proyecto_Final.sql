drop database bases_1;
CREATE DATABASE Bases_1;
USE Bases_1;

-- =========================
-- TABLAS CATALOGALES
-- =========================

CREATE TABLE DOCUMENTO_IDENTIDAD (
    id_tipo_documento INT PRIMARY KEY AUTO_INCREMENT,
    sigla VARCHAR(10) NOT NULL UNIQUE,
    descripcion VARCHAR(100)
);

CREATE TABLE ESTADO_ORDEN (
    id_estado_orden INT PRIMARY KEY AUTO_INCREMENT,
    nombre_estado VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(200)
);

CREATE TABLE ESTADO_MECANICO (
    id_estado_mecanico INT PRIMARY KEY AUTO_INCREMENT,
    nombre_estado VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(200)
);

CREATE TABLE MARCA (
    id_marca INT PRIMARY KEY AUTO_INCREMENT,
    nombre_marca VARCHAR(80) NOT NULL UNIQUE,
    pais_origen VARCHAR(60)
);

CREATE TABLE COLOR (
    id_color INT PRIMARY KEY AUTO_INCREMENT,
    nombre_color VARCHAR(50) NOT NULL UNIQUE,
    codigo_hex VARCHAR(10)
);

CREATE TABLE TIPO_COMBUSTIBLE (
    id_tipo_combustible INT PRIMARY KEY AUTO_INCREMENT,
    nombre_combustible VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(150)
);

CREATE TABLE ESPECIALIDAD (
    id_especialidad INT PRIMARY KEY AUTO_INCREMENT,
    nombre_especialidad VARCHAR(80) NOT NULL UNIQUE,
    descripcion VARCHAR(200)
);

CREATE TABLE MARCA_REPUESTO (
    id_marca_repuesto INT PRIMARY KEY AUTO_INCREMENT,
    nombre_marca VARCHAR(80) NOT NULL UNIQUE,
    pais_origen VARCHAR(60)
);

CREATE TABLE CIUDAD (
    id_ciudad INT PRIMARY KEY AUTO_INCREMENT,
    nombre_ciudad VARCHAR(80) NOT NULL UNIQUE
);

-- =========================
-- TABLAS PRINCIPALES
-- =========================

CREATE TABLE CLIENTE (
    id_cliente INT PRIMARY KEY AUTO_INCREMENT,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    numero_documento VARCHAR(20) NOT NULL,
    id_tipo_documento INT NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    correo VARCHAR(100) UNIQUE,
    direccion VARCHAR(200),
    fecha_registro DATE NOT NULL DEFAULT (CURRENT_DATE),

    CONSTRAINT uq_cliente_documento
        UNIQUE (numero_documento, id_tipo_documento),

    CONSTRAINT fk_cliente_documento
        FOREIGN KEY (id_tipo_documento)
        REFERENCES DOCUMENTO_IDENTIDAD(id_tipo_documento)
);

CREATE TABLE VEHICULO (
    id_vehiculo INT PRIMARY KEY AUTO_INCREMENT,
    id_cliente INT NOT NULL,
    placa VARCHAR(10) NOT NULL UNIQUE,
    id_marca INT NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    id_color INT NOT NULL,
    cilindraje INT NOT NULL,
    id_tipo_combustible INT NOT NULL,
    kilometraje_actual INT NOT NULL,

    CONSTRAINT chk_vehiculo_cilindraje
        CHECK (cilindraje > 0),

    CONSTRAINT chk_vehiculo_kilometraje
        CHECK (kilometraje_actual >= 0),

    CONSTRAINT fk_vehiculo_cliente
        FOREIGN KEY (id_cliente)
        REFERENCES CLIENTE(id_cliente),

    CONSTRAINT fk_vehiculo_marca
        FOREIGN KEY (id_marca)
        REFERENCES MARCA(id_marca),

    CONSTRAINT fk_vehiculo_color
        FOREIGN KEY (id_color)
        REFERENCES COLOR(id_color),

    CONSTRAINT fk_vehiculo_combustible
        FOREIGN KEY (id_tipo_combustible)
        REFERENCES TIPO_COMBUSTIBLE(id_tipo_combustible)
);

CREATE TABLE MECANICO (
    id_mecanico INT PRIMARY KEY AUTO_INCREMENT,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    numero_documento VARCHAR(20) NOT NULL,
    id_tipo_documento INT NOT NULL,
    id_especialidad INT NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    fecha_ingreso DATE NOT NULL,
    id_estado_mecanico INT NOT NULL,

    CONSTRAINT uq_mecanico_documento
        UNIQUE (numero_documento, id_tipo_documento),

    CONSTRAINT fk_mecanico_documento
        FOREIGN KEY (id_tipo_documento)
        REFERENCES DOCUMENTO_IDENTIDAD(id_tipo_documento),

    CONSTRAINT fk_mecanico_especialidad
        FOREIGN KEY (id_especialidad)
        REFERENCES ESPECIALIDAD(id_especialidad),

    CONSTRAINT fk_mecanico_estado
        FOREIGN KEY (id_estado_mecanico)
        REFERENCES ESTADO_MECANICO(id_estado_mecanico)
);

CREATE TABLE PROVEEDOR (
    id_proveedor INT PRIMARY KEY AUTO_INCREMENT,
    razon_social VARCHAR(150) NOT NULL,
    nit VARCHAR(20) NOT NULL UNIQUE,
    telefono VARCHAR(20) NOT NULL,
    correo VARCHAR(100),
    id_ciudad INT NOT NULL,
    tiempo_entrega_dias INT NOT NULL,

    CONSTRAINT chk_proveedor_tiempo
        CHECK (tiempo_entrega_dias > 0),

    CONSTRAINT fk_proveedor_ciudad
        FOREIGN KEY (id_ciudad)
        REFERENCES CIUDAD(id_ciudad)
);

CREATE TABLE SERVICIO (
    id_servicio INT PRIMARY KEY AUTO_INCREMENT,
    nombre_servicio VARCHAR(100) NOT NULL UNIQUE,
    descripcion TEXT,
    precio_base DECIMAL(10,2) NOT NULL,
    tiempo_estimado_horas DECIMAL(5,2) NOT NULL,

    CONSTRAINT chk_servicio_precio
        CHECK (precio_base > 0),

    CONSTRAINT chk_servicio_tiempo
        CHECK (tiempo_estimado_horas > 0)
);

CREATE TABLE REPUESTO (
    id_repuesto INT PRIMARY KEY AUTO_INCREMENT,
    id_proveedor INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    referencia VARCHAR(50) NOT NULL UNIQUE,
    id_marca_repuesto INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    stock_actual INT NOT NULL,
    stock_minimo INT NOT NULL,

    CONSTRAINT chk_repuesto_precio
        CHECK (precio_unitario > 0),

    CONSTRAINT chk_repuesto_stock_actual
        CHECK (stock_actual >= 0),

    CONSTRAINT chk_repuesto_stock_minimo
        CHECK (stock_minimo >= 0),

    CONSTRAINT fk_repuesto_proveedor
        FOREIGN KEY (id_proveedor)
        REFERENCES PROVEEDOR(id_proveedor),

    CONSTRAINT fk_repuesto_marca
        FOREIGN KEY (id_marca_repuesto)
        REFERENCES MARCA_REPUESTO(id_marca_repuesto)
);

CREATE TABLE ORDEN_TRABAJO (
    id_orden INT PRIMARY KEY AUTO_INCREMENT,
    id_vehiculo INT NOT NULL,
    id_mecanico INT NOT NULL,
    fecha_ingreso DATE NOT NULL,
    fecha_estimada_entrega DATE NOT NULL,
    fecha_real_entrega DATE,
    descripcion_falla TEXT NOT NULL,
    id_estado_orden INT NOT NULL,

    CONSTRAINT chk_orden_fechas
        CHECK (
            fecha_real_entrega IS NULL
            OR fecha_real_entrega >= fecha_ingreso
        ),

    CONSTRAINT fk_orden_vehiculo
        FOREIGN KEY (id_vehiculo)
        REFERENCES VEHICULO(id_vehiculo),

    CONSTRAINT fk_orden_mecanico
        FOREIGN KEY (id_mecanico)
        REFERENCES MECANICO(id_mecanico),

    CONSTRAINT fk_orden_estado
        FOREIGN KEY (id_estado_orden)
        REFERENCES ESTADO_ORDEN(id_estado_orden)
);

CREATE TABLE DETALLE_ORDEN (
    id_detalle INT PRIMARY KEY AUTO_INCREMENT,
    id_orden INT NOT NULL,
    id_servicio INT NULL,
    id_repuesto INT NULL,
    cantidad INT NOT NULL,
    precio_aplicado DECIMAL(10,2) NOT NULL,
    observacion TEXT,

    CONSTRAINT chk_detalle_cantidad
        CHECK (cantidad > 0),

    CONSTRAINT chk_detalle_precio
        CHECK (precio_aplicado > 0),

    CONSTRAINT chk_detalle_tipo
        CHECK (
            (id_servicio IS NOT NULL AND id_repuesto IS NULL)
            OR
            (id_servicio IS NULL AND id_repuesto IS NOT NULL)
        ),

    CONSTRAINT fk_detalle_orden
        FOREIGN KEY (id_orden)
        REFERENCES ORDEN_TRABAJO(id_orden),

    CONSTRAINT fk_detalle_servicio
        FOREIGN KEY (id_servicio)
        REFERENCES SERVICIO(id_servicio),

    CONSTRAINT fk_detalle_repuesto
        FOREIGN KEY (id_repuesto)
        REFERENCES REPUESTO(id_repuesto)
);

-- =========================
-- DATOS INICIALES OPCIONALES
-- =========================

INSERT INTO ESTADO_ORDEN (nombre_estado, descripcion)
VALUES
('Abierta','Orden recién creada'),
('En Proceso','Orden en ejecución'),
('Cerrada','Trabajo finalizado'),
('Cancelada','Orden anulada');

INSERT INTO ESTADO_MECANICO (nombre_estado, descripcion)
VALUES
('Activo','Mecánico disponible'),
('Inactivo','Mecánico no disponible');

INSERT INTO DOCUMENTO_IDENTIDAD (sigla, descripcion)
VALUES
('CC','Cédula de Ciudadanía'),
('CE','Cédula de Extranjería'),
('PAS','Pasaporte'),
('NIT','Número de Identificación Tributaria');

-- =====================================
-- DATOS DE APOYO
-- =====================================

INSERT INTO CIUDAD (nombre_ciudad) VALUES
('Bogotá'),
('Medellín'),
('Cali'),
('Barranquilla'),
('Cartagena'),
('Bucaramanga'),
('Pereira'),
('Manizales'),
('Cúcuta'),
('Ibagué');

INSERT INTO MARCA (nombre_marca,pais_origen) VALUES
('Toyota','Japón'),
('Chevrolet','Estados Unidos'),
('Renault','Francia'),
('Mazda','Japón'),
('Kia','Corea del Sur'),
('Hyundai','Corea del Sur'),
('Ford','Estados Unidos'),
('Nissan','Japón'),
('Volkswagen','Alemania'),
('BMW','Alemania');

INSERT INTO COLOR (nombre_color,codigo_hex) VALUES
('Blanco','#FFFFFF'),
('Negro','#000000'),
('Rojo','#FF0000'),
('Azul','#0000FF'),
('Gris','#808080'),
('Verde','#00FF00'),
('Plateado','#C0C0C0'),
('Amarillo','#FFFF00'),
('Naranja','#FFA500'),
('Vinotinto','#800020');

INSERT INTO TIPO_COMBUSTIBLE (nombre_combustible,descripcion) VALUES
('Gasolina','Motor gasolina'),
('Diésel','Motor diésel'),
('Gas','Gas vehicular'),
('Híbrido','Motor híbrido'),
('Eléctrico','Motor eléctrico'),
('Extra','Gasolina extra'),
('Corriente','Gasolina corriente'),
('Premium','Combustible premium'),
('Biodiesel','Biodiesel'),
('Etanol','Etanol');

INSERT INTO ESPECIALIDAD (nombre_especialidad,descripcion) VALUES
('Motor','Reparación de motores'),
('Electricidad','Sistemas eléctricos'),
('Suspensión','Sistema suspensión'),
('Frenos','Sistema de frenos'),
('Transmisión','Sistema transmisión'),
('Inyección','Sistema inyección'),
('Diagnóstico','Diagnóstico electrónico'),
('Latonería','Reparación carrocería'),
('Pintura','Pintura automotriz'),
('Alineación','Alineación y balanceo');

INSERT INTO MARCA_REPUESTO (nombre_marca,pais_origen) VALUES
('Bosch','Alemania'),
('Valeo','Francia'),
('Denso','Japón'),
('ACDelco','Estados Unidos'),
('SKF','Suecia'),
('NGK','Japón'),
('Monroe','Estados Unidos'),
('TRW','Alemania'),
('Brembo','Italia'),
('Mann Filter','Alemania');

-- =====================================
-- CLIENTES (10)
-- =====================================

INSERT INTO CLIENTE
(nombres,apellidos,numero_documento,id_tipo_documento,telefono,correo,direccion)
VALUES
('Juan','Pérez','100000001',1,'3001111111','juan1@gmail.com','Cra 1 #10-01'),
('Carlos','Gómez','100000002',1,'3001111112','juan2@gmail.com','Cra 2 #10-02'),
('María','Rodríguez','100000003',1,'3001111113','juan3@gmail.com','Cra 3 #10-03'),
('Ana','Martínez','100000004',1,'3001111114','juan4@gmail.com','Cra 4 #10-04'),
('Luis','López','100000005',1,'3001111115','juan5@gmail.com','Cra 5 #10-05'),
('Pedro','Torres','100000006',1,'3001111116','juan6@gmail.com','Cra 6 #10-06'),
('Laura','Ramírez','100000007',1,'3001111117','juan7@gmail.com','Cra 7 #10-07'),
('Andrés','Moreno','100000008',1,'3001111118','juan8@gmail.com','Cra 8 #10-08'),
('Sofía','Castro','100000009',1,'3001111119','juan9@gmail.com','Cra 9 #10-09'),
('Miguel','Vargas','100000010',1,'3001111120','juan10@gmail.com','Cra 10 #10-10');

-- =====================================
-- VEHICULOS (10)
-- =====================================

INSERT INTO VEHICULO
(id_cliente,placa,id_marca,modelo,id_color,cilindraje,id_tipo_combustible,kilometraje_actual)
VALUES
(1,'AAA101',1,'Corolla',1,1600,1,50000),
(2,'AAA102',2,'Onix',2,1400,1,60000),
(3,'AAA103',3,'Logan',3,1600,1,70000),
(4,'AAA104',4,'Mazda 3',4,2000,1,40000),
(5,'AAA105',5,'Rio',5,1400,1,30000),
(6,'AAA106',6,'Accent',6,1600,1,45000),
(7,'AAA107',7,'Fiesta',7,1500,1,52000),
(8,'AAA108',8,'Versa',8,1600,1,35000),
(9,'AAA109',9,'Jetta',9,2000,1,80000),
(10,'AAA110',10,'320i',10,2000,1,25000);

-- =====================================
-- MECANICOS (10)
-- =====================================

INSERT INTO MECANICO
(nombres,apellidos,numero_documento,id_tipo_documento,id_especialidad,telefono,fecha_ingreso,id_estado_mecanico)
VALUES
('Mario','Rojas','200000001',1,1,'3100000001','2022-01-10',1),
('Diego','Gil','200000002',1,2,'3100000002','2022-02-10',1),
('Jorge','Ruiz','200000003',1,3,'3100000003','2022-03-10',1),
('David','Suarez','200000004',1,4,'3100000004','2022-04-10',1),
('Daniel','Acosta','200000005',1,5,'3100000005','2022-05-10',1),
('Felipe','León','200000006',1,6,'3100000006','2022-06-10',1),
('Ricardo','Vega','200000007',1,7,'3100000007','2022-07-10',1),
('Wilson','Díaz','200000008',1,8,'3100000008','2022-08-10',1),
('Camilo','Mora','200000009',1,9,'3100000009','2022-09-10',1),
('Óscar','Navarro','200000010',1,10,'3100000010','2022-10-10',1);

-- =====================================
-- PROVEEDORES (10)
-- =====================================

INSERT INTO PROVEEDOR
(razon_social,nit,telefono,correo,id_ciudad,tiempo_entrega_dias)
VALUES
('AutoPartes SAS','900000001','6011111111','prov1@gmail.com',1,2),
('Motor Express','900000002','6011111112','prov2@gmail.com',2,3),
('Repuestos GT','900000003','6011111113','prov3@gmail.com',3,4),
('Todo Motor','900000004','6011111114','prov4@gmail.com',4,2),
('AutoCenter','900000005','6011111115','prov5@gmail.com',5,3),
('Partes Uno','900000006','6011111116','prov6@gmail.com',6,4),
('Mundo Repuesto','900000007','6011111117','prov7@gmail.com',7,5),
('Full Motor','900000008','6011111118','prov8@gmail.com',8,3),
('Repuestera SAS','900000009','6011111119','prov9@gmail.com',9,2),
('Mega Partes','900000010','6011111120','prov10@gmail.com',10,4);

-- =====================================
-- SERVICIOS (10)
-- =====================================

INSERT INTO SERVICIO
(nombre_servicio,descripcion,precio_base,tiempo_estimado_horas)
VALUES
('Cambio Aceite','Aceite y filtro',120000,1),
('Alineación','Alineación ruedas',80000,1),
('Balanceo','Balanceo ruedas',70000,1),
('Cambio Frenos','Pastillas freno',250000,2),
('Diagnóstico','Scanner',60000,1),
('Cambio Batería','Instalación batería',150000,1),
('Lavado Motor','Lavado especializado',90000,1),
('Suspensión','Revisión suspensión',180000,3),
('Cambio Correa','Correa distribución',450000,4),
('Pintura','Trabajo pintura',800000,8);

-- =====================================
-- REPUESTOS (10)
-- =====================================

INSERT INTO REPUESTO
(id_proveedor,nombre,referencia,id_marca_repuesto,precio_unitario,stock_actual,stock_minimo)
VALUES
(1,'Filtro Aceite','REF001',1,25000,50,10),
(2,'Pastillas Freno','REF002',2,80000,40,10),
(3,'Batería','REF003',3,300000,20,5),
(4,'Correa','REF004',4,120000,30,5),
(5,'Amortiguador','REF005',5,200000,15,3),
(6,'Bujía','REF006',6,18000,100,20),
(7,'Disco Freno','REF007',7,150000,25,5),
(8,'Filtro Aire','REF008',8,35000,50,10),
(9,'Rodamiento','REF009',9,90000,30,5),
(10,'Filtro Combustible','REF010',10,40000,40,8);

-- =====================================
-- ORDENES DE TRABAJO (10)
-- =====================================

INSERT INTO ORDEN_TRABAJO
(id_vehiculo,id_mecanico,fecha_ingreso,fecha_estimada_entrega,fecha_real_entrega,descripcion_falla,id_estado_orden)
VALUES
(1,1,'2025-01-01','2025-01-02','2025-01-02','Cambio aceite',3),
(2,2,'2025-01-03','2025-01-04','2025-01-04','Problema frenos',3),
(3,3,'2025-01-05','2025-01-06','2025-01-06','Suspensión dañada',3),
(4,4,'2025-01-07','2025-01-08','2025-01-08','Diagnóstico general',3),
(5,5,'2025-01-09','2025-01-10','2025-01-10','Cambio batería',3),
(6,6,'2025-01-11','2025-01-12','2025-01-12','Motor falla',3),
(7,7,'2025-01-13','2025-01-14','2025-01-14','Correa desgastada',3),
(8,8,'2025-01-15','2025-01-16','2025-01-16','Ruido suspensión',3),
(9,9,'2025-01-17','2025-01-18','2025-01-18','Pintura rayada',3),
(10,10,'2025-01-19','2025-01-20','2025-01-20','Mantenimiento',3);

-- =====================================
-- DETALLE ORDEN (10)
-- =====================================

INSERT INTO DETALLE_ORDEN
(id_orden,id_servicio,id_repuesto,cantidad,precio_aplicado,observacion)
VALUES
(1,1,NULL,1,120000,'Servicio'),
(2,4,NULL,1,250000,'Servicio'),
(3,8,NULL,1,180000,'Servicio'),
(4,5,NULL,1,60000,'Servicio'),
(5,6,NULL,1,150000,'Servicio'),
(6,NULL,3,1,300000,'Repuesto'),
(7,NULL,4,1,120000,'Repuesto'),
(8,NULL,5,1,200000,'Repuesto'),
(9,10,NULL,1,800000,'Servicio'),
(10,2,NULL,1,80000,'Servicio');