-- 1
SELECT *
FROM student
WHERE age BETWEEN 19 AND 22;
-- 2
SELECT name
FROM student;
-- 3
SELECT name
FROM student
WHERE name LIKE ('%o%')
   OR name LIKE ('%O%');
-- 4
SELECT *
FROM student
WHERE age < student.id;
-- 5
SELECT *
FROM student
ORDER BY age;