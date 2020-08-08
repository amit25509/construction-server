
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
    SELECT occupation_name FROM OCCUPATION WHERE occupation_name = 'Architect'
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
SELECT * FROM (SELECT 5,'Plumber',10) AS tmp
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




INSERT INTO employee_data (`id`,`aadhar_back`,`aadhar_front`,`availability`,`experience`,`is_verified`,`job_start_date`,`per_day_charge`,`rating`,`occupation`) VALUES (1,'','',0,'2',0,NULL,NULL,NULL,1);
INSERT INTO employee_data (`id`,`aadhar_back`,`aadhar_front`,`availability`,`experience`,`is_verified`,`job_start_date`,`per_day_charge`,`rating`,`occupation`) VALUES (2,'','',0,'3',0,NULL,NULL,NULL,2);
INSERT INTO employee_data (`id`,`aadhar_back`,`aadhar_front`,`availability`,`experience`,`is_verified`,`job_start_date`,`per_day_charge`,`rating`,`occupation`) VALUES (3,'','',0,'5',0,NULL,NULL,NULL,4);
INSERT INTO employee_data (`id`,`aadhar_back`,`aadhar_front`,`availability`,`experience`,`is_verified`,`job_start_date`,`per_day_charge`,`rating`,`occupation`) VALUES (4,'','',0,'8',0,NULL,NULL,NULL,5);
INSERT INTO employee_data (`id`,`aadhar_back`,`aadhar_front`,`availability`,`experience`,`is_verified`,`job_start_date`,`per_day_charge`,`rating`,`occupation`) VALUES (5,'','',0,'6',0,NULL,NULL,NULL,6);
INSERT INTO employee_data (`id`,`aadhar_back`,`aadhar_front`,`availability`,`experience`,`is_verified`,`job_start_date`,`per_day_charge`,`rating`,`occupation`) VALUES (6,'','',0,'2',0,NULL,NULL,NULL,4);




