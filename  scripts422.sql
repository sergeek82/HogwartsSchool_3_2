CREATE TABLE person
(
    id          SERIAL PRIMARY KEY,
    "name"      VARCHAR(50),
    age         INTEGER,
    has_licence BOOLEAN DEFAULT FALSE,
    vehicle_id  INTEGER REFERENCES vehicle (id) NOT NULL
);

CREATE TABLE vehicle
(
    id     SERIAL PRIMARY KEY,
    "name" VARCHAR(50),
    model  VARCHAR(50),
    price  DECIMAL(8, 2)
);

SELECT s.name, s.age
FROM student s
         RIGHT JOIN avatar a ON s.id = a.student_id;

SELECT s.name, s.age, f.name
FROM student s
         FULL OUTER JOIN faculty f ON f.id = s.faculty_id;
