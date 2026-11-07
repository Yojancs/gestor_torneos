# Gestor de Torneos

Backend REST para el Laboratorio 05 de Ingeniería de Software II. Permite crear torneos y equipos, registrar jugadores e inscribir equipos en torneos. El código de producción está preparado para que los estudiantes diseñen pruebas de dominio, repositorio y servicio.

## Tecnologías

- Java 21, Spring Boot 4.1.1, Spring Web MVC, Spring Data JPA y Bean Validation.
- H2 en archivo para desarrollo; H2 en memoria disponible con el perfil `test`.
- Maven, JUnit 5, Mockito y JaCoCo para el trabajo posterior de pruebas.

## Arquitectura

Paquete raíz: `pe.edu.unsa.gestortorneos`.

```text
domain/model                 Entidades y reglas sin Spring ni JPA
domain/repository            Contratos de persistencia
domain/exception             Errores de dominio, conflicto y ausencia
application/service          Casos de uso y transacciones
application/dto              Solicitudes y respuestas REST
infrastructure/persistence   Entidades JPA, repositorios, adaptadores y mappers
infrastructure/config        Clock inyectable
presentation/controller      Endpoints HTTP
presentation/exception       Traducción de errores a HTTP
```

Los servicios reciben interfaces de repositorio por constructor. `RegistrationService` recibe además un `Clock`, que permite fijar la hora en pruebas.

## Ejecutar

Se necesita JDK 21 o superior. Desde la raíz del repositorio:

```bash
./mvnw spring-boot:run
```

También se puede compilar y ejecutar el JAR:

```bash
./mvnw -DskipTests package
java -jar target/gestortorneos-0.0.1-SNAPSHOT.jar
```

La API escucha en `http://localhost:8080`. En IntelliJ IDEA, importa `pom.xml` como proyecto Maven, selecciona JDK 21 y ejecuta `GestortorneosApplication`. Para producción, activa el perfil `prod` con `--spring.profiles.active=prod` o `SPRING_PROFILES_ACTIVE=prod`.

## H2

El perfil predeterminado es `dev`. La base se guarda en `./data/gestor_torneos` (directorio ignorado por Git). La consola está en `http://localhost:8080/h2-console`, con URL JDBC `jdbc:h2:file:./data/gestor_torneos`, usuario `sa` y contraseña vacía. La consola se deshabilita en `prod`.

El perfil `test` ofrece H2 en memoria mediante `src/test/resources/application-test.properties`; las futuras pruebas Spring pueden activarlo con `@ActiveProfiles("test")`. `ddl-auto=update` es una comodidad de desarrollo, no una estrategia de migraciones de producción.

## API

| Método | Ruta | Resultado |
| --- | --- | --- |
| POST | `/api/tournaments` | Crear torneo, 201 |
| GET | `/api/tournaments` | Listar torneos, 200 |
| GET | `/api/tournaments/{id}` | Buscar torneo, 200 |
| PATCH | `/api/tournaments/{id}/activate` | Activar, 200 |
| PATCH | `/api/tournaments/{id}/finish` | Finalizar, 200 |
| POST | `/api/teams` | Crear equipo, 201 |
| GET | `/api/teams` | Listar equipos, 200 |
| GET | `/api/teams/{id}` | Buscar equipo, 200 |
| POST | `/api/players` | Registrar jugador, 201 |
| GET | `/api/players/{id}` | Buscar jugador, 200 |
| GET | `/api/teams/{teamId}/players` | Jugadores de un equipo, 200 |
| POST | `/api/registrations` | Inscribir equipo, 201 |
| GET | `/api/tournaments/{tournamentId}/registrations` | Inscripciones del torneo, 200 |

Ejemplos de cuerpos JSON:

```json
{"name":"Copa UNSA","startDate":"2027-01-01","endDate":"2027-01-31"}
```

```json
{"name":"Arequipa FC","city":"Arequipa"}
```

```json
{"fullName":"Ana Torres","age":21,"teamId":1}
```

```json
{"tournamentId":1,"teamId":1}
```

Las solicitudes inválidas devuelven 400, los recursos ausentes 404 y los conflictos de negocio 409. Las creaciones devuelven 201 y la representación creada. No se exponen entidades JPA en la API.

## Reglas de negocio

- **Torneo:** nombre y fechas obligatorios; la fecha final no precede a la inicial. Comienza en `PLANNED`; solo `PLANNED → ACTIVE → FINISHED`.
- **Equipo:** nombre y ciudad obligatorios. El nombre es único sin distinguir mayúsculas y espacios exteriores.
- **Jugador:** nombre obligatorio, edad entre 16 y 60 y equipo existente.
- **Inscripción:** torneo y equipo existentes; el torneo debe estar `PLANNED`; no se repite la pareja torneo/equipo. La hora se genera con un `Clock` inyectable.

## Pruebas futuras y cobertura

El proyecto conserva la prueba de carga de contexto generada por Spring Initializr. Los casos del laboratorio están pendientes y se describen en [docs/TESTING_GUIDE.md](docs/TESTING_GUIDE.md).

```bash
./mvnw test
./mvnw verify
```

Tras `verify`, JaCoCo genera `target/site/jacoco/index.html`. No hay pruebas ficticias para incrementar cobertura.
