--liquibase formatted sql

--changeSet sergey-filimonov : 1
CREATE INDEX faculty_name_color_index ON faculty (color, "name");
