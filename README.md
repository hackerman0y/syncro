# Syncro

**Syncro** is a full-stack team collaboration platform for managing shared teams, tasks, and meetings — built to practice real-world Spring Boot + Angular integration, from JWT authentication to a fully custom UI.

![Syncro login screen](docs/screenshot-login.png)

## What it does

- Create an account and log in securely (JWT-based auth)
- Create a team — you're automatically set as its **owner**
- Invite teammates to a team by email
- Create tasks scoped to a team
- Schedule meetings scoped to a team
- View a team's members, tasks, and meetings all in one place

## Tech Stack

**Backend** — `/backend`
- Java 25, Spring Boot 4
- Spring Data JPA / Hibernate
- Spring Security + JJWT (JSON Web Tokens)
- MySQL 8
- Maven

**Frontend** — `/frontend`
- Angular 22 (standalone components, Signals, zoneless change detection)
- Angular Material
- RxJS
- Custom design system (CSS variables, Space Grotesk + Inter typography)

## Architecture
Angular (localhost:4200)
│ HTTP + JWT (Authorization: Bearer <token>)
▼
Spring Boot REST API (localhost:8080)
│
▼
MySQL


The frontend and backend are two independent servers communicating over HTTP — a standard SPA + REST API setup. Every authenticated request from Angular automatically carries a JWT (attached by an HTTP interceptor), which the backend validates on every request via a custom Spring Security filter.

## Data model

| Entity | Description |
|---|---|
| `User` | A registered account |
| `Team` | A group with an owner |
| `TeamMember` | Join table linking users to teams, with a role (`OWNER` / `MEMBER`) |
| `Task` | Belongs to a team, optionally assigned to a user |
| `Meeting` | Scheduled event belonging to a team |
| `MeetingAttendee` | Join table linking users to meetings |

## Getting Started

### Prerequisites
- Java 21+, Maven
- Node.js 22+
- MySQL 8+

### 1. Backend

```bash
cd backend

# create the database
mysql -u root -p -e "CREATE DATABASE syncro_db;"

# set credentials (or edit application.properties directly for local dev)
export DB_USERNAME=root
export DB_PASSWORD=your_password

mvn spring-boot:run
```

API runs at `http://localhost:8080`.

### 2. Frontend

```bash
cd frontend
npm install
ng serve
```

App runs at `http://localhost:4200`.

## API Overview

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/register` | Create an account |
| POST | `/api/auth/login` | Log in, receive a JWT |
| GET | `/api/users/me` | Get the current logged-in user |
| GET / POST | `/api/teams` | List / create teams |
| GET | `/api/teams/{id}` | Get a single team |
| GET / POST | `/api/tasks` | List / create tasks |
| GET | `/api/tasks/team/{teamId}` | Tasks scoped to a team |
| GET / POST | `/api/meetings` | List / create meetings |
| GET | `/api/meetings/team/{teamId}` | Meetings scoped to a team |
| GET | `/api/team-members/team/{teamId}` | Members of a team |
| POST | `/api/team-members/invite` | Invite a user to a team by email |

All routes except `/api/auth/**` require a valid `Authorization: Bearer <token>` header.

## Frontend structure
frontend/src/app/
├── layout/ → shared sidebar shell (wraps all authenticated pages)
├── pages/
│ ├── login/
│ ├── register/
│ ├── dashboard/
│ ├── teams/
│ ├── team-detail/
│ ├── tasks/
│ └── meetings/
├── services/ → HttpClient wrappers per resource
├── guards/ → route protection
└── interceptors/ → auto-attaches JWT to every request

## Design system

- **Ink** `#1B2432` — primary text/brand color
- **Accent** `#FF6B4A` — coral, used for primary actions
- **Space Grotesk** — headings/wordmark
- **Inter** — body text

## Roadmap

- [ ] Assign tasks to specific team members
- [ ] Meeting attendees (RSVP)
- [ ] Task status updates from the UI
- [ ] Deployment (live demo link)

---

Built by [Yousef Hassan](https://github.com/hackerman0y) while learning Spring Boot + Angular.
