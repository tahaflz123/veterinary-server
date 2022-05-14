INSERT INTO users (id, name, surname, phone_number, email, password, role, created_date)
VALUES (1, 'wolf', 'larsen', '123123', 'wolf_larsen@veterinary.com', '$2a$10$JdMSNoDmdvZ.sB91Cn8QrOuLn86GQHhdAGSTWVbZOc1INzx.KxnTO','ADMIN', now());

INSERT INTO users (id, name, surname, phone_number, email, password, role, created_date)
VALUES (2, 'taha', 'filiz', '+90530000000', 'taha.433@outlook.com', '$2a$10$JdMSNoDmdvZ.sB91Cn8QrOuLn86GQHhdAGSTWVbZOc1INzx.KxnTO','USER', now());

INSERT INTO animals (id, animal_type, breed, age, gender, about, created_date, user_id)
VALUES (3, 'CAT', 'Van cat', 8, 'MALE', 'about van cat', now(), 2);

INSERT INTO animals (id, animal_type, breed, age, gender, about, created_date, user_id)
VALUES (4, 'DOG', 'dog breed', 7, 'FEMALE', 'about female dog type adfasdfasdfasdfas', now(), 1);

INSERT INTO animals (id, animal_type, breed, age, gender, about, created_date, user_id)
VALUES (5, 'BIRD', 'parrot bird breed', 4, 'MALE', 'this parrot very agressive', now(), 2);

INSERT INTO animals (id, animal_type, breed, age, gender, about, created_date, user_id)
VALUES (6, 'FISH', 'Japanese', 1, 'NONE', 'I didnt understand the gender of this fish', now(), 2);

INSERT INTO animals (id, animal_type, breed, age, gender, about, created_date, user_id)
VALUES (7, 'DOG', 'Pitbull', 7, 'MALE', 'Its very agressive dog, take care', now(), 1);

INSERT INTO animals (id, animal_type, breed, age, gender, about, created_date, user_id)
VALUES (8, 'CAT', 'Tekir', 1, 'MALE', 'Left back leg is broken', now(), 1);

INSERT INTO animals (id, animal_type, breed, age, gender, about, created_date, user_id)
VALUES (9, 'DOG', 'Golden', 2, 'FEMALE', 'sick', now(), 1);

INSERT INTO animals (id, animal_type, breed, age, gender, about, created_date, user_id)
VALUES (10, 'BIRD', 'Carrot', 7, 'MALE', 'He talks to much', now(), 1);