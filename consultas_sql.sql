-- ============================================================
-- CONSULTAS SQL - TALLER MECÁNICO
-- Módulos: Cliente, Vehículo, Mecánico, Orden, Inventario,
--          Catálogos, Reportes
-- ============================================================

-- ============================================================
-- MÓDULO: CLIENTE
-- ============================================================

-- 1. Listar todos los clientes con su tipo de documento (INNER JOIN)
SELECT
    c.id_cliente,
    c.nombres,
    c.apellidos,
    di.sigla AS tipo_documento,
    c.numero_documento,
    c.telefono,
    c.correo,
    c.direccion,
    c.fecha_registro
FROM CLIENTE c
INNER JOIN DOCUMENTO_IDENTIDAD di ON c.id_tipo_documento = di.id_tipo_documento
ORDER BY c.apellidos, c.nombres;

-- 2. Buscar cliente por número de documento (WHERE)
SELECT
    c.id_cliente,
    c.nombres,
    c.apellidos,
    di.sigla AS tipo_documento,
    c.numero_documento,
    c.telefono,
    c.correo
FROM CLIENTE c
INNER JOIN DOCUMENTO_IDENTIDAD di ON c.id_tipo_documento = di.id_tipo_documento
WHERE c.numero_documento = '100000001';

-- 3. Clientes con al menos un vehículo registrado (subconsulta)
SELECT
    c.id_cliente,
    c.nombres,
    c.apellidos,
    c.telefono,
    c.correo
FROM CLIENTE c
WHERE c.id_cliente IN (
    SELECT DISTINCT v.id_cliente FROM VEHICULO v
)
ORDER BY c.apellidos;

-- 4. Clientes SIN vehículo registrado (LEFT JOIN + NULL)
SELECT
    c.id_cliente,
    c.nombres,
    c.apellidos,
    c.telefono
FROM CLIENTE c
LEFT JOIN VEHICULO v ON c.id_cliente = v.id_cliente
WHERE v.id_vehiculo IS NULL
ORDER BY c.apellidos;

-- 5. Cliente con mayor número de vehículos (GROUP BY + COUNT + ORDER BY)
SELECT
    c.id_cliente,
    c.nombres,
    c.apellidos,
    COUNT(v.id_vehiculo) AS total_vehiculos
FROM CLIENTE c
LEFT JOIN VEHICULO v ON c.id_cliente = v.id_cliente
GROUP BY c.id_cliente, c.nombres, c.apellidos
ORDER BY total_vehiculos DESC
LIMIT 1;

-- ============================================================
-- MÓDULO: VEHÍCULO
-- ============================================================

-- 6. Listar vehículos con datos completos (3 tablas: VEHICULO, CLIENTE, MARCA, COLOR, TIPO_COMBUSTIBLE)
SELECT
    v.id_vehiculo,
    v.placa,
    CONCAT(c.nombres, ' ', c.apellidos) AS propietario,
    m.nombre_marca AS marca,
    v.modelo,
    col.nombre_color AS color,
    v.cilindraje,
    tc.nombre_combustible AS combustible,
    v.kilometraje_actual
FROM VEHICULO v
INNER JOIN CLIENTE c ON v.id_cliente = c.id_cliente
INNER JOIN MARCA m ON v.id_marca = m.id_marca
INNER JOIN COLOR col ON v.id_color = col.id_color
INNER JOIN TIPO_COMBUSTIBLE tc ON v.id_tipo_combustible = tc.id_tipo_combustible
ORDER BY v.placa;

-- 7. Buscar vehículo por placa
SELECT
    v.id_vehiculo,
    v.placa,
    CONCAT(c.nombres, ' ', c.apellidos) AS propietario,
    c.telefono,
    m.nombre_marca AS marca,
    v.modelo,
    col.nombre_color AS color,
    v.kilometraje_actual
FROM VEHICULO v
INNER JOIN CLIENTE c ON v.id_cliente = c.id_cliente
INNER JOIN MARCA m ON v.id_marca = m.id_marca
INNER JOIN COLOR col ON v.id_color = col.id_color
WHERE v.placa = 'AAA101';

-- 8. Vehículos por marca (GROUP BY + COUNT)
SELECT
    m.nombre_marca,
    COUNT(v.id_vehiculo) AS total_vehiculos
FROM MARCA m
LEFT JOIN VEHICULO v ON m.id_marca = v.id_marca
GROUP BY m.id_marca, m.nombre_marca
ORDER BY total_vehiculos DESC;

