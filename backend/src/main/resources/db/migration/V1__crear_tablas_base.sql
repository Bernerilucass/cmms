-- =========================
-- TABLA: Talleres
-- =========================
CREATE TABLE talleres (
                          id_taller SERIAL PRIMARY KEY,
                          nombre_taller VARCHAR(50) NOT NULL
);

-- =========================
-- TIPOS ENUM
-- =========================
CREATE TYPE rol_enum AS ENUM ('ADMINISTRADOR', 'TECNICO');
CREATE TYPE criticidad_enum AS ENUM ('ALTA', 'MEDIA', 'BAJA');
CREATE TYPE tipo_reparacion_enum AS ENUM ('INTERNA', 'EXTERNA');
CREATE TYPE tipo_ot_enum AS ENUM ('CORRECTIVA', 'PREVENTIVA');
CREATE TYPE estado_ot_enum AS ENUM ('ABIERTA', 'EN_PROCESO', 'FINALIZADA');

-- =========================
-- TABLA: Usuarios
-- =========================
CREATE TABLE usuarios (
                          id_usuarios SERIAL PRIMARY KEY,
                          legajo INT UNIQUE NOT NULL,
                          dni VARCHAR(8) NOT NULL,
                          nombre VARCHAR(50) NOT NULL,
                          apellido VARCHAR(50) NOT NULL,
                          rol rol_enum NOT NULL,
                          fecha_alta TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================
-- TABLA: Activos
-- =========================
CREATE TABLE activos (
                         id_activo SERIAL PRIMARY KEY,
                         nombre_activo VARCHAR(100) NOT NULL,
                         criticidad criticidad_enum NOT NULL,
                         tipo_reparacion tipo_reparacion_enum NOT NULL,
                         id_taller INT REFERENCES talleres(id_taller)
);

-- =========================
-- TABLA: Planes Preventivos
-- =========================
CREATE TABLE planes_preventivos (
                                    id_plan SERIAL PRIMARY KEY,
                                    id_activo INT NOT NULL REFERENCES activos(id_activo),
                                    descripcion VARCHAR(200) NOT NULL,
                                    intervalo_dias INT NOT NULL,
                                    ultimo_mantenimiento DATE,
                                    proximo_mantenimiento DATE,
                                    activo BOOLEAN DEFAULT TRUE
);

-- =========================
-- TABLA: Ordenes de Trabajo
-- =========================
CREATE TABLE ordenes_trabajo (
                                 id_ot SERIAL PRIMARY KEY,
                                 id_activo INT NOT NULL REFERENCES activos(id_activo),
                                 id_plan INT REFERENCES planes_preventivos(id_plan),
                                 tipo_ot tipo_ot_enum NOT NULL,
                                 motivo_tarea TEXT,
                                 tarea_realizada TEXT,
                                 observaciones TEXT,
                                 fecha_recepcion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 fecha_finalizacion TIMESTAMP,
                                 estado estado_ot_enum NOT NULL DEFAULT 'ABIERTA'
);

-- =========================
-- TABLA: Detalle de Trabajo
-- =========================
CREATE TABLE detalle_trabajo (
                                 id_detalle SERIAL PRIMARY KEY,
                                 id_ot INT NOT NULL REFERENCES ordenes_trabajo(id_ot),
                                 id_usuarios INT NOT NULL REFERENCES usuarios(id_usuarios),
                                 horas_hombre DECIMAL(5,2)
);

-- =========================
-- TABLA: Repuestos
-- =========================
CREATE TABLE repuestos (
                           id_repuesto SERIAL PRIMARY KEY,
                           id_ot INT NOT NULL REFERENCES ordenes_trabajo(id_ot),
                           descripcion VARCHAR(100) NOT NULL,
                           cantidad INT NOT NULL
);