--liquibase formatted sql

--changeSet Sergey Filimonoff : 1
CREATE INDEX student_name_index ON student ("name");
