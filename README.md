¿Qué librerías incluye?
DependenciaPara qué sirve en TU sistemaSpring WebPermite crear los endpoints REST (/api/activos, /api/ordenes, etc.)Spring Data JPAConecta Java con PostgreSQL. Vos escribís clases Java y él las convierte en tablasSpring SecurityManeja el login y los roles (Admin vs Técnico)PostgreSQL DriverEl "traductor" entre Java y PostgreSQL específicamenteFlyway MigrationVersiona tu base de datos. Cada cambio en las tablas queda registrado en archivos SQLLombokEvita escribir código repetitivo. Genera getters, setters, constructores automáticamenteValidationValida datos antes de guardarlos. Ej: que el DNI no esté vacío, que el email tenga @
1. application.properties    ← conectar Spring con PostgreSQL
2. SQL (Flyway)              ← crear las tablas en la BD
3. Entities                  ← clases Java que representan las tablas
4. Repositories              ← acceso a la BD
5. Services                  ← lógica de negocio
6. Controllers               ← endpoints REST