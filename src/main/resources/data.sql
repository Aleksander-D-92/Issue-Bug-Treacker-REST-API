use bug_tracker;
insert into authorities(authority, authority_level)
values ('ROLE_SUBMITTER', 1),
       ('ROLE_DEVELOPER', 2),
       ('ROLE_PROJECT_MANAGER', 3),
       ('ROLE_ADMIN', 4);


insert into users (username, password, registration_date, account_non_locked)
values ('Sasho', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('Pesho', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('Gosho', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('Damqn', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('Petkan', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('Marin', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('Vasil', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('Kiril', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('Developer 1', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('Developer 2', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('Developer 3', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('Developer 4', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('Developer 5', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('Submitter 1', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('submitter 2', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('submitter 3', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('submitter 4', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('submitter 5', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true);

insert into users_authorities(user_id, authority_id)
VALUES (1, 3),
       (2, 3),
       (3, 3),
       (4, 2),
       (5, 2),
       (6, 2),
       (7, 2),
       (8, 2),
       (9, 2),
       (10, 2),
       (11, 2),
       (12, 2),
       (13, 2),
       (14, 1),
       (15, 1),
       (16, 1),
       (17, 1),
       (18, 1);

insert into projects(title, description, creation_date, project_manager_id)
values ('Issue tracker', 'project for tracking issues', now(), 1),
       ('Pizza Restaurant', 'project for selling pizzas', now(), 1),
       ('Car project', 'project for selling cars online', now(), 2),
       ('Massage place', 'project for implementing a massage reservation website', now(), 2),
       ('Project 4', 'description', now(), 3),
       ('Project 5', 'description', now(), 3);