-- 9. Vehículos con más de 50000 km (WHERE + ORDER BY)
SELECT
    v.placa,
    CONCAT(c.nombres, ' ', c.apellidos) AS propietario,
    m.nombre_marca,
    v.modelo,
    v.kilometraje_actual
FROM VEHICULO v
INNER JOIN CLIENTE c ON v.id_cliente = c.id_cliente
INNER JOIN MARCA m ON v.id_marca = m.id_marca
WHERE v.kilometraje_actual > 50000
ORDER BY v.kilometraje_actual DESC;

-- ============================================================
-- MÓDULO: MECÁNICO
-- ============================================================

-- 10. Listar mecánicos con especialidad y estado (INNER JOIN)
SELECT
    m.id_mecanico,
    m.nombres,
    m.apellidos,
    di.sigla AS tipo_documento,
    m.numero_documento,
    e.nombre_especialidad AS especialidad,
    m.telefono,
    m.fecha_ingreso,
    em.nombre_estado AS estado
FROM MECANICO m
INNER JOIN DOCUMENTO_IDENTIDAD di ON m.id_tipo_documento = di.id_tipo_documento
INNER JOIN ESPECIALIDAD e ON m.id_especialidad = e.id_especialidad
INNER JOIN ESTADO_MECANICO em ON m.id_estado_mecanico = em.id_estado_mecanico
ORDER BY m.apellidos;

-- 11. Mecánicos activos disponibles
SELECT
    m.id_mecanico,
    m.nombres,
    m.apellidos,
    e.nombre_especialidad AS especialidad,
    m.telefono
FROM MECANICO m
INNER JOIN ESPECIALIDAD e ON m.id_especialidad = e.id_especialidad
INNER JOIN ESTADO_MECANICO em ON m.id_estado_mecanico = em.id_estado_mecanico
WHERE em.nombre_estado = 'Activo'
ORDER BY e.nombre_especialidad, m.apellidos;

-- 12. Mecánico con más órdenes atendidas (GROUP BY + COUNT + subconsulta)
SELECT
    m.id_mecanico,
    CONCAT(m.nombres, ' ', m.apellidos) AS mecanico,
    e.nombre_especialidad,
    COUNT(o.id_orden) AS total_ordenes
FROM MECANICO m
INNER JOIN ESPECIALIDAD e ON m.id_especialidad = e.id_especialidad
LEFT JOIN ORDEN_TRABAJO o ON m.id_mecanico = o.id_mecanico
GROUP BY m.id_mecanico, m.nombres, m.apellidos, e.nombre_especialidad
ORDER BY total_ordenes DESC;

-- 13. Mecánicos sin órdenes asignadas (LEFT JOIN + NULL)
SELECT
    m.id_mecanico,
    CONCAT(m.nombres, ' ', m.apellidos) AS mecanico,
    e.nombre_especialidad
FROM MECANICO m
INNER JOIN ESPECIALIDAD e ON m.id_especialidad = e.id_especialidad
LEFT JOIN ORDEN_TRABAJO o ON m.id_mecanico = o.id_mecanico
WHERE o.id_orden IS NULL;

-- ============================================================
-- MÓDULO: ORDEN DE TRABAJO
-- ============================================================

-- 14. Listar órdenes con datos completos (5 tablas)
SELECT
    ot.id_orden,
    ot.fecha_ingreso,
    ot.fecha_estimada_entrega,
    ot.fecha_real_entrega,
    v.placa,
    m.nombre_marca AS marca_vehiculo,
    veh.modelo,
    CONCAT(c.nombres, ' ', c.apellidos) AS cliente,
    CONCAT(mec.nombres, ' ', mec.apellidos) AS mecanico,
    eo.nombre_estado AS estado,
    ot.descripcion_falla
FROM ORDEN_TRABAJO ot
INNER JOIN VEHICULO v ON ot.id_vehiculo = v.id_vehiculo
INNER JOIN MARCA m ON v.id_marca = m.id_marca
INNER JOIN VEHICULO veh ON ot.id_vehiculo = veh.id_vehiculo
INNER JOIN CLIENTE c ON v.id_cliente = c.id_cliente
INNER JOIN MECANICO mec ON ot.id_mecanico = mec.id_mecanico
INNER JOIN ESTADO_ORDEN eo ON ot.id_estado_orden = eo.id_estado_orden
ORDER BY ot.fecha_ingreso DESC;

