# CampusPass

> Full-stack university event management & ticketing platform

A production-grade backend built with Spring Boot 3, PostgreSQL, and JWT authentication. Supports the full lifecycle of campus events — from club creation and event approval to seat booking, QR-code ticket generation, and attendee check-in.

---

## Features

- **JWT Authentication** — stateless login/register flow with BCrypt password hashing
- **Role-Based Access Control** — three roles (Student, Organizer, Admin) enforced via `@PreAuthorize` and Spring SecurityContext
- **Event Approval Workflow** — organizers create events (PENDING), admins approve/reject (APPROVED/REJECTED)
- **Atomic Seat Booking** — conditional JPQL `UPDATE` increments `booked` only when `booked < capacity`, preventing race conditions without optimistic lock storms
- **QR-Code Ticketing** — ZXing generates a scannable QR code per registration; organizers scan to check attendees in
- **Club Membership Enforcement** — check-in authorization validated against club membership, not just role
- **Cancellation Flow** — students can cancel registrations; seat count decremented atomically

---

## Tech Stack

| Layer | Technology |
|---|---|
| Runtime | Java 17 |
| Framework | Spring Boot 3.x |
| Security | Spring Security + JJWT |
| Persistence | Spring Data JPA + Hibernate |
| Database | PostgreSQL |
| QR Codes | ZXing (Google) |
| Build | Gradle |
| Planned Frontend | React |

---

## Architecture

```
Controller → Service → Repository → Entity
```

- **Controllers** — thin, handle HTTP mapping and `@PreAuthorize` guards
- **Services** — all business logic; SecurityContext access isolated to per-method calls (never field-level)
- **Repositories** — Spring Data JPA with custom `@Query` for atomic operations
- **DTOs** — strict request/response separation; entities never exposed directly

---

## API Overview

### Auth — `/api/auth`
| Method | Endpoint | Role | Description |
|---|---|---|---|
| POST | `/register` | Public | Register new student account |
| POST | `/login` | Public | Returns JWT token |

### Events — `/api/event`
| Method | Endpoint | Role | Description |
|---|---|---|---|
| POST | `/create` | ORGANIZER | Create event (status: PENDING) |
| PUT | `/{id}/approval` | ADMIN | Approve or reject event |
| GET | `/getappr` | Any authenticated | Get events filtered by role |
| GET | `?clubID=` | Any authenticated | Get all events for a club |
| GET | `/{eventId}/fetchticket` | STUDENT | Download QR ticket as PNG |

### Registrations — `/api/registration`
| Method | Endpoint | Role | Description |
|---|---|---|---|
| PUT | `/reserve` | STUDENT | Book a seat |
| PUT | `/{ticketid}/checkin` | ORGANIZER | Mark attendee present |
| GET | `/registered` | STUDENT | List my registrations |
| DELETE | `/cancel` | STUDENT | Cancel a registration |

### Clubs — `/api/club`
| Method | Endpoint | Description |
|---|---|---|
| POST | `/createclub` | Create a new club |
| GET | `/get?clubID=` | Fetch club details |

---

## Database Schema (key design decisions)

- `Registration` has a composite unique constraint on `(User_ID, Event_ID)` — prevents duplicate bookings at DB level
- `Event` uses `@Version` for optimistic locking
- `Registration_ID` and `User_ID + Event_ID` are both indexed for fast lookups
- `Membership` composite key enforces club membership integrity

---

## Getting Started

### Prerequisites
- Java 17+
- PostgreSQL (database: `CEM`)
- Gradle

### Configuration

Edit `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/CEM
    username: your_username
    password: your_password

jwt:
  secret: your_base64_secret_min_32_chars
  expiration: 86400000   # 1 day in ms
```

### Run

```bash
./gradlew bootRun
```

Hibernate will auto-create/update tables on first run (`ddl-auto: update`).

---

## Security Notes

- All non-auth endpoints require a valid JWT: `Authorization: Bearer <token>`
- Roles are read exclusively from the SecurityContext — never from client input
- `/api/auth/**` and Swagger UI are the only public routes

---

## Planned

- React frontend (student dashboard, organizer panel, admin console)
- Email notifications on approval/rejection
- Pagination on event listing endpoints
- Integration test suite (JUnit 5 + H2)
- Docker Compose for local dev

---

## Project Structure

```
src/main/java/com/campusEvent/campus_event/
├── controller/         # AuthController, EventController, RegistrationController, ClubController
├── service/            # Business logic + JWT utilities
│   └── jwt/            # JwtService, AuthFilter, JwtProperties
├── entity/             # User, Event, Club + enums
├── relations/          # Registration, Membership (join tables)
├── repository/         # Spring Data JPA interfaces
├── dto/                # Request/response DTOs
└── security/           # SecurityConfig
```# CampusPass

