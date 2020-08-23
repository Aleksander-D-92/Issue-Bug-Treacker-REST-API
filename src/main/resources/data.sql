use bug_tracker;
insert into authorities(authority, authority_level)
values ('ROLE_SUBMITTER', 1),
       ('ROLE_DEVELOPER', 2),
       ('ROLE_PROJECT_MANAGER', 3),
       ('ROLE_ADMIN', 4);


insert into users (username, password, registration_date, account_non_locked)
values ('sasho', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('pesho', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('gosho', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('developer1', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('developer2', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('developer3', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('developer4', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('developer5', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('developer6', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('developer7', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('developer8', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('developer9', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('developer10', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('developer11', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('developer12', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('developer13', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('developer14', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('developer15', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('submitter1', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('submitter2', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('submitter3', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('submitter4', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('submitter5', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('submitter6', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('submitter7', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('submitter8', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('submitter9', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true),
       ('submitter10', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true);

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
       (14, 2),
       (15, 2),
       (16, 2),
       (17, 2),
       (18, 2),
       (19, 1),
       (20, 1),
       (21, 1),
       (22, 1),
       (23, 1),
       (24, 1),
       (25, 1),
       (26, 1),
       (27, 1),
       (28, 1);


insert into projects(title, description, creation_date, project_manager_id)
values ('Issue tracker', 'project for tracking issues', now(), 1),
       ('Pizza Restaurant', 'project for selling pizzas', now(), 1),
       ('Car project', 'project for selling cars online', now(), 2),
       ('Massage place', 'project for implementing a massage reservation website', now(), 2),
       ('Spring security project', 'trying to understand spring security', now(), 3),
       ('My first Hibernate project', 'trying to figure out how to optimize hibernate queries ', now(), 3);

insert into projects_developers (project_id, user_id)
values (1, 4),
       (1, 5),
       (1, 6),
       (1, 7),
       (1, 8),
       (2, 9),
       (2, 10),
       (2, 11),
       (2, 12),
       (2, 13),
       (2, 14),
       (3, 10),
       (3, 12),
       (3, 15),
       (3, 4),
       (3, 7),
       (4, 15),
       (4, 8),
       (4, 9),
       (4, 10),
       (5, 4),
       (5, 7),
       (5, 6),
       (5, 13),
       (5, 14),
       (3, 8);

insert into tickets(title, description, creation_date, category, priority, status, project_id, submitter_id)
values ('some title', 'some description', now(), 'OTHER', 'HIGH', 'NEW', 1, 14),
       ('some title', 'some description', now(), 'OTHER', 'HIGH', 'NEW', 2, 14),
       ('some title', 'some description', now(), 'OTHER', 'HIGH', 'NEW', 3, 15),
       ('some title', 'some description', now(), 'OTHER', 'HIGH', 'NEW', 4, 15),
       ('some title', 'some description', now(), 'OTHER', 'HIGH', 'NEW', 1, 15),
       ('some title', 'some description', now(), 'OTHER', 'HIGH', 'NEW', 2, 16),
       ('some title', 'some description', now(), 'OTHER', 'HIGH', 'NEW', 3, 16),
       ('some title', 'some description', now(), 'OTHER', 'HIGH', 'NEW', 4, 16)




