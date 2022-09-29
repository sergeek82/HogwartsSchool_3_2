--liquibase formatted sql

--changeset sergey:1

CREATE INDEX faculty_name_color_index ON faculty (color, "name")