INSERT INTO users (`id`,`age`,`created_date`,`dob`,`email`,`image`,`is_enabled`,`last_modified_by`,`last_modified_date`,`name`,`password`,`phone`,`username`,`employee_data`,`location`) VALUES (1,NULL,'2020-08-08 22:37:35',NULL,'johndoe1@gmail.com',NULL,1,'Jon Doe','2020-08-08 22:37:35','Jon Doe','$2a$10$hbhxwHcFhYa4nVGoHRkx6.7xLbTAUbJSnydC4dJvcabYiGco8wtMK',1,'1',NULL,1);
INSERT INTO users (`id`,`age`,`created_date`,`dob`,`email`,`image`,`is_enabled`,`last_modified_by`,`last_modified_date`,`name`,`password`,`phone`,`username`,`employee_data`,`location`) VALUES (2,NULL,'2020-08-08 22:38:37','2020-08-08 05:30:00','janedoe2@email.com',NULL,1,'Jane Doe2','2020-08-08 22:38:37','Jane Doe2','$2a$10$d1wd/lXw10MHdqkaDyFTu.cVplRvb2V4z6O9fikBy7fyrXbEqHySS',51,'51',1,2);
INSERT INTO users (`id`,`age`,`created_date`,`dob`,`email`,`image`,`is_enabled`,`last_modified_by`,`last_modified_date`,`name`,`password`,`phone`,`username`,`employee_data`,`location`) VALUES (3,NULL,'2020-08-08 22:42:37',NULL,'johndoe2@gmail.com',NULL,1,'Jon Doe2','2020-08-08 22:42:37','Jon Doe2','$2a$10$7NTk.kp/3AQMKwG4tdCmfOeu9LSB.z03bMzfCssSKMlZLnHamQwp2',2,'2',NULL,2);
INSERT INTO users (`id`,`age`,`created_date`,`dob`,`email`,`image`,`is_enabled`,`last_modified_by`,`last_modified_date`,`name`,`password`,`phone`,`username`,`employee_data`,`location`) VALUES (4,NULL,'2020-08-08 22:43:14',NULL,'johndoe3@gmail.com',NULL,1,'Jon Doe3','2020-08-08 22:43:14','Jon Doe3','$2a$10$P8zjGSqXSQiSSOF1.aHNdOOwRYCToGtFzgPhJhOYtrW2nZVb4wqtq',3,'3',NULL,3);
INSERT INTO users (`id`,`age`,`created_date`,`dob`,`email`,`image`,`is_enabled`,`last_modified_by`,`last_modified_date`,`name`,`password`,`phone`,`username`,`employee_data`,`location`) VALUES (5,NULL,'2020-08-08 22:43:29',NULL,'johndoe4@gmail.com',NULL,1,'Jon Doe4','2020-08-08 22:43:29','Jon Doe4','$2a$10$HSHNkLgUTgAZVZnTkNO0Suuu29sykuCLKwvmmjsStQhfXAiHjGI6S',4,'4',NULL,4);
INSERT INTO users (`id`,`age`,`created_date`,`dob`,`email`,`image`,`is_enabled`,`last_modified_by`,`last_modified_date`,`name`,`password`,`phone`,`username`,`employee_data`,`location`) VALUES (6,NULL,'2020-08-08 22:43:43',NULL,'johndoe5@gmail.com',NULL,1,'Jon Doe5','2020-08-08 22:43:43','Jon Doe5','$2a$10$LrIQb/J5.PGBFYlQ.LiXXupO1ryhYLi6EP2x4wUaY.rwZm3p1II16',5,'5',NULL,1);
INSERT INTO users (`id`,`age`,`created_date`,`dob`,`email`,`image`,`is_enabled`,`last_modified_by`,`last_modified_date`,`name`,`password`,`phone`,`username`,`employee_data`,`location`) VALUES (7,NULL,'2020-08-08 22:45:40','2020-08-07 05:30:00','janedoe@email.com',NULL,1,'Jane Doe2','2020-08-08 22:45:40','Jane Doe2','$2a$10$fAYhGZl2fRieqrLY.Fa9..xLQ5d2pHGmSvR..FRywZIstXBYQ9m3O',52,'52',2,2);
INSERT INTO users (`id`,`age`,`created_date`,`dob`,`email`,`image`,`is_enabled`,`last_modified_by`,`last_modified_date`,`name`,`password`,`phone`,`username`,`employee_data`,`location`) VALUES (8,NULL,'2020-08-08 22:46:21','2020-08-22 05:30:00','janedoe3@email.com',NULL,1,'Jane Doe3','2020-08-08 22:46:21','Jane Doe3','$2a$10$DjoItQwwP4bjQQrBk59h3e5SgAh5fIdiLFUlzpshcMnJqb3hjDv86',53,'53',3,3);
INSERT INTO users (`id`,`age`,`created_date`,`dob`,`email`,`image`,`is_enabled`,`last_modified_by`,`last_modified_date`,`name`,`password`,`phone`,`username`,`employee_data`,`location`) VALUES (9,NULL,'2020-08-08 22:47:03','2020-08-25 05:30:00','janedoe4@email.com',NULL,1,'Jane Doe4','2020-08-08 22:47:03','Jane Doe4','$2a$10$UipszcnN/VxI6Jxt9tmp7uHpTsZ/WnMVilecsdwQxtBh4rt3yTZHC',54,'54',4,3);
INSERT INTO users (`id`,`age`,`created_date`,`dob`,`email`,`image`,`is_enabled`,`last_modified_by`,`last_modified_date`,`name`,`password`,`phone`,`username`,`employee_data`,`location`) VALUES (10,NULL,'2020-08-08 22:47:47','2020-08-01 05:30:00','janedoe5@email.com',NULL,1,'Jane Doe5','2020-08-08 22:47:47','Jane Doe5','$2a$10$0eyR0/MtCNkRkLhxgwQnKO2zyYLy.ZD.wSqAGUTlhtSM1fvmUO7Cm',55,'55',5,4);
INSERT INTO users (`id`,`age`,`created_date`,`dob`,`email`,`image`,`is_enabled`,`last_modified_by`,`last_modified_date`,`name`,`password`,`phone`,`username`,`employee_data`,`location`) VALUES (11,NULL,'2020-08-09 01:20:10',NULL,'admin@email.com',NULL,1,'admin','2020-08-09 01:20:10','admin','$2a$10$Eaa5fcKxL/zfwQGhvdAX5OBzwtEvfl6mO5Nv8hNnwQ9mpqXK.J35.',96,'96',NULL,4);
INSERT INTO users (`id`,`age`,`created_date`,`dob`,`email`,`image`,`is_enabled`,`last_modified_by`,`last_modified_date`,`name`,`password`,`phone`,`username`,`employee_data`,`location`) VALUES (12,NULL,'2020-08-09 01:25:02','2020-01-01 05:30:00','admin@12',NULL,1,'Admin','2020-08-09 01:25:02','Admin','$2a$10$nMaLT2fYTjKSkqEa5Uo4HeKuVnUm0C1Cs9hqh7viIBz2Z3A1QJH2i',100,'100',6,3);






