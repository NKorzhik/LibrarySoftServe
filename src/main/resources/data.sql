INSERT INTO user_account (name, surname, email, password, role, birthday, registration_date)
values ('NIKITA', 'Korzh', 'nikita@gmail.com', '$2a$12$/rvn3kHDg/BigpA99UIJY.fBX55kgTmrmktLNjkwGH0Py9bIzpS0u',
        'MANAGER', '09.02.2000', '05.05.2021'),
       ('Renat', 'Safarov', 'renat@gmail.com', '$2a$12$6ILIpUHQ45gxUfF4dJO7h.uhKSIiD9BCQQxuz7eW4SyIe.xnPz/Qm',
        'MANAGER', '28.04.2003', '07.08.2021'),
       ('Name1', 'Surname1', 'user1@gmail.com', '$2a$12$E8UBHb0RCiMOcyLd3JAECegfE6jGRSiBwuhLDh8tC8zEGrP476dzG',
        'READER', '10.02.2005', '12.10.2022'),
       ('Name2', 'Surname2', 'user2@gmail.com', '$2a$12$E8UBHb0RCiMOcyLd3JAECegfE6jGRSiBwuhLDh8tC8zEGrP476dzG',
        'READER', '01.03.1999', '05.05.2022'),
       ('Name3', 'Surname3', 'user3@gmail.com', '$2a$12$E8UBHb0RCiMOcyLd3JAECegfE6jGRSiBwuhLDh8tC8zEGrP476dzG',
        'READER', '01.04.1995', '04.07.2022');


insert into author (name, surname)
VALUES ('Author1', 'Author1'),
       ('Author2', 'Author2'),
       ('Author3', 'Author3'),
       ('Author4', 'Author4');

INSERT INTO book (title, author_id, co_author_id, genre, description, isbn)
VALUES ('book1', 1, 2, 'ROMAN', 'book1', 1234567891234),
       ('book2', 2, 1, 'NOVEL', 'book2', 2234567891234),
       ('book3', 3, 1, 'CLASSICS', 'book3', 3234567891234),
       ('book4', 1, 2, 'FANTASY', 'book4', 4234567891234),
       ('book5', 3, 2, 'HORROR', 'book5', 5234567891234),
       ('book6', 3, null, 'ROMAN', 'book6', 6234567891234),
       ('book7', 3, null, 'LITERARY_FICTION', 'book7', 7234567891234),
       ('book8', 2, 1, 'ROMAN', 'book8', 8234567891234),
       ('book9', 1, 3, 'ROMAN', 'book9', 9234567891234),
       ('book10', 2, 1, 'HORROR', 'book10', 1034567891234),
       ('book11', 2, 1, 'CLASSICS', 'book11', 1134567891234);

INSERT INTO quantity (book_id, type)
VALUES (1, 'FREE'),
       (1, 'FREE'),
       (1, 'FREE'),
       (1, 'FREE'),
       (1, 'FREE'),
       (1, 'FREE'),
       (1, 'FREE'),
       (1, 'READING'),
       (2, 'READING'),
       (2, 'FREE'),
       (2, 'FREE'),
       (2, 'FREE'),
       (2, 'FREE'),
       (3, 'FREE'),
       (3, 'FREE'),
       (3, 'FREE'),
       (3, 'READING'),
       (4, 'FREE'),
       (4, 'FREE'),
       (5, 'FREE'),
       (5, 'FREE'),
       (6, 'FREE'),
       (6, 'FREE'),
       (7, 'FREE'),
       (7, 'FREE'),
       (8, 'FREE'),
       (8, 'FREE'),
       (9, 'READING'),
       (9, 'READING'),
       (10, 'FREE'),
       (10, 'FREE'),
       (11, 'FREE'),
       (11, 'FREE');

INSERT INTO history_of_request (user_id, book_id, date_of_issue, should_be_return, return_date, status)
VALUES (4, 2, '06.05.2021', '06.08.2021', '06.07.2021', 'RETURNED_ON_TIME'),
       (4, 5, '09.10.2021', '09.01.2022', '09.12.2021', 'RETURNED_ON_TIME'),
       (3, 11, '20.10.2022', '20.01.2023', null, 'READING'),
       (5, 4, '21.09.2021', '21.12.2021', '21.11.2021', 'RETURNED_ON_TIME'),
       (5, 2, '07.05.2022', '07.08.2022', '07.07.2022', 'RETURNED_ON_TIME'),
       (4, 9, '10.10.2022', '10.01.2023', null, 'READING'),
       (3, 8, '08.05.2022', '08.08.2022', '10.08.2022', 'RETURNED_NOT_ON_TIME'),
       (4, 10, '08.05.2022', '08.08.2022', '10.08.2022', 'RETURNED_NOT_ON_TIME'),
       (3, 2, '20.10.2022', '20.01.2023', null, 'READING'),
       (3, 9, '24.10.2022', '24.01.2023', null, 'READING'),
       (5,9, '10.07.2022','10.10.2022','15.10.2022','RETURNED_NOT_ON_TIME'),
       (5,9, '10.09.2022','10.12.2022',null,'READING'),
       (3,9, '12.09.2022','12.12.2022',null,'REJECTED');




