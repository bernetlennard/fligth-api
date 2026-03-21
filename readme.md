# Flight Booking System - Backend API

Dieses Projekt beinhaltet das Backend für das Flugbuchungssystem. Es wurde mit Spring Boot und PostgreSQL umgesetzt.

## 1. Verwendete Software & Versionen
- **Java:** Version 21
- **Framework:** Spring Boot mit Spring WebMVC und Spring Data JPA
- **Datenbank:** Aktueller PostgreSQL
- **Build-Management:** Maven
- **Weitere Tools:** Lombok

## 2. Datenbank Setup
Bevor die Anwendung gestartet werden kann, muss die PostgreSQL-Datenbank lokal eingerichtet werden.

**Datenbankverbindung / Logins:**
Die Datenbankverbindung ist konfiguriert in `src/main/resources/application.properties`

**Schritt-für-Schritt Einrichtung:**
1. Stelle sicher, dass der PostgreSQL-Server läuft.
2. Führe das Skript `src/main/resources/Database_DDL.sql` auf dem PostgreSQL-Server aus.
    - Erstellt die Datenbank `FLIGHT`
    - Erstellt die benötigten Tabellen (`userdata`, `flight`, `booking`) mit allen Relationen.
3. Um die Datenbank mit umfangreichen Testdaten zu befüllen, führe das Skript `src/main/resources/extended_testdata.sql` aus.

## 3. Anwendung starten
Die Anwendung kann auf zwei Arten gestartet werden:

**Option A: Über die IDE (z.B. IntelliJ IDEA)**
Führe die Hauptklasse `src/main/java/com/teko/fligthapi/FligthApiApplication.java` aus.

**Option B: Über Maven (Kommandozeile)**
Navigiere im Terminal ins Root-Verzeichnis des Projekts und führe folgenden Befehl aus:
```bash
  ./mvnw spring-boot:run
```

## 4. Testing