> Full-stack university event management & ticketing platform

A production-grade backend built with Spring Boot 3, PostgreSQL, and JWT authentication. Supports the full lifecycle of campus events — from club creation and event approval to seat booking, QR-code ticket generation, and attendee check-in.

---

## Features

- **JWT Authentication** — stateless login/register flow with BCrypt password hashing
- **Role-Based Access Control** — three roles (Student, Organizer, Admin) enforced via `@PreAuthorize` and Spring SecurityContext
- **Event Approval Workflow** — organizers create events (PENDING), admins approve/reject (APPROVED/REJECTED)
- **Atomic Seat Booking** — conditional JPQL `UPDATE` increments `booked` only when `booked < capacity`, preventing race conditions without optimistic lock storms
- **QR-Code Ticketing** — ZXing generates a scannable QR code per registration; organizers scan to check attendees in
- **Club Membership Enforcement** — check-in authorization validated against club membership, not just role
- **Cancellation Flow** — students can cancel registrations; seat count decremented atomically

---

## Tech Stack

| Layer | Technology |
|---|---|
| Runtime | Java 17 |
| Framework | Spring Boot 3.x |
| Security | Spring Security + JJWT |
| Persistence | Spring Data JPA + Hibernate |
| Database | PostgreSQL |
| QR Codes | ZXing (Google) |
| Build | Gradle |
| Planned Frontend | React |

---

## Architecture

```
Controller → Service → Repository → Entity
```

- **Controllers** — thin, handle HTTP mapping and `@PreAuthorize` guards
- **Services** — all business logic; SecurityContext access isolated to per-method calls (never field-level)
- **Repositories** — Spring Data JPA with custom `@Query` for atomic operations
- **DTOs** — strict request/response separation; entities never exposed directly

---

## API Overview

### Auth — `/api/auth`
| Method | Endpoint | Role | Description |
|---|---|---|---|
| POST | `/register` | Public | Register new student account |
| POST | `/login` | Public | Returns JWT token |

### Events — `/api/event`
| Method | Endpoint | Role | Description |
|---|---|---|---|
| POST | `/create` | ORGANIZER | Create event (status: PENDING) |
| PUT | `/{id}/approval` | ADMIN | Approve or reject event |
| GET | `/getappr` | Any authenticated | Get events filtered by role |
| GET | `?clubID=` | Any authenticated | Get all events for a club |
| GET | `/{eventId}/fetchticket` | STUDENT | Download QR ticket as PNG |

### Registrations — `/api/registration`
| Method | Endpoint | Role | Description |
|---|---|---|---|
| PUT | `/reserve` | STUDENT | Book a seat |
| PUT | `/{ticketid}/checkin` | ORGANIZER | Mark attendee present |
| GET | `/registered` | STUDENT | List my registrations |
| DELETE | `/cancel` | STUDENT | Cancel a registration |

### Clubs — `/api/club`
| Method | Endpoint | Description |
|---|---|---|
| POST | `/createclub` | Create a new club |
| GET | `/get?clubID=` | Fetch club details |

---

## Database Schema (key design decisions)

- `Registration` has a composite unique constraint on `(User_ID, Event_ID)` — prevents duplicate bookings at DB level
- `Event` uses `@Version` for optimistic locking
- `Registration_ID` and `User_ID + Event_ID` are both indexed for fast lookups
- `Membership` composite key enforces club membership integrity

---

## Getting Started

### Prerequisites
- Java 17+
- PostgreSQL (database: `CEM`)
- Gradle

### Configuration

Edit `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/CEM
    username: your_username
    password: your_password

jwt:
  secret: your_base64_secret_min_32_chars
  expiration: 86400000   # 1 day in ms
```

### Run

```bash
./gradlew bootRun
```

Hibernate will auto-create/update tables on first run (`ddl-auto: update`).

---

## Security Notes

- All non-auth endpoints require a valid JWT: `Authorization: Bearer <token>`
- Roles are read exclusively from the SecurityContext — never from client input
- `/api/auth/**` and Swagger UI are the only public routes

---

## Planned

- React frontend (student dashboard, organizer panel, admin console)
- Email notifications on approval/rejection
- Pagination on event listing endpoints
- Integration test suite (JUnit 5 + H2)
- Docker Compose for local dev

---

## Project Structure

```
src/main/java/com/campusEvent/campus_event/
├── controller/         # AuthController, EventController, RegistrationController, ClubController
├── service/            # Business logic + JWT utilities
│   └── jwt/            # JwtService, AuthFilter, JwtProperties
├── entity/             # User, Event, Club + enums
├── relations/          # Registration, Membership (join tables)
├── repository/         # Spring Data JPA interfaces
├── dto/                # Request/response DTOs
└── security/           # SecurityConfig
```
