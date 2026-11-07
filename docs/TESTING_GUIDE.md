# Guía de diseño de pruebas — Laboratorio 05

Este documento describe superficies y escenarios de prueba. Los estudiantes deben escribir sus propios casos con JUnit 5, Mockito y Spring Data JPA. La única clase preexistente en `src/test` es `GestortorneosApplicationTests`, generada al crear el proyecto.

## Dominio sin Spring

| Clase | Métodos y reglas | Escenarios sugeridos |
| --- | --- | --- |
| `Tournament` | Constructores, `activate()`, `finish()`, `ensureRegistrationOpen()` | Nombre vacío, fechas nulas o invertidas; estado inicial `PLANNED`; transiciones válidas e inválidas, incluida reactivación de `FINISHED`; inscripción abierta solo en `PLANNED` |
| `Team` | Constructores | Nombre o ciudad nulos, vacíos y de solo espacios; recorte de espacios exteriores |
| `Player` | Constructores | Edades 15, 16, 60, 61; nombre y `teamId` ausentes |
| `Registration` | Constructores | IDs o fecha ausentes; conservación de la fecha proporcionada |

Las entradas inválidas del dominio lanzan `DomainException`; las transiciones inválidas lanzan `BusinessConflictException`.

## Persistencia con H2

Las interfaces de dominio son `TournamentRepository`, `TeamRepository`, `PlayerRepository` y `RegistrationRepository`. Sus adaptadores usan, respectivamente, `JpaTournamentRepository`, `JpaTeamRepository`, `JpaPlayerRepository` y `JpaRegistrationRepository`. Para pruebas de persistencia, usar H2 en memoria y el perfil `test`.

| Repositorio | Operaciones a comprobar |
| --- | --- |
| `TournamentRepository` | `save`, `findById`, `findAll`; conservar fechas y estado |
| `TeamRepository` | `save`, `findById`, `findAll`, `existsByNameIgnoringCaseAndOuterSpaces`; índice único de nombre normalizado |
| `PlayerRepository` | `save`, `findById`, `findByTeamId`; excluir jugadores de otros equipos |
| `RegistrationRepository` | `save`, `existsByTournamentIdAndTeamId`, `findByTournamentId`; restricción única torneo/equipo |

Las restricciones únicas de base de datos cubren también solicitudes simultáneas. Una infracción de integridad se traduce a HTTP 409.

## Servicios con Mockito

Todos los servicios admiten inyección por constructor de repositorios simulados. `RegistrationService` admite además un `Clock` fijo para comprobar `registeredAt` sin depender del reloj real.

| Servicio | Operaciones y comportamiento esperado |
| --- | --- |
| `TournamentService` | `create` guarda un torneo planificado; `findById` y `findAll` consultan; `activate` y `finish` cambian estado y guardan; ID ausente produce `ResourceNotFoundException` |
| `TeamService` | `create` comprueba duplicado mediante repositorio antes de guardar; `findById` y `findAll` consultan; duplicado produce `BusinessConflictException` |
| `PlayerService` | `register` verifica existencia del equipo y guarda; `findById` busca; `findByTeamId` verifica equipo y filtra; equipo o jugador ausente produce `ResourceNotFoundException` |
| `RegistrationService` | `register` busca torneo y equipo, comprueba `PLANNED` y duplicado, obtiene hora del `Clock` y guarda; `findByTournamentId` verifica torneo y lista; ausencias producen 404 en la capa HTTP y conflictos producen 409 |

Escenarios negativos útiles: torneo inexistente, equipo inexistente, equipo duplicado con distintas mayúsculas y espacios exteriores, jugador fuera del rango de edad, inscripción repetida, inscripción en torneo activo o terminado, activación doble y finalización antes de activar. Verificar también que `save` **no** se invoque cuando falle una precondición.

## Comandos

`./mvnw test` ejecuta las pruebas; `./mvnw verify` genera el reporte JaCoCo en `target/site/jacoco/index.html`. No hay una meta de cobertura artificial configurada.