-- 15. Órdenes abiertas o en proceso
SELECT
    ot.id_orden,
    v.placa,
    CONCAT(c.nombres, ' ', c.apellidos) AS cliente,
    CONCAT(mec.nombres, ' ', mec.apellidos) AS mecanico,
    ot.fecha_ingreso,
    ot.fecha_estimada_entrega,
    eo.nombre_estado
FROM ORDEN_TRABAJO ot
INNER JOIN VEHICULO v ON ot.id_vehiculo = v.id_vehiculo
INNER JOIN CLIENTE c ON v.id_cliente = c.id_cliente
INNER JOIN MECANICO mec ON ot.id_mecanico = mec.id_mecanico
INNER JOIN ESTADO_ORDEN eo ON ot.id_estado_orden = eo.id_estado_orden
WHERE eo.nombre_estado IN ('Abierta', 'En Proceso')
ORDER BY ot.fecha_ingreso;

-- 16. Detalle de una orden (servicios y repuestos)
SELECT
    ot.id_orden,
    v.placa,
    CASE
        WHEN d.id_servicio IS NOT NULL THEN 'Servicio'
        ELSE 'Repuesto'
    END AS tipo_item,
    COALESCE(s.nombre_servicio, r.nombre) AS descripcion_item,
    d.cantidad,
    d.precio_aplicado,
    (d.cantidad * d.precio_aplicado) AS subtotal,
    d.observacion
FROM DETALLE_ORDEN d
INNER JOIN ORDEN_TRABAJO ot ON d.id_orden = ot.id_orden
INNER JOIN VEHICULO v ON ot.id_vehiculo = v.id_vehiculo
LEFT JOIN SERVICIO s ON d.id_servicio = s.id_servicio
LEFT JOIN REPUESTO r ON d.id_repuesto = r.id_repuesto
WHERE ot.id_orden = 1;

-- 17. Total facturado por orden (GROUP BY + SUM)
SELECT
    ot.id_orden,
    v.placa,
    CONCAT(c.nombres, ' ', c.apellidos) AS cliente,
    ot.fecha_ingreso,
    SUM(d.cantidad * d.precio_aplicado) AS total_orden
FROM ORDEN_TRABAJO ot
INNER JOIN VEHICULO v ON ot.id_vehiculo = v.id_vehiculo
INNER JOIN CLIENTE c ON v.id_cliente = c.id_cliente
INNER JOIN DETALLE_ORDEN d ON ot.id_orden = d.id_orden
GROUP BY ot.id_orden, v.placa, c.nombres, c.apellidos, ot.fecha_ingreso
ORDER BY total_orden DESC;

-- ============================================================
-- MÓDULO: INVENTARIO (REPUESTO, PROVEEDOR, MARCA_REPUESTO)
-- ============================================================

-- 18. Listar repuestos con proveedor y marca (3 tablas)
SELECT
    r.id_repuesto,
    r.nombre,
    r.referencia,
    mr.nombre_marca AS marca_repuesto,
    p.razon_social AS proveedor,
    r.precio_unitario,
    r.stock_actual,
    r.stock_minimo,
    CASE
        WHEN r.stock_actual <= r.stock_minimo THEN 'REORDENAR'
        ELSE 'OK'
    END AS estado_stock
FROM REPUESTO r
INNER JOIN PROVEEDOR p ON r.id_proveedor = p.id_proveedor
INNER JOIN MARCA_REPUESTO mr ON r.id_marca_repuesto = mr.id_marca_repuesto
ORDER BY r.nombre;

-- 19. Repuestos con stock crítico (WHERE)
SELECT
    r.nombre,
    r.referencia,
    r.stock_actual,
    r.stock_minimo,
    p.razon_social AS proveedor,
    p.telefono,
    p.tiempo_entrega_dias
FROM REPUESTO r
INNER JOIN PROVEEDOR p ON r.id_proveedor = p.id_proveedor
WHERE r.stock_actual <= r.stock_minimo
ORDER BY r.stock_actual ASC;

-- 20. Repuesto más usado en órdenes (GROUP BY + COUNT)
SELECT
    r.nombre AS repuesto,
    r.referencia,
    COUNT(d.id_detalle) AS veces_usado,
    SUM(d.cantidad) AS unidades_total,
    SUM(d.cantidad * d.precio_aplicado) AS valor_total
FROM REPUESTO r
INNER JOIN DETALLE_ORDEN d ON r.id_repuesto = d.id_repuesto
GROUP BY r.id_repuesto, r.nombre, r.referencia
ORDER BY veces_usado DESC;

