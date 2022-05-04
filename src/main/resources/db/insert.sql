insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, password, authority_id)
values ('admin', '$2a$10$UNHKFSrgLhTxmlh1izIRmOlNN0cHvLWODtynxAWhZNRyec5PmvbIi', 1);