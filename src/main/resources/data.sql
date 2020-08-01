
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




INSERT INTO OCCUPATION(occupation_id,occupation_name,commission_rate)
SELECT * FROM (SELECT 1,'Architect',5) AS tmp
WHERE NOT EXISTS (
    SELECT occupation_name FROM OCCUPATION WHERE occupation_id = 'Architect'
) LIMIT 1;
INSERT INTO OCCUPATION(occupation_id,occupation_name,commission_rate)
SELECT * FROM (SELECT 2,'Labour',10) AS tmp
WHERE NOT EXISTS (
    SELECT occupation_name FROM OCCUPATION WHERE occupation_name = 'Labour'
) LIMIT 1;
INSERT INTO OCCUPATION(occupation_id,occupation_name,commission_rate)
SELECT * FROM (SELECT 3,'Engineer',5) AS tmp
WHERE NOT EXISTS (
    SELECT occupation_name FROM OCCUPATION WHERE occupation_name = 'Engineer'
) LIMIT 1;
INSERT INTO OCCUPATION(occupation_id,occupation_name,commission_rate)
SELECT * FROM (SELECT 4,'Electrician',10) AS tmp
WHERE NOT EXISTS (
    SELECT occupation_name FROM OCCUPATION WHERE occupation_name = 'Electrician'
) LIMIT 1;
INSERT INTO OCCUPATION(occupation_id,occupation_name,commission_rate)
SELECT * FROM (SELECT 5,'Plumber',5) AS tmp
WHERE NOT EXISTS (
    SELECT occupation_name FROM OCCUPATION WHERE occupation_name = 'Plumber'
) LIMIT 1;
INSERT INTO OCCUPATION(occupation_id,occupation_name,commission_rate)
SELECT * FROM (SELECT 6,'Mason',10) AS tmp
WHERE NOT EXISTS (
    SELECT occupation_name FROM OCCUPATION WHERE occupation_name = 'Mason'
) LIMIT 1;
INSERT INTO OCCUPATION(occupation_id,occupation_name,commission_rate)
SELECT * FROM (SELECT 7,'Carpenter',5) AS tmp
WHERE NOT EXISTS (
    SELECT occupation_name FROM OCCUPATION WHERE occupation_name = 'Carpenter'
) LIMIT 1;
INSERT INTO OCCUPATION(occupation_id,occupation_name,commission_rate)
SELECT * FROM (SELECT 8,'Painter',10) AS tmp
WHERE NOT EXISTS (
    SELECT occupation_name FROM OCCUPATION WHERE occupation_name = 'Painter'
) LIMIT 1;