INSERT INTO user_roles (`user_id`,`role_id`) VALUES (11,1);
INSERT INTO user_roles (`user_id`,`role_id`) VALUES (12,1);
INSERT INTO user_roles (`user_id`,`role_id`) VALUES (2,2);
INSERT INTO user_roles (`user_id`,`role_id`) VALUES (7,2);
INSERT INTO user_roles (`user_id`,`role_id`) VALUES (8,2);
INSERT INTO user_roles (`user_id`,`role_id`) VALUES (9,2);
INSERT INTO user_roles (`user_id`,`role_id`) VALUES (10,2);
INSERT INTO user_roles (`user_id`,`role_id`) VALUES (1,3);
INSERT INTO user_roles (`user_id`,`role_id`) VALUES (3,3);
INSERT INTO user_roles (`user_id`,`role_id`) VALUES (4,3);
INSERT INTO user_roles (`user_id`,`role_id`) VALUES (5,3);
INSERT INTO user_roles (`user_id`,`role_id`) VALUES (6,3);






INSERT INTO bookings (`booking_id`,`booking_from`,`booking_to`,`days_worked`,`status`,`employee`,`rating`,`user`) VALUES (1,'2020-08-01','2020-08-16',15,'Pending',2,NULL,1);
INSERT INTO bookings (`booking_id`,`booking_from`,`booking_to`,`days_worked`,`status`,`employee`,`rating`,`user`) VALUES (2,'2020-08-01','2020-08-16',15,'Pending',2,NULL,1);
INSERT INTO bookings (`booking_id`,`booking_from`,`booking_to`,`days_worked`,`status`,`employee`,`rating`,`user`) VALUES (3,'2020-08-01','2020-08-16',15,'Pending',2,NULL,1);
INSERT INTO bookings (`booking_id`,`booking_from`,`booking_to`,`days_worked`,`status`,`employee`,`rating`,`user`) VALUES (4,'2020-08-14','2020-08-19',5,'Pending',8,NULL,6);








INSERT INTO commissions (`commission_id`,`commission_status`,`due_commission_amount`,`paid_commission_amount`,`total_commission_amount`,`booking_id`) VALUES (1,'pending',0,0,0,1);
INSERT INTO commissions (`commission_id`,`commission_status`,`due_commission_amount`,`paid_commission_amount`,`total_commission_amount`,`booking_id`) VALUES (2,'pending',0,0,0,2);
INSERT INTO commissions (`commission_id`,`commission_status`,`due_commission_amount`,`paid_commission_amount`,`total_commission_amount`,`booking_id`) VALUES (3,'pending',0,0,0,3);
INSERT INTO commissions (`commission_id`,`commission_status`,`due_commission_amount`,`paid_commission_amount`,`total_commission_amount`,`booking_id`) VALUES (4,'pending',0,0,0,4);





 

