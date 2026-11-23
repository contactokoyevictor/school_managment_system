# School Management System

A **Java Spring MVC** school administration platform for running academic operations, people, exams, and school finance from role-based dashboards.

Built for multi-branch school environments with dedicated experiences for administrators, principals, teachers, students, guardians, and cashiers.

## What it covers

| Domain | Capabilities |
|--------|----------------|
| **Organisation** | Companies, branches, departments, designations, employees |
| **Academics** | Academic years, terms, classes, sections, subjects, class routines |
| **Students & guardians** | Student records, guardians, behavioural traits |
| **Attendance** | Student daily attendance, subject attendance, employee attendance |
| **Exams & grading** | Exams, marks, grades, class positions, terminal comments |
| **HR & payroll** | Employee awards, bank details, salary payment details |
| **Finance** | Invoices, school fees, bank accounts, account transactions, purchase orders, utility bills, miscellaneous expenses |
| **Scratch cards** | Card types, unit pricing, sales orders, student card assignment |
| **Communications** | Noticeboard, messages / message threads |
| **Audit** | Activity logging via audit vault |

## Architecture

- **Spring MVC** (`@EnableWebMvc`) with JSP views under `WEB-INF/views`
- **Role controllers** — `AdminController`, `PrincipalController`, `TeacherController`, `StudentController`, `GuardianController`, `CashierController`, plus login / modal flows
- **JPA persistence** — EclipseLink entities and controllers over MySQL (`sivotek_school_management_system_v1_2`)
- **CRUD / identity helpers** — shared data-access and ID generation utilities
- **Security utilities** — encryption helpers for sensitive URL / payload handling
- **GlassFish / Java EE** deployment resources under `setup/`
- **Apache Ant** + **NetBeans** project layout (`build.xml`, `nbproject/`)

```
src/java/com/sivotek/school_management_system/
├── app/controllers/     Role-based Spring MVC controllers
├── app/config/          Web MVC configuration & session listener
├── app/security/        Encryption helpers
├── app/crud/            Shared persistence helpers
├── entities/            JPA entities + controllers
web/WEB-INF/views/       JSP UI (login + admin dashboards/modules)
```

## Stack

- **Java** / **Spring MVC** / **JSP**
- **JPA** (EclipseLink) + **MySQL**
- **GlassFish** deployment descriptors
- **Apache Ant** / NetBeans

## Notes

Portfolio snapshot of an earlier school ERP-style web application. Database credentials belong in local server / JDBC configuration and should not be committed as secrets.