-- 21. Proveedores con sus repuestos (LEFT JOIN - incluye proveedores sin repuestos)
SELECT
    p.razon_social,
    p.nit,
    ci.nombre_ciudad AS ciudad,
    p.tiempo_entrega_dias,
    COUNT(r.id_repuesto) AS total_repuestos
FROM PROVEEDOR p
INNER JOIN CIUDAD ci ON p.id_ciudad = ci.id_ciudad
LEFT JOIN REPUESTO r ON p.id_proveedor = r.id_proveedor
GROUP BY p.id_proveedor, p.razon_social, p.nit, ci.nombre_ciudad, p.tiempo_entrega_dias
ORDER BY total_repuestos DESC;

-- ============================================================
-- MÓDULO: REPORTES (consultas avanzadas)
-- ============================================================

-- 22. RESUMEN GENERAL DEL SISTEMA
SELECT
    (SELECT COUNT(*) FROM CLIENTE) AS total_clientes,
    (SELECT COUNT(*) FROM VEHICULO) AS total_vehiculos,
    (SELECT COUNT(*) FROM MECANICO WHERE id_estado_mecanico = 1) AS mecanicos_activos,
    (SELECT COUNT(*) FROM ORDEN_TRABAJO WHERE id_estado_orden IN (1,2)) AS ordenes_activas,
    (SELECT COUNT(*) FROM ORDEN_TRABAJO WHERE id_estado_orden = 3) AS ordenes_cerradas,
    (SELECT COALESCE(SUM(d.cantidad * d.precio_aplicado),0)
     FROM DETALLE_ORDEN d
     INNER JOIN ORDEN_TRABAJO o ON d.id_orden = o.id_orden
     WHERE o.id_estado_orden = 3) AS ingresos_totales,
    (SELECT COUNT(*) FROM REPUESTO WHERE stock_actual <= stock_minimo) AS repuestos_criticos;

-- 23. Ingresos por mes (GROUP BY + SUM + ORDER BY)
SELECT
    YEAR(ot.fecha_ingreso) AS anio,
    MONTH(ot.fecha_ingreso) AS mes,
    DATE_FORMAT(ot.fecha_ingreso, '%Y-%m') AS periodo,
    COUNT(DISTINCT ot.id_orden) AS total_ordenes,
    SUM(d.cantidad * d.precio_aplicado) AS ingresos_mes
FROM ORDEN_TRABAJO ot
INNER JOIN DETALLE_ORDEN d ON ot.id_orden = d.id_orden
WHERE ot.id_estado_orden = 3
GROUP BY YEAR(ot.fecha_ingreso), MONTH(ot.fecha_ingreso), DATE_FORMAT(ot.fecha_ingreso, '%Y-%m')
ORDER BY anio, mes;

-- 24. Servicios más solicitados (GROUP BY + COUNT + HAVING)
SELECT
    s.nombre_servicio,
    s.precio_base,
    COUNT(d.id_detalle) AS veces_solicitado,
    SUM(d.cantidad * d.precio_aplicado) AS ingresos_generados
FROM SERVICIO s
INNER JOIN DETALLE_ORDEN d ON s.id_servicio = d.id_servicio
GROUP BY s.id_servicio, s.nombre_servicio, s.precio_base
HAVING COUNT(d.id_detalle) >= 1
ORDER BY veces_solicitado DESC;

-- 25. Mecánico con mayores ingresos generados (GROUP BY + SUM)
SELECT
    CONCAT(mec.nombres, ' ', mec.apellidos) AS mecanico,
    e.nombre_especialidad,
    COUNT(DISTINCT ot.id_orden) AS ordenes_atendidas,
    SUM(d.cantidad * d.precio_aplicado) AS ingresos_generados,
    AVG(d.cantidad * d.precio_aplicado) AS ingreso_promedio_por_item
FROM MECANICO mec
INNER JOIN ESPECIALIDAD e ON mec.id_especialidad = e.id_especialidad
INNER JOIN ORDEN_TRABAJO ot ON mec.id_mecanico = ot.id_mecanico
INNER JOIN DETALLE_ORDEN d ON ot.id_orden = d.id_orden
GROUP BY mec.id_mecanico, mec.nombres, mec.apellidos, e.nombre_especialidad
ORDER BY ingresos_generados DESC;

-- 26. Clientes con mayor gasto total (GROUP BY + SUM + ORDER BY)
SELECT
    CONCAT(c.nombres, ' ', c.apellidos) AS cliente,
    c.telefono,
    c.correo,
    COUNT(DISTINCT ot.id_orden) AS total_ordenes,
    SUM(d.cantidad * d.precio_aplicado) AS gasto_total
