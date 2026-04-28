¿Qué librerías incluye?
DependenciaPara qué sirve en TU sistemaSpring WebPermite crear los endpoints REST (/api/activos, /api/ordenes, etc.)Spring Data JPAConecta Java con PostgreSQL. Vos escribís clases Java y él las convierte en tablasSpring SecurityManeja el login y los roles (Admin vs Técnico)PostgreSQL DriverEl "traductor" entre Java y PostgreSQL específicamenteFlyway MigrationVersiona tu base de datos. Cada cambio en las tablas queda registrado en archivos SQLLombokEvita escribir código repetitivo. Genera getters, setters, constructores automáticamenteValidationValida datos antes de guardarlos. Ej: que el DNI no esté vacío, que el email tenga @
1. application.properties    ← conectar Spring con PostgreSQL
2. SQL (Flyway)              ← crear las tablas en la BD
3. Entities                  ← clases Java que representan las tablas
4. Repositories              ← acceso a la BD
5. Services                  ← lógica de negocio
6. Controllers               ← endpoints REST
   Controller llama al Service →
   Service llama al Repository →
   Repository consulta PostgreSQL →
   Devuelve la lista al Controller →
   Controller la envuelve en ResponseEntity y responde JSON
   Cliente envía: POST /api/ordenes
   ↓
   @RestController recibe la petición
   ↓
   @PostMapping la dirige al método crear()
   ↓
   @RequestBody convierte el JSON a objeto Java
   ↓
   ordenTrabajoService.guardar(ot) ejecuta la lógica
   ↓
   El repository hace INSERT en PostgreSQL
   ↓
   ResponseEntity.ok() envuelve el resultado en HTTP 200
   ↓
   Spring convierte el objeto Java a JSON
   ↓
   Cliente recibe el JSON con la OT creada