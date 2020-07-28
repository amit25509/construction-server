INSERT INTO roles(id,name)
SELECT * FROM (SELECT 1,'ROLE_ADMIN') AS tmp
WHERE NOT EXISTS (
    SELECT name FROM roles WHERE name = 'ROLE_ADMIN'
) LIMIT 1;
INSERT INTO roles(id,name)
SELECT * FROM (SELECT 2,'ROLE_EMPLOYEE') AS tmp
WHERE NOT EXISTS (
    SELECT name FROM roles WHERE name = 'ROLE_EMPLOYEE'
) LIMIT 1;
INSERT INTO roles(id,name)
SELECT * FROM (SELECT 3,'ROLE_USER') AS tmp
WHERE NOT EXISTS (
    SELECT name FROM roles WHERE name = 'ROLE_USER'
) LIMIT 1;


INSERT INTO LOCATIONS(location_id,location_name)
SELECT * FROM (SELECT 1,'Delhi') AS tmp
WHERE NOT EXISTS (
    SELECT location_name FROM locations WHERE location_name = 'Delhi'
) LIMIT 1;
INSERT INTO LOCATIONS(location_id,location_name)
SELECT * FROM (SELECT 2,'Pune') AS tmp
WHERE NOT EXISTS (
    SELECT location_name FROM locations WHERE location_name = 'Pune'
) LIMIT 1;
INSERT INTO LOCATIONS(location_id,location_name)
SELECT * FROM (SELECT 3,'Bangalore') AS tmp
WHERE NOT EXISTS (
    SELECT location_name FROM locations WHERE location_name = 'Bangalore'
) LIMIT 1;
INSERT INTO LOCATIONS(location_id,location_name)
SELECT * FROM (SELECT 4,'UP') AS tmp
WHERE NOT EXISTS (
    SELECT location_name FROM locations WHERE location_name = 'UP'
) LIMIT 1;