-- Clear existing data
DELETE FROM medical_treatment;
DELETE FROM visit;
DELETE FROM patient;
DELETE FROM doctor;
DELETE FROM address;

-- Reset auto-increment IDs
ALTER TABLE address ALTER COLUMN id RESTART WITH 1;
ALTER TABLE doctor ALTER COLUMN id RESTART WITH 1;
ALTER TABLE patient ALTER COLUMN id RESTART WITH 1;
ALTER TABLE medical_treatment ALTER COLUMN id RESTART WITH 1;
ALTER TABLE visit ALTER COLUMN id RESTART WITH 1;

-- Address entries
INSERT INTO address (address_line1, address_line2, city, postal_code)
VALUES
    ('Main St.', 'Suite 101', 'Springfield', '62-030'),
    ('Park Ave.', 'Apt. 202', 'New York', '10001'),
    ('Highland Rd.', NULL, 'Boston', '02134');

-- Doctor entries
INSERT INTO doctor (first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
VALUES
    ('John', 'Doe', '555-1234', 'johndoe@example.com', 'D123', 'CARDIOLOGY', 1),
    ('Alice', 'Smith', '555-5678', 'alicesmith@example.com', 'D124', 'DERMATOLOGY', 2),
    ('Robert', 'Brown', '555-8765', 'robertbrown@example.com', 'D125', 'PEDIATRICS', 3);

-- Patient entries
INSERT INTO patient (first_name, last_name, telephone_number, email, patient_number, date_of_birth, shoe_size, address_id)
VALUES
    ('Emily', 'Johnson', '555-4321', 'emilyj@example.com', 'P123', '1990-06-15', 38, 1),
    ('Michael', 'Taylor', '555-8765', 'michaelt@example.com', 'P124', '1985-02-20', 42, 2),
    ('Sophia', 'Williams', '555-6789', 'sophiaw@example.com', 'P125', '1995-11-30', 37, 3);

-- Visit entries
INSERT INTO visit (description, visit_date, doctor_id, patient_id)
VALUES
    ('Routine checkup', '2024-03-01 09:00:00', 1, 1),
    ('Skin treatment', '2024-03-02 11:00:00', 2, 2),
    ('Pediatric consultation', '2024-03-03 14:00:00', 3, 3);

-- MedicalTreatment entries
INSERT INTO medical_treatment (description, type, visit_id)
VALUES
    ('Appendectomy', 'SURGERY', 1),
    ('Physical Therapy', 'THERAPY', 2),
    ('Flu Vaccination', 'VACCINATION', 3);
