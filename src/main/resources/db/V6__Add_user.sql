insert into usr (username, password, active)
    values ('justUser', '123', true);

insert into user_role (user_id, roles)
    values ((select id from usr u where u.username='justUser') , 'USER');