FROM CLIENTE c
INNER JOIN VEHICULO v ON c.id_cliente = v.id_cliente
INNER JOIN ORDEN_TRABAJO ot ON v.id_vehiculo = ot.id_vehiculo
INNER JOIN DETALLE_ORDEN d ON ot.id_orden = d.id_orden
GROUP BY c.id_cliente, c.nombres, c.apellidos, c.telefono, c.correo
ORDER BY gasto_total DESC;

-- 27. Órdenes que superan el promedio de facturación (HAVING + subconsulta)
SELECT
    ot.id_orden,
    v.placa,
    CONCAT(c.nombres, ' ', c.apellidos) AS cliente,
    SUM(d.cantidad * d.precio_aplicado) AS total_orden
FROM ORDEN_TRABAJO ot
INNER JOIN VEHICULO v ON ot.id_vehiculo = v.id_vehiculo
INNER JOIN CLIENTE c ON v.id_cliente = c.id_cliente
INNER JOIN DETALLE_ORDEN d ON ot.id_orden = d.id_orden
GROUP BY ot.id_orden, v.placa, c.nombres, c.apellidos
HAVING SUM(d.cantidad * d.precio_aplicado) > (
    SELECT AVG(sub_total) FROM (
        SELECT SUM(d2.cantidad * d2.precio_aplicado) AS sub_total
        FROM DETALLE_ORDEN d2
        GROUP BY d2.id_orden
    ) AS promedios
)
ORDER BY total_orden DESC;

-- 28. Tiempo promedio de entrega de órdenes cerradas por mecánico
SELECT
    CONCAT(mec.nombres, ' ', mec.apellidos) AS mecanico,
    COUNT(ot.id_orden) AS ordenes_cerradas,
    AVG(DATEDIFF(ot.fecha_real_entrega, ot.fecha_ingreso)) AS dias_promedio_entrega,
    MIN(DATEDIFF(ot.fecha_real_entrega, ot.fecha_ingreso)) AS min_dias,
    MAX(DATEDIFF(ot.fecha_real_entrega, ot.fecha_ingreso)) AS max_dias
FROM MECANICO mec
INNER JOIN ORDEN_TRABAJO ot ON mec.id_mecanico = ot.id_mecanico
WHERE ot.id_estado_orden = 3
  AND ot.fecha_real_entrega IS NOT NULL
GROUP BY mec.id_mecanico, mec.nombres, mec.apellidos
ORDER BY dias_promedio_entrega ASC;

-- 29. Historial completo de un vehículo por placa
SELECT
    v.placa,
    CONCAT(c.nombres, ' ', c.apellidos) AS propietario,
    ot.id_orden,
    ot.fecha_ingreso,
    ot.fecha_real_entrega,
    eo.nombre_estado AS estado,
    CASE
        WHEN d.id_servicio IS NOT NULL THEN 'Servicio'
        ELSE 'Repuesto'
    END AS tipo,
    COALESCE(s.nombre_servicio, r.nombre) AS item,
    d.cantidad,
    d.precio_aplicado,
    (d.cantidad * d.precio_aplicado) AS subtotal
FROM VEHICULO v
INNER JOIN CLIENTE c ON v.id_cliente = c.id_cliente
INNER JOIN ORDEN_TRABAJO ot ON v.id_vehiculo = ot.id_vehiculo
INNER JOIN ESTADO_ORDEN eo ON ot.id_estado_orden = eo.id_estado_orden
INNER JOIN DETALLE_ORDEN d ON ot.id_orden = d.id_orden
LEFT JOIN SERVICIO s ON d.id_servicio = s.id_servicio
LEFT JOIN REPUESTO r ON d.id_repuesto = r.id_repuesto
WHERE v.placa = 'AAA101'
ORDER BY ot.fecha_ingreso DESC, d.id_detalle;

-- 30. Órdenes por estado (GROUP BY + COUNT)
SELECT
    eo.nombre_estado,
    COUNT(ot.id_orden) AS total,
    ROUND(COUNT(ot.id_orden) * 100.0 / (SELECT COUNT(*) FROM ORDEN_TRABAJO), 2) AS porcentaje
FROM ESTADO_ORDEN eo
LEFT JOIN ORDEN_TRABAJO ot ON eo.id_estado_orden = ot.id_estado_orden
GROUP BY eo.id_estado_orden, eo.nombre_estado
ORDER BY total DESC;
