# Supermarket Shopping List System Backend

**Student:** Musana Jean Bosco
**Student ID:** 27133
**Project:** Supermarket Shopping List System
**Course:** INSY 8322 Web Technology and Internet, Mid-Term Examination (AUCA)

Spring Boot 3.4.5, Java 17, Spring Data JPA and PostgreSQL.

## Run locally
1. Install JDK 17+ and Maven.
2. Create the database in PostgreSQL:
   ```sql
   CREATE DATABASE supermarket_db;
   ```
3. Set the database credentials with environment variables `DB_USERNAME` (default `postgres`) and `DB_PASSWORD` (default `postgres`), or edit `src/main/resources/application.properties`.
4. Run `mvn spring-boot:run`.
5. API base URL: `http://localhost:8083`.

Hibernate creates/updates tables from the entities (`ddl-auto=update`) and SQL statements are logged (`show-sql=true`).

## Entity relationships
- `Attendant` belongs to one optional `Till`.
- `Attendant` has many `Department` records through `attendant_department`.
- `ShoppingList` belongs to one `Shopper` and one `Attendant`.
- `PurchasedItem` belongs to one `ShoppingList`.
- `Department.attendants` is ignored in JSON to prevent a circular response.

## Main endpoints

| Requirement | Method and endpoint |
|---|---|
| A2 | CRUD under `/api/departments`, `/api/tills`, `/api/attendants`, `/api/shoppers`, `/api/shopping-lists` using `/save`, `/all`, `/{id}`, `/update/{id}`, `/delete/{id}` |
| B1 | `GET /api/shoppers/by-last-name?lastName=musana` |
| B2 | `GET /api/shopping-lists/by-status?status=DRAFTED` |
| B3 | `GET /api/shopping-lists/between?start=2026-10-01&end=2026-10-31` |
| C1 | `GET /api/attendants/by-department?name=produce` |
| C2 | `GET /api/tills/free` |
| C3 | `GET /api/departments/unused` |
| C4 | `GET /api/shoppers/of-attendant/{attendantId}` |
| D1 | `POST /api/shopping-lists/save` |
| D2 | `PATCH /api/shopping-lists/{id}/status?status=SUBMITTED` |
| D3 | `PUT /api/attendants/{attendantId}/departments/{departmentId}` |
| D4 | `PATCH /api/attendants/{attendantId}/till?tillNumber=4` |
| E1 | `POST /api/purchased-items/save?shoppingListId={uuid}` |
| E2 | `GET /api/purchased-items/shopper/{loyaltyCard}` |
| E3 | `DELETE /api/purchased-items/delete/{id}` and protected shopping-list deletion |
| F1 | `GET /api/shopping-lists/stats/by-status` |
| F2 | `PATCH /api/shopping-lists/cancel-day?attendantId={uuid}&date=2026-10-20` |
| Bonus | `GET /api/dashboard/summary`; `GET /api/shopping-lists/page?page=0&size=5&sort=shoppingDate,desc` |

## Seed data
Enter the data through your own `/save` endpoints. Notes: D1 rejects dates before today, so use dates from today onward; every new list starts as DRAFTED, so move lists to SUBMITTED, FULFILLED or ABANDONED with the D2 PATCH endpoint. Create:
- Departments: Produce, Dairy, Bakery, Butchery (leave Butchery unused).
- Tills 1–4 (leave till 4 free initially).
- Three attendants assigned to tills 1–3; assign two departments to one attendant and one department to another.
- Five shoppers, including two with last name `Musana`; use unique loyalty-card values.
- Ten lists across October and November 2026, covering all four statuses. Give one shopper at least three lists. Give the same attendant two lists on `2026-10-20`, one `ABANDONED`. Ensure at least two lists are `FULFILLED` before creating purchased items.

## Postman
Import `supermarket-shopping-list-system-midterm.postman_collection.json`. Replace UUID placeholders with IDs returned by your save endpoints. Test at least one successful and one failing case for each business-rule requirement where applicable.
