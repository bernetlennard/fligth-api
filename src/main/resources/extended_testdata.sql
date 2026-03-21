-- 1. Tabellen leeren und IDs zurücksetzen (Start mit sauberer DB)
TRUNCATE TABLE booking, flight, userdata RESTART IDENTITY CASCADE;

-- 2. Test-User anlegen
INSERT INTO userdata (name, email) VALUES
                                       ('Felix Huber', 'felix.huber@example.com', '123456789'),
                                       ('Anna Muster', 'anna.muster@example.com', '123456789');

-- 3. Test-Flüge anlegen (Grössere Auswahl für diverse Filter-Tests)
INSERT INTO flight (departure_location, arrival_location, departure_date, departure_time, airline, price, available_tickets)
VALUES
    -- Flüge von Zürich
    ('Zurich', 'London', '2026-05-10', '08:30:00', 'Swiss', 250.00, 50),
    ('Zurich', 'Berlin', '2026-05-12', '14:15:00', 'Lufthansa', 180.50, 2),
    ('Zurich', 'Paris', '2026-05-15', '09:00:00', 'Air France', 120.00, 15),
    ('Zurich', 'New York', '2026-06-01', '10:45:00', 'Swiss', 850.00, 120),
    ('Zurich', 'London', '2026-05-10', '18:00:00', 'British Airways', 190.00, 40),

    -- Flüge von Genf
    ('Geneva', 'Madrid', '2026-05-20', '07:30:00', 'Iberia', 145.50, 35),
    ('Geneva', 'Rome', '2026-05-22', '11:20:00', 'Alitalia', 95.00, 0), -- Ausgebuchter Flug für Tests!
    ('Geneva', 'Berlin', '2026-05-25', '16:40:00', 'EasyJet', 75.00, 80),

    -- Flüge von Basel
    ('Basel', 'Barcelona', '2026-06-10', '06:15:00', 'EasyJet', 60.00, 10),
    ('Basel', 'Amsterdam', '2026-06-12', '08:50:00', 'KLM', 210.00, 25),

    -- Rückflüge / Andere Strecken
    ('London', 'Zurich', '2026-05-17', '19:30:00', 'Swiss', 260.00, 45),
    ('Berlin', 'Zurich', '2026-05-19', '09:15:00', 'Lufthansa', 175.00, 5),
    ('New York', 'Zurich', '2026-06-15', '22:00:00', 'Swiss', 790.00, 90),
    ('Paris', 'Zurich', '2026-05-18', '14:00:00', 'Air France', 130.00, 20),
    ('Madrid', 'Geneva', '2026-05-27', '15:45:00', 'Iberia', 150.00, 12);