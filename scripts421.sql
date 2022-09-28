-- Task 1
ALTER TABLE student
    ADD CONSTRAINT age_constraint CHECK ( age >= 16 );
-- Task 2
ALTER TABLE student
    ALTER COLUMN "name" SET NOT NULL;
ALTER TABLE student
    ADD CONSTRAINT name_unique UNIQUE ("name");
-- Task 3
ALTER TABLE faculty
    ADD CONSTRAINT name_color_unique UNIQUE ("name", color);
-- Task 4
ALTER TABLE student
    ALTER COLUMN age SET DEFAULT 20;
