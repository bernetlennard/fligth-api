-- Erstelle die Datenbank
CREATE DATABASE "FLIGHT"

-- 1. Tabelle für User erstellen (in Anführungszeichen wegen reserviertem Schlüsselwort)
CREATE TABLE userdata (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255),
                          email VARCHAR(255) UNIQUE,
                          password VARCHAR(255)
);

-- 2. Tabelle für Flüge erstellen
CREATE TABLE flight (
                        id BIGSERIAL PRIMARY KEY,
                        departure_location VARCHAR(255),
                        arrival_location VARCHAR(255),
                        departure_date DATE,
                        departure_time TIME,
                        airline VARCHAR(255),
                        price NUMERIC(10, 2),
                        available_tickets INTEGER
);

-- 3. Tabelle für Buchungen erstellen
CREATE TABLE booking (
                         id BIGSERIAL PRIMARY KEY,
                         user_id BIGINT NOT NULL,
                         flight_id BIGINT NOT NULL,
                         booking_date TIMESTAMP,

                         CONSTRAINT fk_booking_user
                             FOREIGN KEY (user_id) REFERENCES userdata (id) ON DELETE CASCADE,

                         CONSTRAINT fk_booking_flight
                             FOREIGN KEY (flight_id) REFERENCES flight (id) ON DELETE CASCADE
);