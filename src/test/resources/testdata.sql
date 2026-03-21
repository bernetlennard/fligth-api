-- 1. Tabellen leeren und IDs zurücksetzen (Start mit leerer DB)
TRUNCATE TABLE booking, flight, "user" RESTART IDENTITY CASCADE;

-- 2. Test-User "Felix Huber" anlegen (bekommt automatisch ID 1)
INSERT INTO "user" (name, email) VALUES ('Felix Huber', 'felix.huber@example.com');

-- 3. Test-Flüge anlegen (bekommen automatisch ID 1 und 2)
INSERT INTO flight (departure_location, arrival_location, departure_date, departure_time, airline, price, available_tickets)
VALUES
    ('Zurich', 'London', '2026-05-10', '08:30:00', 'Swiss', 250.00, 50),
    ('Zurich', 'Berlin', '2026-05-12', '14:15:00', 'Lufthansa', 180.50, 2);
