use bug_tracker;
insert into authorities(authority, authority_level)
values ('ROLE_QA', 1),
       ('ROLE_DEVELOPER', 2),
       ('ROLE_PROJECT_MANAGER', 3),
       ('ROLE_ADMIN', 4);


insert into users (username, password, registration_date, account_non_locked, manager_id)
values ('demo-manager', '$2a$10$DZ1gIzTSzj.uB0D0x6oncOBpKgbm10ic0l4WrRV6PiUfKeXHTYaQG', now(), true, null),
       ('manager2', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, null),
       ('manager3', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, null),
       ('demo-developer', '$2a$10$DZ1gIzTSzj.uB0D0x6oncOBpKgbm10ic0l4WrRV6PiUfKeXHTYaQG', now(), true, 1),
       ('developer2', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('developer3', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('developer4', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('developer5', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('developer6', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('developer7', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('developer8', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('developer9', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('developer10', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('developer11', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('developer12', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('developer13', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('developer14', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('developer15', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('developer16', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('developer17', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('developer18', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('developer19', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('developer20', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('demo-qa', '$2a$10$DZ1gIzTSzj.uB0D0x6oncOBpKgbm10ic0l4WrRV6PiUfKeXHTYaQG', now(), true, 1),
       ('submitter2', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('submitter3', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('submitter4', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('submitter5', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('submitter6', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('submitter7', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('submitter8', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('submitter9', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('submitter10', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('submitter11', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('submitter12', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('submitter13', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('submitter14', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('submitter15', '$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6', now(), true, 1),
       ('admin', '$2a$10$DZ1gIzTSzj.uB0D0x6oncOBpKgbm10ic0l4WrRV6PiUfKeXHTYaQG', now(), true, 1);

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
       (19, 2),
       (20, 2),
       (21, 2),
       (22, 2),
       (23, 2),
       (24, 1),
       (25, 1),
       (26, 1),
       (27, 1),
       (28, 1),
       (29, 1),
       (30, 1),
       (31, 1),
       (32, 1),
       (33, 1),
       (34, 1),
       (35, 1),
       (36, 1),
       (37, 1),
       (38, 1),
       (39, 4);


insert into projects(title, description, creation_date, project_manager_id)
values ('Issue tracker', 'Tracking issues to help with project development', now(), 1),
       ('Finance tracking system project', 'System that tracks your weekly finances and your families', now(), 1),
       ('Demo project 1', 'Securing web applications with the Web Security module', now(), 1),
       ('Demo project 2', 'Securing web applications with the Web Security module', now(), 1),
       ('Demo project 3', 'Securing web applications with the Web Security module', now(), 1),
       ('Demo project 4', 'Securing web applications with the Web Security module', now(), 1),
       ('Demo project 5', 'Securing web applications with the Web Security module', now(), 1),
       ('Demo project 6', 'Securing web applications with the Web Security module', now(), 1),
       ('Demo project 7', 'Securing web applications with the Web Security module', now(), 1),
       ('Demo project 8', 'Securing web applications with the Web Security module', now(), 1),
       ('Demo project 9', 'Securing web applications with the Web Security module', now(), 1),
       ('Demo project 10', 'Securing web applications with the Web Security module', now(), 1);

insert into projects_developers (project_id, developer_id)
values (1, 4),
       (1, 5),
       (1, 6),
       (1, 7),
       (1, 8),
       (1, 9),
       (1, 10),
       (1, 11),
       (2, 12),
       (2, 13),
       (2, 14),
       (2, 15),
       (2, 16),
       (2, 17),
       (2, 18),
       (3, 19),
       (3, 20),
       (3, 21),
       (2, 22),
       (3, 24),
       (4, 4),
       (4, 5),
       (4, 6),
       (4, 7),
       (4, 8),
       (3, 9),
       (3, 8),
       (3, 10),
       (3, 11);

insert into projects_qa(project_id, qa_id)
values (1, 24),
       (1, 25),
       (1, 26),
       (1, 27),
       (1, 28),
       (1, 29),
       (1, 30),
       (1, 31),
       (1, 32),
       (1, 33),
       (1, 34),
       (1, 35),
       (1, 36),
       (2, 24),
       (2, 25),
       (2, 26),
       (2, 27),
       (2, 28),
       (2, 29),
       (2, 30),
       (2, 31),
       (2, 32),
       (2, 33),
       (2, 34),
       (2, 35),
       (2, 36),
       (3, 24),
       (3, 25),
       (3, 26),
       (3, 27),
       (3, 28),
       (3, 29),
       (3, 30),
       (3, 31),
       (3, 32),
       (3, 33),
       (3, 34),
       (3, 35),
       (3, 36);



insert into tickets(title, description, creation_date, category, priority, status, project_id, submitter_id)
values ('Remember User log in after page refresh', 'after page refreshes user is no longer logged in', now(),
        'FEATURE_REQUEST', 'HIGH', 'UNASSIGNED', 1, 24),
       ('Assign developer issue', 'assign developers doesnt work', now(), 'BUGS_AND_ERRORS', 'LOW', 'UNASSIGNED', 1,
        24),
       ('Remove developer issue', 'remove developer doesnt work', now(), 'BUGS_AND_ERRORS', 'MEDIUM', 'UNASSIGNED', 1,
        24),
       ('Implement ticket history', 'some description', now(), 'FEATURE_REQUEST', 'HIGH', 'UNASSIGNED', 1, 24),
       ('Implement website animation', 'Implement animations on componentDidMount();', now(), 'FEATURE_REQUEST', 'HIGH',
        'UNASSIGNED', 2, 24),
       ('Bug with transactions', 'transactions not working correctly', now(), 'BUGS_AND_ERRORS', 'MEDIUM', 'UNASSIGNED',
        2,
        24),
       ('How do i add an account?', 'How do i add an account?', now(), 'OTHER', 'LOW', 'UNASSIGNED', 2, 24),
       ('Logout URL', 'Redirect people to home page on logout not to login', now(), 'FEATURE_REQUEST', 'MEDIUM',
        'UNASSIGNED',
        3, 24),
       ('I cant see my account details', 'it says \'403-forbidden\' when ever i want to see my account page', now(),
        'BUGS_AND_ERRORS', 'HIGH', 'UNASSIGNED', 3, 24),
       ('How do i change my authority?', 'how do i become an admin', now(), 'OTHER', 'URGENT', 'UNASSIGNED', 3, 24),
       ('Rout changes', 'Changing routes refreshes the entire page', now(), 'BUGS_AND_ERRORS', 'HIGH', 'UNASSIGNED', 3,
        24),
       ('Implement animations', 'Implement animations or componentDidMount()', now(), 'FEATURE_REQUEST', 'MEDIUM',
        'UNASSIGNED', 1, 24),
       ('How do i log out', 'I cant find the logout page', now(), 'OTHER', 'LOW', 'UNASSIGNED', 1, 19),
       ('New rest endpoint', 'Can you implement a rest endpoint for user details?', now(), 'FEATURE_REQUEST', 'HIGH',
        'UNASSIGNED', 2, 24),
       ('I cant logout', 'Logging out doesnt work', now(), 'BUGS_AND_ERRORS', 'MEDIUM', 'UNASSIGNED', 3, 24),
       ('Demo ticket1-1', 'Demo ticket1 description for test purposes', now(), 'OTHER', 'LOW', 'UNASSIGNED', 1, 24),
       ('Demo ticket1-2', 'Demo ticket2 description for test purposes', now(), 'OTHER', 'LOW', 'UNASSIGNED', 2, 24),
       ('Demo ticket1-3', 'Demo ticket3 description for test purposes', now(), 'OTHER', 'LOW', 'UNASSIGNED', 3, 24),
       ('Demo ticket1', 'Demo ticket4 description for test purposes', now(), 'OTHER', 'LOW', 'UNASSIGNED', 1, 24),
       ('Demo ticket2', 'Demo ticket5 description for test purposes', now(), 'OTHER', 'LOW', 'UNASSIGNED', 2, 24),
       ('Demo ticket3', 'Demo ticket6 description for test purposes', now(), 'OTHER', 'LOW', 'UNASSIGNED', 3, 24),
       ('Demo ticket4', 'Demo ticket7 description for test purposes', now(), 'FEATURE_REQUEST', 'MEDIUM', 'UNASSIGNED',
        1, 24),
       ('Demo ticket5', 'Demo ticket8 description for test purposes', now(), 'FEATURE_REQUEST', 'MEDIUM', 'UNASSIGNED',
        2, 24),
       ('Demo ticket6', 'Demo ticket9 description for test purposes', now(), 'FEATURE_REQUEST', 'MEDIUM', 'UNASSIGNED',
        3, 24),
       ('Demo ticket7', 'Demo ticket10 description for test purposes', now(), 'FEATURE_REQUEST', 'MEDIUM', 'UNASSIGNED',
        1,
        27),
       ('Demo ticket8', 'Demo ticket11 description for test purposes', now(), 'BUGS_AND_ERRORS', 'HIGH', 'UNASSIGNED',
        1, 24),
       ('Demo ticket9', 'Demo ticket12 description for test purposes', now(), 'BUGS_AND_ERRORS', 'URGENT', 'UNASSIGNED',
        2, 24),
       ('Demo ticket10', 'Demo ticket13 description for test purposes', now(), 'BUGS_AND_ERRORS', 'URGENT',
        'UNASSIGNED', 3, 25),
       ('Demo ticket11', 'Demo ticket14 description for test purposes', now(), 'BUGS_AND_ERRORS', 'HIGH', 'UNASSIGNED',
        1, 25),
       ('Demo ticket12', 'Demo ticket14 description for test purposes', now(), 'BUGS_AND_ERRORS', 'URGENT',
        'UNASSIGNED', 2, 25),
       ('Demo ticket13', 'Demo ticket14 description for test purposes', now(), 'BUGS_AND_ERRORS', 'HIGH', 'UNASSIGNED',
        3, 25),
       ('Demo ticket14', 'Demo ticket14 description for test purposes', now(), 'FEATURE_REQUEST', 'HIGH', 'UNASSIGNED',
        1, 25),
       ('Demo ticket15', 'Demo ticket14 description for test purposes', now(), 'BUGS_AND_ERRORS', 'HIGH', 'UNASSIGNED',
        2, 25),
       ('Demo ticket16', 'Demo ticket14 description for test purposes', now(), 'FEATURE_REQUEST', 'HIGH', 'UNASSIGNED',
        3, 25),
       ('Demo ticket17', 'Demo ticket14 description for test purposes', now(), 'BUGS_AND_ERRORS', 'LOW', 'UNASSIGNED',
        1, 25),
       ('Demo ticket18', 'Demo ticket14 description for test purposes', now(), 'OTHER', 'LOW', 'UNASSIGNED', 2, 25),
       ('Demo ticket19', 'Demo ticket14 description for test purposes', now(), 'FEATURE_REQUEST', 'LOW', 'UNASSIGNED',
        3, 25),
       ('Demo ticket20', 'Demo ticket14 description for test purposes', now(), 'BUGS_AND_ERRORS', 'MEDIUM',
        'UNASSIGNED', 1,
        25),
       ('Demo ticket21', 'Demo ticket15 description for test purposes', now(), 'OTHER', 'MEDIUM', 'UNASSIGNED', 2, 25);

insert into comments(description, creation_date, ticket_id, user_id)
values ('demo comment1 description', now(), 1, 4),
       ('demo comment2 description', now(), 1, 4),
       ('demo comment3 description', now(), 2, 4),
       ('demo comment4 description', now(), 2, 4),
       ('demo comment5 description', now(), 3, 4),
       ('demo comment6 description', now(), 3, 4),
       ('demo comment7 description', now(), 4, 4),
       ('demo comment71 description', now(), 4, 4),
       ('demo comment72 description', now(), 4, 4),
       ('demo comment73 description', now(), 4, 4),
       ('demo comment74 description', now(), 4, 4),
       ('demo comment75 description', now(), 4, 19),
       ('demo comment9 description', now(), 5, 24),
       ('demo comment10 description', now(), 5, 24),
       ('demo comment11 description', now(), 6, 24),
       ('demo comment12 description', now(), 6, 24),
       ('demo comment13 description', now(), 7, 24),
       ('demo comment14 description', now(), 7, 24),
       ('demo comment15 description', now(), 7, 24),
       ('demo comment16 description', now(), 7, 24),
       ('demo comment16 description', now(), 7, 24),
       ('demo comment17 description', now(), 1, 24),
       ('demo comment18 description', now(), 2, 24),
       ('demo comment19 description', now(), 3, 1),
       ('demo comment20 description', now(), 4, 1),
       ('demo comment21 description', now(), 5, 1),
       ('demo comment22 description', now(), 6, 1),
       ('demo comment23 description', now(), 7, 1),
       ('demo comment24 description', now(), 7, 1),
       ('demo comment25 description', now(), 7, 1),
       ('demo comment26 description', now(), 7, 1);

update tickets t
set t.assigned_developer_id=4,
    t.status='IN_PROGRESS'
where t.id in (1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

update tickets t
set t.assigned_developer_id=4,
    t.status='RESOLVED'
where t.id in (32, 33, 34, 35, 36, 37, 38, 39);




